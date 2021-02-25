package sim;


import users.User;

/**
 * The Simulation class prints the various components of the text based user
 * interface of the code
 */

public class Simulation {

	private final Building BUILDING;
	private double time;

	/**
	 * Constructor for simulation
	 * 
	 * @param BUILDING
	 * @param time
	 */
	public Simulation(Building BUILDING, double time) {

		this.BUILDING = BUILDING;
		this.time = time;
	}

	private void waiting(double time) {

		int i = 0;
		while (i < time) {

			System.out.print("-------------------------");
			i++;
		}

	}

	/**
	 * Prints information about the floor that the elevator has reached
	 */
	public void printFloor() {
		int floor = BUILDING.getElevator().getCurrentFloor();
		
		displayWaitingTime(time, floor);
		System.out.println();
		printElevator(BUILDING.getElevator());
		
		isFullException();
		
		printQueueGoingUp(floor);
		printQueueGoingDown(floor);
		printPeopleNotWaiting(floor);
		
	}

	/**
	 * Prints Information about the queue going down
	 * 
	 * @param floor The floor of the going down queue
	 */
	private void printQueueGoingDown(int floor) {

		int size = BUILDING.getFloor(floor).getDownSize();
		System.out.println(" - IN QUEUE TO GO DOWN: " + size);

		System.out.println();
		System.out.println();
	}

	/**
	 * Prints Information about the queue going down
	 * 
	 * @param floor The floor of the going down queue
	 */
	private void printQueueGoingUp(int floor) {

		int size = BUILDING.getFloor(floor).getUpSize();
		System.out.println("\n - IN QUEUE TO GO UP: " + size);

		System.out.println();
		System.out.println();
	}

	public void printPeopleNotWaiting(int floor) {
		int size = BUILDING.getFloor(floor).peopleNotWaiting.size();
		System.out.println(" - Number of people not in the elevator Queue on floor " + floor + "  :  "  + size);

	}

	/**
	 * Prints information about the elevator
	 * 
	 * @param elevator The elevator that information is being called from
	 */
	private void printElevator(Elevator elevator) {
		int size = elevator.getUsersInElevator().size();
		System.out.println("Passesngers in elevator: " + size);
		elevator.getUsersInElevator().stream().map(User::toString).forEach(System.out::print);
		System.out.println();
		

	}
	
	public void isFullException() {
		if(BUILDING.getElevator().isFull()) {
			System.out.println("\n - Elevator is Full!");
		}
	}

	/**
	 * Displays the elevator with information such as the floor it is going too and
	 * where it currently is
	 * 
	 * @param time
	 * @param floor The floor that the elevator is travelling too.
	 */
	private void displayWaitingTime(double time, int floor) {
		System.out.println("\n-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-");
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-");
		System.out.println("-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-");
		System.out.println("\nElevator is moving to FLOOR " + floor + "...");
		waiting(time);
		System.out.println("\nUnloading/boarding passengers in FLOOR " + floor + "...");
		waiting(time);
	}

}