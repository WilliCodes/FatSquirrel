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
	

	/**
	 * generates an instance of a MasterSquirrelBot
	 * 
	 * @param id as int
	 * @param position as XY
	 * @param botcon as BotController
	 * @param botName as Name of the Bot
	 */
	public MasterSquirrelBot(int id, XY position, BotController botcon, String botName) {
		super(id, position, botName);
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
		
		/**
		 * 
		 * @param context as EntityContext
		 * @param masterSquirrelBot as MasterSquirrelBot
		 */
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
			
			if (!isInView(xy)) {
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
			
			if (!isInView(loc))
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
			System.out.println(masterSquirrelBot);
			System.out.println(masterSquirrelBot.getPosition());
			return masterSquirrelBot.getPosition();
		}

		@Override
		public boolean isMine(XY pos) throws OutOfViewException {
			
			if (isInView(pos))
				return context.isMyMini(pos, masterSquirrelBot.getId());
			else
				throw new OutOfViewException("The MasterSquirrel cannot see this cell!");
		}

		@Override
		public void implode(int impactRadius) {
			throw new UnsupportedOperationException("A MasterSquirrel cannot implode!");
		}

		@Override
		public XY directionOfMaster() {
			return null;
		}

		@Override
		public long getRemainingSteps() {
			return 0;
		}
		
		/**
		 * 
		 * @param pos as XY
		 * @return checks if XY is visible for MasterSquirrel
		 */
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
