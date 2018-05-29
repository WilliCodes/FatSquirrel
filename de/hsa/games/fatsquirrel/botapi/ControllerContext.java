package de.hsa.games.fatsquirrel.botapi;

import de.hsa.games.fatsquirrel.core.EntityType;
import de.hsa.games.fatsquirrel.core.XY;

public interface ControllerContext {

	XY getViewLowerLeft();
	XY getViewUpperRight();
	EntityType getEntityAt(XY xy);
	void move(XY direction);
	void spawnMiniBot(XY direction, int energy);
	int getEnergy();
}
