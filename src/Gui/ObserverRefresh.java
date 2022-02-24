package Gui;

import java.util.ArrayList;

public class ObserverRefresh {
    private ArrayList<Observer> observers;

    public ObserverRefresh(){
        this.observers = new ArrayList<>();
    }

    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }

    public void update(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
