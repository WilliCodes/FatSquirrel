package de.hsa.games.fatsquirrel.core;

public class GoodBeast extends Character {
	
	private static final int initEnergy = 200;

	public GoodBeast(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	@Override
	public void nextStep(EntityContext context) {
		if (++lastMove < 4)
			return;
		
		lastMove = 0;
		
		PlayerEntity nearestPE = context.nearestPlayerEntity(getPosition());
		
		if (nearestPE == null) {
			context.tryMove(this, XY.randomVector());
			return;
		}
		
		
		XY nearestPEpos = nearestPE.getPosition();
		double xDiff = nearestPEpos.x - getPosition().x;
		double yDiff = nearestPEpos.y - getPosition().y;
		
		double maxDiff = Math.max(Math.abs(xDiff), (Math.abs(yDiff)));
		
		xDiff /= -maxDiff;
		yDiff /= -maxDiff;
		
		XY moveVector = new XY((int) Math.round(xDiff), (int) Math.round(yDiff));
		
		context.tryMove(this, moveVector);
			
		
	}

}
