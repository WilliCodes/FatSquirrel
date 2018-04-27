package de.hsa.games.fatsquirrel.core;



public enum EntityType {
	Wall, BadBeast, GoodBeast, BadPlant, GoodPlant, MiniSquirrel, MasterSquirrel, HandOperatedMasterSquirrel, Empty;

	
     public static EntityType getEntityType(Entity e) {
		
		if (e == null || !e.isActive())
			return EntityType.Empty;
		
		String name = e.getClass().getSimpleName();
		
		switch (name) {
		case "BadBeast":
			return EntityType.BadBeast;
		case "GoodBeast":
			return EntityType.GoodBeast;
		case "BadPlant":
			return EntityType.BadPlant;
		case "GoodPlant":
			return EntityType.GoodPlant;
		case "Wall":
			return EntityType.Wall;
		case "MasterSquirrel":
			return EntityType.MasterSquirrel;
		case "HandOperatedMasterSquirrel":
			return EntityType.HandOperatedMasterSquirrel;
		case "MiniSquirrel":
			return EntityType.MiniSquirrel;
		default:
			return null;
		}
	}
}
