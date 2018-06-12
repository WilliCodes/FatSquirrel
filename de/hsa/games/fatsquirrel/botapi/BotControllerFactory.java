package de.hsa.games.fatsquirrel.botapi;

public interface BotControllerFactory {
	
	/**
	 * @param botName Name of the Bot
	 * @return creates Controller for MasterBot
	 */
	BotController createMasterBotController(String botName);
	
	/**
	 * @param botName Name of the Bot
	 * @return creates Controller for MiniBot
	 */
	BotController createMiniBotController(String botName);

}
