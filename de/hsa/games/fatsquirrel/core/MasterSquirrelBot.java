package de.hsa.games.fatsquirrel.core;

import java.lang.reflect.Proxy;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotInvocationHandler;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.OutOfViewException;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.core.EntityContext;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotController botcon;
	
	
	public MasterSquirrelBot(int id, XY position, BotController botcon) {
		super(id, position);
		this.botcon = botcon;
	}
	

	@Override
	public void nextStep(EntityContext context) {
		if (nextMove > 0) {
			nextMove--;
			return;
		} else {
			ControllerContextImpl conConImpl = new ControllerContextImpl(context, this);
			BotInvocationHandler botInvocationHandler = new BotInvocationHandler(conConImpl);
			ControllerContext conCon = (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(), new Class[] { ControllerContext.class }, botInvocationHandler);
			botcon.nextStep(conCon);
			context.tryMove(this, nextMoveCommand);
		}
	}
	
	public class ControllerContextImpl implements ControllerContext {
		
		private final int sight = 15;
		
		private EntityContext context;
		private MasterSquirrelBot masterSquirrelBot;
		
		public ControllerContextImpl(EntityContext context, MasterSquirrelBot masterSquirrelBot) {
			this.context = context;
			this.masterSquirrelBot = masterSquirrelBot;
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
			XY pos = getPosition();
			XY viewLL = getViewLowerLeft();
			XY viewUR = getViewUpperRight();
			
			// make private method
			if (pos.x < viewLL.x || pos.x > viewUR.x || pos.y < viewUR.y || pos.y > viewLL.y) {
				throw new OutOfViewException("The MasterSquirrel cannot see this cell!");
			}
				
			return context.getEntityType(xy);
		}


		@Override
		public void move(XY direction) {
			masterSquirrelBot.setNextCommand(direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) throws SpawnException {
			if (direction.x < -1 || direction.x > 1 || direction.y < -1 || direction.y > 1 || (direction.equals(XY.ZERO_ZERO)))
				throw new SpawnException("Invalid spawn direction!");
			
			XY loc = masterSquirrelBot.getPosition().plus(direction);
			
			if (loc.x < 0 || loc.y < 0 || loc.x > context.getSize().x || loc.y > context.getSize().y)
				throw new SpawnException("Spawning out of bounds!");
			if (context.getEntityType(loc) != EntityType.NONE)
				throw new SpawnException("Field already in use!");

			masterSquirrelBot.setSpawnMini(energy, direction);
		}

		@Override
		public int getEnergy() {
			return masterSquirrelBot.getEnergy();
		}

		@Override
		public XY locate() {
			return masterSquirrelBot.getPosition();
		}

		@Override
		public boolean isMine(XY pos) throws OutOfViewException {
			
			XY viewLL = getViewLowerLeft();
			XY viewUR = getViewUpperRight();
			
			if (pos.x < viewLL.x || pos.x > viewUR.x || pos.y < viewUR.y || pos.y > viewLL.y) {
				throw new OutOfViewException("The MasterSquirrel cannot see this cell!");
			}
			return context.isMyMini(pos, masterSquirrelBot.getId());
		}

		@Override
		public void implode(int impactRadius) {
			// throw unsopp. exc
		}

		@Override
		public XY directionOfMaster() {
			return null;
		}

		@Override
		public long getRemainingSteps() {
			return 0;
		}
	}
}
