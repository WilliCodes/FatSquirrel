package de.hsa.games.fatsquirrel.core;


import de.hsa.games.fatsquirrel.Launcher.GameMode;

public class BoardConfig {
	
	public final GameMode gameMode = GameMode.AI_GAME;
	public final int stepsPerRound = 20;
	
	public final int width = 60;
	public final int height = 40;
	
	public final int startGoodBeasts = 5;
	public final int startBadBeasts = 5;
	public final int startGoodPlants = 4;
	public final int startBadPlants = 4;
	public final int startWalls = 30;
	public final boolean surroundWithWalls = true;
	
	public final String[] masterSquirrelBotNames = {"Wilhelm", "ZickeZacke"};
	public final String[] masterSquirrelPlayerNames = {"Player 1"};
	

}
