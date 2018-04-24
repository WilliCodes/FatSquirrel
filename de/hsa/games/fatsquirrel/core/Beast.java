package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher;

public class Beast extends Character{
	
	protected static final int movePeriod = 4;

	public Beast(int id, int energy, XY position) {
		super(id, energy, position);
	}
	
	
	protected XY beastMove(EntityContext context) {
		
		if (++lastMove < movePeriod) {
			if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " cant move"); }
			return null;
		}
			
		lastMove = 0;
		
		PlayerEntity nearestPE = context.nearestPlayerEntity(this.getPosition());
		
		if (nearestPE == null) {
			if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " moves randomly"); }
			return XY.randomVector();
		}
		
		
		XY nearestPEpos = nearestPE.getPosition();
		double xDiff = nearestPEpos.x - getPosition().x;
		double yDiff = nearestPEpos.y - getPosition().y;
		
		double maxDiff = Math.max(Math.abs(xDiff), (Math.abs(yDiff)));
		
		xDiff /= maxDiff;
		yDiff /= maxDiff;
		
		
		XY moveVector = new XY((int) Math.round(xDiff), (int) Math.round(yDiff));
		
		if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " sees PlayerEntity in direction " + moveVector); }
		return moveVector;
	}
	
	

}
