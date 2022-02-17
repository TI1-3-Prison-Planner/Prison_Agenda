import Data.*;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        hoerenveeltestcode();
    }


    public static void hoerenveeltestcode(){
        Person billy = new Person("Billy",true);
        Person jan = new Person("Jan",true);
        Person bob = new Person("Bob",true);
        Person harry = new Person("Harry",true);
        Person piet = new Person("Piet",true);

        Person janus = new Person("Janus",true);
        Person hans = new Person("Hans",true);
        Person stijn = new Person("Stijn",true);
        Person hailey = new Person("Hailey",true);
        Person julia = new Person("Julia",true);

        Person daan = new Person("Daan",true);
        Person lucas = new Person("Lucas",true);
        Person sem = new Person("Sem",true);
        Person noah = new Person("Noah",true);
        Person levi = new Person("Levi",true);

        Person mila = new Person("Mila",true);
        Person emma = new Person("Emma",true);
        Person nora = new Person("Nora",true);
        Person olivia = new Person("Olivia",true);
        Person liam = new Person("Liam",true);

        Person tess = new Person("Tess",true);
        Person milou = new Person("Milou",true);
        Person james = new Person("James",true);
        Person yara = new Person("Yara",true);
        Person finn = new Person("Finn",true);

        Person luca = new Person("Luca",true);
        Person milan = new Person("Milan",true);
        Person casper = new Person("Casper",true);
        Person charlotte = new Person("Charlotte",true);
        Person cornelis = new Person("Cornelis",true);


        PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW, false);
        a1.addPerson(billy);
        a1.addPerson(jan);
        a1.addPerson(bob);
        a1.addPerson(harry);
        a1.addPerson(piet);

        PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM,false);
        a2.addPerson(janus);
        a2.addPerson(hans);
        a2.addPerson(stijn);
        a2.addPerson(hailey);
        a2.addPerson(julia);

        PrisonGroup a3 = new PrisonGroup("A3", PrisonGroup.securityDetail.HIGH, false);
        a3.addPerson(daan);
        a3.addPerson(lucas);
        a3.addPerson(sem);
        a3.addPerson(noah);
        a3.addPerson(levi);

        PrisonGroup b1 = new PrisonGroup("B1", PrisonGroup.securityDetail.LOW, false);
        b1.addPerson(mila);
        b1.addPerson(emma);
        b1.addPerson(nora);
        b1.addPerson(olivia);
        b1.addPerson(liam);

        PrisonGroup b2 = new PrisonGroup("B2", PrisonGroup.securityDetail.MEDIUM, false);
        b2.addPerson(tess);
        b2.addPerson(milou);
        b2.addPerson(james);
        b2.addPerson(yara);
        b2.addPerson(finn);

        PrisonGroup b3 = new PrisonGroup("B3", PrisonGroup.securityDetail.HIGH, false);
        b3.addPerson(luca);
        b3.addPerson(milan);
        b3.addPerson(casper);
        b3.addPerson(charlotte);
        b3.addPerson(cornelis);

        Location breakRoom = new Location("BreakRoom", Location.locationType.BREAKROOOM);
        Location cell = new Location("Cell" ,Location.locationType.CELL);
        Location cellblock = new Location("Cellblock", Location.locationType.CELLBLOCK);
        Location commonRoom = new Location("CommonRoom", Location.locationType.COMMONROOM);
        Location kitchen = new Location("Kitchen", Location.locationType.KITCHEN);
        Location workshop = new Location("Workshop", Location.locationType.WORKSKHOP);
        Location yard = new Location("Yard", Location.locationType.YARD);

        Activity freeTime = new Activity("Free Time", LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"), a1, yard);
        Activity cellTime = new Activity("Cell Time", LocalTime.parse("14:00:00"),LocalTime.parse("17:00:00"), a2, cell);
        Activity work = new Activity("Work", LocalTime.parse("8:00:00"),LocalTime.parse("12:00:00"), b1, workshop);
        Activity eat = new Activity("Eat", LocalTime.parse("13:00:00"),LocalTime.parse("14:00:00"), b2, kitchen);

        System.out.println(breakRoom);
        System.out.println(commonRoom);
    }
}
