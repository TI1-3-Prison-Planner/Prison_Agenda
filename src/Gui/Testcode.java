package Gui;

import Data.Activity;
import Data.Location;
import Data.PrisonGroup;
import Data.Roster;

import java.time.LocalTime;

public class Testcode {

    public void test(){

    }

    public Roster testdata(){
        Roster roster = new Roster();
//
//        PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW, false,1);
//        PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM, false,2);
//        PrisonGroup a3 = new PrisonGroup("A3", PrisonGroup.securityDetail.HIGH, false,3);
//        PrisonGroup a4 = new PrisonGroup("A4", PrisonGroup.securityDetail.HIGH, false,4);

//
//        a1.addInmates(roster.getInmateDatabase());
//        a1.addGuard(roster.getGuardDatabase());
//        a2.addInmates(roster.getInmateDatabase());
//        a2.addGuard(roster.getGuardDatabase());
//        a3.addInmates(roster.getInmateDatabase());
//        a3.addGuard(roster.getGuardDatabase());

        roster.getLocationDatabase().put("breakroom1", 	new Location("Break room", Location.locationType.BREAKROOOM));
        roster.getLocationDatabase().put("cellA1", 		new Location("Cell", Location.locationType.CELL));
        roster.getLocationDatabase().put("Cellblock_A", new Location("Cell block A", Location.locationType.CELLBLOCK));
        roster.getLocationDatabase().put("Commonroom1", new Location("Common room A", Location.locationType.COMMONROOM));
        roster.getLocationDatabase().put("Kitchen1", 	new Location("Kitchen", Location.locationType.KITCHEN));
        roster.getLocationDatabase().put("Workshop1", 	new Location("Workshop", Location.locationType.WORKSKHOP));
        roster.getLocationDatabase().put("Yard1", 		new Location("Yard", Location.locationType.YARD));


//        Activity cellTime = 	new Activity("Cell Time", LocalTime.parse("14:00"), LocalTime.parse("17:00"), a2, roster.getLocationDatabase().get("breakroom1"));
//        Activity eat = 			new Activity("Eat", LocalTime.parse("13:00"), LocalTime.parse("14:00"), a2, roster.getLocationDatabase().get("Kitchen1"));
//        Activity work = 		new Activity("Work", LocalTime.parse("08:00"), LocalTime.parse("12:00"), a1, roster.getLocationDatabase().get("Workshop1"));
//        Activity freeTime = 	new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a1, roster.getLocationDatabase().get("Yard1"));

//        roster.getActivities().add(freeTime);
//        roster.getActivities().add(cellTime);
//        roster.getActivities().add(work);
//        roster.getActivities().add(eat);
//        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a1, roster.getLocationDatabase().get("Yard1")));
//        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("10:00"), LocalTime.parse("13:00"), a2, roster.getLocationDatabase().get("Yard1")));
//        roster.getActivities().add(new Activity("Free Time", LocalTime.parse("12:00"), LocalTime.parse("13:00"), a3, roster.getLocationDatabase().get("Yard1")));
//        roster.getGroups().add(a1);
//        roster.getGroups().add(a2);
//        roster.getGroups().add(a3);
//        roster.getGroups().add(a4);
//
        for (int i = 1; i<10;i++){
            roster.getGroups().add(new PrisonGroup("A"+i , PrisonGroup.securityDetail.HIGH, false,i));
        }

        System.out.println(roster.getLocationDatabase().size());

        return roster;
    }
}
