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
			break;
		case BadBeast:
			masterSquirrel.updateEnergy(target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				move(masterSquirrel, from, to);
			}
			break;
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
			masterSquirrel.updateEnergy(target.getEnergy());
			killAndReplace(target);
			move(masterSquirrel, from, to);
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
			break;
		case MiniSquirrel:
			if (masterSquirrel.isMyMini((MiniSquirrel) target))
				masterSquirrel.updateEnergy(target.getEnergy());
			else 
				masterSquirrel.updateEnergy(150);
			kill(target);
			move(masterSquirrel, from, to);
			break;
		case Wall:
			masterSquirrel.updateEnergy(target.getEnergy());
			masterSquirrel.setParalyzed();
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
			break;
		case BadBeast:
			miniSquirrel.updateEnergy(target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				killAndReplace(target);
				move(miniSquirrel, from, to);
			}
			break;
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
			miniSquirrel.updateEnergy(target.getEnergy());
			killAndReplace(target);
			move(miniSquirrel, from, to);
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
			if (miniSquirrel.getMasterID() == target.getId()) {
				target.updateEnergy(miniSquirrel.getEnergy());
			} else {
				target.updateEnergy(150);
			}
			kill(miniSquirrel);
			break;
		case MiniSquirrel:
			if (miniSquirrel.getMasterID() != ((MiniSquirrel) target).getMasterID()) {
				kill(miniSquirrel);
				kill(target);
			}
			break;
		case Wall:
			miniSquirrel.updateEnergy(target.getEnergy());
			miniSquirrel.setParalyzed();
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
			break;
		case BadBeast:
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
		case Wall:
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
		case MiniSquirrel:
			target.updateEnergy(goodBeast.getEnergy());
			killAndReplace(goodBeast);
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
			break;
		case BadBeast:
		case BadPlant:
		case GoodBeast:
		case GoodPlant:
		case Wall:
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
		case MiniSquirrel:
			target.updateEnergy(badBeast.getEnergy());
			if (badBeast.nextBite()) {
				killAndReplace(badBeast);
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
