package users;

import java.util.Random;

public class Developer extends User {
    
    public static double floorChangeChance = 0.001;
    public boolean isIdleOnFloor = false;
    
    /**
     * The space taken up within an elevator by this user
     */
    private final static int SPACE_TAKEN_UP = 1;

    /**
     * Creates a new user of type developer
     * @param rnd
     */
    public Developer( Random rnd, final int TOP_FLOOR ) {
        super(SPACE_TAKEN_UP, rnd, TOP_FLOOR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int randomDestignation() {
        System.out.println( "Developer change floor");
        Random rnd = super.getRNG();
        int targetFloor = rnd.nextInt((TOP_FLOOR + 1 )/2) + 3;
        setTargetFloor(targetFloor);
        isIdleOnFloor = false;
        return targetFloor;

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void arrive( int tickNum ) { 
        super.arrive(tickNum);
        isIdleOnFloor = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int tick( int tickNum ) {
        return isIdleOnFloor && ( super.getRNG().nextDouble() <= floorChangeChance )? 1 : 0;
    }
}
