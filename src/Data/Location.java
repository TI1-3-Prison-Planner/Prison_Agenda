package Data;

import java.io.Serializable;

public class Location implements Serializable {

    public enum locationType{
        CELL,
        CELLBLOCK,
        WORKSKHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }
    private String locationName;
    private locationType locationType;


    public Location(String locationName, locationType location){
        this.locationName = locationName;
        this.locationType = location;
    }

    public locationType getName(){
        return this.locationType;
    }

    public String toString() {
        return this.locationName + ", " + this.locationType;
    }
}
