package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot.ControllerContextImpl;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon) {
		super(id, energy, position, mid);
		this.botcon = botcon;
	}
	
	
	public void implode(EntitySet netitySet) {
		//TODO: suchen nach entitys und schaden übergeben
//		for(int i = getPosition().x; i < upper right; i++) {
//			
//		}
		
		int distance;
		int impactRadius = 5;
		double impactArea = impactRadius * impactRadius * Math.PI;
		//double energyLoss = 200 * (getEnergy()/impactArea) * (1 - distance/impactRadius);
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
			
			if (pos.x < viewLL.x || pos.x > viewUR.x || pos.y < viewUR.y || pos.y > viewLL.y)
				return EntityType.Empty;
			
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
			return getEnergy();
		}

	}

}
