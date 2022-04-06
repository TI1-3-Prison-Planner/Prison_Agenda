package Gui;

import java.util.ArrayList;

public class ObserverRefresh {
    private ArrayList<Observer> observers;

    //Constructor to initialize the Attribute
    public ObserverRefresh(){
        this.observers = new ArrayList<>();
    }

    //Method to add observers to the Observer ArrayList
    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Method to update all observers
     */
    public void updateAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
