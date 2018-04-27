package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	
	protected MoveCommand nextMoveCommand;

	public PlayerEntity(int id, int energy, XY position) {
		super(id, energy, position);
	}

	public boolean isParalyzed() {
		 return (lastMove < 3);
	}
	
	public void setParalyzed() {
		lastMove = 0;
	}
	
	public void setNextCommand(MoveCommand mc) {
		nextMoveCommand = mc;
	}
}
