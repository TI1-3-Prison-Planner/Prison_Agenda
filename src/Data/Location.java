package Data;

public class Location{

    private String name;
    enum locationType{
        CELL,
        CELLBLOCK,
        WORKSKHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }

    public Location(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**TODO
     * make a nice toString();
     * @return String
     */
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }
}
