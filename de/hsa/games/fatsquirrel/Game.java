package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

public abstract class Game {
	
	protected State state;
	
	public Game(State state) {
		this.state = state;
	}
	
	protected abstract void render();
	
	protected abstract void processInput();
	
	protected void update() {
		state.update();
	}
	

	public void run() {
	    while (true) {
	        render();
	        processInput();
	        update();
	        
	        try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
