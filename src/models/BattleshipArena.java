package models;

public class BattleshipArena {

	private char[][] xySpace;
	private Ship[] shipList;
	private static int shipcounter = 0;
	private int xlength;
	private int ylength;

	// TODO: initialize instance variables in the constructor
	public BattleshipArena(int xlength, int ylength) {
		this.xlength = xlength;
		this.ylength = ylength;
		this.shipList = new Ship[10];
		xySpace = new char[xlength][ylength];
		initXYspace(xySpace, '.');
	}

	public void drawArena() {
		for (int j = 0; j < ylength; j++)
			System.out.print("- ");
		System.out.println("-> y axis columns");
		for (int i = 0; i < xlength; i++) {
			System.out.print("|");
			for (int j = 0; j < ylength; j++) {
				System.out.print(xySpace[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("v");
		System.out.println("x axis rows");
	}

	// TODO: implement xyspace initializer
	private void initXYspace(char[][] xySpace, char pattern) {
		for (int i = 0; i < xySpace.length; i++) {
			for (int j = 0; j < xySpace[i].length; j++) {
				xySpace[i][j] = pattern;
			}
		}
	}

	// TODO: implement addShip method
	public boolean addShip(Ship ship, Location location) {
		boolean ret = true;
		int locX = location.getxCoordinate();
		int locY = location.getyCoordinate();

		for (int i = locX; i < getXlength(); i++) {
			for (int j = locY; j < getYlength(); j++) {
				if (xySpace[i][j] == 'S') {
					ret = false;
					break;
				}
			}
		}

		if (shipcounter >= 10) {
			System.err.println("There is no available space for another ship.");
			return false;
		} else if (isLocationsValid(location) == false) {
			System.err.println("Location is not in the arena.");
			return false;
		} else if ((ship.getWidth() + locX) >= xlength || (ship.getHeight() + locY) >= ylength) {
			System.err.println("Ship exceeds the boundries of the arena.");
			return false;
		} else if (ret == false) {
			System.err.println("Location overlap with another ship.");
			return false;
		}
		ship.setLocation(location);
		for (int i = locX; i < (ship.getWidth() + locX); i++) {
			for (int j = locY; j < (ship.getHeight() + locY); j++) {
				xySpace[i][j] = 'S';
			}
		}
		for (int count = 0; count < shipList.length; count++) {
			if (shipList[count] == null) {
				shipList[count] = ship;
				shipcounter++;
				return true;
			}
		}
		return true;
	}

	public int getXlength() {
		return xlength;
	}

	public int getYlength() {
		return ylength;
	}

	// TODO: implement attack method
	public void attack(Location location) {
		int locX = location.getxCoordinate();
		int locY = location.getyCoordinate();

		if (isLocationsValid(location)) {
			if (isHit(location) != null) {
				Ship hittedShip = isHit(location);
				xySpace[locX][locY] = 'X';
				hittedShip.reduceHealth(calculateHitDemage(hittedShip));
				System.out.printf("Hit!! The ship has %.1f percent health left! Is ship alive: %b%n",
						hittedShip.getHealth(), hittedShip.isAlive());
				this.drawArena();
			}
		}
	}

	// TODO: print status of the ships by calling their toString method.
	public void showShipInfo() {
		for (Ship ship : shipList) {
			if (ship != null) {
				System.out.println(ship.toString());
			}
		}
	}

	private double calculateHitDemage(Ship ship) {
		return (100.0 / (ship.getWidth() * ship.getHeight()));
	}

	// TODO: check if there's a ship on the given location
	private Ship isHit(Location location) {
		int locX = location.getxCoordinate();
		int locY = location.getyCoordinate();

		if (xySpace[locX][locY] == 'S') {
			if (shipList != null) {
				for (Ship ship : shipList) {
					if (ship != null) {
						if (isLocationHitsTheShip(ship, location)) {
							return ship;
						}
					}
				}
			}
		}
		return null;
	}

	// TODO: check if the location hits the given ship
	private boolean isLocationHitsTheShip(Ship ship, Location location) {
		int shipWidth = ship.getWidth();
		int shipHeight = ship.getHeight();
		Location shipLoc = ship.getLocation();
		int shipX = shipLoc.getxCoordinate();
		int shipY = shipLoc.getyCoordinate();

		if (shipX <= location.getxCoordinate() && location.getxCoordinate() < (shipX + shipWidth)
				&& shipY <= location.getyCoordinate() && location.getyCoordinate() < (shipY + shipHeight)) {
			return true;
		}
		return false;
	}

	// TODO: check if the given location exceeds our simulation space
	public boolean isLocationsValid(Location location) {
		int x = location.getxCoordinate();
		int y = location.getyCoordinate();
		if (x < 0 || x > this.getYlength() || y < 0 || y > this.getYlength()) {
			return false;
		}
		return true;
	}

	// TODO: return alive ship count
	public long getAliveShipCount() {
		shipcounter = 0;
		for (Ship ship : shipList) {
			if (ship != null) {
				if (ship.isAlive()) {
					shipcounter++;
				}
			}
		}
		return shipcounter;
	}

}
