package Data;

import java.util.ArrayList;

public class Roster {
    ArrayList<Person> guards = new ArrayList<>();
    ArrayList<Person> inmates = new ArrayList<>();
    ArrayList<PrisonGroup> groups = new ArrayList<>();
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();

    public Roster(){

    }

    public ArrayList<Person> getGuards() {
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
}
