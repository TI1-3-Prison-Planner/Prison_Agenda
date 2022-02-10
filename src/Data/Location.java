package Data;

public class Location{

    public enum location{
        CELL,
        CELLBLOCK,
        WORKSKHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }
    private location location;

    public Location(location location){
        this.location = location;
    }

    public location getName() {
        return this.location;
    }

    public void setName(location location) {
        this.location = location;
    }

    public String toString() {
        return "Location: " + this.location;
    }
}
