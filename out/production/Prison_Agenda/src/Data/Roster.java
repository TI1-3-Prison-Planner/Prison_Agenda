package Data;
import java.util.ArrayList;
import java.util.HashMap;

public class Roster {

    //this hashmap contains all guards in the prison. a boolean is used to check if a guard has already been assigned
    // to a prisonGroup
    private HashMap<Person, Boolean> guards;
    private ArrayList<Person> inmates;
    private ArrayList<PrisonGroup> groups;
    private ArrayList<Activity> activities;
    private ArrayList<Location> locations;

    public Roster(){
        this.guards = new HashMap<>();
        this.groups = new ArrayList<>();
        this.inmates = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.activities = new ArrayList<>();
    }
    public HashMap<Person, Boolean> getGuards() {
        return this.guards;
    }

    public ArrayList<Person> getInmates() {
        return this.inmates;
    }

    public ArrayList<PrisonGroup> getGroups() {
        return this.groups;
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    public ArrayList<Location> getLocations() {
        return this.locations;
    }

    @Override
    public String toString() {
        return "Roster:\n" +
                "guards=" + guards + '\n'  +
                "inmates=" + inmates + '\n'+
                "groups=" + groups + '\n' +
                "activities=" + activities + '\n' +
                "locations=" + locations;
    }
}
