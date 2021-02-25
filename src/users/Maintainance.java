package users;

import java.util.Random;

public class Maintainance extends User {    
    /**
     * The space taken up within an elevator by this user
     */
    private final static int SPACE_TAKEN_UP = 4;
    /**
     * The tick on which this user will leave their target floor and return to the entrance 
     */
    private int tickToLeave = -1;
    /**
     * the inclusive bounds of the time in minutes that a maintenance crew will spend on their target floor
     */
    private final int[] TIME_BOUNDS = { 120, 240 };

    /**
     * Creates a new user of type maintenance crew
     * @param rnd
     * @param TOP_FLOOR
     */
    public Maintainance( Random rnd, final int TOP_FLOOR ) {
        super(SPACE_TAKEN_UP, rnd, TOP_FLOOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int randomDestignation() {
        int targetFloor = getCurrentFloor() == 0 ? TOP_FLOOR: 0;
        setTargetFloor(targetFloor);
        return targetFloor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int tick(int tickNum) {
        if ( tickToLeave > -1 && tickNum >= tickToLeave ) {
            tickToLeave = -1;
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void arrive(int tickNum) {
        super.arrive( tickNum );
        tickToLeave = tickNum + super.getRNG().nextInt(TIME_BOUNDS[1] + 1 - TIME_BOUNDS[0]) + TIME_BOUNDS[0];
    }

}
