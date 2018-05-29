package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.mastersquirrelbot.ControllerContextImpl;

public class MasterSquirrelBot extends MasterSquirrel {

	private BotController botcon;
	private BotControllerFactory botconfac;
	
	public MasterSquirrelBot(int id, XY position, BotController botcon, BotControllerFactory botconfac) {
		super(id, position);
		this.botcon = botcon;
		this.botconfac = botconfac;
	}
	
	public void nextStep() {
		botcon.nextStep();
	}
}
