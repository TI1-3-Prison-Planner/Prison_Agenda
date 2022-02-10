package Data;

import java.util.ArrayList;

public class Groups implements CRUD{

    private String groupName;
    ArrayList<Person> persons = new ArrayList<>();
    enum securityDetail{
        LOW,
        MEDIUM,
        HIGH,
    }

    public Groups(){

    }

    @Override
    public void add() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }
}
