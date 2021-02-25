package users;

import java.util.*;

/**
 * The default case of a user.
 */
public abstract class User {

    private int targetFloor;
    private int currentFloor;
    protected final int TOP_FLOOR;
    private boolean waiting;
    private final Random RND;
    
    /**
     * The space taken up within an elevator by this user
     */
    private final int SPACE_TAKEN_UP;

    /**
     * Creates a new user, taking up a given amount of space
     * @param spaceTakenUp the amount of space this user takes up within the elevator
     * @param rnd the {@link java.util.Random Random} object which will be used to randomise all elements of this user
     * @param TOP_FLOOR The value of the top floor of the building
     */
    public User(int spaceTakenUp, Random rnd, final int TOP_FLOOR) {
        this.RND = rnd;
        this.SPACE_TAKEN_UP = spaceTakenUp;
        this.TOP_FLOOR = TOP_FLOOR;
        randomDestignation();
    }
    
    /**
     * Gets the random number generator associated with this user
     * @return the {@link java.util.Random java.util.Random} object associated with this user
     */
    public Random getRNG() { 
        return RND;
    }
    
    /**
     * Gets the target floor that the user is travelling too
     * @return The target floor of the current user
     */
    public int getTargetFloor() {
        return targetFloor;
    }
    /**
     * Sets the target floor that user will attempt to go to
     * @param newTargetFloor The floor the user is attempting to go to
     */
    public void setTargetFloor(int newTargetFloor ) {
        targetFloor = newTargetFloor;
    }
    /**
     * Get the floor that the user is currently on
     * @return And int value denouting the current floor.
     */
    public int getCurrentFloor() {
        return currentFloor;
    }
    /**
     * Sets the floor the user is at
     * @param newFloor The new floor that user is at
     */
    public void setCurrentFloor(int newFloor) {
        currentFloor = newFloor;
    }
    /**
     * Gets the amount of space the user takes up
     * @return an int value that is either 1 or 4 depending on the amount of elevator spaces they take
     */
    public int getSpaceTakenUp() {
        return SPACE_TAKEN_UP;
    }
    /**
     * checks if the user is waiting for the elevator 
     * @return True if the user is in a queue
     */
    public boolean isWaiting() {
        return waiting;
    }

    /**
     * Notifies this user that they are waiting for the elevator on the given tick
     * @param tickNum the tick on which the user begins waiting for the elevator
     */
    public void waitForElevator( int tickNum ) {
        waiting = true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format( "ID %s C:%d T:%d, ", getClass().getSimpleName() , currentFloor, targetFloor );
    }
    
    /**
     * Called when this user arrives at their target floor
     * @param tickNum the tick on which this user arrived on their target floor
     */
    public void arrive( int tickNum ) {
        currentFloor = targetFloor;
    }

    /**
     * Called when this user boards the elevator
     */
    public void boardElevator() {
        currentFloor = -1;
        waiting = false;
    }
    /**
     * 
     * @return 0?
     */
    public int setProbability() {
        Random rand = RND;
        int rand_int1 = rand.nextInt(1);
        return rand_int1;
    }

    /**
     * Assigns a random destination to this user
     * @return the randomly generated floor to which this user would like to go next
     */
    public int randomDestignation() {
        Random rand = RND;
        int rand_int1 = rand.nextInt(TOP_FLOOR);
        return rand_int1;

    }
   /**
    * Generates the random user of type employee or developer 
    * @return A new Employee or Developer
    */
    public static User generateRandomUser(Random rng, final int TOP_FLOOR) {
        int rnd = (int) Math.floor(rng.nextDouble() * 2);
        switch (rnd) {
            case 0:
                return new Developer( rng, TOP_FLOOR );
            default:
                return new Employee( rng, TOP_FLOOR );
        }
    }

    /**
     * Causes this user to advance by one tick.
     * @param tickNum The current tick of the simulation
     * @return 
     */
    public abstract int tick(int tickNum);

}
