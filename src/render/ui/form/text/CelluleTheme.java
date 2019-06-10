package render.ui.form.text;

import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;

public class CelluleTheme extends ListCell<String>
{
	/**
	 Instancie le CelluleTheme
	 */
	public void updateItem(String item, boolean empty)
	{
		super.updateItem(item, empty);
		
		if (empty || item == null) {
            setText("");
        } else {
            setBackground(Background.EMPTY);
            setStyle("-fx-background-color: transparent; -fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
            setText(item);
            getStyleClass().add("cellule-theme");
        }
	}
}
