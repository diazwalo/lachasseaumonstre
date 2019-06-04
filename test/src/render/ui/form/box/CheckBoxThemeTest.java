package src.render.ui.form.box;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import render.ui.form.box.CheckBoxTheme;

public class CheckBoxThemeTest
{

	@Test
	public void test()
	{
		new JFXPanel();
		CheckBoxTheme checkBox = new CheckBoxTheme(false);
		
		assertFalse(checkBox.isSelected());
		assertEquals("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;", checkBox.getStyle());
		
		checkBox = new CheckBoxTheme(true);
		assertTrue(checkBox.isSelected());
	}

}
