package de.hsa.games.fatsquirrel.core;

public class GoodBeast extends Character {
	
	private static final int initEnergy = 200;

	public GoodBeast(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	public void nextStep() {
		move(XY.randomVector());
	}

}
