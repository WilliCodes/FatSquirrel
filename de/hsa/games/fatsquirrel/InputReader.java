package de.hsa.games.fatsquirrel;


public class InputReader extends Thread {

	UI ui;
	
	public InputReader(UI ui) {
		this.ui = ui;
	}
	
	public void run() {
		while(true) {
			ui.commandLoop();
		}
	}
	
}
