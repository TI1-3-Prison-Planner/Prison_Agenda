package Gui;

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

public class NewGroupPopup extends Observer implements Popup {
    private ObserverRefresh obsRefresh;
    private Stage newGroupPopupDisplay;
    private Label groupNameInstruction;
    private Label securityDetailInstruction;
    private TextField groupName;
    private Label idInstruction;
    private TextField groupId;
    private ComboBox<PrisonGroup.SecurityDetail> securityTypeBox;
    private Button addButton;
    private Button cancelButton;
    private Button editButton;
    private Roster roster;
    private ErrorPopup errorPopup;
    private PrisonGroup pGroup;

    public NewGroupPopup(String title, Roster roster, ObserverRefresh obsRefresh) {
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
        this.obsRefresh = obsRefresh;
        this.obsRefresh.addObservers(this);
    }

    public NewGroupPopup(String title, Roster roster, PrisonGroup group, ObserverRefresh obsRefresh) {
        this.newGroupPopupDisplay = new Stage();
        this.newGroupPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        this.newGroupPopupDisplay.setTitle(title);
        this.groupNameInstruction = new Label("Type the name of the group:");
        this.securityDetailInstruction = new Label("Choose the type of security:");
        this.idInstruction = new Label("Type in the group ID:");
        this.groupName = new TextField();
        this.groupName.setMaxWidth(200);
        this.groupId = new TextField();
        this.editButton = new Button("edit");
        this.cancelButton = new Button("Cancel");
        this.securityTypeBox = new ComboBox<>();
        this.securityTypeBox.getItems().setAll(PrisonGroup.SecurityDetail.values());
        this.roster = roster;
        this.obsRefresh = obsRefresh;
        this.obsRefresh.addObservers(this);
        this.pGroup = group;
    }

    @Override
    public void display() {
        this.cancelButton.setOnAction(e -> close());


        //TODO fix duplicate code (make a new class perhaps)
        VBox vBox = new VBox();
        HBox buttonBox = new HBox();

        if (this.pGroup != null) {
            this.editButton.setOnAction(event -> editGroup());
            this.groupName.setText(pGroup.getGroupName());
            this.groupId.setText(String.valueOf(pGroup.getGroupID()));
            this.securityTypeBox.getSelectionModel().select(pGroup.getSecurityDetail());
            buttonBox.getChildren().addAll(this.editButton, this.cancelButton);

        } else {
            this.addButton.setOnAction(e -> addGroup());
            buttonBox.getChildren().addAll(this.addButton, this.cancelButton);
        }

        buttonBox.setSpacing(50);
        vBox.getChildren().addAll(this.groupNameInstruction, this.groupName, this.idInstruction, this.groupId, this.securityDetailInstruction, this.securityTypeBox, buttonBox);
        vBox.setSpacing(10);
        HBox displayItemBox = new HBox(vBox);
        displayItemBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(displayItemBox, 300, 275);
        this.newGroupPopupDisplay.setScene(scene);
        this.newGroupPopupDisplay.setResizable(false);
        this.newGroupPopupDisplay.showAndWait();


    }

    private void editGroup() {
        int index = this.roster.getGroups().indexOf(this.pGroup);

        this.pGroup.setGroupName(this.groupName.getText());
        this.pGroup.setGroupID(Integer.parseInt(this.groupId.getText()));
        this.pGroup.setSecurityDetail(this.securityTypeBox.getSelectionModel().getSelectedItem());

        if (this.roster.getGroups().get(index).getSecurityDetail().equals(this.pGroup.getSecurityDetail()))
            this.pGroup.refreshGuards(this.roster);

        this.roster.getGroups().set(index, pGroup);
        this.obsRefresh.updateAllObservers();
        close();
    }

    private void addGroup() {
        PrisonGroup prisonGroup = null;
        try {
            prisonGroup = new PrisonGroup(this.groupName.getText(),
                    Integer.parseInt(this.groupId.getText()),
                    this.securityTypeBox.getSelectionModel().getSelectedItem());

            if (!this.roster.getGroups().contains(prisonGroup) || !this.groupName.getText().equals("")) {
                if (hasText()) {
                    prisonGroup.addGuard(this.roster.getGuardDatabase());
                    prisonGroup.addInmates(this.roster.getInmateDatabase());
                    this.roster.getGroups().add(prisonGroup);
                    //TODO fill method can't be used this way!
                    this.obsRefresh.updateAllObservers();
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

    @Override
    public void close() {
        groupName.clear();
        securityTypeBox.getSelectionModel().selectFirst();
        newGroupPopupDisplay.close();
    }

    public boolean hasText() {
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
