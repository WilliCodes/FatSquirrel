package de.hsa.games.fatsquirrel.core;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;



public class EntitySet {
	

	private List<Entity> activeEntities = new LinkedList<Entity>();
	
	private int idCounter = 0;
	
	public void placeWall(XY position) {
		Wall wall = new Wall(idCounter++, position);
		activeEntities.add(wall);
	}
	
	public void placeGoodBeast(XY position) {
		GoodBeast goodBeast = new GoodBeast(idCounter++, position);
		activeEntities.add(goodBeast);
		
	}
	
	public void placeBadBeast(XY position) {
		BadBeast badBeast = new BadBeast(idCounter++, position);
		activeEntities.add(badBeast);
	}
	
	public void placeGoodPlant(XY position) {
		GoodPlant goodPlant = new GoodPlant(idCounter++, position);
		activeEntities.add(goodPlant);
	}
	
	public void placeBadPlant(XY position) {
		BadPlant badPlant = new BadPlant(idCounter++, position);
		activeEntities.add(badPlant);
	}
	
	public void placeMasterSquirrel(XY position) {
		MasterSquirrel masterSquirrel = new MasterSquirrel(idCounter++, position);
		activeEntities.add(masterSquirrel);
	}
	
	public void placeMiniSquirrel(XY position, int masterID, int initialEnergy) {
		MiniSquirrel miniSquirrel = new MiniSquirrel(idCounter++, initialEnergy,  position, masterID);
		activeEntities.add(miniSquirrel);
	}
	
	public void placeHandOperatedMasterSquirrel(XY position) {
		HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(idCounter++, position);
		activeEntities.add(handOperatedMasterSquirrel);
	}
	
	
	public List<Entity> getEntities() {
		return activeEntities;
	}
	
	public void removeDeaktivated() {
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
	
	public void entitiesNextStep(EntityContext context) {
		for (Entity entity : activeEntities)
			if (entity instanceof Character && entity.isActive()) {
				Character c = (Character) entity;
				c.nextStep(context);
			}
				
	}
	
	
}
