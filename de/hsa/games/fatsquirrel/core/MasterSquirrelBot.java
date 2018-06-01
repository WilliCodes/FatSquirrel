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
		botcon.nextStep(new ControllerContextImpl(context));
	}
	
	
	public class ControllerContextImpl implements ControllerContext {
		
		private EntityContext context;
		
		public ControllerContextImpl(EntityContext context) {
			this.context = context;
		}

		@Override
		public XY getViewLowerLeft() {
			return new XY(getPosition().x - 15, getPosition().y - 15);
		}

		@Override
		public XY getViewUpperRight() {
			return new XY(getPosition().x + 15, getPosition().y + 15);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			return context.getEntityType(xy);
		}

		@Override
		public void move(XY direction) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getEnergy() {
			return getEnergy();
		}

	}

}
