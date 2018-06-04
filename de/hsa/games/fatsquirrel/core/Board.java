package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.Launcher.GameMode;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactory;
import de.hsa.games.fatsquirrel.botapi.BotControllerFactoryImpl;
import de.hsa.games.fatsquirrel.core.EntityType;


public class Board {
	
	private EntitySet entitySet = new EntitySet();
	private int width;
	private int height;
	
	BotControllerFactory botCF;
	
	public Board(BoardConfig bC) {
		
		// Position of created Entities is stored to prevent collisions
		ArrayList<XY> blockedXY = new ArrayList<>();
		XY tmpXY;
		
		// load board dimensions
		width = bC.width;
		height = bC.height;
		
		// load surrounding walls
		if (bC.surroundWithWalls) {
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
		for (int i = 0; i < bC.startWalls; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeWall(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random GoodPlants
		for (int i = 0; i < bC.startGoodPlants; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeGoodPlant(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random BadPlants
		for (int i = 0; i < bC.startBadPlants; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeBadPlant(tmpXY);
			blockedXY.add(tmpXY);
		}
				
		// load random GoodBeasts
		for (int i = 0; i < bC.startGoodBeasts; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeGoodBeast(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random BadBeasts
		for (int i = 0; i < bC.startBadBeasts; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeBadBeast(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random MasterSquirrels
		if (bC.gameMode == GameMode.AI_GAME) {
			botCF = new BotControllerFactoryImpl();
		}
		for (int i = 0; i < bC.startMasterSquirrels; i++) {
			tmpXY = randomPosition(blockedXY);
			if (bC.gameMode == GameMode.AI_GAME)
				entitySet.placeMasterSquirrelBot(tmpXY, botCF.createMasterBotController());
			else
				entitySet.placeHandOperatedMasterSquirrel(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		
	}
	
	public EntitySet getEntitySet() {
		return entitySet;
	}
	
	private XY randomPosition(ArrayList<XY> blockedXY) {	
		XY pos;
		
		do 	
			pos = XY.randomXY(0, width, 0, height);
		while (blockedXY.contains(pos));
		
		return pos;	
	}
	
	
	
	
	public Entity[][] flatten() {
		Entity[][] flatBoard = new Entity[width][height];
		
		List<Entity> entities = entitySet.getEntities();
		
		for (Entity e : entities) {
			XY pos = e.getPosition();
			//System.out.println(pos.toString());
			//System.out.print(pos.x + " " + pos.y + " " + e.toString());
			flatBoard[pos.x][pos.y] = e;
		}	
		
		return flatBoard;
	}
	
	
	public MasterSquirrel getHandOperatedMasterSquirrel() {
		
		
		for (Entity e : entitySet.getEntities()) {
			if (e instanceof MasterSquirrel) {
				return (MasterSquirrel) e;
			}
		}
		
		return null;
	}
	
	
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
	
	

	public void updateCharacters(EntityContext context) {
		entitySet.entitiesNextStep(context);
	}


	public void spawnMini(XY pos, MasterSquirrel ms) {
		entitySet.placeMiniSquirrel(pos, ms.getId(), ms.getSpawmMini());
		ms.setSpawnMini(0, null);
	}

	public List<MasterSquirrel> getMasterSquirrels() {
		List<MasterSquirrel> masterSquirrels = new ArrayList<>();
		
		for (Entity e : entitySet.getEntities()) {
			if (e instanceof HandOperatedMasterSquirrel || e instanceof MasterSquirrelBot)
				masterSquirrels.add((MasterSquirrel) e); 
		}
		
		return masterSquirrels;
	}
	
	

}
