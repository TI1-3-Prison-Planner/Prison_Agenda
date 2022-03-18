package Gui;

import Data.Person;
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

public class NewPersonPopup extends Observer implements Popup {
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

    public NewPersonPopup(String title, Roster roster, ObserverRefresh obsRefresh) {
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

//        Label inGroupLabel = new Label("Is the person assigned to a group?");
//        this.inGroupCheck = new CheckBox("Yes");
//        this.inGroupCheck.setAllowIndeterminate(false);
//
//        Label selectionLabel = new Label("Select a group:");
//        this.groupSelection = new ComboBox<>();
//        this.groupSelection.getItems().setAll(this.roster.getGroups());

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> {
            addPerson();
            this.obsRefresh.updateAllObservers();
            close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> close());

        HBox radioBox = new HBox(guardButton, inmateButton);
        radioBox.setAlignment(Pos.CENTER);
        radioBox.setSpacing(10);

        HBox buttonBox = new HBox(confirmButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

//        VBox subDisplayBox = new VBox(inGroupLabel, inGroupCheck);
//        subDisplayBox.setAlignment(Pos.CENTER);
//        subDisplayBox.setSpacing(10);

        VBox displayItemBox = new VBox(nameLabel, this.insertedName, statusLabel, radioBox, buttonBox);

//        //allows the user to select a group if inGroupCheck is selected
//        //otherwise the groupselection is hidden from the user
//        this.inGroupCheck.setOnAction(event -> {
//            if (this.inGroupCheck.isSelected()) {
//                subDisplayBox.getChildren().addAll(selectionLabel, groupSelection);
//            } else {
//                if (subDisplayBox.getChildren().contains(selectionLabel) &&
//                        subDisplayBox.getChildren().contains(groupSelection)) {
//                    subDisplayBox.getChildren().removeAll(selectionLabel, groupSelection);
//                }
//            }
//        });

        displayItemBox.setAlignment(Pos.TOP_CENTER);
        displayItemBox.setSpacing(10);

        Scene scene = new Scene(displayItemBox, 200, 150);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.showAndWait();
    }

    private void addPerson() {
        try {
            if (this.insertedName != null && this.toggleGroup.getSelectedToggle().isSelected()) {
                if (guardButton.isSelected()) {
                    this.roster.getGuardDatabase().put(new Person(this.insertedName.getText(), true), false);
                } else {
                    this.roster.getInmateDatabase().put(new Person(this.insertedName.getText(), false), false);
                }
            }
        } catch (Exception e) {
            Alert addAlert = new Alert(Alert.AlertType.ERROR, "Wrong input");
            addAlert.show();
        }

    }

    @Override
    public void close() {
        this.insertedName.clear();
        if (this.toggleGroup.getSelectedToggle() != null)
            this.toggleGroup.getSelectedToggle().setSelected(false);
//        if (this.inGroupCheck != null)
//            this.inGroupCheck.setSelected(false);
        this.stage.close();
    }

    @Override
    public void update() {

    }
}
