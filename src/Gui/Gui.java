package Gui;


import Data.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Line2D;

import java.io.File;

import java.util.ArrayList;


/**
 * Author: Moustapha Azaimi, Thomas Dam, Martijn van der Linden en Simon van der Wulp, Midas filius.
 */

public class Gui extends Application {

	protected Canvas canvas;
	private Canvas canvasTime;
	private TabPane tabPane;
	private Tab rosterTab;
	private Tab tableTab;
	private TableView tableView;
	private MenuBar menuBar;
	private Menu fileMenu;
	private Menu editMenu;
	private MenuItem delete;
	private BorderPane mainPane;
	private Roster roster;
	private ActivityCreator agendaCreator;
	private ArrayList<TimeBlock>timeBlocks = new ArrayList<>();
	private DataViewer dataViewer;
	private TimeBlockManager TBM;
	private Menu newMenu;
	private NewGroupPopup newGroupPopup;
	private NewLocationPopup newLocationPopup;
	private FileIO fileIO;
	private ObserverRefresh obsRefresh;
	private MenuItem refresh;
	private MenuItem edit;


	@Override
	public void start(Stage stage) {
		//Testcode test = new Testcode();


		stage.setTitle("Agenda GUI");
		this.mainPane = new BorderPane();
		this.tabPane = new TabPane();
		this.rosterTab = new Tab();
		this.obsRefresh = new ObserverRefresh();

		this.canvas = new ResizableCanvas(g -> draw(g), this.mainPane);
		
		this.tableTab = new Tab();
		this.tableView = new TableView();

		canvas.setOnMouseEntered(e-> draw(new FXGraphics2D(canvas.getGraphicsContext2D())));


		this.fileIO = new FileIO();
		File file = new File("roster.json");
		this.roster = this.fileIO.readData(file);


		this.dataViewer = new DataViewer(this.roster, this.obsRefresh);
		this.agendaCreator = new ActivityCreator(this.roster, this.obsRefresh);
		this.newGroupPopup = new NewGroupPopup("add new group", this.roster, this.obsRefresh);
		this.newLocationPopup = new NewLocationPopup("add new location", this.roster, this.obsRefresh);
		fillMenuBar(stage);
		createPanes();
		fillTableTab();
//		testCode();

		Scene scene = new Scene(this.mainPane, 700, 700);
		stage.setScene(scene);
		stage.show();
	}

	public void init() {
		//TODO, Init code missing
	}

	public void draw(FXGraphics2D graphics) {
		//TODO, improve time display left side
//		graphics.setColor(Color.WHITE);
        graphics.setBackground(Color.white);
//		graphics.drawRect(0,0,(int)canvas.getWidth(),(int)canvas.getHeight());
		graphics.clearRect(0,0,(int)this.canvas.getWidth(),(int)this.canvas.getHeight());


		drawTime(graphics);
		this.TBM = new TimeBlockManager(this.roster, graphics);
		this.TBM.draw();
//		drawTimeBlocks(graphics);

	}

	public void fillMenuBar(Stage stage) {

		this.fileMenu = new Menu("File");

		MenuItem save = new MenuItem("Save file");
		save.setOnAction(e -> saveFile(stage));

		MenuItem load = new MenuItem("Load file");
		load.setOnAction(e -> loadFile(stage));

		this.refresh = new MenuItem("Refresh");
		this.refresh.setOnAction(e -> this.obsRefresh.update());

        this.fileMenu.getItems().addAll(save, load, this.refresh);


		this.newMenu = new Menu("New");
		MenuItem newActivity = new MenuItem("New activity");
		newActivity.setOnAction(e -> {
			this.agendaCreator.init(this.roster);
			this.agendaCreator.display();
		});

		MenuItem newGroup = new MenuItem("Add new group");
		newGroup.setOnAction(e -> newGroupPopup.display());

		MenuItem newLocation = new MenuItem("Add new location");
		newLocation.setOnAction(e -> newLocationPopup.display());
		this.newMenu.getItems().addAll(newActivity, newGroup, newLocation);



		//todo create functions
		this.edit = new MenuItem("Edit");
		this.edit.setOnAction(e -> {
			//TODO, Edit code missing
		});

		this.delete = new MenuItem("Delete");
		this.delete.setOnAction(e -> {
			deleteObject();
		});

		this.editMenu = new Menu("Edit");
		this.editMenu.getItems().addAll(this.edit, this.delete);
	}

