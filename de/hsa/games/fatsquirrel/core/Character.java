package de.hsa.games.fatsquirrel.core;

public abstract class Character extends Entity {
	
	protected int lastMove = 0;

	public Character(int id, int energy, XY position) {
		super(id, energy, position);
	}
	
	
	public void nextStep(EntityContext context) {};
	

}
