package models;

public class Location {
	private final int xCoordinate;
	private final int yCoordinate;
	
	public Location(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}
	
	public String toString() {
		String returnThis = String.format("Location(x=%d,y%d",getxCoordinate(),getyCoordinate());
		return returnThis;
	}
}
