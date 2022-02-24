package Data;

import java.io.Serializable;

public class Location implements Serializable {
    private String locationName;
    private LocationType type;

    public Location(String locationName, LocationType locationtype){
        this.locationName = locationName;
        this.type = locationtype;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public LocationType getType(){return this.type;}

    public String toString() {
        return this.locationName;
    }

    //enumeration for the different locations on the tilemap.
    public enum LocationType {
        CELL,
        CELLBLOCK,
        WORKSKHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }
}
