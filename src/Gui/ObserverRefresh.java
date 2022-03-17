package Gui;

import java.util.ArrayList;

public class ObserverRefresh {
    private ArrayList<Observer> observers;

    //TODO fix the entire observer pattern
    public ObserverRefresh(){
        this.observers = new ArrayList<>();
    }

    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }


    public void updateAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
