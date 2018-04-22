package de.hsa.games.fatsquirrel.core;

public class BadPlant extends Entity {
	
	private static final int initEnergy = -100;

	public BadPlant(int id, XY position) {
		super(id, initEnergy, position);
	}

}
