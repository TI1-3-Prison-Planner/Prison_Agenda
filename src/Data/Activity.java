package Data;

import java.io.Serializable;
import java.time.LocalTime;

public class Activity implements Serializable {
    private String activityName;
    private LocalTime startTime;
    private LocalTime endTime;
    private PrisonGroup prisonGroup;
    private Location location;


    public Activity(String activityName, LocalTime startTime, LocalTime endTime, PrisonGroup group, Location location) {
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prisonGroup = group;
        this.location = location;
    }

    //setters for the activity object
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

    //getters for the activity object
    public LocalTime getStartTime() {
        return this.startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public PrisonGroup getPrisonGroup() {
        return prisonGroup;
    }
    public Location getLocation() {
        return location;
    }
    public String getActivityName() {
        return activityName;
    }

    //toString method to print certain activity
    @Override
    public String toString() {
        return "Activity: " + this.activityName + ", " + this.startTime + " - " + this.endTime + ", " + this.location.getLocationName() + ", " + this.prisonGroup;
    }
}
