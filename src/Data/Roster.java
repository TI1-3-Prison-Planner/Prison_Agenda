package Data;

import org.omg.PortableServer.ServantRetentionPolicy;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;

public class Roster implements Comparator<LocalTime>, Serializable {

	//this hashmap contains all guards in the prison. a boolean is used to check if a guard has already been assigned
	// to a prisonGroup
	private HashMap<Person, Boolean> guards;
	private ArrayList<Person> inmates;
	private ArrayList<PrisonGroup> groups;
	private ArrayList<Location> locations;
	private ArrayList<Activity> activities;

	public Roster() {
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

	//method to clear all the ArrayLists in the agenda.
	public void clear() {
		this.guards.clear();
		this.groups.clear();
		this.inmates.clear();
		this.locations.clear();
		this.activities.clear();

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

	@Override
	public String toString() {
		return "Roster:\n" +
				"guards=" + guards + '\n' +
				"inmates=" + inmates + '\n' +
				"groups=" + groups + '\n' +
				"activities=" + activities + '\n' +
				"locations=" + locations;
	}
}

