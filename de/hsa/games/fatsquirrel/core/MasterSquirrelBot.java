package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.EntityContext;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotController botcon;
	private BotControllerFactory botconfac;
	
	public MasterSquirrelBot(int id, XY position, BotController botcon, BotControllerFactory botconfac) {
		//BotControllerFactory nicht übergeben
		super(id, position);
		this.botcon = botcon;
		this.botconfac = botconfac;
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
