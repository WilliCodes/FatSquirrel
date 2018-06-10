package de.hsa.games.fatsquirrel.core;

public interface EntityContext {
	
	/**
	 * 
	 * @return XY with the size
	 */
	public XY getSize();
	
	/**
	 * tries to move MasterSquirrel to given direction
	 * @param masterSquirrel as MasterSquirrel
	 * @param moveDirection as XY
	 */
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
	
	/**
	 * tries to move MiniSquirrel to given direction
	 * @param miniSquirrel as MiniSquirrel
	 * @param moveDirection as XY
	 */
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
	
	/**
	 * tries to move GoodBeast to given direction
	 * @param miniSquirrel as MiniSquirrel
	 * @param moveDirection as XY
	 */
	public void tryMove(GoodBeast goodBeast, XY moveDirection);
	
	/**
	 * tries to move BadBeast to given direction
	 * @param miniSquirrel as MiniSquirrel
	 * @param moveDirection as XY
	 */
	public void tryMove(BadBeast badBeast, XY moveDirection);
	
	/**
	 * 
	 * @param pos as XY
	 * @param id as int
	 * @return if own Master is on given location
	 */
	public boolean isMyMaster(XY pos, int id);
	
	/**
	 * 
	 * @param pos as XY
	 * @param id as int
	 * @return if own Mini is on given location
	 */
	public boolean isMyMini(XY pos, int id);
	
	/**
	 * implodes MiniSquirrel with given blastradius
	 * @param impactRadius as int
	 * @param miniSquirrelBot as MiniSquirrel
	 */
	public void implodeMini(int impactRadius, MiniSquirrel miniSquirrelBot);
	
	/**
	 * 
	 * @param pos as XY
	 * @return nearest PlayerEntity from location XY
	 */
	public PlayerEntity nearestPlayerEntity(XY pos);
	
	/**
	 * kills Entity
	 * @param entity as Entity
	 */
	public void kill(Entity entity);
	
	/**
	 * kills and replaces Entity
	 * @param entity as Entity
	 */
	public void killAndReplace(Entity entity);
	
	/**
	 * 
	 * @param pos as XY
	 * @return EntityType on given location
	 */
	public EntityType getEntityType(XY pos);

	/**
	 * 
	 * @param miniSquirrel as MiniSquirrel
	 * @return XY direction to the MasterSquirrel of the MiniSquirrel
	 */
	public XY directionOfMaster(MiniSquirrel miniSquirrel);

}
