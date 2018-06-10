package de.hsa.games.fatsquirrel.core;

public class BadBeast extends Beast{
	
	private static final int initEnergy = -150;
	
	private int bitCounter = 0;
	
	/**
	 * 
	 * @param id as int
	 * @param position as XY
	 */
	public BadBeast(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	@Override
	public void nextStep(EntityContext context) {
		
		XY moveVector = this.beastMove(context);
		
		if (moveVector == null)
			return;
		
		context.tryMove(this, moveVector);
	}

	/**
	 * 
	 * @return check if Beast has already used all of its bites
	 */
	public boolean nextBite() {
		if (++bitCounter >= 7) {
			return true;
		}
		
		return false;
	}
	
	
}
