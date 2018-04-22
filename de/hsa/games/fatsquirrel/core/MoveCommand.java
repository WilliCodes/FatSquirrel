package de.hsa.games.fatsquirrel.core;

public class MoveCommand {
	
	public final XY xy;
	
	public MoveCommand(int numBlockInput) {
		xy = convertInputToVector(numBlockInput);
	}
	
	private XY convertInputToVector(int direction) {
		int x = 0, y = 0;
		if (direction == 7 || direction == 8 || direction == 9)
			y = 1;
		else if (direction == 1 || direction == 2 || direction == 3)
			y = -1;
		
		if (direction == 1 || direction == 4 || direction == 7)
			x = -1;
		else if (direction == 3 || direction == 6 || direction == 9)
			x = 1;
		
		return new XY(x,y);
	}

}
