package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	private final static int paralyzedDuration = 3;
	
	protected XY nextMoveCommand = XY.ZERO_ZERO;
	public final String playerName;

	public PlayerEntity(int id, int energy, XY position, String playerName) {
		super(id, energy, position);
		this.playerName = playerName;
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
