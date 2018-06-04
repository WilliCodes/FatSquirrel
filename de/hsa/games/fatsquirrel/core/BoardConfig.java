package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher.GameMode;

public class BoardConfig {
	
	public final GameMode gameMode = GameMode.AI_GAME;
	public final int width = 30;
	public final int height = 20;
	
	public final int startGoodBeasts = 3;
	public final int startBadBeasts = 3;
	public final int startGoodPlants = 3;
	public final int startBadPlants = 3;
	public final int startWalls = 10;
	public final int startMasterSquirrels = 2;
	public final boolean surroundWithWalls = true;
	
	
	public BoardConfig() {
	}
	
	public XY getSize() {
		return new XY(width, height);
	}

}
