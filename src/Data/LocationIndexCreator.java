package Data;

import java.io.Serializable;
import java.util.ArrayList;

//Class to create a String to use as Key value in the Roster Hashmap LocationDatabase
public class LocationIndexCreator implements Serializable {
    private Integer index;
    private String fullName;
    private Location.LocationType locationType;
    private Roster roster;
    private ArrayList<Location> locations;


    public LocationIndexCreator(Location.LocationType locationType, Roster roster) {
        this.locationType = locationType;
        this.roster = roster;
        this.locations = new ArrayList<>(this.roster.getLocationDatabase().values());
    }

    public String indexCreator(Boolean check){
        int ammount = 0;
        for(Location location : this.locations){
            if(location.getType().equals(this.locationType)){
                ammount++;
            }
        }
        this.index = ammount;
        if(check){
            return this.fullName = this.locationType + " " + (this.index - 1);
        } else {
            return this.fullName = this.locationType + " " + this.index;

        }
    }
}
