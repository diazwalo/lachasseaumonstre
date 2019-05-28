package render.ui.component;

import config.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.core.Plateau;
import render.ui.form.box.CheckBoxTheme;
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
		this.menu.setAlignment(Pos.CENTER);
		
		VBox vboxWidth = new VBox();
		LabelTheme labelWidth = new LabelTheme("Largeur du plateau :");
		TextFieldTheme textFieldWidth = new TextFieldTheme("xxx");
		vboxWidth.setPadding(new Insets(0,0,20,0));
		vboxWidth.getChildren().addAll(labelWidth, textFieldWidth);
		
		VBox vboxHeight = new VBox();
		LabelTheme labelHeight = new LabelTheme("Hauteur du plateau :");
		TextFieldTheme textFieldHeight = new TextFieldTheme("xxx");
		vboxHeight.setPadding(new Insets(0,0,20,0));
		vboxHeight.getChildren().addAll(labelHeight, textFieldHeight);
		
		VBox vboxNbTop = new VBox();
		LabelTheme labelNbTp = new LabelTheme("Nombre de téléportation pour le monstre :");
		TextFieldTheme textFieldNbTp = new TextFieldTheme("xxx");
		vboxNbTop.setPadding(new Insets(0,0,20,0));
		vboxNbTop.getChildren().addAll(labelNbTp, textFieldNbTp);
		
		VBox vboxListMap = new VBox();
		LabelTheme labelListMap = new LabelTheme("Liste des plateaux :");
		ComboBoxTheme listMap = new ComboBoxTheme();
		
		for (int i = 0; i < Map.values().length; i++) {
			listMap.getItems().addAll(Map.values()[i].getName());		
		}
		
		listMap.getSelectionModel().selectFirst();
		vboxListMap.setPadding(new Insets(0,0,20,0));
		vboxListMap.getChildren().addAll(labelListMap, listMap);
		
		VBox vboxBonus = new VBox();
		
		LabelTheme labelBonus1 = new LabelTheme("Bonus X : ");
		CheckBoxTheme checkBoxThemeBonus1 = new CheckBoxTheme();
		
		LabelTheme labelBonus2 = new LabelTheme("Bonus Y : ");
		CheckBoxTheme checkBoxThemeBonus2 = new CheckBoxTheme();
		
		LabelTheme labelBonus3 = new LabelTheme("Bonus Z : ");
		CheckBoxTheme checkBoxThemeBonus3 = new CheckBoxTheme();
		
		LabelTheme labelBonus4 = new LabelTheme("Bonus W : ");
		CheckBoxTheme checkBoxThemeBonus4 = new CheckBoxTheme();
		
		HBox hBoxBonus1 = new HBox();
		HBox hBoxBonus2 = new HBox();
		HBox hBoxBonus3 = new HBox();
		HBox hBoxBonus4 = new HBox();
		
		hBoxBonus1.getChildren().addAll(labelBonus1, checkBoxThemeBonus1);
		hBoxBonus1.setPadding(new Insets(0,0,20,0));
		hBoxBonus2.getChildren().addAll(labelBonus2, checkBoxThemeBonus2);
		hBoxBonus2.setPadding(new Insets(0,0,20,0));
		hBoxBonus3.getChildren().addAll(labelBonus3, checkBoxThemeBonus3);
		hBoxBonus3.setPadding(new Insets(0,0,20,0));
		hBoxBonus4.getChildren().addAll(labelBonus4, checkBoxThemeBonus4);
		hBoxBonus4.setPadding(new Insets(0,0,20,0));
		vboxBonus.getChildren().addAll(hBoxBonus1, hBoxBonus2, hBoxBonus3, hBoxBonus4);
		
		this.menu.getChildren().addAll(vboxWidth, vboxHeight, vboxNbTop, vboxListMap, vboxBonus);
		
		HBox horizontalFooter = new HBox();
		HomeMinButton left = new HomeMinButton("Retour");
		left.setOnAction(e -> {
			//new Scene(new Home());
		});
		HomeMinButton save = new HomeMinButton("Sauver");
		
		horizontalFooter.setAlignment(Pos.CENTER);
		horizontalFooter.getChildren().addAll(left, save);
		horizontalFooter.setPadding(new Insets(50, 0, 0, 0));
		this.menu.getChildren().add(horizontalFooter);
	}
	
	public VBox getMenu()
	{
		return this.menu;
	}
	
}
