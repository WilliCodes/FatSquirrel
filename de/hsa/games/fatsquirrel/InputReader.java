package de.hsa.games.fatsquirrel;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.console.ConsoleUI;

public class InputReader extends Thread {

	UI ui;
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public InputReader(UI ui) {
		this.ui = ui;
	}
	
	public void run() {
		logger.info("commandloop-thread started");
		while(true) {
			((ConsoleUI)ui).commandLoop();
		}
	}
	
}
