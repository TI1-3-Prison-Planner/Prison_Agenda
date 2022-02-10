package Data;

import java.time.LocalTime;
import java.util.ArrayList;

public class Activity {

    private LocalTime startTime;
    private LocalTime endTime;
    private PrisonGroup prisonGroup;
    private Location location;

    public Activity(){

    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

}
