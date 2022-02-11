package Data;

import java.util.ArrayList;

public class PrisonGroup {

    private String groupName;
    private ArrayList<Person> inmates;
    private ArrayList<Person> guards;
    private static securityDetail securityDetail;
    private boolean isGuardGroup;

    public enum securityDetail {
        LOW,
        MEDIUM,
        HIGH;
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


    @Override
    public String toString() {
        return "Group name:" + this.groupName + "\n"
                + this.inmates.size() + "inmates\n" + "security: " + this.securityDetail;
    }
}
