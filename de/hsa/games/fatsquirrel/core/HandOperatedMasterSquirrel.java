package de.hsa.games.fatsquirrel.core;


public class HandOperatedMasterSquirrel extends MasterSquirrel {
	
	private static final int initEnergy = 1000;
	
	

	/**
	 * 
	 * @param id as int
	 * @param position as XY
	 */
	public HandOperatedMasterSquirrel(int id, XY position, String playerName) {
		super(id, initEnergy, position, playerName);
	}
	


	public void nextStep(EntityContext context) {
		
		if (nextMove > 0) {
			nextMove--;
			return;
		}else {
			if (spawnMini == 0)
				context.tryMove(this, nextMoveCommand);
		}
	}


}
