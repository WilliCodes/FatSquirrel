package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	private final static int paralyzedDuration = 3;
	
	protected XY nextMoveCommand;

	public PlayerEntity(int id, int energy, XY position) {
		super(id, energy, position);
	}

	/**
	 * 
	 * @return if PlayerEntity is paralyzed
	 */
	public boolean isParalyzed() {
		return nextMove > 0;
	}
	
	/**
	 * sets PlayerEntity paralyzed
	 */
	public void setParalyzed() {
		nextMove = paralyzedDuration;
	}
	
	/**
	 * sets nextMoveCommand to given location
	 * @param mc as XY
	 */
	public void setNextCommand(XY mc) {
		nextMoveCommand = mc;
	}
}
