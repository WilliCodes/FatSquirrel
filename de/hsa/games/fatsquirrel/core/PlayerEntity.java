package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	private final static int paralyzedDuration = 3;
	
	protected XY nextMoveCommand;

	public PlayerEntity(int id, int energy, XY position) {
		super(id, energy, position);
	}

	public boolean isParalyzed() {
		return nextMove > 0;
	}
	
	public void setParalyzed() {
		nextMove = paralyzedDuration;
	}
	
	public void setNextCommand(XY mc) {
		nextMoveCommand = mc;
	}
}
