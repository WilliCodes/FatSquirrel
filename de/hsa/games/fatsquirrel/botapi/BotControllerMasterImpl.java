package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.XY;

public class BotControllerMasterImpl implements BotController{

	@Override
	public void nextStep(ControllerContext view) {
		view.move(XY.DOWN);
		
	}

}
