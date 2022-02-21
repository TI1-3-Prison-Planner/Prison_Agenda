package Gui;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TimeBlock {
    private String group;
    private String location;
    private int timeStartHour;
    private int timeStartMin;
    private int timeEnd;
    private int timeEndMinute;
    private int timeEndHour;
    private int groupNumber;


    public TimeBlock(String group, String location, int timeStart, int timeEnd,int groupNumber) {
        this.group = group;
        this.location = location;
        this.timeStartMin = timeStart %100;
        this.timeStartHour = timeStart /100;
        this.timeEndHour = timeEnd / 100;
        this.timeEndMinute = timeEnd %100;
        this.groupNumber = groupNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
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

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void draw(FXGraphics2D g){
        int x = groupNumber*100;
        int y = timeStartHour*60+timeStartMin;
        int height = ((timeEndHour - timeStartHour)*60+timeEndMinute-timeStartMin);

       ;
        System.out.println(this.group);


        Rectangle2D rect = new Rectangle2D.Double(x,y,60,height);
        g.setColor(Color.BLACK);
        g.fill(rect);
        g.setColor(Color.WHITE);
        g.drawString(this.group,x, y+height/4);
        g.drawString(this.location,x,y+2*(height/4));
        g.drawString(this.timeStartHour+":"+this.timeStartMin,x,y+3*(height/4));
        g.drawString(this.timeEndHour+":"+this.timeEndMinute,x,y+4*(height/4));
    }
}
