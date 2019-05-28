package render.ui.component;

import config.Map;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.form.button.HomeMinButton;
import render.ui.form.label.LabelTheme;
import render.ui.form.text.ComboBoxTheme;
import render.ui.form.text.TextFieldTheme;
import render.ui.util.Interface;

public class Settings
{
	private VBox menu;
	
	public Settings()
	{
		this.menu = new VBox();
		Interface.maxHeight(this.menu);
		
		VBox vboxWidth = new VBox();
		LabelTheme labelWidth = new LabelTheme("Largeur du plateau :");
		TextFieldTheme textFieldWidth = new TextFieldTheme("xxx");
		vboxWidth.getChildren().addAll(labelWidth, textFieldWidth);
		
		VBox vboxHeight = new VBox();
		LabelTheme labelHeight = new LabelTheme("Hauteur du plateau :");
		TextFieldTheme textFieldHeight = new TextFieldTheme("xxx");
		vboxWidth.getChildren().addAll(labelHeight, textFieldHeight);
		
		VBox vboxNbTop = new VBox();
		LabelTheme labelNbTp = new LabelTheme("Nombre de téléportation pour le monstre :");
		TextFieldTheme textFieldNbTp = new TextFieldTheme("xxx");
		vboxNbTop.getChildren().addAll(labelNbTp, textFieldNbTp);
		
		VBox vboxListMap = new VBox();
		LabelTheme labelListMap = new LabelTheme("Liste des plateaux :");
		ComboBoxTheme listMap = new ComboBoxTheme();
		
		for (int i = 0; i < Map.values().length; i++) {
			listMap.getItems().addAll(Map.values()[i].getName());		
		}
		
		listMap.getSelectionModel().selectFirst();
		
		vboxListMap.getChildren().addAll(labelListMap, listMap);
				
		this.menu.getChildren().addAll(vboxWidth, vboxHeight, vboxNbTop, vboxListMap);
		
		HBox horizontalFooter = new HBox();
		HomeMinButton left = new HomeMinButton("Quitter");
		HomeMinButton save = new HomeMinButton("Sauver");
		
		horizontalFooter.getChildren().addAll(left, save);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
	}
	
	public VBox getMenu()
	{
		return this.menu;
	}
	
}
