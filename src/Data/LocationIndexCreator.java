package Data;

import java.io.Serializable;
import java.util.ArrayList;

//Class to create a String to use as Key value in the Roster Hashmap LocationDatabase
public class LocationIndexCreator implements Serializable {
    private Integer index;
    private Location.LocationType locationType;
    private Roster roster;
    private ArrayList<Location> locations;


    public LocationIndexCreator(Location.LocationType locationType, Roster roster) {
        this.locationType = locationType;
        this.roster = roster;
        this.locations = new ArrayList<>(this.roster.getLocationDatabase().values());
    }

    /**
     * method to add an index to all locations.
     * @param check
     * @return String with the location type and an index
     */
    public String indexCreator(Boolean check){
        int amount = 0;
        for(Location location : this.locations){
            if(location.getType().equals(this.locationType)){
                amount++;
            }
        }
        this.index = amount;
        if(check){
            return this.locationType + " " + (this.index - 1);
        } else {
            return this.locationType + " " + this.index;
        }
    }
}
