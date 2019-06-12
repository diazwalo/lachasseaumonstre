package render.ui.form.text;

import javafx.geometry.Insets;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SpinnerTheme extends Spinner<Integer>{
	/**
	 * Instancie le SpinnerTheme
	 * @param min la taille maximal
	 * @param max la taille minimal
	 * @param init la taille de base
	 */
	public SpinnerTheme(int min, int max, int init) {
		super(min, max, init);
		super.setStyle("-fx-background-color: transparent; -fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");

		Paint p = Color.WHITE;
		BorderStroke bs = new BorderStroke(p, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(8));
		super.setBorder(new Border(bs));
	}
}
