package render.ui.view;

import config.Config;
import config.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.box.CheckBoxTheme;
import render.ui.form.button.HomeMinButton;
import render.ui.form.label.LabelTheme;
import render.ui.form.text.ComboBoxTheme;
import render.ui.form.text.SpinnerTheme;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class Settings
{
	private Window window;
	
	private HBox core;
	private VBox menu;
	
	/**
	 * Créer une view pour choisir la configuration du jeu.
	 * @param window La Window du jeu.
	 * @param config La configuration du jeu.
	 */
	public Settings(Window window, Config config)
	{		
		this.window = window;
		
		this.core = new HBox();
		this.menu = new VBox();
		
		Interface.maxHeight(this.menu);
		this.core.setBackground(Interface.getBackground());
		this.core.setAlignment(Pos.CENTER);
		this.menu.setAlignment(Pos.CENTER);
		
		VBox vboxWidth = new VBox();
		LabelTheme labelWidth = new LabelTheme("Largeur du plateau :");
		Spinner<Integer> textFieldWidth = new SpinnerTheme(Config.MINSIZE, Config.MAXSIZE, config.getWidth());
		//textFieldWidth.setEditable(true);
		vboxWidth.setPadding(new Insets(0,0,20,0));
		vboxWidth.getChildren().addAll(labelWidth, textFieldWidth);
		
		VBox vboxHeight = new VBox();
		LabelTheme labelHeight = new LabelTheme("Hauteur du plateau :");
		SpinnerTheme textFieldHeight = new SpinnerTheme(Config.MINSIZE, Config.MAXSIZE, config.getHeight());
		//textFieldHeight.setEditable(true);
		vboxHeight.setPadding(new Insets(0,0,20,0));
		vboxHeight.getChildren().addAll(labelHeight, textFieldHeight);
		
		VBox vboxNbTop = new VBox();
		LabelTheme labelNbTp = new LabelTheme("Nombre de teleportation pour le monstre :");
		SpinnerTheme textFieldNbTp = new SpinnerTheme(Config.MINTP, Config.MAXTP, config.getNbTeleportation());
		//textFieldNbTp.setEditable(true);
		vboxNbTop.setPadding(new Insets(0,0,20,0));
		vboxNbTop.getChildren().addAll(labelNbTp, textFieldNbTp);
		
		VBox vboxListMap = new VBox();
		LabelTheme labelListMap = new LabelTheme("Liste des plateaux :");
		ComboBoxTheme listMap = new ComboBoxTheme();
		
		for (int i = 0; i < Map.values().length; i++) {
			listMap.getItems().addAll(Map.values()[i].getName());
			
			if(config.getMap().equals(Map.values()[i]))
			{
				listMap.getSelectionModel().select(i);
			}
		}
		
		vboxListMap.setPadding(new Insets(0,0,20,0));
		vboxListMap.getChildren().addAll(labelListMap, listMap);
		
		VBox vboxBonus = new VBox();
		
		LabelTheme labelTrap = new LabelTheme("Activer les pieges");
		labelTrap.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeTrap = new CheckBoxTheme(config.isTrap());
		
		LabelTheme labelWard = new LabelTheme("Activer les balises de vision");
		labelWard.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeWard = new CheckBoxTheme(config.isWard());
		
		LabelTheme labelBait = new LabelTheme("Activer les leurres");
		labelBait.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeBait = new CheckBoxTheme(config.isBait());
		
		LabelTheme labelCamouflage = new LabelTheme("Activer les camouflages");
		labelCamouflage.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeCamouflage = new CheckBoxTheme(config.isCamouflage());
		
		HBox hBoxBonus1 = new HBox();
		HBox hBoxBonus2 = new HBox();
		HBox hBoxBonus3 = new HBox();
		HBox hBoxBonus4 = new HBox();
		
		hBoxBonus1.getChildren().addAll(checkBoxThemeTrap, labelTrap);
		hBoxBonus1.setPadding(new Insets(0,0,20,0));
		hBoxBonus2.getChildren().addAll(checkBoxThemeWard, labelWard);
		hBoxBonus2.setPadding(new Insets(0,0,20,0));
		hBoxBonus3.getChildren().addAll(checkBoxThemeBait, labelBait);
		hBoxBonus3.setPadding(new Insets(0,0,20,0));
		hBoxBonus4.getChildren().addAll(checkBoxThemeCamouflage, labelCamouflage);
		hBoxBonus4.setPadding(new Insets(0,0,20,0));
		vboxBonus.getChildren().addAll(hBoxBonus1, hBoxBonus2, hBoxBonus3, hBoxBonus4);
		
		this.menu.getChildren().addAll(vboxWidth, vboxHeight, vboxNbTop, vboxListMap, vboxBonus);
		
		HBox horizontalFooter = new HBox();
		HomeMinButton reset = new HomeMinButton("Reset");
		reset.setOnAction(e -> {
			config.setDefaultConfig();
			textFieldWidth.getValueFactory().setValue(Config.MINSIZE);
			textFieldHeight.getValueFactory().setValue(Config.MINSIZE);
			textFieldNbTp.getValueFactory().setValue((Config.MINTP+Config.MAXTP)/2);
			listMap.getSelectionModel().select(0);
			checkBoxThemeTrap.setSelected(false);
			checkBoxThemeWard.setSelected(false);
			checkBoxThemeBait.setSelected(false);
			checkBoxThemeCamouflage.setSelected(false);
		});
		
		HomeMinButton left = new HomeMinButton("Retour");
		left.setOnAction(e -> {
			new Home(this.window, config);
		});
		HomeMinButton save = new HomeMinButton("Sauver");
		save.setOnAction(e -> {
			
			config.saveConfig(
				textFieldWidth.getValue(), 
				textFieldHeight.getValue(), 
				textFieldNbTp.getValue(), 
				Map.getMap(listMap.getValue()).ordinal(), 
				checkBoxThemeTrap.isSelected(), 
				checkBoxThemeCamouflage.isSelected(), 
				checkBoxThemeWard.isSelected(), 
				checkBoxThemeBait.isSelected()
			);
			
			new Home(this.window, config);
		});
		
		horizontalFooter.setAlignment(Pos.CENTER);
		horizontalFooter.getChildren().addAll(reset, left, save);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
		this.core.getChildren().add(this.menu);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		window.stage.setScene(scene);
		window.stage.setMaximized(true);
	}
}
