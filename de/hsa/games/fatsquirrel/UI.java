package de.hsa.games.fatsquirrel;

import de.hsa.games.fatsquirrel.console.commands.Command;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MoveCommand;

public interface UI {
	
	public Command getCommand();
	
	public void render(BoardView view);
	

}
