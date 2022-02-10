package Data;

public class Location implements CRUD{

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
