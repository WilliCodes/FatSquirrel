package de.hsa.games.fatsquirrel.botapi;

public class BotControllerFactoryImpl implements BotControllerFactory{

	@Override
	public BotController createMasterBotController() {
		BotController botCon = new BotControllerMasterImpl();
		return botCon;
	}

	@Override
	public BotController createMiniBotController() {
		BotController botCon = new BotControllerMiniImpl();
		return botCon;
	}

}
