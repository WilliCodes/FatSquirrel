package de.hsa.games.fatsquirrel.botimpls;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

	public class MiniWilhelm implements BotController{
		
		private XY generalDirection = XY.DOWN;
		private int count = 0;
		
		public MiniWilhelm() {
		}

		@Override
		public void nextStep(ControllerContext view) {
			count++;
			view.move(XY.randomVector());
		}
		
		
		public EntityType getNextGoodEntity() {
			return EntityType.NONE;
		}

	}
