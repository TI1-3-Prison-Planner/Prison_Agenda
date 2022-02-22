package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorPopup {

    private final String errorMessage;


    public ErrorPopup(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void display(Stage stage) {

        GridPane grid = new GridPane();
        Stage errorPopupDisplay = new Stage();

        errorPopupDisplay.initModality(Modality.APPLICATION_MODAL);
        errorPopupDisplay.setTitle("Error");
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new Label(this.errorMessage));

        Button button = new Button("Ok");

        BorderPane.setAlignment(button, Pos.BOTTOM_CENTER);

        borderPane.setBottom(button);
        button.setOnAction(e -> {
            errorPopupDisplay.close();
        });

        Scene activityScene = new Scene(borderPane, 300, 100);


        errorPopupDisplay.setScene(activityScene);
        errorPopupDisplay.showAndWait();


        stage.show();
    }

}
