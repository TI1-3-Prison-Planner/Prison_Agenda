package Gui;

import Data.Roster;

public abstract class Observer {
    protected Roster roster;
    public abstract void update();
}
