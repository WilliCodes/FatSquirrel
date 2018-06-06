package de.hsa.games.fatsquirrel.core;

import java.util.List;

import de.hsa.games.fatsquirrel.core.EntityType;

public interface BoardView {
	
	public EntityType getEntityType(int x, int y);
	public XY getSize();
	public int getMasterSquirrelEnergy();
	public List<MasterSquirrel> getMasterSquirrels();
	
	@Override
	public String toString();

}
