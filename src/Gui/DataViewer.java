package Gui;

import javafx.collections.ObservableMap;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Data.*;

public class DataViewer {
    private TabPane dataTab;
    private Tab guards;
    private Tab inmates;
    private Tab groups;
    private Tab locations;
    private Tab activities;
    private Roster roster;

    DataViewer(Stage stage){
        this.dataTab = new TabPane();
        this.roster = new Roster();
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
        TableColumn name = new TableColumn("name");
        TableColumn isInGroup = new TableColumn("Assigned");

        ObservableMap map = (ObservableMap) roster.getGuardDatabase().keySet();

        name.getColumns().add(map);
//        isInGroup.getColumns().add();

        guards.getColumns().addAll(name, isInGroup);
        this.guards.setContent(guards);
    }

    public TabPane allTabs(){
        return this.dataTab;
    }

}
