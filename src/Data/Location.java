package Data;

import java.io.Serializable;

public class Location implements Serializable {

    //enumeration for the different locations on the tilemap.
    public enum LocationType {
        CELL,
        CELLBLOCK,
        WORKSHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOOM
    }

    private String locationID;
    private String locationName;
    private LocationType type;

    public Location(String locationName, LocationType locationtype){
        this.locationName = locationName;
        this.type = locationtype;
    }

    public Location(String locationName, LocationType locationType, String locationID){
        this.locationName = locationName;
        this.type = locationType;
        this.locationID = locationID;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName(){
        return this.locationName;
    }

    public LocationType getType(){return this.type;}

    public String getLocationID() {
        return locationID;
    }

    public String toString() {
        return this.locationName;
    }


}
