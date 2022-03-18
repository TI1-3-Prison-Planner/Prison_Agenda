package Gui;

import Data.PrisonGroup;
import Data.Roster;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Author: Moustapha Azaimi
 */

public class NewPersonPopup extends Observer implements Popup{
    private String title;
    private Roster roster;
    private ObserverRefresh obsRefresh;
    private Stage stage;
    private TextField insertedName;
    private RadioButton guardButton;
    private RadioButton inmateButton;
    private ToggleGroup toggleGroup;
    private CheckBox inGroupCheck;
    private boolean inGroup;
    private ComboBox<PrisonGroup> groupSelection;

    public NewPersonPopup(String title, Roster roster, ObserverRefresh obsRefresh){
        this.title = title;
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setTitle(title);
    }

    @Override
    public void display() {
        Label nameLabel = new Label("Type in the name:");
        this.insertedName = new TextField();
        this.insertedName.setMaxWidth(150);

        Label statusLabel = new Label("Status of person:");
        this.guardButton = new RadioButton("Guard");
        this.inmateButton = new RadioButton("Inmate");
        this.toggleGroup = new ToggleGroup();
        this.toggleGroup.getToggles().addAll(guardButton, inmateButton);

        Label groupLabel = new Label("Is the person assigned to a group?");
        this.inGroupCheck = new CheckBox("Yes");
        this.inGroupCheck.setAllowIndeterminate(false);

        Label selectionLabel = new Label("Select a group:");
        this.groupSelection = new ComboBox<>();
        this.groupSelection.getItems().setAll(this.roster.getGroups());

        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        HBox radioBox = new HBox(guardButton, inmateButton);
        radioBox.setAlignment(Pos.CENTER);
        radioBox.setSpacing(10);

        HBox buttonBox = new HBox(confirmButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        VBox displayItemBox = new VBox(nameLabel, insertedName, statusLabel, radioBox, groupLabel,
                inGroupCheck, buttonBox);
        

        displayItemBox.setAlignment(Pos.TOP_CENTER);
        displayItemBox.setSpacing(10);

        Scene scene = new Scene(displayItemBox,360,275);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.showAndWait();
    }

    @Override
    public void close() {

    }

    @Override
    public void update() {

    }
}
