package Gui;

import Data.Location;
import Data.Roster;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewLocationPopup {
    private Stage newLocationPopupDisplay;
    private Label instructionLabel;
    private Label instructionLabel1;
    private TextField locationName;
    private ComboBox<Location.LocationType> locationTypeBox;
    private Button addButton;
    private Button cancelButton;
    private Roster roster;

    public NewLocationPopup(String title, Roster roster) {
        this.newLocationPopupDisplay = new Stage();
        this.newLocationPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        this.newLocationPopupDisplay.setTitle(title);
        this.instructionLabel = new Label("Type the name of the location:");
        this.instructionLabel1 = new Label("Choose the type of location:");
        this.locationName = new TextField();
        locationName.setMaxWidth(200);
        this.addButton = new Button("Add");
        this.cancelButton = new Button("Cancel");
        this.locationTypeBox = new ComboBox<>();
        this.locationTypeBox.getItems().setAll(Location.LocationType.values());

    }

    public void display() {
        this.cancelButton.setOnAction(e-> newLocationPopupDisplay.close());
        this.addButton.setOnAction(e-> {
                addLocation();
        });



        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addButton, cancelButton);
        buttonBox.setSpacing(50);
        vBox.getChildren().addAll(instructionLabel, locationName, instructionLabel1, locationTypeBox, buttonBox);
        vBox.setSpacing(10);
        HBox displayBox = new HBox(vBox);
        displayBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(displayBox, 300,200);
        newLocationPopupDisplay.setScene(scene);
        newLocationPopupDisplay.setResizable(false);
        newLocationPopupDisplay.showAndWait();
    }

    private void addLocation() {

    }
}
