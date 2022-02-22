package Gui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class PopUp {
    private Stage popUpDisplay;



    public PopUp(String title, Stage stage) {
        this.popUpDisplay = stage;
        this.popUpDisplay.setTitle(title);
        this.popUpDisplay.initModality(Modality.APPLICATION_MODAL);

    }

    public void display() {
    }


}
