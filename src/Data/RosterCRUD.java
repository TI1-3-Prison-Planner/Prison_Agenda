package Data;

import java.util.ArrayList;

public class RosterCRUD implements CRUD {

    ArrayList<Person> guards = new ArrayList<>();
    ArrayList<Person> inmates = new ArrayList<>();
    ArrayList<PrisonGroup> groups = new ArrayList<>();
    ArrayList<Activity> activities = new ArrayList<>();
    ArrayList<Location> locations = new ArrayList<>();

    public RosterCRUD(){
        this.guards.add(new Person("Bob"));
    }


    /**
     * Author: Moustapha Azaïmi
     *
     * @param data : an object to be added to the chosen ArrayList
     * @param dataList :
     */
    @Override
    public void add(Object data, ArrayList dataList) {
        dataList.add(data);
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

    public ArrayList<Person> getGuards() {
        return this.guards;
    }

    public ArrayList<Person> getInmates() {
        return inmates;
    }

    public ArrayList<PrisonGroup> getGroups() {
        return groups;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    /**TODO
     * make a nice toString();
     * @return String
     */
    @Override
    public String toString() {
        return "RosterCRUD{" +
                "guards=" + guards +
                ", inmates=" + inmates +
                ", groups=" + groups +
                ", activities=" + activities +
                ", locations=" + locations +
                '}';
    }
}
