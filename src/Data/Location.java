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
    private String locationName;
    private locationType locationType;


    public Location(String locationName, locationType location){
        this.locationName = locationName;
        this.locationType = location;
    }

    //getters to return the location name and type.
    public locationType getLocationType(){
        return this.locationType;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public String toString() {
        return this.locationName + ", " + this.locationType;
    }
}
