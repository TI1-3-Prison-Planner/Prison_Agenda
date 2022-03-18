package Data;

import java.io.Serializable;

public class Location implements Serializable {

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

    private String locationName;
    private LocationType type;

    public Location(String locationName, LocationType locationtype){
        this.locationName = locationName;
        this.type = locationtype;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public LocationType getType(){return this.type;}

    public String toString() {
        return this.locationName;
    }


}
