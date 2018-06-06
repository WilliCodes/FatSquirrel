package de.hsa.games.fatsquirrel.core;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import de.hsa.games.fatsquirrel.GameMode;


public class BoardConfig {
	
	private GameMode gameMode = GameMode.AI_GAME;
	private int stepsPerRound = 20;
	
	private int width = 60;
	private int height = 40;
	
	private int startGoodBeasts = 5;
	private int startBadBeasts = 5;
	private int startGoodPlants = 4;
	private int startBadPlants = 4;
	private int startWalls = 30;
	private boolean surroundWithWalls = true;
	
	private String[] masterSquirrelBotNames = {"Wilhelm", "ZickeZacke"};
	private String[] masterSquirrelPlayerNames = {"Player 1"};
	
	Properties properties = new Properties();
	
	
	public BoardConfig(String propertyFile) {
		
		Reader reader = null;
		
		try {
			reader = new FileReader(propertyFile);
			properties.load(reader);
		} catch (IOException e) {
		} finally {
			try {reader.close();} catch (Exception e) {}
		}
	}


	public GameMode getGameMode() {
		if (properties.containsKey("gameMode")) {
			try {
				return GameMode.valueOf(properties.getProperty("gameMode"));
			} catch (Exception e) {}
		}
			
		return gameMode;
	}


	public int getStepsPerRound() {
		Integer i = propertiyToInt("stepsPerRound");
		return (i == null) ? stepsPerRound :  i;
	}


	public int getWidth() {
		Integer i = propertiyToInt("width");
		return (i == null) ? width :  i;
	}


	public int getHeight() {
		Integer i = propertiyToInt("height");
		return (i == null) ? height :  i;
	}


	public int getStartGoodBeasts() {
		Integer i = propertiyToInt("startGoodBeasts");
		return (i == null) ? startGoodBeasts :  i;
	}


	public int getStartBadBeasts() {
		Integer i = propertiyToInt("startBadBeasts");
		return (i == null) ? startBadBeasts :  i;
	}


	public int getStartGoodPlants() {
		Integer i = propertiyToInt("startGoodPlants");
		return (i == null) ? startGoodPlants :  i;
	}


	public int getStartBadPlants() {
		Integer i = propertiyToInt("startBadPlants");
		return (i == null) ? startBadPlants :  i;
	}


	public int getStartWalls() {
		Integer i = propertiyToInt("startWalls");
		return (i == null) ? startWalls :  i;
	}


	public boolean getSurroundWithWalls() {
		if (properties.containsKey("surroundWithWalls")) {
			try {
				return Boolean.parseBoolean(properties.getProperty("surroundWithWalls"));
			} catch (Exception e) {}
		}
		return surroundWithWalls;
	}


	public String[] getMasterSquirrelBotNames() {
		if (properties.containsKey("masterSquirrelBotNames")) {
			try {
				return properties.getProperty("masterSquirrelBotNames").split(",");
			} catch (Exception e) {}
		}
		return masterSquirrelBotNames;
	}


	public String[] getMasterSquirrelPlayerNames() {
		if (properties.containsKey("masterSquirrelPlayerNames")) {
			try {
				return properties.getProperty("masterSquirrelPlayerNames").split(",");
			} catch (Exception e) {}
		}
		return masterSquirrelPlayerNames;
	}
	
	
	private Integer propertiyToInt(String property) {
		
		if (properties.containsKey(property)) {
			try {
				return Integer.parseInt(properties.getProperty(property));
			} catch (Exception e) {}
		}
		
		return null;
	}

}
