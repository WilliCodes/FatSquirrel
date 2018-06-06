package de.hsa.games.fatsquirrel.core;

public abstract class PlayerEntity extends Character {
	
	private final static int paralyzedDuration = 3;
	
	protected XY nextMoveCommand;
	public final String playerName;

	public PlayerEntity(int id, int energy, XY position, String playerName) {
		super(id, energy, position);
		this.playerName = playerName;
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
