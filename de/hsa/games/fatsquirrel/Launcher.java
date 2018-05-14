package de.hsa.games.fatsquirrel;

import java.util.Timer;
import java.util.TimerTask;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;

public class Launcher {
	
	public static boolean printDebugInfo = false;

	public static void main(String[] args) {
		Board board = new Board(new BoardConfig());
		State state = new State(board);
		GameImpl game = new GameImpl(state);
		
		startGame(game);
		startCommandLoop(game);
	}
	
	public static void startGame(GameImpl game) {
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask(){
			
			public void run() {
				game.run();
			}
			
		}, 3*1000);
		
	}
	
	public static void startCommandLoop(GameImpl game) {
		game.startCommandLoop();
	}
}
