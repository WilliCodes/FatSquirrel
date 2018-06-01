package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon) {
		super(id, energy, position, mid);
		this.botcon = botcon;
	}
	
	public void nextStep(EntityContext context) {
		botcon.nextStep(new ControllerContextImpl(context));
	}
	
	public void implode(EntitySet netitySet) {
		
		for(int i = getPosition().x; i < upper right; i++) {
			
		}
		
		int distance;
		int impactRadius = 5;
		double impactArea = impactRadius * impactRadius * Math.PI;
		double energyLoss = 200 * (getEnergy()/impactArea) * (1 - distance/impactRadius);
	}
	
	public class ControllerContextImpl implements ControllerContext {
		
		private EntityContext context;
		
		public ControllerContextImpl(EntityContext context) {
			this.context = context;
		}

		@Override
		public XY getViewLowerLeft() {
			return new XY(getPosition().x - 10, getPosition().y - 10);
		}

		@Override
		public XY getViewUpperRight() {
			return new XY(getPosition().x - 10, getPosition().y - 10);
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			return context.getEntityType(xy);
		}

		@Override
		public void move(XY direction) {
		
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			
			
		}

		@Override
		public int getEnergy() {
			return getEnergy();
		}

	}

}
