package de.hsa.games.fatsquirrel.core;

public class Wall extends Entity{
	
	private static final int initEnergy = -10;
	
	public Wall(int id, XY position) {
		super(id, initEnergy, position);
	}
}
