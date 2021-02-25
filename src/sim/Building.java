package sim;

import java.util.ArrayList;
import java.util.Random;

import users.User;

public class Building {

	private ArrayList<Floor> floorArray = new ArrayList<>(); // Array list of type Floor
	private Elevator elevator;
	public int numFloors; // Number Of Floors

	/**
	 * Constructor of the Building Class
	 * 
	 * @param numFloors        Number of floors in Building
	 * @param elevatorCapacity Capacity of the Elevator
	 */
	public Building(int numFloors, int elevatorCapacity) {

		this.numFloors = numFloors;
		this.elevator = new Elevator(this, elevatorCapacity);

		int i = 0;
		while (i < numFloors) {
			floorArray.add(new Floor(this));
			i++;
		}

	}

	/**
	 * Generates random Queues of people for ground floor
	 * 
	 * @param flag Set Type of Randomisation
	 */
	void generateRandom(int generate) {
		if (generate == 1) {
			double num = Math.random() * 0.99;

			if (num > 0.5) {
				floorArray.forEach(Floor::randomize);
			}
		}
	}

	/**
	 * Gets Elevator
	 * 
	 * @returns The Elevator
	 */
	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * Get specific Floor
	 * 
	 * @param numFloor The Floor Number that is desired
	 * @return The Floor of they Floor
	 */
	public Floor getFloor(int numFloor) {
		return floorArray.get(numFloor);
	}

	/**
	 * Enter Building Method
	 * 
	 * @param usr     User Entering the Building
	 * @param tickNum The tick Number that they enter on.
	 */
	public void enter(User usr, int tickNum) {
		getFloor(0).goingUp.add(usr);
		usr.waitForElevator(tickNum);

	}
}
