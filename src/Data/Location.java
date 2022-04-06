package Data;

import java.io.Serializable;

public class Location implements Serializable {

    //enumeration for the different types of locations on the tilemap.
    public enum LocationType {
        CELL,
        CELLBLOCK,
        WORKSHOP,
        YARD,
        COMMONROOM,
        KITCHEN,
        BREAKROOM
    }

    private String locationID;
    private String locationName;
    private LocationType type;

    //2 contructors to choose if you want to add an ID to your location
    public Location(String locationName, LocationType locationtype){
        this.locationName = locationName;
        this.type = locationtype;
    }

    public Location(String locationName, LocationType locationType, String locationID){
        this.locationName = locationName;
        this.type = locationType;
        this.locationID = locationID;
    }
    public Location(String locationName){
        this.locationName = locationName;
    }

    //setters for the locationobject
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    //getters for the locationobject
    public String getLocationName(){
        return this.locationName;
    }

    public LocationType getType(){return this.type;}

    public String getLocationID() {
        return locationID;
    }

    //toString for the locationobject
    public String toString() {
        return this.locationName;
    }


}
