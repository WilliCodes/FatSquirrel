package de.hsa.games.fatsquirrel.core;

import java.util.Random;


public class XY {
    public final int x;
    public final int y;



    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XY(int x, int y) {
    	this.x = x;
		this.y = y;
    }

    public XY plus(XY xy) {
    	System.out.println(xy.toString() + " " + this.toString());
    	int x = xy.x + this.x;
    	int y = xy.y + this.y;
    	return new XY(x, y);
    }

    public XY minus(XY xy) {
    	int x = xy.x - this.x;
    	int y = xy.y - this.y;
    	return new XY(x, y);
    }

    public XY times(int factor) {
    	return new XY(this.x * factor, this.y * factor);
    }

    public double length() {
    	return Math.sqrt(this.x * this.x + this.y *this.y);
    }

    /**
     * 
     * @param xy a second coordinate pair
     * @return the euklidian distance (pythagoras)
     */
    public double distanceFrom(XY xy) {
    	int xDist = xy.x - this.x;
		int yDist = xy.y - this.y;
		
		return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    public int hashCode() {
    	return this.hashCode();
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
