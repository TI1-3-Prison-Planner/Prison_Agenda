package Gui;

import Data.Activity;
import Data.PrisonGroup;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalTime;

public class TimeBlock {
    private PrisonGroup group;
    private String location;
    private int timeStartHour;
    private int timeStartMin;
//    private int timeEnd;
    private int timeEndMinute;
    private int timeEndHour;
    private int groupNumber;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Rectangle2D rect;


    public TimeBlock(String group, String location, int timeStart, int timeEnd, int groupNumber) {
        this.group = group;
        this.location = location;
        this.timeStartMin = timeStart % 100;
        this.timeStartHour = timeStart / 100;
        this.timeEndHour = timeEnd / 100;
        this.timeEndMinute = timeEnd % 100;

        this.groupNumber = groupNumber;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    public TimeBlock(){

    }
    public TimeBlock getTimeblock(Event e){

        return this;
    }

    public String getGroup() {
        return group.getGroupName();
    }

    public void setGroup(PrisonGroup group) {
        this.group = group;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTimeStartHour() {
        return timeStartHour;
    }

    public void setTimeStartHour(int timeStartHour) {
        this.timeStartHour = timeStartHour;
    }

    public int getTimeStartMin() {
        return timeStartMin;
    }

    public void setTimeStartMin(int timeStartMin) {
        this.timeStartMin = timeStartMin;
    }

//    public int getTimeEnd() {
//        return timeEnd;
//    }

//    public void setTimeEnd(int timeEnd) {
//        this.timeEnd = timeEnd;
//    }

    public void draw(FXGraphics2D g) {
        int x = groupNumber * 100;
        int y = timeStartHour * 60 + timeStartMin;
        int height = ((timeEndHour - timeStartHour) * 60 + timeEndMinute - timeStartMin);



        g.setColor(Color.BLACK);
        rect = new Rectangle2D.Double(x,y,100,height);
        g.setColor(Color.BLACK);
        g.fill(rect);
        g.setColor(Color.WHITE);
        g.drawString(this.group.getGroupName(),x, y+height/4);
        g.drawString(this.location,x,y+2*(height/4));
        g.drawString(this.timeStart.toString(),x,y+3*(height/4));
        g.drawString(this.timeEnd.toString(),x,y+4*(height/4));

    }


    public static TimeBlock convertToTimeblcok(Activity a){


        TimeBlock b = new TimeBlock(a.getPrisonGroup(),a.getLocation().toString(),a.getStartTime(),a.getEndTime(),a.getPrisonGroup().getGroupNumber());
        return  b;

//         Rectangle2D rect = new Rectangle2D.Double(x, y, 60, height);
//         g.setColor(Color.BLACK);
//         g.fill(rect);
//         g.setColor(Color.WHITE);
//         g.drawString(this.group, x, y + height / 4);
//         g.drawString(this.location, x, y + 2 * (height / 4));
//         g.drawString(this.timeStartHour + ":" + this.timeStartMin, x, y + 3 * (height / 4));
//         g.drawString(this.timeEndHour + ":" + this.timeEndMinute, x, y + 4 * (height / 4));

    }
}
