package de.hsa.games.fatsquirrel.botimpls;

import java.util.HashMap;
import java.util.Map;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.ControllerContext;
import de.hsa.games.fatsquirrel.botapi.SpawnException;
import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

	public class MasterWilhelm implements BotController{
		
		private int count = 0;
		private XY me;
		
		private Map<XY, Integer> options;
		
		public MasterWilhelm() {
			options = new HashMap<XY, Integer>();
		}
		
		private void resetOptions() {
			options.clear();
			options.put(XY.DOWN, 0);
			options.put(XY.UP, 0);
			options.put(XY.LEFT, 0);
			options.put(XY.RIGHT, 0);
			options.put(XY.LEFT_DOWN, 0);
			options.put(XY.LEFT_UP, 0);
			options.put(XY.RIGHT_DOWN, 0);
			options.put(XY.RIGHT_UP, 0);
		}

		@Override
		public void nextStep(ControllerContext view) {
			count++;
			resetOptions();
			//me = view.locate();
			//System.out.println(me);
			
			
			for (XY option : options.keySet()) {
				for (int x = 1; x < 15; x++) {
					for (int y = 1; y < 15; y++) {
						continue;
					}
				}
			}
			
			view.move(XY.randomVector());
		}
		
		
		
		private boolean isInView(ControllerContext view, XY pos) {
			XY ll = view.getViewLowerLeft();
			XY ur = view.getViewUpperRight();
			
			if (pos.x < ll.x || pos.x > ur.x || pos.y < ur.y || pos.y > ll.y)
				return false;
			
		
			return true;
		}
		

	}
