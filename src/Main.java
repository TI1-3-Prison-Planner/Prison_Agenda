import Data.*;

import java.io.File;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Roster testRoster = new Roster();
        FileIO fio = new FileIO();

        String FileName = "namen.txt";
        testRoster.fillInmatesDataBase(fio.setUpNamelist(FileName));

        System.out.println(testRoster.getInmateDatabase());

        System.out.println("");

        hoerenveeltestcode(testRoster);
        System.out.println(testRoster);

        fio.savePersonDatabase("fkdit.txt",testRoster.getInmateDatabase());

    }


    public static void hoerenveeltestcode(Roster roster) {

//		roster.getGuardDatabase().put(new Person("Johnny Bill", true), false);
//		roster.getGuardDatabase().put(new Person("Bill Bill", true), false);
//		roster.getGuardDatabase().put(new Person("Johnny Johnny", true),false);
//		roster.getGuardDatabase().put(new Person("Johnny John", true), false);
//
//
//		PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW, false);
//		a1.addPerson(new Person("Billy", false ));
//		a1.addPerson(new Person("Jan", false));
//		a1.addPerson(new Person("Bob", false));
//		a1.addPerson(new Person("Harry", false));
//		a1.addPerson(new Person("Piet", false));
//
//		PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM, false);
//		a2.addPerson(new Person("Janus", false));
//		a2.addPerson(new Person("Hans", false));
//		a2.addPerson(new Person("Stijn", false));
//		a2.addPerson(new Person("Hailey", false));
//		a2.addPerson(new Person("Julia", false));
//
//		PrisonGroup a3 = new PrisonGroup("A3", PrisonGroup.securityDetail.HIGH, false);
//		a3.addPerson(new Person("Daan", false));
//		a3.addPerson(new Person("Lucas", false));
//		a3.addPerson(new Person("Sem", false));
//		a3.addPerson(new Person("Noah", false));
//		a3.addPerson(new Person("Levi", false));
//
//		PrisonGroup b1 = new PrisonGroup("B1", PrisonGroup.securityDetail.LOW, false);
//		b1.addPerson(new Person("Mila", false));
//		b1.addPerson(new Person("Emma", false));
//		b1.addPerson(new Person("Nora", false));
//		b1.addPerson(new Person("Olivia", false));
//		b1.addPerson(new Person("Liam", false));
//
//		PrisonGroup b2 = new PrisonGroup("B2", PrisonGroup.securityDetail.MEDIUM, false);
//		b2.addPerson(new Person("Tess", false));
//		b2.addPerson(new Person("Milou", false));
//		b2.addPerson(new Person("James", false));
//		b2.addPerson(new Person("Yara", false));
//		b2.addPerson(new Person("Finn", false));
//
//		PrisonGroup b3 = new PrisonGroup("B3", PrisonGroup.securityDetail.HIGH, false);
//		b3.addPerson(new Person("Luca", false));
//		b3.addPerson(new Person("Milan", false));
//		b3.addPerson(new Person("Casper", false));
//		b3.addPerson(new Person("Charlotte", false));
//		b3.addPerson(new Person("Cornelis", false));
//
//		a1.addGuard(roster.getGuardDatabase());
//		b1.addGuard(roster.getGuardDatabase());

        PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW, false);
        PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM, false);
        PrisonGroup a3 = new PrisonGroup("A3", PrisonGroup.securityDetail.HIGH, false);

        a1.addInmates(roster.getInmateDatabase());
        a1.addGuard(roster.getGuardDatabase());
        a2.addInmates(roster.getInmateDatabase());
        a2.addGuard(roster.getGuardDatabase());
        a3.addInmates(roster.getInmateDatabase());
        a3.addGuard(roster.getGuardDatabase());

        roster.getLocationDatabase().put("breakroom1", 	new Location("Break room", Location.locationType.BREAKROOOM));
        roster.getLocationDatabase().put("cellA1", 		new Location("Cell", Location.locationType.CELL));
        roster.getLocationDatabase().put("Cellblock_A", new Location("Cell block A", Location.locationType.CELLBLOCK));
        roster.getLocationDatabase().put("Commonroom1", new Location("Common room A", Location.locationType.COMMONROOM));
        roster.getLocationDatabase().put("Kitchen1", 	new Location("Kitchen", Location.locationType.KITCHEN));
        roster.getLocationDatabase().put("Workshop1", 	new Location("Workshop", Location.locationType.WORKSKHOP));
        roster.getLocationDatabase().put("Yard1", 		new Location("Yard", Location.locationType.YARD));


        Activity cellTime = 	new Activity("Cell Time", LocalTime.parse("14:00:00"), LocalTime.parse("17:00:00"), a2, roster.getLocationDatabase().get("breakroom1"));
        Activity eat = 			new Activity("Eat", LocalTime.parse("13:00:00"), LocalTime.parse("14:00:00"), a2, roster.getLocationDatabase().get("Kitchen1"));
        Activity work = 		new Activity("Work", LocalTime.parse("08:00:00"), LocalTime.parse("12:00:00"), a1, roster.getLocationDatabase().get("Workshop1"));
        Activity freeTime = 	new Activity("Free Time", LocalTime.parse("12:00:00"), LocalTime.parse("13:00:00"), a1, roster.getLocationDatabase().get("Yard1"));

        roster.getActivities().add(freeTime);
        roster.getActivities().add(cellTime);
        roster.getActivities().add(work);
        roster.getActivities().add(eat);

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
