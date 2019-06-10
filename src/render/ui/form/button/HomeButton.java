package render.ui.form.button;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class HomeButton extends Button
{
	private static final String TRANSPARENT = "-fx-background-color: transparent;";
    private static final String HOVER = "-fx-background-color: rgba(255, 255, 255, 0.3);";
    private static final String STYLE = "-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 40px; -fx-text-fill: #eee; -fx-background-radius: 40px;";
	
    /**
     * Instacie un HomeButtons
     * @param value
     * @param image
     */
    public HomeButton(String value, Image image)
	{
		super(value);
		super.setStyle("-fx-font-family: 'Press Start 2P', cursive; -fx-font-size: 40px; -fx-text-fill: #eee;");
		super.setBackground(Background.EMPTY);
		super.setOnMouseEntered(e -> super.setStyle(STYLE + HOVER));
		super.setOnMouseExited(e -> super.setStyle(STYLE + TRANSPARENT));
		super.setEffect(getShadow());
		super.setMinSize(500, 100);
		
		if(image != null) {
			ImageView iv = new ImageView(image);
			iv.setFitHeight(40);
			iv.setFitWidth(40);
			super.setGraphic(iv);
		}
	}
    
    /**
     * Instancie un HomeButton
     * @param value
     */
	public HomeButton(String value)
	{
		this(value, null);
	}
	
	
	/**
	 * Retourne le DropShadow du HomeButtons
	 * @return
	 */
	public DropShadow getShadow()
	{
		DropShadow dropShadow = new DropShadow();
		dropShadow.setColor(new Color(0.6, 0.6, 0.6, 1));
		dropShadow.setRadius(1.0);
		dropShadow.setOffsetX(3.0);
		
		return dropShadow;
	}
}
