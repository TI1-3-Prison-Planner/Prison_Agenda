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
 * Author: Moustapha Azaimi & Ramon Rampaart
 */

public class NewPersonPopup implements Popup {

    //Private attributes
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
    private Person person;

    //Constructor to initialize the attributes
    public NewPersonPopup(String title, Roster roster, ObserverRefresh obsRefresh) {
        this.title = title;
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setTitle(title);
    }

    //Constructor to initialize the attributes
    public NewPersonPopup(String title, Roster roster, ObserverRefresh obsRefresh, Person person) {
        this.title = title;
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.setTitle(title);
        this.person = person;
    }

    /**
     * Popup display to create a new person in the database
     */
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

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> {
            addPerson();
            this.obsRefresh.updateAllObservers();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> close());

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> editPerson());

        HBox radioBox = new HBox(guardButton, inmateButton);
        radioBox.setAlignment(Pos.CENTER);
        radioBox.setSpacing(10);

        HBox buttonBox = new HBox();

        if (this.person != null) {
            buttonBox.getChildren().addAll(editButton, cancelButton);
            this.insertedName.setText(this.person.getName());
            if (this.person.isGuard()) {
                this.guardButton.setSelected(true);
            } else {
                this.inmateButton.setSelected(true);
            }
        } else {
            buttonBox.getChildren().addAll(confirmButton, cancelButton);
        }

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);

        VBox displayItemBox = new VBox(nameLabel, this.insertedName, statusLabel, radioBox, buttonBox);

        displayItemBox.setAlignment(Pos.TOP_CENTER);
        displayItemBox.setSpacing(10);

        Scene scene = new Scene(displayItemBox, 200, 150);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.showAndWait();
    }

    /**
     * Popup display to edit a person in the database
     */
    private void editPerson() {
        this.person.setName(this.insertedName.getText());
        if (guardButton.isSelected()) {
            this.person.setGuard(true);

            this.roster.getGuardDatabase().add(this.person);
            this.roster.getInmateDatabase().remove(this.person);
        } else {
            this.person.setGuard(false);

            this.roster.getInmateDatabase().add(this.person);
            this.roster.getGuardDatabase().remove(this.person);
        }
        this.roster.manageGroups(this.person);
        this.obsRefresh.updateAllObservers();
        close();
    }

    /**
     * Method to help to popup display to add a person to the database
     */
    private void addPerson() {
        try {
            if (this.insertedName != null && this.toggleGroup.getSelectedToggle().isSelected()) {
                if (guardButton.isSelected()) {
                    this.roster.getGuardDatabase().add(new Person(this.insertedName.getText(), true));
                } else {
                    this.roster.getInmateDatabase().add(new Person(this.insertedName.getText(), false));
                }
            }
            close();
        } catch (Exception e) {
            Alert addAlert = new Alert(Alert.AlertType.ERROR, "Wrong input");
            addAlert.show();
        }

    }

    /**
     * Method to clear and close the popup
     */
    @Override
    public void close() {
        this.insertedName.clear();
        if (this.toggleGroup.getSelectedToggle() != null)
            this.toggleGroup.getSelectedToggle().setSelected(false);
        this.stage.close();
    }
}
