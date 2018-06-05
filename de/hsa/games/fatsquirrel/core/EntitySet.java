package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import de.hsa.games.fatsquirrel.botapi.BotController;



public class EntitySet {
	

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private List<Entity> activeEntities = Collections.synchronizedList(new LinkedList<Entity>());
	
	private int idCounter = 0;
	
	public synchronized void placeWall(XY position) {
		Wall wall = new Wall(idCounter++, position);
		logger.finer(wall.toString() + " was placed");
		activeEntities.add(wall);
	}
	
	public synchronized void placeGoodBeast(XY position) {
		GoodBeast goodBeast = new GoodBeast(idCounter++, position);
		activeEntities.add(goodBeast);
		logger.finer(goodBeast.toString() + " was placed");
	}
	
	public synchronized void placeBadBeast(XY position) {
		BadBeast badBeast = new BadBeast(idCounter++, position);
		activeEntities.add(badBeast);
		logger.finer(badBeast.toString() + " was placed");
	}
	
	public synchronized void placeGoodPlant(XY position) {
		GoodPlant goodPlant = new GoodPlant(idCounter++, position);
		activeEntities.add(goodPlant);
		logger.finer(goodPlant.toString() + " was placed");
	}
	
	public synchronized void placeBadPlant(XY position) {
		BadPlant badPlant = new BadPlant(idCounter++, position);
		activeEntities.add(badPlant);
		logger.finer(badPlant.toString() + " was placed");
	}

	public synchronized MiniSquirrel placeMiniSquirrel(XY position, int masterID, int initialEnergy) {
		MiniSquirrel miniSquirrel = new MiniSquirrel(idCounter++, initialEnergy,  position, masterID);
		activeEntities.add(miniSquirrel);
		logger.finer(miniSquirrel.toString() + " was placed");
		return miniSquirrel;
	}
	
	public void placeHandOperatedMasterSquirrel(XY position) {
		HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(idCounter++, position);
		activeEntities.add(handOperatedMasterSquirrel);
		logger.finer(handOperatedMasterSquirrel.toString() + " was placed");
	}
	

	public synchronized MiniSquirrelBot placeMiniBot(XY position, int masterID, int initialEnergy, BotController botcon) {
		MiniSquirrelBot miniBot = new MiniSquirrelBot(idCounter++, initialEnergy, position, masterID, botcon);

		activeEntities.add(miniBot);
		logger.finer(miniBot.toString() + " was placed");
		return miniBot;
	}
	
	public synchronized void placeMasterSquirrelBot(XY position, BotController botcon) {
		MasterSquirrelBot masterBot = new MasterSquirrelBot(idCounter++, position, botcon);

		activeEntities.add(masterBot);
		logger.finer(masterBot.toString() + " was placed");
	}
	
	
	public synchronized List<Entity> getEntities() {
		return activeEntities;
	}
	
	public synchronized void removeDeaktivated() {
		ListIterator<Entity> iterator = activeEntities.listIterator();
		while(iterator.hasNext()) {
			if (!iterator.next().isActive()) 
				iterator.remove();
		}
	}
	
	
	@Override
	public String toString() {
		String toString = "";
		for (Entity entity : activeEntities)
			toString += entity.toString() + "\n";
		
		return toString;
	}
	
	public synchronized void entitiesNextStep(EntityContext context) {
		Collections.shuffle(activeEntities);
		
		for (Entity entity : new ArrayList<Entity>(activeEntities))
			if (entity instanceof Character && entity.isActive()) {
				Character c = (Character) entity;
				c.nextStep(context);
			}
		removeDeaktivated();
				
	}
	
	
}
