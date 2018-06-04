package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class FlattenedBoard implements BoardView, EntityContext {
	
	private Entity[][] cells;
	private ArrayList<EntityType> toRespawn = new ArrayList<>();
	private Board board;
	private List<MasterSquirrel> masterSquirrels = new ArrayList<>();
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public FlattenedBoard(Board board) {
		this.board = board;
		cells = this.board.flatten();
		masterSquirrels = (board.getMasterSquirrels());
		logger.fine("FlattenBoard created");
	}
	
	public void update() {
		cells = board.flatten();
	}
	
	public ArrayList<EntityType> getRespawnList() {
		return toRespawn;
	}

	@Override
	public EntityType getEntityType(int x, int y) {
		return getEntityType(new XY(x, y));
	}

	@Override
	public XY getSize() {
		return new XY(cells.length, cells[0].length);
	}
	
	private void move(Entity e, XY from, XY to) {
		e.setPosition(to);
		cells[from.x][from.y] = null;
		cells[to.x][to.y] = e;
	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		XY from = masterSquirrel.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		EntityType eT = getEntityType(to);
		
		switch (eT) {
		case NONE:
			move(masterSquirrel, from, to);
			logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case BAD_BEAST:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " got bitten by " + target.toString() + " and lost Energy: " + target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				logger.finer(target.toString() + " was deleted after nextBite() = " + ((BadBeast)target).nextBite());
				move(masterSquirrel, from, to);
				logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			}
			break;
		case BAD_PLANT:
		case GOOD_BEAST:
		case GOOD_PLANT:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " ate " + target.toString() + " and changed energy of: " + target.getEnergy());
			killAndReplace(target);
			logger.finer(target.toString() + " was deleted after " + masterSquirrel.toString() + " ate it");
			move(masterSquirrel, from, to);
			logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			break;

		case MASTER_SQUIRREL:
      logger.finer(masterSquirrel.toString() + " can't move because " + target.toString() + " is in the way");
			break;
		case MINI_SQUIRREL:
			if (masterSquirrel.isMyMini((MiniSquirrel) target))
				masterSquirrel.updateEnergy(target.getEnergy());
				logger.finer(masterSquirrel.toString() + " consumed its own Mini: " + target.toString());
			}
			else {
				masterSquirrel.updateEnergy(150);
				logger.finer(masterSquirrel.toString() + " consumed enemy Mini: " + target.toString() + ", and gained 150 Energy");
			}
			kill(target);
			logger.finer(target.toString() + " was eaten by " + masterSquirrel.toString());
			move(masterSquirrel, from, to);
			logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case WALL:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " collided with " + target.toString() + " and lost Energy of: " + target.getEnergy());
			masterSquirrel.setParalyzed();
			logger.finer(masterSquirrel.toString() + " is paralyzed");
			break;
		default:
			break;
		}
	}

	@Override
	public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
			
		XY from = miniSquirrel.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		EntityType eT = getEntityType(to);
		
		switch (eT) {
		case NONE:
			move(miniSquirrel, from, to);
			logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case BAD_BEAST:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " was bitten by " + target.toString() + " and lost Energy: " + target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				logger.finer(target.toString() + " was deleted after nextBite() = " + ((BadBeast)target).nextBite());
				move(miniSquirrel, from, to);
				logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			}
			break;
		case BAD_PLANT:
		case GOOD_BEAST:
		case GOOD_PLANT:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " ate " + target.toString() + " and changed energy of: " + target.getEnergy());
			killAndReplace(target);
			logger.finer(target.toString() + " was deleted after " + miniSquirrel.toString() + " ate it");
			move(miniSquirrel, from, to);
			logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case MASTER_SQUIRREL:
			if (miniSquirrel.getMasterID() == target.getId()) {
				target.updateEnergy(miniSquirrel.getEnergy());
				logger.finer(miniSquirrel.toString() + " was consumed by his Master " + target.toString() + " who gained energy: " + miniSquirrel.getEnergy());
			} else {
				target.updateEnergy(150);
				logger.finer(miniSquirrel.toString() + " was consumed by an other Master " + target.toString() + " who gained energy: 150");
			}
			kill(miniSquirrel);
			logger.finer(miniSquirrel.toString() + " was delted after " + target.toString() + " consumed it");
			break;
		case MINI_SQUIRREL:
			if (miniSquirrel.getMasterID() != ((MiniSquirrel) target).getMasterID()) {
				kill(miniSquirrel);
				logger.finer(miniSquirrel.toString() + " was deleted after the colission with " + target.toString());
				kill(target);
				logger.finer(target.toString() + " was deleted after the colission with " + miniSquirrel.toString());
			}
			break;
		case WALL:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " collided with " + target.toString() + " and lost Energy of: " + target.getEnergy());
			miniSquirrel.setParalyzed();
			logger.finer(miniSquirrel.toString() + " is paralyzed");
			break;
		default:
			break;
		}
		
		
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		XY from = goodBeast.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		
		switch (getEntityType(to)) {
		case NONE:
			move(goodBeast, from, to);
			logger.finest(goodBeast.toString() + " moved from " + from + " to " + to);
			break;
		case BAD_BEAST:
		case BAD_PLANT:
		case GOOD_BEAST:
		case GOOD_PLANT:
		case WALL:
			logger.finer(goodBeast.toString() + "couldn´t move because " + target.toString() + " was in the way");
			break;
		case MASTER_SQUIRREL:
		case MINI_SQUIRREL:
			target.updateEnergy(goodBeast.getEnergy());
			logger.finer(goodBeast.toString() + " was eaten by " + target.toString() + " who gained energy: " + goodBeast.getEnergy());
			killAndReplace(goodBeast);
			logger.finer(goodBeast.toString() + " was deleted after " + target.toString() + "ate it");
			break;
		}
	}

	@Override
	public void tryMove(BadBeast badBeast, XY moveDirection) {
		XY from = badBeast.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		
		switch (getEntityType(to)) {
		case NONE:
			move(badBeast, from, to);
			logger.finest(badBeast.toString() + " moved from " + from + " to " + to);
			break;
		case BAD_BEAST:
		case BAD_PLANT:
		case GOOD_BEAST:
		case GOOD_PLANT:
		case WALL:
			logger.finer(badBeast.toString() + " couldn´t move because " + target.toString() + " was in the way");
			break;
		case MASTER_SQUIRREL:
		case MINI_SQUIRREL:
			target.updateEnergy(badBeast.getEnergy());
			logger.finer(badBeast.toString() + " was eaten by " + target.toString() + " who lost energy: " + badBeast.getEnergy());
			if (badBeast.nextBite()) {
				killAndReplace(badBeast);
				logger.finer(badBeast.toString() + " was deleted after nextBite() = " + badBeast.nextBite());
			}
		}
		
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY pos) {
		
		PlayerEntity nearest = null;
		double distance = 7;
		
		for (int i = pos.x - 6; i <= pos.x + 6; i++) {
			for (int j = pos.y - 6; j <= pos.y + 6; j++) {
				if (i >= 0 && j >= 0 && i < cells.length && j < cells[0].length && cells[i][j] != null && cells[i][j] instanceof PlayerEntity && cells[i][j].getPosition() != pos) {
					
					int tmp = XY.distanceToEntity(pos, new XY(i,j));
					if (tmp < distance) {
						distance = tmp;
						nearest = (PlayerEntity) cells[i][j];
					}	
				}
			}
		}
		return nearest;
	}
	
	

	@Override
	public void kill(Entity entity) {
		entity.deactivate();
		XY pos = entity.getPosition();
		cells[pos.x][pos.y] = null; 
	}

	@Override
	public void killAndReplace(Entity entity) {
		toRespawn.add(getEntityType(entity.getPosition()));
		kill(entity);	
	}

	@Override
	public EntityType getEntityType(XY pos) {
			return EntityType.getEntityType(cells[pos.x][pos.y]);
	}
	
	@Override
	public String toString() {
		String view = "";
		for (int y = 0; y < cells[0].length; y++) {
			String line = "";
			for (int x = 0; x < cells.length; x++) {
				EntityType t = getEntityType(x, y);
				switch (t) {
				case BAD_BEAST:
					line += " b ";
					break;
				case GOOD_BEAST:
					line += " B ";
					break;
				case BAD_PLANT:
					line += " p ";
					break;
				case GOOD_PLANT:
					line += " P ";
					break;
				case WALL:
					line += " X ";
					break;
				case MASTER_SQUIRREL:
					line += " M ";
					break;
				case MINI_SQUIRREL:
					line += " m ";
					break;
				case NONE:
					line += "   ";
				}
			}
			view += line + "\n";
		}
		
		return view;
		
	}

	public XY getRandomFreeNeighbourCellDirection(XY pos) {
		XY[] directionsTmp = new XY[] { new XY(-1,-1), new XY(-1,0), new XY(-1,1), new XY(0,-1), new XY(0,1), new XY(1,-1), new XY(1,0), new XY(1,1) };
		List<XY> directions = Arrays.asList(directionsTmp);
		Collections.shuffle(directions);
		
		for (XY xy : directions) {
			XY target = xy.plus(pos);
			if( target.x < 0 || target.y < 0 || target.x > cells.length || target.y > cells[0].length || getEntityType(target) != EntityType.NONE)
				continue;
			return xy;
		}
		
		return null;
	}

	@Override
	public int getMasterSquirrelEnergy() {
		return masterSquirrels.get(0).getEnergy();
	}

	@Override
	public boolean isMyMaster(XY pos, int id) {
		if (getEntityType(pos) != EntityType.MASTER_SQUIRREL)
			return false;
		
		if (((MasterSquirrel) cells[pos.x][pos.y]).getId() == id)
			return true;
		
		return false;
	}

	@Override
	public boolean isMyMini(XY pos, int id) {
		if (getEntityType(pos) != EntityType.MINI_SQUIRREL)
			return false;
		
		if (((MiniSquirrel) cells[pos.x][pos.y]).getMasterID() == id)
			return true;
		
		return false;
	}

	@Override
	public void implodeMini(int impactRadius, MiniSquirrel miniSquirrel) {
		XY pos = miniSquirrel.getPosition();
		
		int impactArea = (int) (impactRadius * impactRadius * Math.PI);
		
		for (int x = pos.x - impactRadius; x <= pos.x + impactRadius; x++ ) {
			if (x < 0 || x > getSize().x) 
				continue;
			for (int y = pos.y - impactRadius; y <= pos.y + impactRadius; y++ ) {
				if (y < 0 || y > getSize().y) 
					continue;
				
				XY targetPos = new XY(x,y);
				EntityType entityType = getEntityType(targetPos);
				
				if (entityType == EntityType.NONE || entityType == EntityType.WALL)
					continue;
				
				Entity target = cells[x][y];
				int energyLoss = 200 * (miniSquirrel.getEnergy() / impactArea) * (1 - XY.distanceToEntity(pos, targetPos) / impactRadius);
				
				switch (entityType) {
				case BAD_BEAST:
				case BAD_PLANT:
					if (energyLoss >= -target.getEnergy()) {
						miniSquirrel.updateEnergy(-target.getEnergy());
						killAndReplace(target);
					} else {
						miniSquirrel.updateEnergy(energyLoss);
						target.updateEnergy(energyLoss);
					}
					break;
				case GOOD_BEAST:
				case GOOD_PLANT:
					if (energyLoss >= target.getEnergy()) {
						miniSquirrel.updateEnergy(target.getEnergy());
						killAndReplace(target);
					} else {
						miniSquirrel.updateEnergy(energyLoss);
						target.updateEnergy(-energyLoss);
					}
					break;
				case MASTER_SQUIRREL:
					if (isMyMaster(targetPos, miniSquirrel.getId()))
						break;
					if (energyLoss >= target.getEnergy()) {
						miniSquirrel.updateEnergy(target.getEnergy());
					} else {
						miniSquirrel.updateEnergy(energyLoss);
						target.updateEnergy(-energyLoss);
					}
					break;
				case MINI_SQUIRREL:
					if (isMyMini(targetPos, miniSquirrel.getMasterID()))
						break;
					if (energyLoss >= target.getEnergy()) {
						miniSquirrel.updateEnergy(target.getEnergy());
					} else {
						miniSquirrel.updateEnergy(energyLoss);
						target.updateEnergy(-energyLoss);
					}
					break;
				default:
					break;
				}
				
				
			}
		}
		
		
	}

	@Override
	public XY directionOfMaster(MiniSquirrel miniSquirrel, int sight) {
		// TODO : Nur in sight oder Richtung global einsehbar?
		XY pos = miniSquirrel.getPosition();
		for (int x = pos.x - sight; x <= pos.x + sight; x++) {
			if (x < 0 || x > getSize().x)
				continue;
			for (int y = pos.y - sight; y <= pos.y + sight; y++) {
				if (y < 0 || y > getSize().y)
					continue;
				if (getEntityType(new XY(x,y)) == EntityType.MASTER_SQUIRREL && ((MasterSquirrel) cells[x][y]).isMyMini(miniSquirrel))
					return XY.vectorToEntity(miniSquirrel, cells[x][y]);
			}
			
		}
		return null;
	}

	public void spawnMinis() {
		for (MasterSquirrel ms : masterSquirrels)
			if (ms.getSpawmMini() > 0) {
				XY pos = ms.getSpawnMiniPos();
				if (pos == null)
					pos = getRandomFreeNeighbourCellDirection(ms.getPosition());
				board.spawnMini(pos.plus(ms.getPosition()), ms);
			}
		
	}

	public MasterSquirrel getHandOperatedMasterSquirrel() {
		return masterSquirrels.get(0);
	}


}
