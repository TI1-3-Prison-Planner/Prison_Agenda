package Data;

import Gui.Observer;

import java.util.List;

public abstract class Subject {
    abstract void attach(Observer observer);
    abstract void detach(Observer observer);
    abstract void notifyObservers();
}
