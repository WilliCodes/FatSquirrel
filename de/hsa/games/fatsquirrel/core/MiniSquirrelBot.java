package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.core.minisquirrelbot.ControllerContextImpl;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotController botcon;
	private BotControllerFactory botconfac;
	
	public MiniSquirrelBot(int id, int energy, XY position, int mid, BotController botcon, BotControllerFactory botconfac) {
		super(id, energy, position, mid);
		this.botcon = botcon;
		this.botconfac = botconfac;
	}
	
	public void nextStep() {
		botcon.nextStep();
	}
}
