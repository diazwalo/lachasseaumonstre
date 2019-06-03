package render.ui.form.button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import render.ui.util.Directory;

public class KeyboardButton {
	
	private Button bouton;
	private Image img;
	
	
	
	
	public KeyboardButton(String contenu) {
	
	try(FileInputStream is = new FileInputStream(new File(Directory.GAME_TEXTURE))){
	    	img = new Image(is);
	    } catch (FileNotFoundException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		} catch (IOException e) {
	    	img = new Image("https://www.iut-info.univ-lille.fr/~casiez/M2105/TP/TP4EvenementsListView/folder.png");
			e.printStackTrace();
		}
		this.bouton = new Button(contenu);
		bouton.setMaxSize(50, 50);
		bouton.setMinSize(50,50);
		//this.bouton.setGraphic(new ImageView(img));
	}


	public Button getBouton() {
		return bouton;
	}


	public void setBouton(Button bouton) {
		this.bouton = bouton;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(Image img) {
		this.img = img;
	}
	
	
}
