package de.hsa.games.fatsquirrel.core;

import java.util.Random;

public class XY {
	
	public final int x;
	public final int y;
	
	public XY (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public XY (XY position) {
		this.x = position.x;
		this.y = position.y;
	}
	
	public XY move(XY vector) {
		int x = this.x + vector.x;
		int y = this.y + vector.y;
		
		return new XY(x, y);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof XY) {
			XY position = (XY) o;
			if (x == position.x && y == position.y)
				return true;
		}		
		
		return false;
	}
	
	public static XY randomVector() {
		Random random = new Random();
		int x = random.nextInt(3) - 1;
		int y = random.nextInt(3) - 1;
		
		return new XY(x,y);
	}
	
	public static XY randomXY(int minX, int maxX, int minY, int maxY) {
		Random random = new Random();
		int x = random.nextInt(maxX) + minX;
		int y = random.nextInt(maxY) - minY;
		
		return new XY(x,y);
	}
	
	public static int distanceToEntity(XY from, XY to) {
		
		int xDist = to.x - from.x;
		int yDist = to.y - from.y;
		
		int diagMoves = Math.min(xDist, yDist);
		
		xDist -= diagMoves;
		yDist -= diagMoves;
		
		int horizMoves = Math.max(xDist, yDist);
		
		return diagMoves + horizMoves;
	}
	
	public static XY vectorToEntity(Entity fromEntity, Entity toEntity) {
		XY fromPos = fromEntity.getPosition();
		XY toPos = toEntity.getPosition();
		
		double xDiff = toPos.x - fromPos.x;
		double yDiff = toPos.y - fromPos.y;
		
		double maxDiff = Math.max(Math.abs(xDiff), (Math.abs(yDiff)));
		
		xDiff /= maxDiff;
		yDiff /= maxDiff;
		
		
		XY vector = new XY((int) Math.round(xDiff), (int) Math.round(yDiff));
		
		return vector;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
