package Gui;

import Data.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;


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
	private VBox vBox;
	private BorderPane mainPane;
	private Roster roster;
	private AgendaEditor agendaEditor;

	@Override
	public void start(Stage stage) {
		this.mainPane = new BorderPane();
		this.tabPane = new TabPane();
		this.rosterTab = new Tab();
		this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
		this.tableTab = new Tab();
		this.tableView = new TableView();
		this.vBox = new VBox();
		this.roster = new Roster();
		this.agendaEditor = new AgendaEditor();

		MenuItem itemNew = new MenuItem("New");
		itemNew.setOnAction(e-> {

		});

		this.fileMenu = new Menu("File");
		this.fileMenu.getItems().add(itemNew);

		this.editMenu = new Menu("Edit");
		this.editMenu.setOnAction(e->{

		});

		this.deleteMenu = new Menu("Delete");
		this.deleteMenu.setOnAction(e-> {

		});


		this.menuBar = new MenuBar(fileMenu, editMenu, deleteMenu);
		mainPane.setTop(this.menuBar);
		mainPane.setCenter(this.tabPane);
		Scene scene = new Scene(mainPane);
		stage.setScene(scene);
		stage.show();
	}

	public void init() {

	}


	public void draw(FXGraphics2D graphics) {
	}

	public void drawBlock(FXGraphics2D g, MouseEvent e) {


//        javafx.geometry.Rectangle2D rect;
//        g.drawString("midas",(int)(e.getX()-e.getX()%50),(int)e.getY()+25);
//        g.draw(new Rectangle2D.Double(e.getX()-e.getX()%50,e.getY(),50,50));


	}

	public static void main(String[] args) {
		launch(args);
	}

}
