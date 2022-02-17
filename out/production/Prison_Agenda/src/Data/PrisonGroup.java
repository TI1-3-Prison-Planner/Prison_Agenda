package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PrisonGroup implements Serializable {

	private String groupName;
	private ArrayList<Person> inmates;
	private ArrayList<Person> guards;
	private securityDetail securityDetail;
	private boolean isGuardGroup;

	public enum securityDetail {
		LOW,
		MEDIUM,
		HIGH
	}

	public PrisonGroup(String groupName, securityDetail securityDetail, boolean isGuardGroup) {
		this.groupName = groupName;
		this.inmates = new ArrayList<>();
		this.guards = new ArrayList<>();
		this.isGuardGroup = isGuardGroup;
		this.securityDetail = securityDetail;
	}

	public void addPerson(Person person) {
		if (!this.inmates.contains(person) && !person.isGuard()) {
			this.inmates.add(person);
			person.setInGroup(true);
		}
		if (!this.guards.contains(person) && person.isGuard()) {
			this.guards.add(person);
			person.setInGroup(true);
		}

	}

	public String getGroupName() {
		return this.groupName;
	}

	public ArrayList<Person> getInmates() {
		return this.inmates;
	}

	public void addGuard(HashMap<Person, Boolean> guardList) {
		int guardAmount = guardsPerDetail();
		for (Person person : guardList.keySet()) {
			if (!guardList.get(person) && guardAmount > 0) {
				this.guards.add(person);
				guardAmount--;
			}
		}

	}

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
