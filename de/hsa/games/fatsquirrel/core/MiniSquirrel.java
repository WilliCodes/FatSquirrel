package de.hsa.games.fatsquirrel.core;

public class MiniSquirrel extends MasterSquirrel{
	
	private int masterID;

	public MiniSquirrel(int id, int energy, XY position, int masterID) {
		super(id, energy, position);
		this.masterID = masterID;
	}

	public int getMasterID() {
		return masterID;
	}
	
	
	@Override
	public void nextStep(EntityContext context) {
		updateEnergy(-1);
		if (getEnergy() <= 0) {
			deactivate();
			return;
		}
		
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
