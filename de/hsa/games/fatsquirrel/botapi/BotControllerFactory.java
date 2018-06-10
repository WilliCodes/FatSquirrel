package de.hsa.games.fatsquirrel.botapi;

public interface BotControllerFactory {
	
	/**
	 * 
	 * @return creates Controller for MasterBot
	 */
	BotController createMasterBotController(String botName);
	
	/**
	 * 
	 * @return creates Controller for MiniBot
	 */
	BotController createMiniBotController(String botName);

}
