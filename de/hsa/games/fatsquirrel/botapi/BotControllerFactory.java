package de.hsa.games.fatsquirrel.botapi;

public interface BotControllerFactory {

	BotController createMasterBotController(String botName);
	BotController createMiniBotController(String botName);
}
