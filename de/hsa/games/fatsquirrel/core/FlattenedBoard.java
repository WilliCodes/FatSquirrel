package de.hsa.games.fatsquirrel.core;

import java.util.ArrayList;

import de.hsa.games.fatsquirrel.Launcher;

public class FlattenedBoard implements BoardView, EntityContext {
	
	private Entity[][] cells;
	private ArrayList<EntityType> toRespawn;
	private Board board;
	
	public FlattenedBoard(Board board) {
		this.board = board;
		cells = this.board.flatten();
	}
	
	public void update() {
		cells = board.flatten();
	}
	
	public void resetRespawnList() {
		toRespawn = new ArrayList<>();
	}
	
	public ArrayList<EntityType> getRespawnList() {
		return toRespawn;
	}

	@Override
	public EntityType getEntityType(int x, int y) {
		return null;
	}

	@Override
	public XY getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
		XY from = masterSquirrel.getPosition();
		XY to = new XY(from.x + moveDirection.x, from.y + moveDirection.y);
		
		Entity target = cells[to.x][to.y]; 
		
		switch (getEntityType(to)) {
		case Empty:
			masterSquirrel.setPosition(to);
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = masterSquirrel;
			break;
		case BadBeast:
			masterSquirrel.updateEnergy(target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				masterSquirrel.setPosition(to);
				cells[from.x][from.y] = null;
				cells[to.x][to.y] = masterSquirrel;
				toRespawn.add(EntityType.BadBeast);
				target.deactivate();
			}
			break;
		case BadPlant:
			toRespawn.add(EntityType.BadPlant);
			masterSquirrel.setPosition(to);
			masterSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = masterSquirrel;
			target.deactivate();
			break;
		case GoodBeast:
			toRespawn.add(EntityType.GoodBeast);
			masterSquirrel.setPosition(to);
			masterSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = masterSquirrel;
			target.deactivate();
			break;
		case GoodPlant:
			toRespawn.add(EntityType.GoodPlant);
			masterSquirrel.setPosition(to);
			masterSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = masterSquirrel;
			target.deactivate();
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
			break;
		case MiniSquirrel:
			if (masterSquirrel.isMyMini((MiniSquirrel) target))
				masterSquirrel.updateEnergy(target.getEnergy());
			else 
				masterSquirrel.updateEnergy(150);
				
			masterSquirrel.setPosition(to);
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = masterSquirrel;
			target.deactivate();
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
		
		switch (getEntityType(to)) {
		case Empty:
			miniSquirrel.setPosition(to);
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = miniSquirrel;
			break;
		case BadBeast:
			miniSquirrel.updateEnergy(target.getEnergy());
			if (((BadBeast) target).nextBite()) {
				miniSquirrel.setPosition(to);
				cells[from.x][from.y] = null;
				cells[to.x][to.y] = miniSquirrel;
				toRespawn.add(EntityType.BadBeast);
				target.deactivate();
			}
			break;
		case BadPlant:
			toRespawn.add(EntityType.BadPlant);
			miniSquirrel.setPosition(to);
			miniSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = miniSquirrel;
			target.deactivate();
			break;
		case GoodBeast:
			toRespawn.add(EntityType.GoodBeast);
			miniSquirrel.setPosition(to);
			miniSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = miniSquirrel;
			target.deactivate();
			break;
		case GoodPlant:
			toRespawn.add(EntityType.GoodPlant);
			miniSquirrel.setPosition(to);
			miniSquirrel.updateEnergy(target.getEnergy());
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = miniSquirrel;
			target.deactivate();
			break;
		case HandOperatedMasterSquirrel:
		case MasterSquirrel:
			if (miniSquirrel.getMasterID() == target.getId()) {
				target.updateEnergy(miniSquirrel.getEnergy());
			} else {
				target.updateEnergy(150);
			}
			miniSquirrel.deactivate();
			break;
		case MiniSquirrel:
			if (miniSquirrel.getMasterID() != ((MiniSquirrel) target).getMasterID()) {
				miniSquirrel.deactivate();
				target.deactivate();
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
		
		
		if (Launcher.printDebugInfo) {
			System.out.println("Moving GoodBeast from " + from + " to " + to + " with the vector " + moveDirection + ".");
			System.out.println("Destination Cell is: " + getEntityType(to).toString() + ".");
		}
		
		Entity target = cells[to.x][to.y]; 
		
		switch (getEntityType(to)) {
		case Empty:
			goodBeast.setPosition(to);
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = goodBeast;
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
			goodBeast.deactivate();
			toRespawn.add(EntityType.GoodBeast);
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
			badBeast.setPosition(to);
			cells[from.x][from.y] = null;
			cells[to.x][to.y] = badBeast;
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
				cells[from.x][from.y] = null;
				toRespawn.add(EntityType.BadBeast);
				badBeast.deactivate();
			}
		}
		
	}

	@Override
	public PlayerEntity nearestPlayerEntity(XY pos) {
		
		PlayerEntity nearestTmp = null;
		double distanceTmp = 0;
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null && cells[i][j] instanceof PlayerEntity && cells[i][j].getPosition() != pos) {
					if (nearestTmp == null) {
						distanceTmp = distanceToEntity(pos, i, j);
						if (distanceTmp <= 6)
							nearestTmp = (PlayerEntity) cells[i][j];
					}
					else
					{
						double n = distanceToEntity(pos, i, j);
						if (n < distanceTmp) {
							distanceTmp = n;
							nearestTmp = (PlayerEntity) cells[i][j];
						}
					}
				}
			}
		}
		return nearestTmp;
	}
	
	private double distanceToEntity(XY from, int xTo, int yTo) {
		return Math.sqrt(Math.pow((xTo - from.x),2) + Math.pow((yTo - from.y),2));
	}

	@Override
	public void kill(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killAndReplace(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityType getEntityType(XY pos) {
		
		if (cells[pos.x][pos.y] == null || !cells[pos.x][pos.y].isActive())
			return EntityType.Empty;
		
		String name = cells[pos.x][pos.y].getClass().getSimpleName();
		
		
		
		switch (name) {
		case "BadBeast":
			return EntityType.BadBeast;
		case "GoodBeast":
			return EntityType.GoodBeast;
		case "BadPlant":
			return EntityType.BadPlant;
		case "GoodPlant":
			return EntityType.GoodPlant;
		case "Wall":
			return EntityType.Wall;
		case "MasterSquirrel":
			return EntityType.MasterSquirrel;
		case "HandOperatedMasterSquirrel":
			return EntityType.HandOperatedMasterSquirrel;
		case "MiniSquirrel":
			return EntityType.MiniSquirrel;
		default:
			return null;
		}
	}
	
	@Override
	public String toString() {
		String view = "";
		for (int j = cells[0].length - 1; j >= 0; j--) {
			String line = "";
			for (int i = 0; i < cells.length; i++) {
				EntityType t = getEntityType(new XY(i, j));
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

}
