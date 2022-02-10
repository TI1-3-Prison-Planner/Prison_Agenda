package Data;

import java.util.ArrayList;

public class RosterCRUD implements CRUD {

    ArrayList<Person> guards = new ArrayList<>();
    ArrayList<Person> inmates = new ArrayList<>();
    ArrayList<Groups> groups = new ArrayList<>();
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();

    private Activity activity = new Activity();
    private Groups group = new Groups();
    private Location location = new Location();
    private Person person = new Person();

    public RosterCRUD(){

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
