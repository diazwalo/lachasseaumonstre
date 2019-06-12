package render.ui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import render.ui.core.Window;
import render.ui.form.button.HomeButton;
import render.ui.form.text.TextFieldTheme;
import render.ui.util.Directory;
import render.ui.util.Interface;

public class askPseudo
{
	public askPseudo(Window window)
	{
		VBox core = new VBox();
		core.setBackground(Interface.getBackground(Directory.GAME_BACKGROUND));
		core.setAlignment(Pos.CENTER);
		
		TextFieldTheme tft = new TextFieldTheme("default");
		HomeButton hb = new HomeButton("Sauvegarder mon score");
		
		hb.setOnAction(e -> {
			//Enregistrer le pseudo
		});
		
		core.getChildren().addAll(tft, hb);
		
		Scene scene = new Scene(core, Interface.getSize().getWidth(), Interface.getSize().getHeight());
		scene.getStylesheets().add(Directory.STYLE_CSS);
		window.stage.setScene(scene);
	}
}
