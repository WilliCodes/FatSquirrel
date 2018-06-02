package de.hsa.games.fatsquirrel.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import de.hsa.games.fatsquirrel.botapi.BotController;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;



public class EntitySet {
	

	private List<Entity> activeEntities = Collections.synchronizedList(new LinkedList<Entity>());
	// ConcurrentLinkedQueue
	
	private int idCounter = 0;
	
	public synchronized void placeWall(XY position) {
		Wall wall = new Wall(idCounter++, position);
		activeEntities.add(wall);
	}
	
	public synchronized void placeGoodBeast(XY position) {
		GoodBeast goodBeast = new GoodBeast(idCounter++, position);
		activeEntities.add(goodBeast);
		
	}
	
	public synchronized void placeBadBeast(XY position) {
		BadBeast badBeast = new BadBeast(idCounter++, position);
		activeEntities.add(badBeast);
	}
	
	public synchronized void placeGoodPlant(XY position) {
		GoodPlant goodPlant = new GoodPlant(idCounter++, position);
		activeEntities.add(goodPlant);
	}
	
	public synchronized void placeBadPlant(XY position) {
		BadPlant badPlant = new BadPlant(idCounter++, position);
		activeEntities.add(badPlant);
	}
	
	public synchronized void placeMasterSquirrel(XY position) {
		MasterSquirrel masterSquirrel = new MasterSquirrel(idCounter++, position);
		activeEntities.add(masterSquirrel);
	}
	
	public synchronized MiniSquirrel placeMiniSquirrel(XY position, int masterID, int initialEnergy) {
		MiniSquirrel miniSquirrel = new MiniSquirrel(idCounter++, initialEnergy,  position, masterID);
		activeEntities.add(miniSquirrel);
		
		return miniSquirrel;
	}
	
	public void placeHandOperatedMasterSquirrel(XY position) {
		HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(idCounter++, position);
		activeEntities.add(handOperatedMasterSquirrel);
	}
	

	public synchronized MiniSquirrelBot placeMiniBot(XY position, int masterID, int initialEnergy, BotController botcon) {
		MiniSquirrelBot miniBot = new MiniSquirrelBot(idCounter++, initialEnergy, position, masterID, botcon);

		activeEntities.add(miniBot);
		
		return miniBot;
	}
	
	public synchronized void placMasterSquirrelBot(XY position, BotController botcon) {
		MasterSquirrelBot masterBot = new MasterSquirrelBot(idCounter++, position, botcon);

		activeEntities.add(masterBot);
		
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
		for (Entity entity : activeEntities)
			if (entity instanceof Character && entity.isActive()) {
				Character c = (Character) entity;
				c.nextStep(context);
			}
		removeDeaktivated();
				
	}
	
	
}
