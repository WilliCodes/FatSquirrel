package de.hsa.games.fatsquirrel.core;



public enum EntityType {
	WALL, BAD_BEAST, GOOD_BEAST, BAD_PLANT, GOOD_PLANT, MINI_SQUIRREL, MASTER_SQUIRREL, NONE;

	
	/**
	 * 
	 * @param e as Entity
	 * @return EntityType of given Entity
	 */
     public static EntityType getEntityType(Entity e) {
		
		if (e == null || !e.isActive())
			return EntityType.NONE;
		
		String name = e.getClass().getSimpleName();
		
		switch (name) {
		case "BadBeast":
			return EntityType.BAD_BEAST;
		case "GoodBeast":
			return EntityType.GOOD_BEAST;
		case "BadPlant":
			return EntityType.BAD_PLANT;
		case "GoodPlant":
			return EntityType.GOOD_PLANT;
		case "Wall":
			return EntityType.WALL;
		case "MasterSquirrel":
		case "HandOperatedMasterSquirrel":
		case "MasterSquirrelBot":
			return EntityType.MASTER_SQUIRREL;
		case "MiniSquirrel":
		case "MiniSquirrelBot":
			return EntityType.MINI_SQUIRREL;
		default:
			return EntityType.NONE;
		}
	}
}
