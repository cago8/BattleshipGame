package models;

public class Ship {
	private String name;
	private double health;
	private Location location;
	private boolean isAlive;
	private int xWidth;
	private int yWidth;

	public Ship(String name, int xWidth, int yWidth) {
		this.name = name;
		this.xWidth = xWidth;
		this.yWidth = yWidth;
		this.isAlive = true;
		this.health = 100;
	}

	public void reduceHealth(double damageScore) {
		setHealth(damageScore);
		if (health < 1) {
			setAlive(false);
		}
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setHealth(double health) {
		this.health -= health;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getName() {
		return name;
	}

	public double getHealth() {
		return health;
	}

	public int getWidth() {
		return xWidth;
	}

	public int getHeight() {
		return yWidth;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public String toString() {
		return "Ship [name=" + name + ", isAlive=" + isAlive + ", health=" + health + ", location=[" + location.getxCoordinate() + ", "
				+ location.getyCoordinate() + "]"
				+ ", width=" + xWidth + ", height=" + yWidth + "]";
	}

}
