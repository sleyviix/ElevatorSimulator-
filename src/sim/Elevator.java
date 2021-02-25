package sim;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import users.User;

public class Elevator {

    private boolean doorsOpen = false; // Checking if the doors are Open Or not
    private static int capacity; // The capacity of the Elevator
    private int peopleWithinElevator; // The Number Of people IN the Elevator
    private int currentFloor; // The Floor the Elevator is on
    private static final int UP = 1; // Puts direction of the Elevator to an int
    private static final int DOWN = 2; // Puts direction of the Elevator to an int
    private int direction = DOWN;
    private List<User> inElevator = new ArrayList<>(); // Arraylist of people in Elevator
    private boolean hasNotLeftFloorYet = false;
    Building building;

    private boolean keepDoorsOpen = false; // Do the doors need to be kept open next tick

    /**
     * Constructor of the elevator
     *
     * @param building The current Building the Elevator is in
     * @param capacity The capacity of the Elevator
     */
    public Elevator(Building building, int capacity) {
        this.building = building;
        this.capacity = capacity;

    }

    /**
     * Change the direction of the elevator if the Elevator Needs to change the
     * direction
     *
     * @param dir The current Direction of the Elevator
     * @return The New Direction
     */
    private int changeDirection(int dir) {
        for (int i = (isGoingDown() ? 0 : building.numFloors - 1); isGoingDown() ? i < currentFloor
                : i > currentFloor; i += isGoingDown() ? 1 : -1) {
            if (building.getFloor(i).goingUp.size() > 0 || building.getFloor(i).goingDown.size() > 0) {
                ding();
                return dir;
            }
        }
        if (building.getFloor(currentFloor).getCurrentQueue(dir).size() > 0) {
            ding();
            return dir;
        }
        for (User user : inElevator) {

            if (isGoingUp() ? user.getTargetFloor() > currentFloor : user.getTargetFloor() < currentFloor) {
                ding();
                return dir;
            }
        }

        for (int i = (isGoingUp() ? 0 : building.numFloors - 1); isGoingUp() ? i < currentFloor
                : i > currentFloor; i += isGoingUp() ? 1 : -1) {
            if (building.getFloor(i).goingUp.size() > 0 || building.getFloor(i).goingDown.size() > 0) {
                ding();
                return flipDirection(dir);
            }
        }
        if (building.getFloor(currentFloor).getCurrentQueue(flipDirection(dir)).size() > 0) {
           // ding();
            return flipDirection(dir);
        }
        System.out.println("Direction: " + dir + "\tContent: "
                + java.util.Arrays.toString(building.getFloor(currentFloor).getCurrentQueue(dir).toArray()));
        ding();
        return DOWN;
    }

    /**
     * Flips the direction no questions asked
     *
     * @param dir Direction of The elevator currently
     * @return The new Direction, This will be opposite the input
     */
    private int flipDirection(int dir) {
        if (dir == DOWN) {
            return UP;
        } else {
            return DOWN;
        }
    }

    /**
     * Checks If the Elevator is going UP
     *
     * @return True if the Elevator is going up else if returns false
     */
    public boolean isGoingUp() {
        return direction == UP;
    }

    /**
     * Checks If the Elevator is going Down
     *
     * @return True if the Elevator is going Down else if returns false
     */
    public boolean isGoingDown() {
        return direction == DOWN;
    }

    /**
     * Checks if the Elevator is full
     *
     * @return True if the People in the elevator is equal to the capacity of
     * the elevator. else returns false.
     */
    public boolean isFull() {
        return capacity == peopleWithinElevator;
    }

    /**
     * Checks to see the number of people in the elevator
     *
     * @return the number of people in the elevator Int
     */
    public int getPeopleWithinElevator() {
        return peopleWithinElevator;
    }

    /**
     * Get the floor that the Elevator is currently at
     *
     * @return The current floor in integer form
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Get whether the elevator doors are currently open
     *
     * @return
     */
    public boolean getDoorsOpen() {
        return doorsOpen;
    }

