package users;

import java.util.Random;

public class Client extends User {

    /**
     * The space taken up within an elevator by this user
     */
    private final static int SPACE_TAKEN_UP = 1;
    /**
     * The tick on which this user will leave their target floor and return to
     * the entrance
     */
    private int tickToLeave = -1;
    /**
     * The tick on which this client started waiting for the elevator
     */
    private int waitingTick = -1;
    /**
     * The maximum amount of time this client will wait before leaving a
     * complaint
     */
    private final int MAX_WAIT_TIME = 60;
    /**
     * the inclusive bounds of the time in minutes that a maintenance crew will
     * spend on their target floor
     */
    private final int[] TIME_BOUNDS = {60, 180};

    /**
     * Creates a new user of type client
     */
    public Client( Random rnd, final int TOP_FLOOR ) {
        super(SPACE_TAKEN_UP, rnd, TOP_FLOOR );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int randomDestignation() {
        Random rnd = super.getRNG();
        int targetFloor = getCurrentFloor() > 0 ? 0 : (rnd.nextInt(TOP_FLOOR/2) + 1);
        setTargetFloor(targetFloor);
        return targetFloor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int tick(int tickNum) {
        if (tickToLeave != -1 && tickNum >= tickToLeave) {
            //if the client has been at their destination floor for long enough,
            //then they should begin waiting for the elevator
            waitForElevator(tickNum);
            return 1;
        } else if ( waitingTick != -1 && tickNum >= waitingTick) {
            //if the client has been waiting for longer than MAX_WAIT_TIME,
            //note their complaint
            System.err.println("Grr I'm an angery client!!! >:( on floor " + getCurrentFloor() + " to " + getTargetFloor() );
            waitingTick = -1;
            return 2;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void arrive(int tickNum) {
        super.arrive(tickNum);
        tickToLeave = tickNum + super.getRNG().nextInt(TIME_BOUNDS[1] + 1 - TIME_BOUNDS[0]) + TIME_BOUNDS[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void boardElevator() { 
        super.boardElevator();
        waitingTick = -1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void waitForElevator(int tickNum) {
        super.waitForElevator(tickNum);
        tickToLeave = -1;
        waitingTick = tickNum + MAX_WAIT_TIME;
    }

}
