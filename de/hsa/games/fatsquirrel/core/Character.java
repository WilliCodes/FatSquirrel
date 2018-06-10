package de.hsa.games.fatsquirrel.core;

public abstract class Character extends Entity {
	
	protected int nextMove = 0;

	/**
	 * 
	 * @param id as int
	 * @param energy as int
	 * @param position as XY
	 */
	public Character(int id, int energy, XY position) {
		super(id, energy, position);
	}
	
	/**
	 * 
	 * @param context as EntityContext
	 */
	public abstract void nextStep(EntityContext context);
	

}
