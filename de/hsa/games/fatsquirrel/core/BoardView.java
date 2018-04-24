package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.core.EntityContext.EntityType;

public interface BoardView {
	
	public EntityType getEntityType(int x, int y);
	public XY getSize();
	
	@Override
	public String toString();

}
