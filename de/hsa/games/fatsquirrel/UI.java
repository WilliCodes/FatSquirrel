package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public interface UI {
	
	public MoveCommand getCommand();
	
	public void render(BoardView view);

}
