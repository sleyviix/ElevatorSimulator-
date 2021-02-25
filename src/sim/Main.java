package sim;

import gui.MainFrame;
import gui.SetupFrame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import javax.swing.JOptionPane;
import users.User;
import users.Client;
import users.Maintainance;

public class Main {

    static int numberOfFloors = 10; //Number of floors(includes ground floors) for 7 0,1,2,3,4,5,6
    static final int ELEVATOR_CAPACITY = 4;
    static final int MAX_ANGERY_CLIENTS = 5;
    static int totalTicks = 2880; //2880 For an 8 hour day
    static int movingTime = 5;
    static int numEmployees = 10; //Number of people starting in the simulation
    static int numDevelopers = 10;
    static double employeeFloorChange = 0.001;
    static double clientArrives = 0.002;
    static final double MAINTAINANCE_CREW_ARRIVES = 0.005;
    static final Random RND = new Random();
    static Building building;
    static MainFrame mainFrame;
    static boolean pause = true;
    static int advanceSim = 0;
    static int delayMS = 1000;
    static Simulation simulation;

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        SetupFrame setupFrame = new SetupFrame();
        setupFrame.setVisible( true );
        while ( setupFrame.isVisible() ) {
            try { 
                Thread.sleep(0);
            } catch ( Exception e ) {}
        }
        
        numEmployees = setupFrame.getStartingEmployeeCount(); 
        numDevelopers = setupFrame.getStartingDevCount();
        employeeFloorChange = setupFrame.getP();
        clientArrives = setupFrame.getQ();
        RND.setSeed( setupFrame.getSeed() );
        totalTicks = setupFrame.getTickCount();
        numberOfFloors = setupFrame.getFloorCount();
        mainFrame = new MainFrame( numberOfFloors, MAX_ANGERY_CLIENTS, totalTicks );
        mainFrame.addPropertyChangeListener( new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
               if ( evt.getPropertyName().equals("simStateChange") ) { 
                   pause = (boolean) evt.getNewValue();
               } else if ( evt.getPropertyName().equals( "simSpeedChange" ) ) { 
                   delayMS = (int) evt.getNewValue();
               } else if ( evt.getPropertyName().equals( "simAdvanceOnce") ) { 
                   advanceSim = (int) evt.getNewValue();
                   mainFrame.disableControls(true);
               }
            }
        });
        mainFrame.setTitle("Simulation (Seed:" + setupFrame.getSeed() + " | Floors: " + numberOfFloors + " | Employees: " + numEmployees + " | Developers: " + numDevelopers + " | P: " + employeeFloorChange + " | Q: " + clientArrives + ") " );
        mainFrame.setVisible(true);
        building = new Building(numberOfFloors, ELEVATOR_CAPACITY);

        System.out.println("henlo");

        enterBuilding(building, numEmployees, numDevelopers);
        building.getElevator().getUsersInElevator();
        simulation = new Simulation(building, movingTime);
        building.getFloor(0);
        simulation.printFloor();
        users.Employee.floorChangeChance = employeeFloorChange;
        users.Developer.floorChangeChance = employeeFloorChange;
        mainFrame.floorList.get(0).setGoingUpList( building.getFloor(0).goingUp );
        mainFrame.tick(0);
        tick();

    }

    /**
     * The main Tick counter, this uses the tick methods of all the other
     * classes that have a tick method.
     */
    public static void tick() {
        for (int thisTick = 0; thisTick < totalTicks; thisTick++) {
            try { 
                if ( pause && advanceSim == 0 ) mainFrame.disableControls( false ); 
                do {
                    Thread.sleep( delayMS );
                } while ( pause && advanceSim == 0 );
            } catch( Exception e ) {
            }

            building.getElevator().tick(thisTick);
//            simulation.printFloor();
            System.out.println("Tick " + thisTick);

            if (RND.nextDouble() <= clientArrives) {
                building.enter(new Client( RND, numberOfFloors - 1 ), thisTick);
                System.out.println("A client has entererd the building!");
            }

            if (RND.nextDouble() <= MAINTAINANCE_CREW_ARRIVES) {
                building.enter(new Maintainance( RND, numberOfFloors - 1 ), thisTick);
                System.out.println("A maintenance has entererd the building!");
            }

            for (int floor = 0; floor < building.numFloors; floor++) {
                for (int userCount = 0; userCount < building.getFloor(floor).peopleNotWaiting.size(); userCount++) {
                    User user = building.getFloor(floor).peopleNotWaiting.get(userCount);
                    switch ( user.tick(thisTick) ) { 
                        case 1: 
                            building.getFloor(floor).waitForElevator(user, user.randomDestignation(), thisTick);
                            break;
                        case 2: 
                            mainFrame.clientComlaint();
                    }
                }
                for (int userCount = 0; userCount < building.getFloor(floor).goingUp.size(); userCount++) {
                    User user = building.getFloor(floor).goingUp.get(userCount);
                    if ( user.tick(thisTick) == 2 ) { 
                        mainFrame.clientComlaint();
                    }
                }
                for (int userCount = 0; userCount < building.getFloor(floor).goingDown.size(); userCount++) {
                    User user = building.getFloor(floor).goingDown.get(userCount);
                    if (user.tick(thisTick) == 2) { 
                        mainFrame.clientComlaint();
                    }
                }
                mainFrame.floorList.get(floor).setIdleList( building.getFloor(floor).peopleNotWaiting );
                mainFrame.floorList.get(floor).setGoingUpList( building.getFloor(floor).goingUp );
                mainFrame.floorList.get(floor).setGoingDownList( building.getFloor(floor).goingDown );
            }
            mainFrame.elevator.updateInfo(building.getElevator().getCurrentFloor(), building.getElevator().isGoingUp(), building.getElevator().getDoorsOpen() );
            mainFrame.elevator.setPassengerList( building.getElevator().getUsersInElevator() );
//            for (User user : building.getElevator().getUsersInElevator()) {
//                if (user.getClass() == users.Developer.class) {
//                    elevatorDev++;
//                } else if (user.getClass() == users.Client.class) {
//                    elevatorCli++;
//                } else if (user.getClass() == users.Maintainance.class) {
//                    elevatorMaint++;
//                } else {
//                    elevatorEmp++;
//                }
//            }
            mainFrame.tick(thisTick+1);
            if ( advanceSim > 0 ) advanceSim--;
        }
        
        
        mainFrame.disableControls( true );
        JOptionPane.showMessageDialog(mainFrame, "The simulation has sucessfully completed.", "Simulation Completed", JOptionPane.INFORMATION_MESSAGE);
        
    }

    /**
     * Adds the initial users to the simulation
     *
     * @param building The building the users are in
     * @param numbers The number of people that are being added to the
     * simulation
     */
    private static void enterBuilding(Building building, int empCount, int devCount) {
        for (int i = 0; i < empCount; i++) {
            building.enter( new users.Employee(RND, numberOfFloors - 1), 0);
        }
        for (int i = 0; i < devCount; i++) {
            building.enter( new users.Developer(RND, numberOfFloors - 1), 0);
        }
        building.generateRandom(1);
    }

}
