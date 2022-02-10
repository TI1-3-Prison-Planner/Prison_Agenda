import Data.Person;
import Data.PrisonGroup;
import Data.RosterCRUD;

public class Main {
    public static void main(String[] args) {
        RosterCRUD rosterCRUD = new RosterCRUD();
        rosterCRUD.add(new Person("Billy"), rosterCRUD.getGuards());
        System.out.println(rosterCRUD.getGuards());
        rosterCRUD.add(new PrisonGroup("A1"), rosterCRUD.getGroups());
        System.out.println(rosterCRUD.getGroups());
    }
}
