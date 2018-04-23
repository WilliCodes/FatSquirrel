package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.GameImpl;
import de.hsa.games.fatsquirrel.core.*;

public class Launcher {
	
	public static boolean printDebugInfo = true;

	public static void main(String[] args) {
		Board board = new Board(new BoardConfig());
		State state = new State(board);
		GameImpl game = new GameImpl(state);
		
		game.run(); // TODO
	}
}
