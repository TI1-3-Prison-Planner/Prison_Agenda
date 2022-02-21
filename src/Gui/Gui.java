package Gui;

import Data.Roster;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


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
	private ActivityCreator agendaCreator;

	
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
		this.agendaCreator = new ActivityCreator();
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



		//test code to fill array with timeBlocks
		agendaCreator.timeBlocks.add(new TimeBlock("midas","thuis",1000,1200));
		agendaCreator.timeBlocks.add(new TimeBlock("midas3","thuis",1300,1400));
		agendaCreator.timeBlocks.add(new TimeBlock("midas4","thuis",1600,1800));
		agendaCreator.timeBlocks.add(new TimeBlock("midas5","thuis",530,845));
		agendaCreator.timeBlocks.add(new TimeBlock("midas6","thuis",1900,2000));

		StackPane flowPane = new StackPane(this.canvas);
		flowPane.setPrefHeight(1440);

		canvas.setHeight(flowPane.getHeight());
		ScrollPane scrollableCenter = new ScrollPane(flowPane);


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
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0,0,(int)canvas.getWidth(),(int)canvas.getHeight());
//		graphics.clearRect(0,0,(int)canvas.getWidth(),(int)canvas.getHeight());

		 int hours = 0;
		for (TimeBlock  o :agendaCreator.timeBlocks ) {
			o.draw(graphics);
			System.out.println("test");
		}
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < 1800; i += 60) {
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
