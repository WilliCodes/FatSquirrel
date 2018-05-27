package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.mastersquirrelbot.ControllerContextImpl;

public class MasterSquirrelBot extends ControllerContextImpl {

	private BotController botcon;
	private BotControllerFactory botconfac;
	
	public MasterSquirrelBot(BotController botcon, BotControllerFactory botconfac) {
		this.botcon = botcon;
		this.botconfac = botconfac;
	}
	
	
}
