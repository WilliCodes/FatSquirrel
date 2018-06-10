package de.hsa.games.fatsquirrel.core;

public abstract class MasterSquirrel extends PlayerEntity {
	
	private static final int initEnergy = 1000;
	
	protected int spawnMini = 0;
	protected XY spawnMiniPos;
	

	/**
	 * creates an instance of a MasterSquirrel
	 * @param id as int
	 * @param energy as int
	 * @param position as XY
	 */
	public MasterSquirrel(int id, int energy, XY position, String playerName) {
		super(id, energy, position, playerName);
	}
	
	/**
	 * creates an instance of a MasterSquirrel
	 * @param _id as int
	 * @param _position  as XY
	 */
	public MasterSquirrel(int _id, XY _position, String playerName) {
		super(_id, initEnergy, _position, playerName);
	}
	

	/**
	 * 
	 * @param mini as MiniSquirrel
	 * @return if Mini belongs to Master
	 */
	public boolean isMyMini(MiniSquirrel mini) {
		if (mini.getMasterID() == this.getId())
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param sharedEnergy
	 * @return if Mini should be spawned next move
	 */
	public boolean setSpawnMini(int sharedEnergy) {
		if (this.getEnergy() < sharedEnergy) 
			return false;
			
		this.spawnMini = sharedEnergy;
		return true;
	}
	
	/**
	 * 
	 * @param sharedEnergy as int
	 * @param spawnMiniDirection as XY
	 * @return if Mini should be spawned at given direction next move
	 */
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

	/**
	 * 
	 * @return spawned Mini
	 */
	public int getSpawmMini() {
		return spawnMini;
	}
	
	/**
	 * 
	 * @return position of spawned Mini
	 */
	public XY getSpawnMiniPos() {
		return spawnMiniPos;
	}

	@Override
	public abstract void nextStep(EntityContext context);


}
