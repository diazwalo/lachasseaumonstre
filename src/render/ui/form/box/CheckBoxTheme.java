package render.ui.form.box;

import javafx.scene.control.CheckBox;

public class CheckBoxTheme extends CheckBox{
	/**
	 * Instancie la CheckBoxTheme
	 * @param check determine si il est selectionner ou non
	 */
	public CheckBoxTheme(boolean check) {
		super();
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 20px; -fx-text-fill: #eee;");
		super.setSelected(check);
	}	
}
