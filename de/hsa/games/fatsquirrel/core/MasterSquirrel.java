package de.hsa.games.fatsquirrel.core;

public class MasterSquirrel extends PlayerEntity {
	
	private static final int initEnergy = 1000;
	
	
	public MasterSquirrel(int id, int energy, XY position) {
		super(id, energy, position);
	}
	
	public MasterSquirrel(int _id, XY _position) {
		super(_id, initEnergy, _position);
	}
	

	
	public boolean isMyMini(MiniSquirrel mini) {
		if (mini.getMasterID() == this.getId())
			return true;
		else
			return false;
	}


}
