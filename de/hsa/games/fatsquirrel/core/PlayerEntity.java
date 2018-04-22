package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	protected boolean paralyzed = false;
	
	protected MoveCommand nextMoveCommand;

	public PlayerEntity(int id, int energy, XY position) {
		super(id, energy, position);
	}

	public boolean isParalyzed() {
		return paralyzed;
	}
	
	public void setParalyzed() {
		paralyzed = true;
	}
	
	public void setNextCommand(MoveCommand mc) {
		nextMoveCommand = mc;
	}
}
