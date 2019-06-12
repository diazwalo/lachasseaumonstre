package render.ui.form.label;

public class LabelThemeVariableSize extends LabelTheme{

	/**
	 * Instancie le LabelThemeVariableSize
	 * @param value le texte a afficher dans le label
	 * @param size la taille du label
	 */
	public LabelThemeVariableSize(String value, int size) {
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: "+size+"px; -fx-text-fill: #eee;");
		// TODO Auto-generated constructor stub
	}
}
