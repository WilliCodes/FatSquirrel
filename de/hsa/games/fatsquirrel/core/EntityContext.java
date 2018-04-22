package de.hsa.games.fatsquirrel.core;

public interface EntityContext {
	
	public enum EntityType {Wall, BadBeast, GoodBeast, BadPlant, GoodPlant, MiniSquirrel, MasterSquirrel, HandOperatedMasterSquirrel, Empty};
	
	public XY getSize();
	
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
	public void tryMove(GoodBeast goodBeast, XY moveDirection);
	public void tryMove(BadBeast badBeast, XY moveDirection);
	
	public PlayerEntity nearestPlayerEntity(XY pos);
	
	public void kill(Entity entity);
	public void killAndReplace(Entity entity);
	public EntityType getEntityType(XY pos);
}
