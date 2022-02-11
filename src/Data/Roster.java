package Data;

import sun.applet.Main;

import java.util.ArrayList;

public class Roster {

    private ArrayList<Person> guards;
    private ArrayList<Person> inmates;
    private ArrayList<PrisonGroup> groups;
    private ArrayList<Activity> activities;
    private ArrayList<Location> locations;

    public Roster(){
        this.guards = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.inmates = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.activities = new ArrayList<>();
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
