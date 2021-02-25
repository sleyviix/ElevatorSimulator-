package sim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import users.User;

public class Floor {

    private final Building BUILDING;
   
     
     
    public ArrayList<User> peopleNotWaiting = new ArrayList<>(); // People not waiting for the elevator on the floor
    public LinkedList<User> goingUp = new LinkedList<>(); //People Wanting to go up on the floor
    public LinkedList<User> goingDown = new LinkedList<>(); //People Wanting to go down on the floor
/**
 * Constructor for the Floor class
 * @param building Takes a Building
 */
    public Floor(Building building) {
        this.BUILDING = building;
    }
    /**
     * Gets the Queue going in the same direction as the elevator for the floor
     * @param direction Direction that the elevator is travelling
     * @return A linked list of the people in the correct Queue for the direction of the elevator
     */
    public LinkedList<User> getCurrentQueue(int direction) {
        return direction == 1 ? goingUp : goingDown;
    }
    /**
     *  Sees if the User should be added to the Up Queue or the Down Queue Then moves them
     * @param user User being moved into the Queue
     * @param targetFloor The Floor the User is going too
     * @param tickNum The tick number the user is begining to wait
     */
    public void waitForElevator(User user, int targetFloor, int tickNum) {

        user.setTargetFloor(targetFloor);
        user.waitForElevator(tickNum);
        if (targetFloor > user.getCurrentFloor()) {
            goingUp.add(user);
        } else if (targetFloor < user.getCurrentFloor()) {
            goingDown.add(user);
        }else {
        	return;
        }

        peopleNotWaiting.remove(user);
    }
    /**
     * Removes a user from the Up Queue Or the Down Queue
     * @param direction The direction of the elevator
     */ 
    public void decrement(int direction) {
        if (direction == 1) {
            goingUp.removeFirst();
        } else if (direction == 2) {
            goingDown.removeFirst();
        }
    }
    /**
     * Moves a user to the floor by adding them to the People Not Waiting Array
     * @param user The user being added to the Floor
     */
    public void enterFloor(User user) {
        peopleNotWaiting.add(user);
    }
    /**
     * Finds if the user is contained in the not Waiting Array of the Floor Floor 
     * @param usr  User to be checked
     * @return Boolean Of if the user is in the floor, but not waiting for lift
     */
    public boolean containedInFloorNotWaiting(User usr) {
        return peopleNotWaiting.contains(usr);
    }
    /**
     * gets the size of the queue going up for the floor
     * @return Int of the size of the going up Queue
     */
    public int getUpSize() {
        return goingUp.size();
    }
    /**
     * gets the size of the queue going Down for the floor
     * @return Int of the size of the going Down Queue
     */
    public int getDownSize() {
        return goingDown.size();
    }
    /**
     * Checks if the Up Queue is empty
     * @return Boolean of if the queue is empty
     */
    public boolean isUpEmpty() {
        return goingUp.isEmpty();
    }
    /**
     * Checks if the Down Queue is empty
     * @return Boolean of if the queue is empty
     */
    public boolean isDownEmpty() {
        return goingDown.isEmpty();
    }
    /**
     * Gets first Passenger that needs to be moved to the Elevator
     * @param direction of the Elevator
     * @return The user that needs to be moved to the elevator 
     */
    public User getFirstPassenger(int direction) {
        if (direction == 1) {
            return getUpSize() > 0 ? goingUp.getFirst() : null;
        } else { 
            return getDownSize() > 0 ? goingDown.getFirst() : null;
        }
    }
    /**
     * Randomises the time that the user will be spending on the floor
     */
    public void randomize() {
        for (Map.Entry<User, Integer> entry : readyToBoard().entrySet()) {
            this.waitForElevator(entry.getKey(), entry.getValue(), 0);
        }
    }
    /**
     * Declares if the user is ready to board the elevator, and if they need to call the elevator yet
     * @return The Queue that they user is going too
     */
    private Map<User, Integer> readyToBoard() {
        Map<User, Integer> queue = new HashMap<>();
        for (User usr : peopleNotWaiting) {
            queue.put(usr, usr.randomDestignation());
        }
        return queue;
    }
    /**
     * Gets all the users on the current floor 
     * @return Array list of all users on the floor.
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = null;
        System.gc();
        allUsers = new ArrayList<>();
        for ( User user : goingUp ) { 
            allUsers.add(user);
        }
        return allUsers;
    }
}
