package de.hsa.games.fatsquirrel.botimpls;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

	public class MasterZickeZacke implements BotController{
		
		private XY generalDirection = XY.DOWN;
		private int count = 0;
		
		public MasterZickeZacke() {
		}

		@Override
		public void nextStep(ControllerContext view) {
			count++;
			view.move(XY.randomVector());
			
			if (count % 10 == 0)
			try {
				view.spawnMiniBot(XY.randomVector(), 10);
			} catch (SpawnException e) {
				System.out.println("Oops");
			}
		}
		
		
		public EntityType getNextGoodEntity() {
			return EntityType.NONE;
		}

	}
