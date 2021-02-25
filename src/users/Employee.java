package users;

import java.util.Random;

public class Employee extends User {

    /**
     * The space taken up within an elevator by this user
     */
    private final static int SPACE_TAKEN_UP = 1;
    
    public boolean isIdleOnFloor = false;
    
    public static double floorChangeChance = 0.001;
    
    public Employee( Random rnd, final int TOP_FLOOR ) {
        super(SPACE_TAKEN_UP, rnd, TOP_FLOOR);
    }

    @Override
    public int randomDestignation() {
        System.out.println( "Employee change floor");
        Random rand = super.getRNG();
        int rand_int1 = rand.nextInt(TOP_FLOOR-1)+1;
        setTargetFloor( rand_int1 );
        return rand_int1;

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
        boolean temp = isIdleOnFloor;
        isIdleOnFloor = false;
        return temp && ( super.getRNG().nextDouble() <= floorChangeChance )? 1 : 0;
    }

    

}
