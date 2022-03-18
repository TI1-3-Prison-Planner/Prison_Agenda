package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PrisonGroup implements Serializable {

	//enumeration for the types of security on a group. For each securityDetail is a different amount of guards with the group.
	public enum SecurityDetail {
		LOW,
		MEDIUM,
		HIGH
	}

	private String groupName;
	private ArrayList<Person> inmates;
	private ArrayList<Person> guards;
	private SecurityDetail securityDetail;
	private int groupID;

	public PrisonGroup(String groupName,int groupID, SecurityDetail securityDetail) {
		this.groupName = groupName;
		this.inmates = new ArrayList<>();
		this.guards = new ArrayList<>();
		this.groupID = groupID;
		this.securityDetail = securityDetail;
	}

	//method to check how much guards a group needs, then add that amount of guards to the hashmap given as parameter and a ArrayList of guards.
	public void addGuard(HashMap<Person, Boolean> guardList) {
		int guardAmount = guardsPerDetail();
		int currentAmount = this.guards.size();
		for (Person person : guardList.keySet()) {
			if (!guardList.get(person) && (guardAmount - currentAmount) > 0) {
				this.guards.add(person);
				person.setInGroup(true);
				guardList.put(person, person.isInGroup());
				guardAmount--;
			}
		}
	}

	public void removeGuard(HashMap<Person, Boolean> guardList){
		int guardAmount = guardsPerDetail();
		int currentAmount = this.guards.size();

		for (Person person : guardList.keySet()) {
			if (this.guards.contains(person) && (guardAmount < currentAmount)) {
				this.guards.remove(person);
				person.setInGroup(false);
				guardList.put(person, person.isInGroup());
				currentAmount--;
			}
		}
	}

	public void addInmates(HashMap<Person, Boolean> inmateList){
		int inmateAmount = 20;
		for (Person person : inmateList.keySet()) {
			if (!inmateList.get(person) && inmateAmount > 0) {
				this.inmates.add(person);
				person.setInGroup(true);
				inmateList.put(person, person.isInGroup());
				inmateAmount--;
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

	public void refreshGuards(Roster roster){
		int currentGuardAmmount = this.guards.size();
		int neededGuardAmmount = guardsPerDetail();

		if (neededGuardAmmount > currentGuardAmmount){
			addGuard(roster.getGuardDatabase());
		} else {
			removeGuard(roster.getGuardDatabase());
		}
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setInmates(ArrayList<Person> inmates) {
		this.inmates = inmates;
	}
	public void setGuards(ArrayList<Person> guards) {
		this.guards = guards;
	}
	public void setSecurityDetail(SecurityDetail securityDetail) {
		this.securityDetail = securityDetail;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return this.groupName;
	}
	public ArrayList<Person> getInmates() {
		return this.inmates;
	}
	public ArrayList<Person> getGuards() {
		return guards;
	}
	public SecurityDetail getSecurityDetail() {
		return securityDetail;
	}
	public int getGroupID() {
		return groupID;
	}


	@Override
	public String toString() {
		return "Security detail: " + this.securityDetail + ", Group: "+ this.groupName + ", inmates: " + this.inmates.size() +  ", guards:" + this.guards.size() + "]";
	}
}
