package de.hsa.games.fatsquirrel.core;

import java.util.List;

import de.hsa.games.fatsquirrel.core.EntityType;

public interface BoardView {
	
	/**
	 * 
	 * @param x as int
	 * @param y as int
	 * @return EntityType of given coordinates
	 */
	public EntityType getEntityType(int x, int y);
	
	/**
	 * 
	 * @return XY as size of the board
	 */
	public XY getSize();
	
	/**
	 * 
	 * @return Energy of the MasterSquirrel
	 */
	public int getMasterSquirrelEnergy();
	public List<MasterSquirrel> getMasterSquirrels();
	
	@Override
	public String toString();

}
