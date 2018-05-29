package de.hsa.games.fatsquirrel.botapi;

public interface BotControllerFactory {

	BotController createMasterBotController();
	BotController createMiniBotController();
}
