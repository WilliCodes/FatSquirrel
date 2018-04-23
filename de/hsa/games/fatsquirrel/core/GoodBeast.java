package de.hsa.games.fatsquirrel.core;

public class GoodBeast extends Beast {
	
	private static final int initEnergy = 200;

	public GoodBeast(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	@Override
	public void nextStep(EntityContext context) {
		
		XY vectorToPlayer = beastMove(context);
		
		if (vectorToPlayer == null)
			return;
		
		XY moveVector = new XY(-vectorToPlayer.x, -vectorToPlayer.y);
		context.tryMove(this, moveVector);
	}

}
