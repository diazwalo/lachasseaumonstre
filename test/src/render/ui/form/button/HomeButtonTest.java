package src.render.ui.form.button;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.Background;
import render.ui.form.button.HomeButton;

public class HomeButtonTest {
    
	@Test
	public void test() {
		new JFXPanel();
		HomeButton homeButton = new HomeButton("Jouer");
		
		assertEquals("Jouer", homeButton.getText());
		assertEquals("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 40px; -fx-text-fill: #eee;", homeButton.getStyle());
		assertEquals(Background.EMPTY, homeButton.getBackground());
	}

}
