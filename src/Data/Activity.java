package Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Activity implements Serializable {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private PrisonGroup prisonGroup;
    private Location location;


    public Activity(String name, LocalTime startTime, LocalTime endTime, PrisonGroup group, Location location) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prisonGroup = group;
        this.location = location;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setPrisonGroup(PrisonGroup prisonGroup) {
        this.prisonGroup = prisonGroup;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PrisonGroup getPrisonGroup() {
        return prisonGroup;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "\nActivity: " + name + ", " + this.startTime + " - " + this.endTime + ", " + this.location + ", " + this.prisonGroup;
    }
}
