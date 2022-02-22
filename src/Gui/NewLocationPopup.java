package Gui;

import Data.Location;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewLocationPopup {
    private Stage newLocationPopup;
    private Label instructionLabel;
    private Label instructionLabel1;
    private TextField locationName;
    private ComboBox<String> locationTypeBox;
    private Button addButton;
    private Button cancelButton;
    private Enum locationType;

    public NewLocationPopup(String title, Stage stage, Location.LocationType locationTypes) {
        this.newLocationPopup = stage;
        this.newLocationPopup.setTitle(title);
        this.instructionLabel = new Label("Type the name of the location:");
        this.instructionLabel1 = new Label("Choose the type of location:");
        this.addButton = new Button("Add");
        this.addButton = new Button("Cancel");
        this.locationType = locationTypes;

    }
}
