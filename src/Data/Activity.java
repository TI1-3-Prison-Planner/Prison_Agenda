package Data;

import java.time.LocalTime;
import java.util.ArrayList;

public class Activity {

    private LocalTime startTime;
    private LocalTime endTime;
    private PrisonGroup prisonGroup;
    private Location location;


    public Activity(LocalTime startTime, LocalTime endTime, PrisonGroup group, Location location) {
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
        return "Activity: " + this.startTime + " - " + this.endTime + ", group: " + this.prisonGroup + ", location: " + this.location;
    }
}
