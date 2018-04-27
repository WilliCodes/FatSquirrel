package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	private static final int initEnergy = 1000;

	public HandOperatedMasterSquirrel(int id, XY position) {
		super(id, initEnergy, position);
		lastMove = 3;
	}
	

	
	public void nextStep(EntityContext context) {
		
		if (lastMove < 3) {
			lastMove++;
			if (lastMove >= 3) {
				if (Launcher.printDebugInfo) {
					System.out.println("MasterSquirrel ist nicht mehr gelähmt!");
				}
				
				
			}
			else {
				if (Launcher.printDebugInfo) {
					System.out.println("MasterSquirrel ist gelähmt!");
				}
			}
		} else {
			context.tryMove(this, nextMoveCommand.xy);
			if (Launcher.printDebugInfo) {
				System.out.println("MasterSquirrel-Energy: " + getEnergy() + ".");
			}
		}
		
	}
	
	


	
}
