package Data;

import java.util.ArrayList;

public class PrisonGroup implements CRUD{

    private String groupName;
    ArrayList<Person> persons = new ArrayList<>();



    enum securityDetail{
        LOW,
        MEDIUM,
        HIGH;
    }

    public PrisonGroup(String groupName){
        this.groupName = groupName;

    }



    @Override
    public void add(Object data, ArrayList dataList) {

    }

    @Override
    public void read(Object data, ArrayList dataList) {

    }

    @Override
    public void update(Object data, ArrayList dataList) {

    }

    @Override
    public void destroy(Object data, ArrayList dataList) {

    }

    @Override
    public String toString() {
        return "PrisonGroup{" +
                "groupName='" + groupName + '\'' +
                ", persons=" + persons +
                '}';
    }
}
