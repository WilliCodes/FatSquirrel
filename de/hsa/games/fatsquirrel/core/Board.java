package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.List;

import de.hsa.games.fatsquirrel.core.EntityType;

public class Board {
	
	private EntitySet entitySet = new EntitySet();
	private int width;
	private int height;
	
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
		for (int i = 0; i < bC.startMasterSquirrels; i++) {
			tmpXY = randomPosition(blockedXY);
			entitySet.placeMasterSquirrel(tmpXY);
			blockedXY.add(tmpXY);
		}
		
		// load random HandOperatedMasterSquirrels
		for (int i = 0; i < bC.startHandOperatedMasterSquirrels; i++) {
			tmpXY = randomPosition(blockedXY);
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
			flatBoard[pos.x][pos.y] = e;
		}	
		
		return flatBoard;
	}
	
	
	public ArrayList<HandOperatedMasterSquirrel> getHandOperatedMasterSquirrels() {
		ArrayList<HandOperatedMasterSquirrel> handOperatedMasterSquirrels = new ArrayList<>();
		
		for (Entity e : entitySet.getEntities()) {
			if (e instanceof HandOperatedMasterSquirrel) {
				HandOperatedMasterSquirrel ms = (HandOperatedMasterSquirrel) e;
				if (ms.isActive() && !ms.isParalyzed())
					handOperatedMasterSquirrels.add(ms);
			}
		}
		
		return handOperatedMasterSquirrels;
	}
	
	
	public void respawn(ArrayList<EntityType> toRespawn) {
		ArrayList<XY> blockedXY = new ArrayList<>();
		
		for (Entity e : entitySet.getEntities())
			blockedXY.add(e.getPosition());
		
		for (EntityType e : toRespawn) {
			
			XY pos = randomPosition(blockedXY);
			blockedXY.add(pos);
			
			switch (e) {
			case GoodBeast:
				entitySet.placeGoodBeast(pos);
				break;
			case BadBeast:
				entitySet.placeBadBeast(pos);
				break;
			case GoodPlant:
				entitySet.placeGoodPlant(pos);
				break;
			case BadPlant:
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
	
	

}
