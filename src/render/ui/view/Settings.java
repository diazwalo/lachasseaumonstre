package render.ui.view;

import java.util.prefs.Preferences;

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
	
	public Settings(Window window, Config config)
	{
		Preferences prefs = Preferences.userRoot().node("lachasseauxmonstres_settings");
		
		this.window = window;
		
		this.core = new HBox();
		this.menu = new VBox();
		
		Interface.maxHeight(this.menu);
		this.core.setBackground(Interface.getBackground());
		this.core.setAlignment(Pos.CENTER);
		this.menu.setAlignment(Pos.CENTER);
		
		VBox vboxWidth = new VBox();
		LabelTheme labelWidth = new LabelTheme("Largeur du plateau :");
		Spinner<Integer> textFieldWidth = new SpinnerTheme(Config.minSize, Config.maxSize, prefs.getInt("width", config.getWidth()));
		vboxWidth.setPadding(new Insets(0,0,20,0));
		vboxWidth.getChildren().addAll(labelWidth, textFieldWidth);
		
		VBox vboxHeight = new VBox();
		LabelTheme labelHeight = new LabelTheme("Hauteur du plateau :");
		SpinnerTheme textFieldHeight = new SpinnerTheme(Config.minSize, Config.maxSize, prefs.getInt("height", config.getHeight()));
		vboxHeight.setPadding(new Insets(0,0,20,0));
		vboxHeight.getChildren().addAll(labelHeight, textFieldHeight);
		
		VBox vboxNbTop = new VBox();
		LabelTheme labelNbTp = new LabelTheme("Nombre de teleportation pour le monstre :");
		SpinnerTheme textFieldNbTp = new SpinnerTheme(Config.minTp, Config.maxTp, prefs.getInt("height", config.getNbTeleportation()));
		vboxNbTop.setPadding(new Insets(0,0,20,0));
		vboxNbTop.getChildren().addAll(labelNbTp, textFieldNbTp);
		
		VBox vboxListMap = new VBox();
		LabelTheme labelListMap = new LabelTheme("Liste des plateaux :");
		ComboBoxTheme listMap = new ComboBoxTheme();
		
		for (int i = 0; i < Map.values().length; i++) {
			listMap.getItems().addAll(Map.values()[i].getName());
			
			if(prefs.getInt("map", -1) == -1) {
				if(config.getMap().equals(Map.values()[i]))
				{
					listMap.getSelectionModel().select(i);
				}
			} else {
				listMap.getSelectionModel().select(prefs.getInt("map", -1));
			}
		}
		
		vboxListMap.setPadding(new Insets(0,0,20,0));
		vboxListMap.getChildren().addAll(labelListMap, listMap);
		
		VBox vboxBonus = new VBox();
		
		LabelTheme labelTrap = new LabelTheme("Activer les pieges");
		labelTrap.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeTrap = new CheckBoxTheme(prefs.getBoolean("trap", config.isTrap()));
		
		LabelTheme labelWard = new LabelTheme("Activer les balises de vision");
		labelWard.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeWard = new CheckBoxTheme(prefs.getBoolean("ward", config.isWard()));
		
		LabelTheme labelBait = new LabelTheme("Activer les leurres");
		labelBait.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeBait = new CheckBoxTheme(prefs.getBoolean("bait", config.isBait()));
		
		LabelTheme labelCamouflage = new LabelTheme("Activer les camouflages");
		labelCamouflage.setPadding(new Insets(6, 0, 0, 10));
		CheckBoxTheme checkBoxThemeCamouflage = new CheckBoxTheme(prefs.getBoolean("camouflage", config.isCamouflage()));
		
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
		HomeMinButton left = new HomeMinButton("Retour");
		left.setOnAction(e -> {
			new Home(this.window, config);
		});
		HomeMinButton save = new HomeMinButton("Sauver");
		save.setOnAction(e -> {
			
			prefs.putInt("width", textFieldWidth.getValue());
			config.setWidth(textFieldWidth.getValue());
			
			prefs.putInt("height", textFieldHeight.getValue());
			config.setHeight(textFieldHeight.getValue());
			
			prefs.putInt("tp", textFieldNbTp.getValue());
			config.setNbTeleportation(textFieldNbTp.getValue());
			
			prefs.putInt("map", Map.getMap(listMap.getValue()).ordinal());
			config.setMap(Map.getMap(listMap.getValue()));
			
			prefs.putBoolean("trap", checkBoxThemeTrap.isSelected());
			config.setTrap(checkBoxThemeTrap.isSelected());
			
			prefs.putBoolean("ward", checkBoxThemeWard.isSelected());
			config.setWard(checkBoxThemeWard.isSelected());
			
			prefs.putBoolean("bait", checkBoxThemeBait.isSelected());
			config.setBait(checkBoxThemeBait.isSelected());
			
			prefs.putBoolean("camouflage", checkBoxThemeCamouflage.isSelected());
			config.setCamouflage(checkBoxThemeCamouflage.isSelected());
			
			new Home(this.window, config);
		});
		
		horizontalFooter.setAlignment(Pos.CENTER);
		horizontalFooter.getChildren().addAll(left, save);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
		this.core.getChildren().add(this.menu);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		
		window.stage.setScene(scene);
		window.stage.setMaximized(true);
	}
}
