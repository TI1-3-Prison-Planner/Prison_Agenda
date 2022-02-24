package Gui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Data.*;

import java.time.LocalTime;
import java.util.*;

public class DataViewer extends Observer {
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
        this.roster.attach(this);
        createTabs();
        fillGuardTab();
        fillInmateTab();
        fillGroupTab();
        fillLoactionTab();
        fillActivityTab();
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
        this.dataTab.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

    }

    public void fillGuardTab(){
        TableView<Person> guards = new TableView<>();
        TableColumn<Person, String> name = new TableColumn<Person, String>("names");
        name.setMinWidth(250);
        TableColumn<Person, String> isInGroup = new TableColumn<Person, String>("Assigned");

        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.addAll(roster.getGuardDatabase().keySet());

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        isInGroup.setCellValueFactory(cellData -> {
            boolean assigend = cellData.getValue().isInGroup();
            String TF;
            if(assigend){
                TF = "true";
            } else {
                TF = "false";
            }
            return new ReadOnlyStringWrapper(TF);
        });

        guards.getColumns().addAll(name, isInGroup);
        guards.setItems(persons);
        this.guards.setContent(guards);
    }

    public void fillInmateTab(){
        TableView<Person> guards = new TableView<>();
        TableColumn<Person, String> name = new TableColumn<Person, String>("names");
        name.setMinWidth(250);
        TableColumn<Person, String> isInGroup = new TableColumn<Person, String>("Assigned");

        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.addAll(roster.getInmateDatabase().keySet());

        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        isInGroup.setCellValueFactory(cellData -> {
            boolean assigend = cellData.getValue().isInGroup();
            String TF;
            if(assigend){
                TF = "true";
            } else {
                TF = "false";
            }
            return new ReadOnlyStringWrapper(TF);
        });

        guards.getColumns().addAll(name, isInGroup);
        guards.setItems(persons);
        this.inmates.setContent(guards);
    }

    public void fillGroupTab(){
        TableView<PrisonGroup> groups = new TableView();
        TableColumn<PrisonGroup, String> groupName = new TableColumn<PrisonGroup, String>("group name");
        TableColumn<PrisonGroup, ArrayList<Person>> inmates = new TableColumn<PrisonGroup, ArrayList<Person>>("inmates");
        TableColumn<PrisonGroup, ArrayList<Person>> guards = new TableColumn<PrisonGroup, ArrayList<Person>>("guards");
        TableColumn<PrisonGroup, PrisonGroup.SecurityDetail> detail = new TableColumn<PrisonGroup, PrisonGroup.SecurityDetail>("securityDetail");

        ObservableList<PrisonGroup> prisonGroups = FXCollections.observableArrayList(this.roster.getGroups());

        groupName.setCellValueFactory(new PropertyValueFactory<PrisonGroup, String>("groupName"));
        inmates.setCellValueFactory(new PropertyValueFactory<PrisonGroup, ArrayList<Person>>("inmates"));
        guards.setCellValueFactory(new PropertyValueFactory<PrisonGroup, ArrayList<Person>>("guards"));
        guards.setCellFactory(param -> {
            return new TableCell<PrisonGroup, ArrayList<Person>>()
            {
                @Override
                protected void updateItem(ArrayList<Person> item, boolean empty) {
                    if(!empty) {
                        String content = "";
                        for(Person p : item)
                            content += p.getName() + "\n";
                        this.setText(content.trim());
                    }
                }
            };
        });
        detail.setCellValueFactory(new PropertyValueFactory<PrisonGroup, PrisonGroup.SecurityDetail>("securityDetail"));


        groups.getColumns().addAll(groupName, inmates, guards, detail);
        groups.setItems(prisonGroups);
        this.groups.setContent(groups);
    }

    public void fillLoactionTab(){
        TableView<Location> locations = new TableView();
        TableColumn<Location, String> locationName = new TableColumn<Location, String>("Name");
        TableColumn<Location, Location.LocationType> locationType = new TableColumn<Location, Location.LocationType>("Type");


        ObservableList<Location> location = FXCollections.observableArrayList();
        for (Location locs : this.roster.getLocationDatabase().values()){
            location.add(locs);
        }

        locationName.setCellValueFactory(new PropertyValueFactory<Location, String>("locationName"));
        locationType.setCellValueFactory(new PropertyValueFactory<Location, Location.LocationType>("type"));


        locations.getColumns().addAll(locationName, locationType);
        locations.setItems(location);
        this.locations.setContent(locations);
    }

    public void fillActivityTab(){
        TableView<Activity> activity = new TableView<>();
        TableColumn<Activity, String> activityName = new TableColumn<Activity, String>("Name");
        TableColumn<Activity, LocalTime> startTime = new TableColumn<Activity, LocalTime>("Start Time");
        TableColumn<Activity, LocalTime> endTime = new TableColumn<Activity, LocalTime>("End Time");
        TableColumn<Activity, PrisonGroup> prisonGroup = new TableColumn<Activity, PrisonGroup>("Prison Group");
        TableColumn<Activity, Location> location = new TableColumn<Activity, Location>("Location");


        ObservableList<Activity> activities = FXCollections.observableArrayList(this.roster.getActivities());

        activityName.setCellValueFactory(new PropertyValueFactory<Activity, String>("activityName"));
        startTime.setCellValueFactory(new PropertyValueFactory<Activity, LocalTime>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<Activity, LocalTime>("endTime"));
        prisonGroup.setCellValueFactory(new PropertyValueFactory<Activity, PrisonGroup>("prisonGroup"));
        location.setCellValueFactory(new PropertyValueFactory<Activity, Location>("location"));

        activity.getColumns().addAll(activityName, startTime, endTime, prisonGroup, location);
        activity.setItems(activities);
        this.activities.setContent(activity);
    }

    public TabPane allTabs(){
        return this.dataTab;
    }


    @Override
    public void update() {
        fillGuardTab();
        fillInmateTab();
        fillGroupTab();
        fillLoactionTab();
        fillActivityTab();
    }


}
