package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.State;

public abstract class Game {
	
	protected State state;
	protected UI ui;
	public final int FPS = 1;
	public boolean paused = true;
	
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
	        
	        if(!paused) {
	        	update();
	        }
	        
	        try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	}
}
