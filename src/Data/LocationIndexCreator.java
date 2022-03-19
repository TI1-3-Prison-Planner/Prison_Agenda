package Data;

import javafx.scene.layout.BorderPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

//Class to create a String to use as Key value in the Roster Hashmap LocationDatabase
public class LocationIndexCreator implements Serializable {
    private String name;
    private Integer index;
    private String fullName;
    private Location.LocationType location;
    private Roster roster;
    private ArrayList<Location> locations;


    public LocationIndexCreator(Location.LocationType location, Roster roster, String name) {
        this.location = location;
        this.roster = roster;
        this.locations = new ArrayList<>(this.roster.getLocationDatabase().values());
        this.name = name;
        System.out.println(this.locations);
    }

    public String indexCreator(Boolean check){
        int ammount = 0;
        for(Location location : this.locations){
            if(location.getType().equals(this.location)){
                ammount++;
            }
        }
        this.index = ammount;
        if(check){
            return this.fullName = this.name + " " + (this.index - 1);
        } else {
            return this.fullName = this.name + " " + this.index;

        }
    }


    public String getName() {
        return name;
    }
    public Integer getIndex() {
        return index;
    }
    public String getFullName() {
        return fullName;
    }
}
