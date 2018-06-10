package de.hsa.games.fatsquirrel.core;

import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotInvocationHandler;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon) {
		super(id, energy, position, mid);
		this.botcon = botcon;
	}
	
	
	
	@Override
	public void nextStep(EntityContext context) {
		if (nextMove > 0) {
			nextMove--;
			return;
		} else {
			
			updateEnergy(-1);
			if (getEnergy() <= 0) {
				deactivate();
				return;
			}
			
			ControllerContextImpl conConImpl = new ControllerContextImpl(context, this);
			BotInvocationHandler botInvocationHandler = new BotInvocationHandler(conConImpl);
			ControllerContext conCon = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(), new Class[] { ControllerContext.class }, botInvocationHandler);
			botcon.nextStep(conCon);
			if (implodeRadius > 0)
				context.implodeMini(implodeRadius, this);
			else
				context.tryMove(this, nextMoveCommand);
		}
	}
	
	
	public class ControllerContextImpl implements ControllerContext {
		
		private final int sight = 10;
		
		private EntityContext context;
		private MiniSquirrelBot miniSquirrelBot;
		
		public ControllerContextImpl(EntityContext context, MiniSquirrelBot miniSquirrelBot) {
			this.context = context;
			this.miniSquirrelBot = miniSquirrelBot;
		}

		@Override
		public XY getViewLowerLeft() {
			XY size = context.getSize();
			
			int x = getPosition().x - sight;
			int y = getPosition().y + sight;
			
			x = x < 0 ? 0 : x;
			y = y > size.y ? size.y : y;
			
			return new XY(x, y);
		}

		@Override
		public XY getViewUpperRight() {
			XY size = context.getSize();
			
			int x = getPosition().x + sight;
			int y = getPosition().y - sight;
			
			x = x > size.x ? size.x : x;
			y = y < 0 ? 0 : y;
			
			return new XY(x, y);
		}

		@Override
		public EntityType getEntityAt(XY xy) throws OutOfViewException {
			
			if (!isInView(xy)) {
				throw new OutOfViewException("The MasterSquirrel cannot see this cell!");
			}
				
			return context.getEntityType(xy);
		}

		@Override
		public void move(XY direction) {
			miniSquirrelBot.setNextCommand(direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			throw new UnsupportedOperationException("A MasterSquirrel cannot implode!");
		}

		@Override
		public int getEnergy() {
			return miniSquirrelBot.getEnergy();
		}

		@Override
		public XY locate() {
			return miniSquirrelBot.getPosition();
		}

		@Override
		public boolean isMine(XY pos) throws OutOfViewException {
			
			if (!isInView(pos)) {
				throw new OutOfViewException("The MasterSquirrel cannot see this cell!");
			}
			return context.isMyMaster(pos, miniSquirrelBot.getId());
		}

		@Override
		public void implode(int impactRadius) {
			if (impactRadius < 2 || impactRadius > 10) {
				throw new IllegalArgumentException("Invalid impactRadius!");
			} else
				miniSquirrelBot.implodeRadius = impactRadius;
		}

		@Override
		public XY directionOfMaster() {
			return context.directionOfMaster(miniSquirrelBot);
		}

		@Override
		public long getRemainingSteps() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		private boolean isInView(XY pos) {
			
			XY viewLL = getViewLowerLeft();
			XY viewUR = getViewUpperRight();
			
			if (pos.x < viewLL.x || pos.x > viewUR.x || pos.y < viewUR.y || pos.y > viewLL.y) {
				return false;
			}
			
			return true;
		}

	}

}
