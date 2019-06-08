package render.ui.view;

import data.score.Score;
import data.score.ScoreManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Stats
{
	VBox core;
	TabPane tabPane;
	
	public Stats(Window window)
	{
		this.core = new VBox();
		this.core.setBackground(Interface.getBackground());
		
		buildTabPane();
		HomeButton back = new HomeButton("Retour");
		
		this.core.getChildren().addAll(this.tabPane, back);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		window.stage.setScene(scene);
		window.stage.setMaximized(true);	
	}

	private void buildTabPane()
	{
		this.tabPane = new TabPane();
		
		Tab tabMulti = new Tab("Dual");
		Tab tabHunter = new Tab("Chasseur");
		Tab tabBeast = new Tab("Monstre");
		Tab tabAI = new Tab("Ordinateur");
		
		tabMulti.setContent(buildTab());
		
		this.tabPane.getTabs().addAll(tabMulti, tabHunter, tabBeast, tabAI);
	}
	
	private TableView<Score> buildTab()//ScoreFile scoreFile
	{
		TableView<Score> tabContent = new TableView<Score>();
		TableColumn<Score, String> col1 = new TableColumn<Score, String>("Joueur 1");
		TableColumn<Score, String> col2 = new TableColumn<Score, String>("Joueur 2");
		TableColumn<Score, Integer> col3 = new TableColumn<Score, Integer>("Nombre de deplacement du joueur 1");
		TableColumn<Score, Integer> col4 = new TableColumn<Score, Integer>("Nombre de deplacement du joueur 2");
		TableColumn<Score, Integer> col5 = new TableColumn<Score, Integer>("Taille du plateau");
		
		col1.setCellValueFactory(new PropertyValueFactory<>("player1"));
		col2.setCellValueFactory(new PropertyValueFactory<>("player2"));
		col3.setCellValueFactory(new PropertyValueFactory<>("nbMouvment1"));
		col4.setCellValueFactory(new PropertyValueFactory<>("nbMouvment2"));
		col5.setCellValueFactory(new PropertyValueFactory<>("size"));
		
		ScoreManagement sm = new ScoreManagement();
		sm.loadScore("data/ia_score.score");
		
		ObservableList<Score> list = sm.getScoreObservbleList();
		tabContent.setItems(list);
		
		tabContent.getColumns().addAll(col1, col2, col3, col4, col5);
		return tabContent;
	}

}
