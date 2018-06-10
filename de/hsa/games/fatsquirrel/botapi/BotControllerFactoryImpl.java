package de.hsa.games.fatsquirrel.botapi;

public class BotControllerFactoryImpl implements BotControllerFactory{

	@Override
	public BotController createMasterBotController(String botName) {
		
		try {
			return (BotController) Class.forName("de.hsa.games.fatsquirrel.botimpls.Master" + botName).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}

	@Override
	public BotController createMiniBotController(String botName) {
		try {
			return (BotController) Class.forName("de.hsa.games.fatsquirrel.botimpls.Mini" + botName).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (InstantiationException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}

}
