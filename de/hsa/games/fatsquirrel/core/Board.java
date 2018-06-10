package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.GameMode;
import de.hsa.games.fatsquirrel.core.EntityType;


public class Board {
	
	private EntitySet entitySet = new EntitySet();
	private int width;
	private int height;
	
	
	/**
	 * 
	 * @param bC as BoardConfig
	 */
	public Board(BoardConfig bC) {
		
		// Position of created Entities is stored to prevent collisions
		ArrayList<XY> blockedXY = new ArrayList<>();
		XY tmpXY;
		
		// load board dimensions
		width = bC.getWidth();
		height = bC.getHeight();
		
		// load surrounding walls
		if (bC.getSurroundWithWalls()) {
			for (int i = 0; i < width; i++) {
				tmpXY = new XY(i,0);
				entitySet.placeWall(tmpXY);
				blockedXY.add(tmpXY);
				
				tmpXY = new XY(i, height-1);
				entitySet.placeWall(tmpXY);
				blockedXY.add(tmpXY);
			}
			for(int i = 1; i < height-1; i++) {
				tmpXY = new XY(0, i);
				entitySet.placeWall(tmpXY);
				blockedXY.add(tmpXY);
				
				tmpXY = new XY(width-1, i);
				entitySet.placeWall(tmpXY);
				blockedXY.add(tmpXY);
			}
		}
		
		// load random walls
		for (int i = 0; i < bC.getStartWalls(); i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeWall(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random GoodPlants
		for (int i = 0; i < bC.getStartGoodPlants(); i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeGoodPlant(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random BadPlants
		for (int i = 0; i < bC.getStartBadPlants(); i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeBadPlant(tmpXY);
			blockedXY.add(tmpXY);
		}
				
		// load random GoodBeasts
		for (int i = 0; i < bC.getStartGoodBeasts(); i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeGoodBeast(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random BadBeasts
		for (int i = 0; i < bC.getStartBadBeasts(); i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeBadBeast(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load MasterSquirrels
		if (bC.getGameMode() == GameMode.AI_GAME) {
			for (String botName : bC.getMasterSquirrelBotNames()) {
				tmpXY = randomPosition(blockedXY);
				entitySet.placeMasterSquirrelBot(tmpXY, botName);
				blockedXY.add(tmpXY);
			}
				
		} else
			for (String playerName : bC.getMasterSquirrelPlayerNames()) {
				tmpXY = randomPosition(blockedXY);
				entitySet.placeHandOperatedMasterSquirrel(tmpXY, playerName);
				blockedXY.add(tmpXY);
			}
		
		
	}
	
	/**
	 * 
	 * @return set of Entitys
	 */
	public EntitySet getEntitySet() {
		return entitySet;
	}
	
	/**
	 * 
	 * @param blockedXY as XY
	 * @return a random XY position
	 */
	private XY randomPosition(ArrayList<XY> blockedXY) {	
		XY pos;
		
		do 	
			pos = XY.randomXY(0, width, 0, height);
		while (blockedXY.contains(pos));
		
		return pos;	
	}
	
	
	
	/**
	 * 
	 * @return array of entitys from Boards entityset
	 */
	public Entity[][] flatten() {
		Entity[][] flatBoard = new Entity[width][height];
		
		List<Entity> entities = entitySet.getEntities();
		
		for (Entity e : entities) {
			XY pos = e.getPosition();
			flatBoard[pos.x][pos.y] = e;
		}	
		
		return flatBoard;
	}
	
	
	/**
	 * 
	 * @return MasterSquirrel from the entityset
	 */
	public MasterSquirrel getHandOperatedMasterSquirrel() {
		
		
		for (Entity e : entitySet.getEntities()) {
			if (e instanceof MasterSquirrel) {
				return (MasterSquirrel) e;
			}
		}
		
		return null;
	}
	
	/**
	 * respawnes dead entities from a given ArrayList
	 * @param toRespawn as ArrayList
	 */
	public void respawn(ArrayList<EntityType> toRespawn) {
		ArrayList<XY> blockedXY = new ArrayList<>();
		
		for (Entity e : entitySet.getEntities())
			blockedXY.add(e.getPosition());
		
		for (EntityType e : toRespawn) {
			
			XY pos = randomPosition(blockedXY);
			blockedXY.add(pos);
			
			switch (e) {
			case GOOD_BEAST:
				entitySet.placeGoodBeast(pos);
				break;
			case BAD_BEAST:
				entitySet.placeBadBeast(pos);
				break;
			case GOOD_PLANT:
				entitySet.placeGoodPlant(pos);
				break;
			case BAD_PLANT:
				entitySet.placeBadPlant(pos);
				break;
			default:
				break;
			}
		}
		
		toRespawn.clear();
	}
	
	

	/**
	 * calls the nextStep() for all Entities in Board
	 * @param context as EntityContext
	 */
	public void updateCharacters(EntityContext context) {
		entitySet.entitiesNextStep(context);
	}


	/**
	 * 
	 * @param pos as XY
	 * @param ms as MasterSquirrel
	 * @return MiniSquirrel from the given MasterSquirrel
	 */
	public MiniSquirrel spawnMini(XY pos, MasterSquirrel ms) {
		if (ms instanceof MasterSquirrelBot)
			return entitySet.placeMiniSquirrelBot(pos, ms.getId(), ms.getSpawmMini(), ms.playerName);
		return entitySet.placeMiniSquirrel(pos, ms.getId(), ms.getSpawmMini());
	}

	/**
	 * 
	 * @return an ArrayList with all MasterSquirrels of Board
	 */
	public List<MasterSquirrel> getMasterSquirrels() {
		List<MasterSquirrel> masterSquirrels = new ArrayList<>();
		
		for (Entity e : entitySet.getEntities()) {
			if (e instanceof HandOperatedMasterSquirrel || e instanceof MasterSquirrelBot)
				masterSquirrels.add((MasterSquirrel) e); 
		}
		
		return masterSquirrels;
	}
	
	

}
