package de.hsa.games.fatsquirrel.core;

public abstract class MasterSquirrel extends PlayerEntity {
	
	private static final int initEnergy = 1000;
	
	protected int spawnMini = 0;
	protected XY spawnMiniPos;
	
	
	
	public MasterSquirrel(int id, int energy, XY position, String playerName) {
		super(id, energy, position, playerName);
	}
	
	public MasterSquirrel(int _id, XY _position, String playerName) {
		super(_id, initEnergy, _position, playerName);
	}
	

	
	public boolean isMyMini(MiniSquirrel mini) {
		if (mini.getMasterID() == this.getId())
			return true;
		else
			return false;
	}
	
	public boolean setSpawnMini(int sharedEnergy) {
		if (this.getEnergy() < sharedEnergy) 
			return false;
			
		this.spawnMini = sharedEnergy;
		return true;
	}
	
	public boolean setSpawnMini(int sharedEnergy, XY spawnMiniDirection) {
		if (this.getEnergy() < sharedEnergy) 
			return false;
			
		this.spawnMini = sharedEnergy;
		if (spawnMiniDirection == null)
			this.spawnMiniPos = null;
		else
			this.spawnMiniPos = getPosition().plus(spawnMiniDirection);
		
		return true;
	}

	public int getSpawmMini() {
		return spawnMini;
	}
	
	public XY getSpawnMiniPos() {
		return spawnMiniPos;
	}

	@Override
	public abstract void nextStep(EntityContext context);


}
