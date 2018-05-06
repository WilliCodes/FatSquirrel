package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher;

public class Beast extends Character{
	
	protected static final int movePeriod = 4;

	public Beast(int id, int energy, XY position) {
		super(id, energy, position);
		nextMove = movePeriod - 1;
	}
	
	
	protected XY beastMove(EntityContext context) {
		
		if (nextMove > 0) {
			if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " can move in " + nextMove + " steps"); }
			nextMove--;
			return null;
		}
			
		nextMove = movePeriod - 1;
		
		PlayerEntity nearestPE = context.nearestPlayerEntity(this.getPosition());
		
		if (nearestPE == null) {
			if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " moves randomly"); }
			return XY.randomVector();
		}
		
		XY moveVector = XY.vectorToEntity(this, nearestPE);
		
		if (Launcher.printDebugInfo) {System.out.println(this.getClass().getSimpleName() + "(" + this.getId() + ") at " + this.getPosition() + " sees PlayerEntity in direction " + moveVector); }
		return moveVector;
	}
	
	

}