    /**
     * Moves the elevator up or down depending on if the elevator is already
     * going up or down.
     */
    public void move() {
        hasNotLeftFloorYet = false;
        if (isGoingUp()) {
            currentFloor++;

        } else if (isGoingDown() && currentFloor > 0) {
            currentFloor--;
        }

    }

    /**
     * Moves passangers from current queue of the floor to the elevator. Checks
     * if the elevator has capcity
     */
    private void letPassengerEnter() {
        System.out.println("lets Aboard passangers  " + currentFloor);
        LinkedList<User> currentQueue = building.getFloor(currentFloor).getCurrentQueue(direction);
        User user;
        keepDoorsOpen = false;
        for (int i = 0; i < currentQueue.size(); i++) {
            user = currentQueue.get(i);

            if (user.getSpaceTakenUp() <= (capacity - peopleWithinElevator)) {
                keepDoorsOpen = true;
                inElevator.add(user);
                peopleWithinElevator += user.getSpaceTakenUp();
                user.boardElevator();
                currentQueue.remove(i--);
            }
        }

    }

    /**
     * Adds users in the elevator to a new array list
     *
     * @return The arrylist of the passangers on the elevator
     */
    private ArrayList<User> getReadyToReleasePassengers() {
        ArrayList<User> passengers = new ArrayList<>();
        for (User user : inElevator) {
            if (user.getTargetFloor() == currentFloor) {
                passengers.add(user);
            }
        }
        return passengers;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Floor" + currentFloor + ":" + getSize();
    }

    /**
     * Gets the size of the people in the elevator
     *
     * @return int number of the people in the elevator
     */
    public int getSize() {
        return inElevator.size();
    }

    /**
     * Gets and array list of the users in the elevator
     *
     * @return An array list of all the people in the Elevator
     */
    public List<User> getUsersInElevator() {
        return inElevator;
    }

    /**
     * Ticks through the entire elevator moment, Will be done every tick for the
     * elevator to function.
     *
     * @param tickNum The tick number that the Elevator is on
     */
    public void tick(int tickNum) {
        ArrayList<User> readyToRelease = getReadyToReleasePassengers();
        direction = changeDirection(direction);
        if (doorsOpen) { // if the doors are open
            if (readyToRelease.size() > 0) { // does anybody wish to alight
                for (User user : readyToRelease) {
                    System.out.println("lets Passsangers off on floor:  " + currentFloor);
                    if (currentFloor > 0) {
                        building.getFloor(currentFloor).enterFloor(user);
                    }
                    user.arrive(tickNum);
                    peopleWithinElevator -= user.getSpaceTakenUp();
                    inElevator.remove(user);

                }
                letPassengerEnter();
                keepDoorsOpen = true;
            } else {
                if (keepDoorsOpen) {
                    letPassengerEnter();
                } else {
                    doorsOpen = false;
                    hasNotLeftFloorYet = true;
                }
            }
        } else {
            if ( !hasNotLeftFloorYet && ( getReadyToReleasePassengers().size() > 0 || (building.getFloor(currentFloor).getCurrentQueue(direction).size() > 0 && building.getFloor(currentFloor).getFirstPassenger(direction).getSpaceTakenUp() <= (capacity - peopleWithinElevator)))) {
                doorsOpen = true;
                keepDoorsOpen = true;
            } else {
                move();
                if (getReadyToReleasePassengers().size() > 0 || (building.getFloor(currentFloor).getCurrentQueue(direction).size() > 0 && building.getFloor(currentFloor).getFirstPassenger(direction).getSpaceTakenUp() <= (capacity - peopleWithinElevator))) {
                    doorsOpen = true;
                    keepDoorsOpen = true;
                }
            }
        }
    }

    /**
     * A testing method for locating when a certain line has been triggered.
     */
    public static void ding() {
        StackTraceElement ding = new Throwable().getStackTrace()[1];
        System.out.println("\u001B[35mDing!\u001B[0m at " + String.format("%s.%s(%s:%d)", ding.getClassName(),
                ding.getMethodName(), ding.getFileName(), ding.getLineNumber()));
    }

}
