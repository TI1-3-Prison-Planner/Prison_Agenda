package Gui;

import Data.Roster;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.geom.Line2D;


/**
 * Author: Moustapha Azaimi, Thomas Dam, Martijn van der Linden en Simon van der Wulp
 */

public class Gui extends Application {
	private Canvas canvas;
	private TabPane tabPane;
	private Tab rosterTab;
	private Tab tableTab;
	private TableView tableView;
	private MenuBar menuBar;
	private Menu fileMenu;
	private Menu editMenu;
	private Menu deleteMenu;
	private BorderPane mainPane;
	private Roster roster;
	private AgendaCreator agendaCreator;
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Agenda GUI");
		this.mainPane = new BorderPane();
		this.tabPane = new TabPane();
		this.rosterTab = new Tab();
		this.canvas = new ResizableCanvas(g -> draw(g), this.mainPane);
		this.tableTab = new Tab();
		this.tableView = new TableView();
		this.roster = new Roster();
		this.agendaCreator = new AgendaCreator();
		this.agendaCreator.init(this.roster);

		MenuItem itemNew = new MenuItem("New");
		itemNew.setOnAction(e-> agendaCreator.display(stage));

		this.fileMenu = new Menu("File");
		this.fileMenu.getItems().add(itemNew);

		this.editMenu = new Menu("Edit");
		this.editMenu.setOnAction(e->{
			//TODO, Edit code missing
		});

		this.deleteMenu = new Menu("Delete");
		this.deleteMenu.setOnAction(e-> {
			//TODO, Delete code missing
		});



		ScrollPane scrollableCenter = new ScrollPane(this.canvas);
		rosterTab.setClosable(false);
		tableTab.setClosable(false);
		tabPane.setSide(Side.LEFT);
		rosterTab.setContent(scrollableCenter);
		tabPane.getTabs().addAll(rosterTab,tableTab);
		this.menuBar = new MenuBar(this.fileMenu, this.editMenu, this.deleteMenu);
		this.mainPane.setTop(this.menuBar);
		this.mainPane.setCenter(tabPane);

		Scene scene = new Scene(this.mainPane, 700, 700);
		stage.setScene(scene);
		stage.show();
	}

	public void init() {
		//TODO, Init code missing
	}


	public void draw(FXGraphics2D graphics) {
		//TODO, improve time display left side
		int hours = 0;

		for (int i = 0; i < 1500; i += 60) {
			graphics.draw(new Line2D.Double(0, i, 100, i));

			if (i > 1 && hours < 23) {
				graphics.drawString(hours + ":00 - " + (hours + 1) + ":00", 10, i - 25);
				hours++;
			} else if (hours == 23) {
				graphics.drawString(hours + ":00 - 00:00", 10, i - 25);
				hours++;
			}
		}
	}



	public static void main(String[] args) {
		launch(args);
	}

}
