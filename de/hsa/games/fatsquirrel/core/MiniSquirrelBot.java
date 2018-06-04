package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon) {
		super(id, energy, position, mid);
		this.botcon = botcon;
	}
	
	
	
	@Override
	public void nextStep(EntityContext context) {
		botcon.nextStep(new ControllerContextImpl(context, this));
	}
	
	
	public class ControllerContextImpl implements ControllerContext {
		
		private final int sight = 21;
		
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
			context.tryMove(miniSquirrelBot, direction);
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
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
		public boolean isMine(XY xy) {
			return context.isMyMaster(xy, miniSquirrelBot.getId());
		}

		@Override
		public void implode(int impactRadius) {
			context.implodeMini(impactRadius, miniSquirrelBot);
		}

		@Override
		public XY directionOfMaster() {
			return context.directionOfMaster(miniSquirrelBot, sight);
		}

		@Override
		public long getRemainingSteps() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
