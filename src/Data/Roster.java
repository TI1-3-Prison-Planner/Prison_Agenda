package Data;


import Gui.Observer;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;

public class Roster implements Comparator<LocalTime>, Serializable {


    private ArrayList<Person> guardDatabase;                    //Guard database with boolean for being assinged to a group
    private ArrayList<Person> inmateDatabase;                   //inmate database with boolean for being assinged to a group
    private HashMap<String, Location> locationDatabase;         //Location database with ID for each Location
    private ArrayList<PrisonGroup> groups;                      //List with all prison groups to assign inmates and guards to
    private ArrayList<Activity> activities;                     //List with all activity's for all groups

    public Roster() {
        this.guardDatabase = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.inmateDatabase = new ArrayList<>();
        this.locationDatabase = new HashMap<>();
        this.activities = new ArrayList<>();
    }

    public void clear() {
        this.guardDatabase.clear();
        this.groups.clear();
        this.inmateDatabase.clear();
        this.locationDatabase.clear();
        this.activities.clear();
    }

    public void fillInmatesDataBase(ArrayList<String> persons) {
        for (String name : persons) {
            Person inmate = new Person(name, false);
            this.inmateDatabase.add(inmate);
        }
    }

    public void fillGuardDatabase(ArrayList<String> persons) {
        for (String name : persons) {
            Person guard = new Person(name, true);
            this.guardDatabase.add(guard);
        }
    }

    //method to sort the activities based on time by using the implemented compare method.
    public void sortOnTime() {
        int n = this.activities.size();
        Activity tempActivity;
        Activity[] activitiesArr = this.activities.toArray(new Activity[this.getActivities().size()]);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (compare(activitiesArr[j - 1].getStartTime(), activitiesArr[j].getStartTime()) > 0) {
                    tempActivity = activitiesArr[j - 1];
                    activitiesArr[j - 1] = activitiesArr[j];
                    activitiesArr[j] = tempActivity;
                }
            }
        }
        this.activities = new ArrayList<>(activities.size());
        Collections.addAll(this.activities, activitiesArr);
    }

    //implemented method to compare 2 different localtime attributes.
    @Override
    public int compare(LocalTime o1, LocalTime o2) {
        if (o1.isAfter(o2)) {
            return 1;
        } else {
            return 0;
        }
    }

    public void manageGroups(Person person) {
        for (PrisonGroup group : this.getGroups()) {
            if (group.getInmates().remove(person))
                group.addInmates(this.getInmateDatabase());
            if(group.getGuards().remove(person))
                group.addGuard(this.getGuardDatabase());
        }
    }
    public ArrayList<Person> getGuardDatabase() {
        return this.guardDatabase;
    }
    public ArrayList<Person> getInmateDatabase() {
        return this.inmateDatabase;
    }
    public HashMap<String, Location> getLocationDatabase() {
        return this.locationDatabase;
    }
    public ArrayList<PrisonGroup> getGroups() {
        return this.groups;
    }
    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    @Override
    public String toString() {
        return "Roster:\n" +
                "guards=" + guardDatabase + '\n' + '\n' +
                "inmates=" + inmateDatabase + '\n' + '\n' +
                "groups=" + groups + '\n' + '\n' +
                "activities=" + activities + '\n' + '\n' +
                "locations=" + locationDatabase;
    }

}


