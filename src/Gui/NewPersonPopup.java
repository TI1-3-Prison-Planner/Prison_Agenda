package Gui;

import Data.Roster;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewPersonPopup extends Observer implements Popup{
    private String title;
    private Roster roster;
    private ObserverRefresh obsRefresh;
    private Stage stage;

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
        HBox displayItemBox = new HBox();
        Scene scene = new Scene(displayItemBox, 300, 275);
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
