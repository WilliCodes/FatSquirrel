package de.hsa.games.fatsquirrel;

import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.Launcher.GameMode;
import de.hsa.games.fatsquirrel.core.State;

public abstract class Game {
	
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	protected State state;
	protected UI ui;
	public final int FPS = 5;
	public boolean paused = false;

	
	public Game(State state) {
		this.state = state;
	}
	
	protected abstract void render();
	
	protected abstract void processInput();
	
	protected void update() {
		state.update();
	}
	
	

	public void run() {
		logger.info("gameloop started");
	    while (true) {
	        render();
	        
	        if (Launcher.gameMode != GameMode.AI_GAME)
	        	processInput();
	        
	        if(!paused) {
	        	update();
	        }
	        
	        try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				logger.severe(e.toString());
				e.printStackTrace();
			}
	    }
	}
}
