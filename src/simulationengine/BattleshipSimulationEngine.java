package simulationengine;

import java.util.Scanner;

import models.BattleshipArena;
import models.Location;
import models.Ship;
import java.security.SecureRandom;

public class BattleshipSimulationEngine {

	private boolean status;
	private BattleshipArena arena;
	SecureRandom random = new SecureRandom();

	/*
	 * As you know "this" keywod corresponds to the current object. Since we can
	 * return any object type from a method we can also return the current object
	 * itself. returning the reference of the current object allows us to chain
	 * functions back to back
	 * 
	 * here we can do the following
	 * 
	 * 
	 * new BattleshipSimulationEngine().init().start();
	 * 
	 * new BattleshipSimulationEngine() -> creates an instance of the object return
	 * its reference init() -> performs some operations and returns the same
	 * reference.
	 * 
	 * 
	 * if you catch the reference, you can access its methods like following.
	 * BattleshipSimulationEngine engine = new BattleshipSimulationEngine();
	 * engine.start()
	 * 
	 * but you can do that without catching the reference, you can do it directly
	 * like new BattleshipSimulationEngine().start(); but after that you lose the
	 * reference since start returns void.
	 * 
	 * however init() function returns its reference again, we don't loose the
	 * reference after calling it. so new
	 * BattleshipSimulationEngine().init().start(); is possible.
	 * 
	 * 
	 */
	// TODO: init enviroment add one ship
	public BattleshipSimulationEngine init() {
		int numberOfShips = random.nextInt(5) + 1;
		Ship[] shipList = new Ship[numberOfShips];

		status = true;
		arena = new BattleshipArena(30, 30);


		for (int i = 0; i < numberOfShips; i++) {
			int xLocation = random.nextInt(arena.getXlength() + 1);
			int yLocation = random.nextInt(arena.getYlength() + 1);
			int width = random.nextInt(5) + 1;
			int height = random.nextInt(5) + 1;

			Location location = new Location(xLocation, yLocation);
			Ship shipObj = new Ship(String.format("Ship %d", i), width, height);
			arena.addShip(shipObj, location);
		}
		arena.drawArena();
		return this;
	}

	public void start() {
		while (this.getStatus()) {
			this.update();
		}
	}

	// TODO: implement this method
	public BattleshipSimulationEngine update() {

		arena.getAliveShipCount();
		
		Scanner input = new Scanner(System.in);

		int xLoc = random.nextInt(30);
	    int yLoc = random.nextInt(30);
		Location attackLocation = new Location(xLoc, yLoc);
		arena.attack(attackLocation);

		if (arena.getAliveShipCount() == 0) {
			setStatus(false);
			arena.drawArena();
			System.out.println("Simulation is now ended! All ships are sunk!!");
			arena.showShipInfo();
		}

		return this;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
