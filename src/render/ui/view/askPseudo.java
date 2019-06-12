package render.ui.view;

import config.Config;
import data.score.IScore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import render.ui.component.gamePlay.AbstractGamePlay;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.form.text.TextFieldTheme;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class askPseudo
{
	private AbstractGamePlay plateau;
	
	public askPseudo(Window window, AbstractGamePlay plateau)
	{
		this.plateau = plateau;
		
		VBox core = new VBox();
		core.setBackground(Interface.getBackground(Directory.GAME_BACKGROUND));
		core.setAlignment(Pos.CENTER);
		
		TextFieldTheme tft = new TextFieldTheme("default");
		HomeButton hb = new HomeButton("Sauvegarder mon score");
		
		tft.setMaxWidth(500);
		hb.setOnAction(e -> {
			manageScore(tft.getText());
			new Home(window, plateau.getMap().getConfig());
		});
		
		core.getChildren().addAll(tft, hb);
		
		Scene scene = new Scene(core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
	}
	
	// Regarde ca virgil, quelle si beau code sans instanceOf xD
	private void manageScore(String username)
	{
		((IScore) this.plateau).buildScore(username);
	}
}
