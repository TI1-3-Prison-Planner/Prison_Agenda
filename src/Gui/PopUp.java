package Gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {
    private Stage popUpDisplay;
    private BorderPane borderPane;
    private Button cancelButton;
    private Button confirmButton;


    public PopUp(String title, Stage stage) {
        this.popUpDisplay = stage;
        this.popUpDisplay.setTitle(title);
        this.popUpDisplay.initModality(Modality.APPLICATION_MODAL);
        this.borderPane = new BorderPane();
        this.confirmButton = new Button("Confirm");
        this.cancelButton = new Button("Cancel");
    }


}
