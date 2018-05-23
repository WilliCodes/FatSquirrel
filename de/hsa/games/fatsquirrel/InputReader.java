package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.ConsoleUI;

public class InputReader extends Thread {

	UI ui;
	
	public InputReader(UI ui) {
		this.ui = ui;
	}
	
	public void run() {
		while(true) {
			((ConsoleUI)ui).commandLoop();
		}
	}
	
}
