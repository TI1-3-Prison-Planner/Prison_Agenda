package Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Data.*;

import java.util.*;

public class DataViewer {
    private TabPane dataTab;
    private Tab guards;
    private Tab inmates;
    private Tab groups;
    private Tab locations;
    private Tab activities;
    private Roster roster;

    DataViewer(Roster roster){
        this.dataTab = new TabPane();
        this.roster = roster;
        createTabs();
        fillGuardTab();
    }

    public void createTabs(){
        this.guards = new Tab("Guards");
        this.inmates = new Tab("Inmates");
        this.groups = new Tab("Groups");
        this.locations = new Tab("Locations");
        this.activities = new Tab("Activities");

        this.dataTab.getTabs().addAll(this.guards,
                                                this.inmates,
                                                this.groups,
                                                this.locations,
                                                this.activities);
    }

    public void fillGuardTab(){
        TableView guards = new TableView();
        TableColumn name = new TableColumn("names");
        TableColumn isInGroup = new TableColumn("Assigned");

        ListView listView = new ListView();

        ObservableList<Person> persons = FXCollections.observableArrayList();
        for (Person pers : roster.getGuardDatabase().keySet()){
            persons.add(pers);
        }

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        isInGroup.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("isInGroup"));

        guards.getColumns().addAll(name, isInGroup);
        guards.setItems(persons);

        this.guards.setContent(guards);
    }

    public TabPane allTabs(){
        return this.dataTab;
    }



}
