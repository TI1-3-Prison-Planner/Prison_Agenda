import Data.*;
import Gui.*;
import javafx.application.Application;

import java.io.File;
import java.time.LocalTime;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        Roster testRoster = new Roster();
        FileIO fio = new FileIO();

        String FileName = "namen.txt";
        testRoster.fillGuardDatabase(fio.setUpNamelist(FileName));
        testRoster.fillInmatesDataBase(fio.setUpNamelist(FileName));

        testcode(testRoster);

        File file = new File("roster.ser");
        fio.saveDataAsFile(file, testRoster);
//        System.out.println(testRoster);
        Application.launch(Gui.class);


    }

    public static void testcode(Roster roster) {
        PrisonGroup a1 = new PrisonGroup("A1", 1, PrisonGroup.SecurityDetail.LOW );
        PrisonGroup a2 = new PrisonGroup("A2", 2, PrisonGroup.SecurityDetail.MEDIUM);
        PrisonGroup a3 = new PrisonGroup("A3", 3, PrisonGroup.SecurityDetail.HIGH);

        a1.addInmates(roster.getInmateDatabase());
        a1.addGuard(roster.getGuardDatabase());
        a2.addInmates(roster.getInmateDatabase());
        a2.addGuard(roster.getGuardDatabase());
        a3.addInmates(roster.getInmateDatabase());
        a3.addGuard(roster.getGuardDatabase());

        roster.getLocationDatabase().put("breakroom1", 	new Location("Break room", Location.LocationType.BREAKROOOM));
        roster.getLocationDatabase().put("cellA1", 		new Location("Cell", Location.LocationType.CELL));
        roster.getLocationDatabase().put("Cellblock_A", new Location("Cell block A", Location.LocationType.CELLBLOCK));
        roster.getLocationDatabase().put("Commonroom1", new Location("Common room A", Location.LocationType.COMMONROOM));
        roster.getLocationDatabase().put("Kitchen1", 	new Location("Kitchen", Location.LocationType.KITCHEN));
        roster.getLocationDatabase().put("Workshop1", 	new Location("Workshop", Location.LocationType.WORKSHOP));
        roster.getLocationDatabase().put("Yard1", 		new Location("Yard", Location.LocationType.YARD));


        Activity cellTime = 	new Activity("Cell Time", LocalTime.parse("14:00:00"), LocalTime.parse("17:00:00"), a2, roster.getLocationDatabase().get("breakroom1"));
        Activity eat = 			new Activity("Eat", LocalTime.parse("13:00:00"), LocalTime.parse("14:00:00"), a2, roster.getLocationDatabase().get("Kitchen1"));
        Activity work = 		new Activity("Work", LocalTime.parse("08:00:00"), LocalTime.parse("12:00:00"), a1, roster.getLocationDatabase().get("Workshop1"));
//        Activity freeTime = 	new Activity("Free Time", LocalTime.parse("12:00:00"), LocalTime.parse("13:00:00"), a1, roster.getLocationDatabase().get("Yard1"));


//        roster.getActivities().add(freeTime);
        roster.getActivities().add(cellTime);
        roster.getActivities().add(work);
        roster.getActivities().add(eat);
        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a1, roster.getLocationDatabase().get("Yard1")));
        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a2, roster.getLocationDatabase().get("Yard1")));
        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a3, roster.getLocationDatabase().get("Yard1")));



        roster.getGroups().add(a1);
        roster.getGroups().add(a2);
        roster.getGroups().add(a3);
//		roster.getGroups().add(b1);
//		roster.getGroups().add(b2);
//		roster.getGroups().add(b3);

        roster.sortOnTime();

//		System.out.println(roster.getActivities().toString());

    }
}
