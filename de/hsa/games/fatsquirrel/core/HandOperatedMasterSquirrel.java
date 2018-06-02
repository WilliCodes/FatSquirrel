package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	private static final int initEnergy = 1000;
	
	

	public HandOperatedMasterSquirrel(int id, XY position) {
		super(id, initEnergy, position);
	}
	

	
	public void nextStep(EntityContext context) {
		
		if (nextMove > 0) {
			nextMove--;
			if (Launcher.printDebugInfo) System.out.println("MasterSquirrel ist gelähmt!");
			return;
		}else {
		
			
			if (spawnMini == 0)
				context.tryMove(this, nextMoveCommand.xy);
			
			if (Launcher.printDebugInfo) {
				System.out.println("MasterSquirrel-Energy: " + getEnergy() + ".");
			}
		
		}
	}


}
