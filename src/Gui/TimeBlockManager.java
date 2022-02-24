package Gui;

import Data.Activity;
import Data.Roster;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class TimeBlockManager extends Observer {
    private Roster roster;
    private FXGraphics2D graphics2D;

    public TimeBlockManager(Roster roster, FXGraphics2D graphics){
        this.roster = roster;
        this.graphics2D = graphics;
    }

    public void draw(){
        if(roster.getActivities().size()>0) {
            for (Activity a : roster.getActivities()) {

                TimeBlock.convertToTimeblock(a).draw(this.graphics2D);
            }
        }
        this.graphics2D.setColor(Color.BLACK);
    }


    @Override
    public void update() {
        draw();
    }
}