	private void deleteObject() {
		String selectedTab = this.dataViewer.getDataTab().getSelectionModel().getSelectedItem().getText();

		switch (selectedTab){
			case "Guards":
				Person guard = this.dataViewer.getGuardsTable().getSelectionModel().getSelectedItem();
				this.roster.getGuardDatabase().remove(guard);
				break;

			case "Inmates":
				Person inmate = this.dataViewer.getInmateTable().getSelectionModel().getSelectedItem();
				this.roster.getInmateDatabase().remove(inmate);
				break;

			case "Groups":
				PrisonGroup group = this.dataViewer.getGroupTable().getSelectionModel().getSelectedItem();
				this.roster.getGroups().remove(group);
				break;

			case "Locations":
				String location = this.dataViewer.getLocationTable().getSelectionModel().getSelectedItem().toString();
				this.roster.getLocationDatabase().remove(location);
				System.out.println(location);
				System.out.println(this.roster.getLocationDatabase());
				break;

			case "Activities":
				Activity activity = this.dataViewer.getActivityTable().getSelectionModel().getSelectedItem();
				this.roster.getActivities().remove(activity);
				break;
		}
		this.obsRefresh.update();
	}

	private void loadFile(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"),
				new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
		);
		File file = fileChooser.showOpenDialog(stage);

		if(file != null){
			this.roster = this.fileIO.readData(file);
			this.obsRefresh.update();
			System.out.println(this.roster);
		}
	}

	private void saveFile(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"),
				new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
		);
		File file = fileChooser.showSaveDialog(stage);

		if(file != null){
			this.fileIO.saveDataAsFile(file, this.roster);
		}
	}

	private void createPanes() {
		BorderPane borderPane = new BorderPane();
		HBox groupBox = new HBox();
		StackPane flowPane = new StackPane(this.canvas);
		ScrollPane scrollableCenter = new ScrollPane(flowPane);

		setPaneSettings(borderPane,groupBox, flowPane, scrollableCenter);
		fillGroupbox(groupBox);
	}

	private void setPaneSettings(BorderPane borderPane, HBox groupBox, StackPane flowPane, ScrollPane scrollableCenter) {
		this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		this.rosterTab.setContent(borderPane);
		this.rosterTab.setText("Rooster");

		this.tabPane.setSide(Side.LEFT);
		this.tableTab.setText("Data");
		this.tabPane.getTabs().addAll(this.rosterTab, this.tableTab);

		flowPane.setPrefHeight(1440);

		canvas.setHeight(flowPane.getHeight());

		ScrollPane groupScroll = new ScrollPane();

		borderPane.setTop(groupScroll);

		this.menuBar = new MenuBar(this.fileMenu, this.newMenu, this.editMenu);

		this.mainPane.setTop(this.menuBar);
		this.mainPane.setCenter(this.tabPane);

		borderPane.setTop(groupBox);

		borderPane.setCenter(scrollableCenter);

		flowPane.setPrefHeight(1440);
		this.canvas.setHeight(flowPane.getHeight());


		groupScroll.hvalueProperty().bindBidirectional(scrollableCenter.hvalueProperty()); //this statenebt binds the h-scrollbar from groupscroll to h scrollbar from scrollablecenter
		groupScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollableCenter.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	}

	public void fillGroupbox(HBox groupBox){
		ArrayList<Label> groups = new ArrayList<>();

		Label empty;

		groupBox.getChildren().add(empty = new Label(""));
		empty.setPrefWidth(100);
//		for (int i = 0; i < 10;i++) {
//			 Label group;
//			groupBox.getChildren().add(group = new Label("group"+(i+1)));
//			group.setPrefWidth(50);
//
//		}
		for (PrisonGroup PG : roster.getGroups()) {
			Label group;
			groupBox.getChildren().add(group = new Label(PG.getGroupName()));

			group.setPrefWidth(100);
		}
//		groupScroll.setContent(groupBox);
//		groupBox.setPrefWidth(groupBox.getChildren().size()*100);
//
//		Scene scene = new Scene(this.mainPane, 700, 700);
//		stage.setScene(scene);
//		stage.show();
//		flowPane.setPrefWidth(groupBox.getChildren().size()*100-20);

	}

	public void fillTableTab(){

		this.obsRefresh.update();
		this.tableTab.setContent(this.dataViewer.allTabs());

	}

	private void drawTime(FXGraphics2D graphics) {
		int hours = 0;
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
