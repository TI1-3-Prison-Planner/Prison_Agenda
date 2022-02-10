package Data;

import java.util.ArrayList;

public class PrisonGroup{

    private String groupName;
    ArrayList<Person> persons = new ArrayList<>();

    private static securityDetail securityDetail;
    public enum securityDetail{
        LOW,
        MEDIUM,
        HIGH;
    }

    public PrisonGroup(String groupName, securityDetail securityDetail){
        this.groupName = groupName;
        this.securityDetail = securityDetail;
    }

    public void add(Person person){
        PrisonGroup prisonGroup = new PrisonGroup("hallo", PrisonGroup.securityDetail.LOW);
        this.persons.add(person);
    }

    @Override
    public String toString() {
        return "Group name:" + this.groupName + "persons" + this.persons + "security: " + this.securityDetail;
    }
}
