package render.ui.view;

import config.Config;
import core.game.GameStatus;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.button.HomeMinButton;
import render.ui.form.label.LabelThemeVariableSize;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class EndScreen {
	private HBox core;
	private VBox center;
	private Window window;
	
	public EndScreen(Window window) {
		this.window = window;
		this.center = new VBox();
		this.core = new HBox();
	}
	
	/**
	 * Affiche le message de fin de partie et Vitctoir et victoire (si victory = 1), defaite (si = 2) ou rien (si = -1)
	 * @param gameStatue
	 * @param victory
	 */
	public void setEndScreen(GameStatus gameStatue, boolean victory, Config config, String entityWinner) {
		Label statue = null;
		
		if(entityWinner == null) {
			if(victory) {
				statue = new LabelThemeVariableSize("Victoire !", 40);
				this.core.setBackground(Interface.getBackground(Directory.WIN_BACKGROUND));
			}else if(!victory) {
				statue = new LabelThemeVariableSize("Defaite...", 40);
				this.core.setBackground(Interface.getBackground(Directory.LOOSE_BACKGROUND));
			}
		}else {
			this.core.setBackground(Interface.getBackground(Directory.WIN_BACKGROUND));
			statue = new LabelThemeVariableSize("Victoire "+entityWinner+" !", 40);
		}
		
		Label txtEnd = new LabelThemeVariableSize(gameStatue.getStatus(), 40);
		Button next = new HomeMinButton("Suivant");
		next.setOnAction(e -> { 
			//On devra faire un this.config (donc l'avoir comme attribut)
			new Home(this.window, config);
		});
		
		if(statue == null) {
			center.getChildren().addAll(txtEnd, new LabelThemeVariableSize("", 40), next);
		}else {
			center.getChildren().addAll(statue, new LabelThemeVariableSize("", 40), txtEnd, new LabelThemeVariableSize("", 40), next);
		}
		
		this.core.getChildren().add(this.center);
		this.core.setAlignment(Pos.CENTER);
		this.center.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(this.core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
		/*Scene scene = new Scene(this.center, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);

		window.stage.setScene(scene);*/
	}
	
}
