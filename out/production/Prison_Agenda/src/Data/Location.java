package Data;

import java.io.Serializable;

public class Location implements Serializable {

    //enumeration for the different locations on the tilemap.
    public enum locationType{
        CELL,
        CELLBLOCK,
        WORKSKHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }
    public String locationName;
    public locationType type;


    public Location(String locationName, locationType locationtype){
        this.locationName = locationName;
        this.type = locationtype;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public locationType getType(){return this.type;}

    public String toString() {
        return this.locationName;
    }
}
