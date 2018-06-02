package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

public interface ControllerContext {

	public XY getViewLowerLeft();
	public XY getViewUpperRight();
	public EntityType getEntityAt(XY xy);
	public void move(XY direction);
	public void spawnMiniBot(XY direction, int energy);
	public int getEnergy();
}
