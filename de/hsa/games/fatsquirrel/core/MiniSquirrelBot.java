package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	private BotControllerFactory botconfac;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon, BotControllerFactory botconfac) {
		super(id, energy, position, mid);
		this.botcon = botcon;
		this.botconfac = botconfac;
	}
	
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public XY getViewUpperRight() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			// TODO Auto-generated method stub
			return null;
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
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
