package de.hsa.games.fatsquirrel.core;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	private static final int initEnergy = 1000;

	public HandOperatedMasterSquirrel(int id, XY position) {
		super(id, initEnergy, position);
	}
	
	public void nextStep(EntityContext context) {
		
		if (paralyzed) {
			lastMove++;
			if (lastMove > 3) {
				paralyzed = false;
				lastMove = 0;
			}
		} else {
			context.tryMove(this, nextMoveCommand.xy);
		}
		
	}
	
	


	
}
