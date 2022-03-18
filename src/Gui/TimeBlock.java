package Gui;

import Data.Activity;
import Data.Location;
import Data.PrisonGroup;
import Data.Roster;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalTime;

public class TimeBlock {
    private PrisonGroup group;
    private String location;
    private int timeStartHour;
    private int timeStartMin;
    private int timeEndMinute;
    private int timeEndHour;
    private int groupNumber;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Rectangle2D rect;



    public TimeBlock(PrisonGroup group, String location, LocalTime timeStart, LocalTime timeEnd) {
        this.group = group;
        this.location = location;
        this.timeStartMin = timeStart.getMinute();
        this.timeStartHour = timeStart.getHour();
        this.timeEndHour = timeEnd.getHour();
        this.timeEndMinute = timeEnd.getMinute();

        this.groupNumber = group.getGroupID();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    
    public void setGroup(PrisonGroup group) {
        this.group = group;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTimeStartHour(int timeStartHour) {
        this.timeStartHour = timeStartHour;
    }
    public void setTimeStartMin(int timeStartMin) {
        this.timeStartMin = timeStartMin;
    }

    public String getGroup() {
        return group.getGroupName();
    }
    public String getLocation() {
        return location;
    }
    public int getTimeStartHour() {
        return timeStartHour;
    }
    public int getTimeStartMin() {
        return timeStartMin;
    }

    public TimeBlock getTimeblock(Event e){
        return this;
    }




    public void draw(FXGraphics2D g2d) {
        int x = groupNumber * 100;
        int y = timeStartHour * 60 + timeStartMin;
        int height = ((timeEndHour - timeStartHour) * 60 + timeEndMinute - timeStartMin);

        g2d.setColor(Color.BLACK);
        rect = new Rectangle2D.Double(x,y,100,height);
        g2d.setColor(Color.BLACK);
        g2d.fill(rect);
        g2d.setColor(Color.WHITE);
        g2d.drawString(this.group.getGroupName(),x, y+height/4);
        g2d.drawString(this.location,x,y+2*(height/4));
        g2d.drawString(this.timeStart.toString(),x,y+3*(height/4));
        g2d.drawString(this.timeEnd.toString(),x,y+4*(height/4));

    }


    public static TimeBlock convertToTimeblock(Activity a){
        TimeBlock b = new TimeBlock(a.getPrisonGroup(), a.getLocation().toString(), a.getStartTime(), a.getEndTime());
        return  b;

    }

}
