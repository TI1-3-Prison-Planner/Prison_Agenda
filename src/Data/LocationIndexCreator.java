package Data;

import java.io.Serializable;

//Class to create a String to use as Key value in the Roster Hashmap LocationDatabase
public class LocationIndexCreator implements Serializable {
    private String name;
    private Integer index;
    private String fullName;

    public LocationIndexCreator(String name, Integer index) {
        this.name = name;
        this.index = index;
        this.fullName = this.name + " " + this.index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String createIndex() {
        return this.fullName;
    }
}
