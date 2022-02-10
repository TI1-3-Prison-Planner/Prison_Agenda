package Data;

import java.time.LocalTime;

public class Activity implements CRUD{

    private LocalTime startTime;
    private LocalTime endTime;
    private Groups groups;
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

    @Override
    public void add() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }
}
