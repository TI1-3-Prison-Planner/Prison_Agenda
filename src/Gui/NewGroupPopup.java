package Gui;

import Data.Location;
import Data.Person;
import Data.PrisonGroup;
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

public class NewGroupPopup extends Observer {
    private Stage newGroupPopupDisplay;
    private Label groupNameInstruction;
    private Label securityDetailInstruction;
    private TextField groupName;
    private Label idInstruction;
    private TextField groupId;
    private ComboBox<PrisonGroup.SecurityDetail> securityTypeBox;
    private Button addButton;
    private Button cancelButton;
    private Roster roster;
    private ErrorPopup errorPopup;

    public NewGroupPopup(String title, Roster roster) {
        this.newGroupPopupDisplay = new Stage();
        this.newGroupPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        this.newGroupPopupDisplay.setTitle(title);
        this.groupNameInstruction = new Label("Type the name of the group:");
        this.securityDetailInstruction = new Label("Choose the type of security:");
        this.idInstruction = new Label("Type in the group ID:");
        this.groupName = new TextField();
        this.groupName.setMaxWidth(200);
        this.groupId = new TextField();
        this.addButton = new Button("Add");
        this.cancelButton = new Button("Cancel");
        this.securityTypeBox = new ComboBox<>();
        this.securityTypeBox.getItems().setAll(PrisonGroup.SecurityDetail.values());
        this.roster = roster;
        this.roster.attach(this);
    }

    public void display() {
        this.roster.getGuardDatabase().put(new Person("Bobby", true), false);
        this.cancelButton.setOnAction(e -> close());
        this.addButton.setOnAction(e -> {
            addGroup();
        });
        //TODO fix duplicate code (make a new class perhaps)
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addButton, cancelButton);
        buttonBox.setSpacing(50);
        vBox.getChildren().addAll(groupNameInstruction, groupName, idInstruction, groupId, securityDetailInstruction, securityTypeBox, buttonBox);
        vBox.setSpacing(10);
        HBox displayItemBox = new HBox(vBox);
        displayItemBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(displayItemBox, 300, 275);
        newGroupPopupDisplay.setScene(scene);
        newGroupPopupDisplay.setResizable(false);
        newGroupPopupDisplay.showAndWait();

    }

    private void addGroup() {
        PrisonGroup prisonGroup = null;
        try {
            prisonGroup = new PrisonGroup(groupName.getText(), Integer.parseInt(groupId.getText()), securityTypeBox.getSelectionModel().getSelectedItem());

            if (!roster.getGroups().contains(prisonGroup) || !groupName.getText().equals("")) {
                if (hasText()) {
                    prisonGroup.addGuard(this.roster.getGuardDatabase());
                    prisonGroup.addInmates(this.roster.getInmateDatabase());
                    this.roster.getGroups().add(prisonGroup);
                    //TODO fill method can't be used this way!
                    roster.notifyObservers();
                    close();
                }
            }

            if (!hasText()) {
                this.errorPopup.setErrorMessage("Incorrect input. Please fill every cell correctly.");
                this.errorPopup.display();
            }

        } catch (NumberFormatException | NullPointerException e) {
            prisonGroup = null;
            ErrorPopup exceptionPopup = new ErrorPopup("Incorrect input. Please fill every cell correctly.");
//            exceptionPopup.setErrorMessage("Incorrect input. Please fill every cell correctly.");
            exceptionPopup.display();
        }
    }

    private void close() {
        groupName.clear();
        securityTypeBox.getSelectionModel().selectFirst();
        newGroupPopupDisplay.close();
    }

    public boolean hasText(){
        boolean hasText = false;
        for (char character : groupName.getText().toCharArray()) {
            if (Character.isLetterOrDigit(character)) {
                hasText = true;
            }
        }
        return hasText;
    }
    @Override
    public void update() {
        this.securityTypeBox.getItems().setAll(PrisonGroup.SecurityDetail.values());
    }
}
