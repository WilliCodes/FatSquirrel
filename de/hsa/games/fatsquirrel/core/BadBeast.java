package de.hsa.games.fatsquirrel.core;

public class BadBeast extends Character{
	
	private int bitCounter = 0;
	
	private static final int initEnergy = -150;

	public BadBeast(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	public void nextStep() {
		move(XY.randomVector());
	}

	public boolean nextBite() {
		if (++bitCounter >= 7) {
			return true;
		}
		
		return false;
	}
	
	
}
