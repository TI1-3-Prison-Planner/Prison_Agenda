package Gui;

import Data.Location;
import Data.LocationIndexCreator;
import Data.Roster;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
public class NewLocationPopup extends Observer implements Popup {
    private ObserverRefresh obsRefresh;
    private Stage newLocationPopupDisplay;
    private Label instructionLabel;
    private Label instructionLabel1;
    private TextField locationName;
    private ComboBox<Location.LocationType> locationTypeBox;
    private Button addButton;
    private Button cancelButton;
    private Button editButton;
    private Roster roster;
    private Location location;

    public NewLocationPopup(String title, Roster roster, ObserverRefresh obsRefresh) {
        this.newLocationPopupDisplay = new Stage();
        this.newLocationPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        this.newLocationPopupDisplay.setTitle(title);
        this.instructionLabel = new Label("Type the name of the location:");
        this.instructionLabel1 = new Label("Choose the type of location:");
        this.locationName = new TextField();
        this.locationName.setMaxWidth(200);
        this.addButton = new Button("Add");
        this.cancelButton = new Button("Cancel");
        this.locationTypeBox = new ComboBox<>();
        this.locationTypeBox.getItems().setAll(Location.LocationType.values());
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.obsRefresh.addObservers(this);
    }

    public NewLocationPopup(String title, Roster roster, ObserverRefresh obsRefresh, Location location) {
        this.newLocationPopupDisplay = new Stage();
        this.newLocationPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        this.newLocationPopupDisplay.setTitle(title);
        this.instructionLabel = new Label("Type the name of the location:");
        this.instructionLabel1 = new Label("Choose the type of location:");
        this.locationName = new TextField();
        this.locationName.setMaxWidth(200);
        this.editButton = new Button("Edit");
        this.cancelButton = new Button("Cancel");
        this.locationTypeBox = new ComboBox<>();
        this.locationTypeBox.getItems().setAll(Location.LocationType.values());
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.obsRefresh.addObservers(this);
        this.location = location;
    }

    @Override
    public void display() {
        this.cancelButton.setOnAction(e -> close());

        VBox vBox = new VBox();
        HBox buttonBox = new HBox();

        if (this.location != null) {
            this.editButton.setOnAction(event -> editLocation());
            this.locationName.setText(this.location.getLocationName());
            this.locationTypeBox.getSelectionModel().select(this.location.getType());
            buttonBox.getChildren().addAll(this.editButton, this.cancelButton);
        } else {
            this.addButton.setOnAction(e -> {
                addLocation();
                close();
            });
            buttonBox.getChildren().addAll(this.addButton, this.cancelButton);
        }

        buttonBox.setSpacing(50);
        vBox.getChildren().addAll(this.instructionLabel, this.locationName, this.instructionLabel1, this.locationTypeBox, buttonBox);
        vBox.setSpacing(10);
        HBox displayBox = new HBox(vBox);
        displayBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(displayBox, 300, 200);
        this.newLocationPopupDisplay.setScene(scene);
        this.newLocationPopupDisplay.setResizable(false);
        this.newLocationPopupDisplay.showAndWait();
    }

    //TODO andere indexering voor locatie hashmap nodig
    private void editLocation() {
        this.location.setLocationName(this.locationName.getText());
        this.location.setType(this.locationTypeBox.getSelectionModel().getSelectedItem());
        this.obsRefresh.updateAllObservers();
        close();
    }

    @Override
    public void close() {
        locationName.clear();
        locationTypeBox.getSelectionModel().selectFirst();
        newLocationPopupDisplay.close();
    }

    private void addLocation() {
        try {
            LocationIndexCreator indexer = new LocationIndexCreator(this.locationTypeBox.getValue(), this.roster);
            String index = indexer.indexCreator(false);

            if (!locationName.getText().equals("")) {
                this.roster.getLocationDatabase().put(index, new Location(this.locationName.getText(), this.locationTypeBox.getValue(), index));
                System.out.println(this.roster.getLocationDatabase());
                this.obsRefresh.updateAllObservers();
            } else {
                //TODO create error popuop
                System.out.println("fill a name in");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        this.locationTypeBox.getItems().setAll(Location.LocationType.values());
    }
}
