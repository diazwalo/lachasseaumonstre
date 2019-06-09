package render.ui.view;

import config.Config;
import core.game.GameStatus;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.button.HomeMinButton;
import render.ui.form.label.LabelThemeVariableSize;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class EndScreen {
	private VBox core;
	private Window window;
	
	public EndScreen(Window window) {
		// TODO Auto-generated constructor stub
		this.window = window;
		this.core = new VBox();
		this.core.setBackground(Interface.getBackground());
		this.core.setAlignment(Pos.CENTER);
	}
	
	/**
	 * Affiche le message de fin de partie et Vitctoir et victoire (si victory = 1), defaite (si = 2) ou rien (si = -1)
	 * @param gameStatue
	 * @param victory
	 */
	public void setEndScreen(GameStatus gameStatue, boolean victory, Config config, String s) {
		Label statue = null;
		
		if(victory) {
			statue = new LabelThemeVariableSize("Victoire !", 40);
		}else if(!victory) {
			statue = new LabelThemeVariableSize("Defaite...", 40);
		}
		
		Label txtEnd = new LabelThemeVariableSize(gameStatue.getStatus(), 40);
		Button next = new HomeMinButton("Suivant");
		next.setOnAction(e -> { 
			//On devra faire un this.config (donc l'avoir comme attribut)
			new Home(this.window, config);
		});
		
		if(statue == null) {
			core.getChildren().addAll(txtEnd, new LabelThemeVariableSize("", 40), next);
		}else {
			core.getChildren().addAll(statue, new LabelThemeVariableSize("", 40), txtEnd, new LabelThemeVariableSize("", 40), next);
		}
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);

		window.stage.setScene(scene);
	}
	
}
