package de.hsa.games.fatsquirrel.core;

import de.hsa.games.fatsquirrel.Launcher.GameMode;

public class BoardConfig {
	
	public final GameMode gameMode = GameMode.AI_GAME;
	public final int width = 60;
	public final int height = 40;
	
	public final int startGoodBeasts = 5;
	public final int startBadBeasts = 5;
	public final int startGoodPlants = 4;
	public final int startBadPlants = 4;
	public final int startWalls = 30;
	public final int startMasterSquirrels = 2;
	public final boolean surroundWithWalls = true;
	
	
	public BoardConfig() {
	}
	
	public XY getSize() {
		return new XY(width, height);
	}

}
