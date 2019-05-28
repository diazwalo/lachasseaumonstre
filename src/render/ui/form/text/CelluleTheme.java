package render.ui.form.text;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CelluleTheme extends ListCell<String>
{
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item, empty);
		
		if (empty || item == null) {
            setText("");
        } else {
            System.out.println("TRANS");
            setStyle("-fx-background-color: transparent; -fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
            setText(item);
        }
	}
}
