import Data.Activity;
import Data.Location;
import Data.Person;
import Data.PrisonGroup;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        
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


        PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW);
        a1.add(billy);
        a1.add(jan);
        a1.add(bob);
        a1.add(harry);
        a1.add(piet);

        PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM);
        a2.add(janus);
        a2.add(hans);
        a2.add(stijn);
        a2.add(hailey);
        a2.add(julia);

        PrisonGroup a3 = new PrisonGroup("A3", PrisonGroup.securityDetail.HIGH);
        a3.add(daan);
        a3.add(lucas);
        a3.add(sem);
        a3.add(noah);
        a3.add(levi);

        PrisonGroup b1 = new PrisonGroup("B1", PrisonGroup.securityDetail.LOW);
        b1.add(mila);
        b1.add(emma);
        b1.add(nora);
        b1.add(olivia);
        b1.add(liam);

        PrisonGroup b2 = new PrisonGroup("B2", PrisonGroup.securityDetail.MEDIUM);
        b2.add(tess);
        b2.add(milou);
        b2.add(james);
        b2.add(yara);
        b2.add(finn);

        PrisonGroup b3 = new PrisonGroup("B3", PrisonGroup.securityDetail.HIGH);
        b3.add(luca);
        b3.add(milan);
        b3.add(casper);
        b3.add(charlotte);
        b3.add(cornelis);

        Location breakRoom = new Location(Location.location.BREAKROOOM);
        Location cell = new Location(Location.location.CELL);
        Location cellblock = new Location(Location.location.CELLBLOCK);
        Location commonRoom = new Location(Location.location.COMMONROOM);
        Location kitchen = new Location(Location.location.KITCHEN);
        Location workshop = new Location(Location.location.WORKSKHOP);
        Location yard = new Location(Location.location.YARD);

        Activity freeTime = new Activity(LocalTime.parse("12:00:00"),LocalTime.parse("13:00:00"), a1, yard);
        Activity cellTime = new Activity(LocalTime.parse("14:00:00"),LocalTime.parse("17:00:00"), a2, cell);
        Activity work = new Activity(LocalTime.parse("8:00:00"),LocalTime.parse("12:00:00"), b1, workshop);
        Activity eat = new Activity(LocalTime.parse("13:00:00"),LocalTime.parse("14:00:00"), b2, kitchen);
    }
}
