package Gui;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import Data.*;

import java.time.LocalTime;
import java.util.*;

public class DataViewer extends Observer{
    private TabPane dataTab;
    private Tab guards;
    private Tab inmates;
    private Tab groups;
    private Tab locations;
    private Tab activities;
    private Roster roster;
    private ObserverRefresh obsRefresh;

    private TableView<Person> guardsTable;
    private TableView<Person> inmateTable;
    private TableView<PrisonGroup> groupTable;
    private TableView<Location> locationTable;
    private TableView<Activity> activityTable;


    DataViewer(Roster roster, ObserverRefresh obsRefresh){
        this.dataTab = new TabPane();
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.obsRefresh.addObservers(this);
        createTabs();
        fillGuardTab();
        fillInmateTab();
        fillGroupTab();
        fillLocationTab();
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
        this.guardsTable = new TableView<>();
        TableColumn<Person, String> name = new TableColumn<Person, String>("names");
        name.setMinWidth(250);
        TableColumn<Person, String> isInGroup = new TableColumn<Person, String>("Assigned");

        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.addAll(this.roster.getGuardDatabase());

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

        this.guardsTable.getColumns().addAll(name, isInGroup);
        this.guardsTable.setItems(persons);
        this.guards.setContent(this.guardsTable);
    }

    public void fillInmateTab(){
        this.inmateTable = new TableView<>();
        TableColumn<Person, String> name = new TableColumn<Person, String>("names");
        name.setMinWidth(250);
        TableColumn<Person, String> isInGroup = new TableColumn<Person, String>("Assigned");

        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.addAll(roster.getInmateDatabase());

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

        this.inmateTable.getColumns().addAll(name, isInGroup);
        this.inmateTable.setItems(persons);
        this.inmates.setContent(this.inmateTable);
    }

    public void fillGroupTab(){
        this.groupTable = new TableView<>();
        TableColumn<PrisonGroup, String> groupName = new TableColumn<PrisonGroup, String>("group name");
        TableColumn<PrisonGroup, ArrayList<Person>> inmates = new TableColumn<PrisonGroup, ArrayList<Person>>("inmates");
        TableColumn<PrisonGroup, ArrayList<Person>> guards = new TableColumn<PrisonGroup, ArrayList<Person>>("guards");
        TableColumn<PrisonGroup, PrisonGroup.SecurityDetail> detail = new TableColumn<PrisonGroup, PrisonGroup.SecurityDetail>("securityDetail");

        ObservableList<PrisonGroup> prisonGroups = FXCollections.observableArrayList(this.roster.getGroups());

        groupName.setCellValueFactory(new PropertyValueFactory<PrisonGroup, String>("groupName"));
        inmates.setCellValueFactory(new PropertyValueFactory<PrisonGroup, ArrayList<Person>>("inmates"));
        inmates.setCellFactory(param -> {
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
        this.groupTable.getColumns().addAll(groupName, inmates, guards, detail);
        this.groupTable.setItems(prisonGroups);
        this.groups.setContent(this.groupTable);
    }

    public void fillLocationTab(){
        this.locationTable = new TableView<>();

        TableColumn<Location, String> locationID = new TableColumn<>("ID");
        TableColumn<Location, String> locationName = new TableColumn<Location, String>("Name");
        TableColumn<Location, Location.LocationType> locationType = new TableColumn<Location, Location.LocationType>("Type");

        ObservableList<Location> location = FXCollections.observableArrayList();
        location.addAll(this.roster.getLocationDatabase().values());

        locationID.setCellValueFactory(new PropertyValueFactory<Location, String>("locationID"));
        locationName.setCellValueFactory(new PropertyValueFactory<Location, String>("locationName"));
        locationType.setCellValueFactory(new PropertyValueFactory<Location, Location.LocationType>("type"));

        this.locationTable.getColumns().addAll(locationID, locationName, locationType);
        this.locationTable.setItems(location);
        this.locations.setContent(this.locationTable);
        this.locationTable.getSortOrder().add(locationID);
    }

    public void fillActivityTab(){
        this.activityTable = new TableView<>();
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

        this.activityTable.getColumns().addAll(activityName, startTime, endTime, prisonGroup, location);
        this.activityTable.setItems(activities);
        this.activities.setContent(this.activityTable);
    }

    public TabPane allTabs(){
        return this.dataTab;
    }

    public TabPane getDataTab() {
        return dataTab;
    }

    public TableView<Person> getGuardsTable() {
        return guardsTable;
    }

    public TableView<Activity> getActivityTable() {
        return activityTable;
    }

    public TableView<Person> getInmateTable() {
        return inmateTable;
    }

    public TableView<PrisonGroup> getGroupTable() {
        return groupTable;
    }

    public TableView<Location> getLocationTable() {
        return locationTable;
    }

    @Override
    public void update() {
        fillGuardTab();
        fillInmateTab();
        fillGroupTab();
        fillLocationTab();
//        test();
        fillActivityTab();
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

}
