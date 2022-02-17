import Data.*;

import java.time.LocalTime;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {
		Roster testRoster = new Roster();
		hoerenveeltestcode(testRoster);

	}


	public static void hoerenveeltestcode(Roster roster) {
		Person billy = new Person("Billy", false);
		Person jan = new Person("Jan", false);
		Person bob = new Person("Bob", false);
		Person harry = new Person("Harry", false);
		Person piet = new Person("Piet", false);

		Person janus = new Person("Janus", false);
		Person hans = new Person("Hans", false);
		Person stijn = new Person("Stijn", false);
		Person hailey = new Person("Hailey", false);
		Person julia = new Person("Julia", false);

		Person daan = new Person("Daan", false);
		Person lucas = new Person("Lucas", false);
		Person sem = new Person("Sem", false);
		Person noah = new Person("Noah", false);
		Person levi = new Person("Levi", false);

		Person mila = new Person("Mila", false);
		Person emma = new Person("Emma", false);
		Person nora = new Person("Nora", false);
		Person olivia = new Person("Olivia", false);
		Person liam = new Person("Liam", false);

		Person tess = new Person("Tess", false);
		Person milou = new Person("Milou", false);
		Person james = new Person("James", false);
		Person yara = new Person("Yara", false);
		Person finn = new Person("Finn", false);

		Person luca = new Person("Luca", false);
		Person milan = new Person("Milan", false);
		Person casper = new Person("Casper", false);
		Person charlotte = new Person("Charlotte", false);
		Person cornelis = new Person("Cornelis", false);

		roster.getGuards().put(new Person("Johnny Bill", true), false);
		roster.getGuards().put(new Person("Bill Bill", true), false);
		roster.getGuards().put(new Person("Johnny Johnny", true),false);
		roster.getGuards().put(new Person("Johnny John", true), false);




		PrisonGroup a1 = new PrisonGroup("A1", PrisonGroup.securityDetail.LOW, false);
		a1.addPerson(billy);
		a1.addPerson(jan);
		a1.addPerson(bob);
		a1.addPerson(harry);
		a1.addPerson(piet);

		PrisonGroup a2 = new PrisonGroup("A2", PrisonGroup.securityDetail.MEDIUM, false);
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

		b1.addGuard(roster.getGuards());


		Location breakRoom = new Location("BreakRoom", Location.locationType.BREAKROOOM);
		Location cell = new Location("Cell", Location.locationType.CELL);
		Location cellblock = new Location("Cellblock", Location.locationType.CELLBLOCK);
		Location commonRoom = new Location("CommonRoom", Location.locationType.COMMONROOM);
		Location kitchen = new Location("Kitchen", Location.locationType.KITCHEN);
		Location workshop = new Location("Workshop", Location.locationType.WORKSKHOP);
		Location yard = new Location("Yard", Location.locationType.YARD);

		Activity freeTime = new Activity("Free Time", LocalTime.parse("12:00:00"), LocalTime.parse("13:00:00"), a1, yard);
		Activity cellTime = new Activity("Cell Time", LocalTime.parse("14:00:00"), LocalTime.parse("17:00:00"), a2, cell);
		Activity work = new Activity("Work", LocalTime.parse("08:00:00"), LocalTime.parse("12:00:00"), b1, workshop);
		Activity eat = new Activity("Eat", LocalTime.parse("13:00:00"), LocalTime.parse("14:00:00"), b2, kitchen);

		roster.getActivities().add(freeTime);
		roster.getActivities().add(cellTime);
		roster.getActivities().add(work);
		roster.getActivities().add(eat);

		roster.getGroups().add(a1);
		roster.getGroups().add(a2);
		roster.getGroups().add(a3);
		roster.getGroups().add(b1);
		roster.getGroups().add(b2);
		roster.getGroups().add(b3);
		System.out.println(roster.getActivities().toString());

	}
}
