package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.EntityContext;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotController botcon;
	
	public MasterSquirrelBot(int id, XY position, BotController botcon) {
		super(id, position);
		this.botcon = botcon;
	}
	
	@Override
	public void nextStep(EntityContext context) {
		System.out.println("Werde ich aufgerufen?");
		botcon.nextStep(new ControllerContextImpl(context, this));
	}
	
	public class ControllerContextImpl implements ControllerContext {
		
		private final int sight = 31;
		
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
		public EntityType getEntityAt(XY xy) {
			XY pos = getPosition();
			XY viewLL = getViewLowerLeft();
			XY viewUR = getViewUpperRight();
			
			if (pos.x < viewLL.x || pos.x > viewUR.x || pos.y < viewUR.y || pos.y > viewLL.y) {
				// TODO Exception
				return EntityType.NONE;
			}
				
			return context.getEntityType(xy);
		}


		@Override
		public void move(XY direction) {
			System.out.println("LOLOL");
			context.tryMove(masterSquirrelBot, direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
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
		public boolean isMine(XY xy) {
			return context.isMyMini(xy, masterSquirrelBot.getId());
		}

		@Override
		public void implode(int impactRadius) {
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
