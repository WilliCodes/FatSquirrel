package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.core.EntityType;

public interface BoardView {
	
	public EntityType getEntityType(int x, int y);
	public XY getSize();
	
	@Override
	public String toString();

}
