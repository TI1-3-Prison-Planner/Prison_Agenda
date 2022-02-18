package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PrisonGroup implements Serializable {

	private String groupName;
	private ArrayList<Person> inmates;
	private ArrayList<Person> guards;
	private securityDetail securityDetail;

	//enumeration for the types of security on a group. For each securityDetail is a different amount of guards with the group.
	public enum securityDetail {
		LOW,
		MEDIUM,
		HIGH
	}

	public PrisonGroup(String groupName, securityDetail securityDetail, boolean isGuardGroup) {
		this.groupName = groupName;
		this.inmates = new ArrayList<>();
		this.guards = new ArrayList<>();
		this.securityDetail = securityDetail;
	}

	//methode to add a person to a group. An ArrayList and a boolean are used to add the person to the right group.
	public void addPerson(Person person) {
		if (!this.inmates.contains(person) && !person.isGuard()) {
			this.inmates.add(person);
		}
	}

	//Getter to return the name of a certain prisongroup.
	public String getGroupName() {
		return this.groupName;
	}

	//method to return the inmates in a certain prisongroup.
	public ArrayList<Person> getInmates() {
		return this.inmates;
	}

	//method to check how much guards a group needs, then add that amount of guards to the hashmap given as parameter and a ArrayList of guards.
	public void addGuard(HashMap<Person, Boolean> guardList) {
		int guardAmount = guardsPerDetail();
		for (Person person : guardList.keySet()) {
			if (!guardList.get(person) && guardAmount > 0) {
				this.guards.add(person);
				guardAmount--;
			}
		}

	}

	//method to set the right amount of guards to a certain group.
	public int guardsPerDetail() {
		switch (securityDetail) {
			case LOW:
				return 2;
			case MEDIUM:
				return 5;
			case HIGH:
				return 10;
			default:
				return -1;
		}
	}

	@Override
	public String toString() {
		return "[" + this.groupName + ", "
				+ this.inmates.size() + ", " + this.securityDetail + ", guards:" + this.guards.size() + "]";
	}
}
