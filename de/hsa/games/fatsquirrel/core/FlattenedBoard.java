package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;
import java.util.logging.Logger;


public class FlattenedBoard implements BoardView, EntityContext {
	
	private Entity[][] cells;
	private ArrayList<EntityType> toRespawn = new ArrayList<>();
	private Board board;
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public FlattenedBoard(Board board) {
		this.board = board;
		cells = this.board.flatten();
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
		case Empty:
			move(masterSquirrel, from, to);
			logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case BadBeast:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " got bitten by " + target.toString() + " and lost Energy: " + target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				logger.finer(target.toString() + " was deleted after nextBite() = " + ((BadBeast)target).nextBite());
				move(masterSquirrel, from, to);
				logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			}
			break;
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " ate " + target.toString() + " and changed energy of: " + target.getEnergy());
			killAndReplace(target);
			logger.finer(target.toString() + " was deleted after " + masterSquirrel.toString() + " ate it");
			move(masterSquirrel, from, to);
			logger.finest(masterSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
			logger.finer(masterSquirrel.toString() + " can't move because " + target.toString() + " is in the way");
			break;
		case MiniSquirrel:
			if (masterSquirrel.isMyMini((MiniSquirrel) target)) {
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
		case Wall:
			masterSquirrel.updateEnergy(target.getEnergy());
			logger.finer(masterSquirrel.toString() + " collided with " + target.toString() + " and lost Energy of: " + target.getEnergy());
			masterSquirrel.setParalyzed();
			logger.finer(masterSquirrel.toString() + " is paralyzed");
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
		case Empty:
			move(miniSquirrel, from, to);
			logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case BadBeast:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " was bitten by " + target.toString() + " and lost Energy: " + target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				logger.finer(target.toString() + " was deleted after nextBite() = " + ((BadBeast)target).nextBite());
				move(miniSquirrel, from, to);
				logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			}
			break;
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " ate " + target.toString() + " and changed energy of: " + target.getEnergy());
			killAndReplace(target);
			logger.finer(target.toString() + " was deleted after " + miniSquirrel.toString() + " ate it");
			move(miniSquirrel, from, to);
			logger.finest(miniSquirrel.toString() + " moved from " + from + " to " + to);
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
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
		case MiniSquirrel:
			logger.finer(miniSquirrel.toString() + " couldn´t move because " + target.toString() + " was in the way");
			if (miniSquirrel.getMasterID() != ((MiniSquirrel) target).getMasterID()) {
				kill(miniSquirrel);
				logger.finer(miniSquirrel.toString() + " was deleted after the colission with " + target.toString());
				kill(target);
				logger.finer(target.toString() + " was deleted after the colission with " + miniSquirrel.toString());
			}
			break;
		case Wall:
			miniSquirrel.updateEnergy(target.getEnergy());
			logger.finer(miniSquirrel.toString() + " collided with " + target.toString() + " and lost Energy of: " + target.getEnergy());
			miniSquirrel.setParalyzed();
			logger.finer(miniSquirrel.toString() + " is paralyzed");
			break;
		}
		
		
	}

	@Override
	public void tryMove(GoodBeast goodBeast, XY moveDirection) {
		XY from = goodBeast.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		
		switch (getEntityType(to)) {
		case Empty:
			move(goodBeast, from, to);
			logger.finest(goodBeast.toString() + " moved from " + from + " to " + to);
			break;
		case BadBeast:
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
		case Wall:
			logger.finer(goodBeast.toString() + "couldn´t move because " + target.toString() + " was in the way");
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
		case MiniSquirrel:
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
		case Empty:
			move(badBeast, from, to);
			logger.finest(badBeast.toString() + " moved from " + from + " to " + to);
			break;
		case BadBeast:
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
		case Wall:
			logger.finer(badBeast.toString() + " couldn´t move because " + target.toString() + " was in the way");
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
		case MiniSquirrel:
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
				case BadBeast:
					line += " b ";
					break;
				case GoodBeast:
					line += " B ";
					break;
				case BadPlant:
					line += " p ";
					break;
				case GoodPlant:
					line += " P ";
					break;
				case Wall:
					line += " X ";
					break;
				case MasterSquirrel:
				case HandOperatedMasterSquirrel:
					line += " M ";
					break;
				case MiniSquirrel:
					line += " m ";
					break;
				case Empty:
					line += "   ";
				}
			}
			view += line + "\n";
		}
		
		return view;
		
	}

	public XY getNextFreeCell(XY pos) {
		for (int x = pos.x - 1; x < pos.x + 1; x++) {
			for (int y = pos.y - 1; y < pos.x + 1; y++) {
				if ((x == pos.x && y == pos.y) || x < 0 || y < 0 || x > cells.length || y > cells[0].length || cells[x][y] != null)
					continue;
				return new XY(x,y);
			}
		}
		return null;
	}

	@Override
	public int getMasterSquirrelEnergy() {
		return board.getHandOperatedMasterSquirrel().getEnergy();
	}


}
