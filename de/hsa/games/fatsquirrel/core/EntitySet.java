package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactoryImpl;



public class EntitySet {
	

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private List<Entity> activeEntities = new LinkedList<>(); //Collections.synchronizedList(new LinkedList<Entity>());
	private BotControllerFactory botCF = new BotControllerFactoryImpl();
	
	private int idCounter = 0;
	
	/**
	 * places a Wall at location XY
	 * @param position as XY
	 */
	public void placeWall(XY position) {
		Wall wall = new Wall(idCounter++, position);
		logger.finer(wall.toString() + " was placed");
		activeEntities.add(wall);
	}
	
	/**
	 * places a GoodBeast at location XY
	 * @param position as XY
	 */

	public void placeGoodBeast(XY position) {
		GoodBeast goodBeast = new GoodBeast(idCounter++, position);
		activeEntities.add(goodBeast);
		logger.finer(goodBeast.toString() + " was placed");
	}
	

	/**
	 * places a BadBeast at location XY
	 * @param position as XY
	 */
	public void placeBadBeast(XY position) {
		BadBeast badBeast = new BadBeast(idCounter++, position);
		activeEntities.add(badBeast);
		logger.finer(badBeast.toString() + " was placed");
	}
	
  
	/**
	 * places a GoodPlant at location XY
	 * @param position as XY
	 */
	public void placeGoodPlant(XY position) {
		GoodPlant goodPlant = new GoodPlant(idCounter++, position);
		activeEntities.add(goodPlant);
		logger.finer(goodPlant.toString() + " was placed");
	}
	

	/**
	 * places a BadPlant at location XY
	 * @param position as XY
	 */
	public void placeBadPlant(XY position) {
		BadPlant badPlant = new BadPlant(idCounter++, position);
		activeEntities.add(badPlant);
		logger.finer(badPlant.toString() + " was placed");
	}


	/**
	 * places a MiniSquirrel at location XY
	 * @param position as XY
	 * @param masterID as int
	 * @param initialEnergy as int
	 * @return placed MiniSquirrel
	 */
	public MiniSquirrel placeMiniSquirrel(XY position, int masterID, int initialEnergy) {
		MiniSquirrel miniSquirrel = new MiniSquirrel(idCounter++, initialEnergy,  position, masterID);
		activeEntities.add(miniSquirrel);
		logger.finer(miniSquirrel.toString() + " was placed");
		return miniSquirrel;
	}
	

	/**
	 * places a HandOperatedMasterSquirrel at location XY
	 * @param position as XY
	 * @param playerName as Name of the  Player
	 */
	public void placeHandOperatedMasterSquirrel(XY position, String playerName) {
		HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(idCounter++, position, playerName);
		activeEntities.add(handOperatedMasterSquirrel);
		logger.finer(handOperatedMasterSquirrel.toString() + " was placed");
	}
	


	/**
	 * places a MiniSquirrelBot at location XY
	 * @param position as XY
	 * @param masterID as int
	 * @param initialEnergy as int 
	 * @return placed MiniSquirrelBot
	 * @param botName as Name of the Bot
	 */
	public MiniSquirrelBot placeMiniSquirrelBot(XY position, int masterID, int initialEnergy, String botName) {
		MiniSquirrelBot miniBot = new MiniSquirrelBot(idCounter++, initialEnergy, position, masterID, botCF.createMiniBotController(botName));
		activeEntities.add(miniBot);
		logger.finer(miniBot.toString() + " was placed");
		return miniBot;
	}
	

	/**
	 * places a MasterSquirrelBot at location XY
	 * @param position as XY
	 * @param botName as Name of the Bot
	 */
	public void placeMasterSquirrelBot(XY position, String botName) {
		MasterSquirrelBot masterBot = new MasterSquirrelBot(idCounter++, position, botCF.createMasterBotController(botName), botName);
		activeEntities.add(masterBot);
		logger.finer(masterBot.toString() + " was placed");
	}
	

	/**
	 * 
	 * @return list of all active Entities of EntitySet
	 */
	public List<Entity> getEntities() {
		return activeEntities;
	}
	
	@Override
	public String toString() {
		String toString = "";
		for (Entity entity : activeEntities)
			toString += entity.toString() + "\n";
		
		return toString;
	}
	

	/**
	 * calls the nextStep() for all Entities in EntitySet
	 * @param context as EntityContext
	 */
	public void entitiesNextStep(EntityContext context) {
		Collections.shuffle(activeEntities);
		
		for (Entity entity : new ArrayList<Entity>(activeEntities))
			if (entity instanceof Character && entity.isActive()) {
				Character c = (Character) entity;
				c.nextStep(context);
			}

		activeEntities.removeIf(e -> !e.isActive());			
	}
}
