package render.ui.component;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import render.bonus.Bait;
import render.bonus.Camouflage;
import render.bonus.IBonus;
import render.bonus.Trap;
import render.bonus.Ward;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;
import render.ui.util.Directory;

public class Inventory {

	private GridPane core;
	
	public Inventory(Entity entity) {
		this.core = new GridPane();
		Button buttonBonus = new Button();
		if(entity instanceof Beast) {
			buttonBonus.setGraphic(new ImageView(Directory.GAME_BAIT));
			buttonBonus.setDisable(true);
			core.add(buttonBonus, 0, 0);
			
			buttonBonus = new Button();
			buttonBonus.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
			buttonBonus.setDisable(true);
			core.add(buttonBonus, 0, 1);
		}else if (entity instanceof Hunter) {
			buttonBonus.setGraphic(new ImageView(Directory.GAME_TRAP));
			buttonBonus.setDisable(true);
			core.add(buttonBonus, 0, 0);
			
			buttonBonus = new Button();
			buttonBonus.setGraphic(new ImageView(Directory.GAME_WARD));
			buttonBonus.setDisable(true);
			core.add(buttonBonus, 0, 1);
		}
	}
	
	/*public void putInInventory(Entity entity) {
		Button buttonBonus = new Button();
		if(entity instanceof Beast) {
			buttonBonus.setGraphic(new ImageView(Directory.GAME_BAIT));
			buttonBonus.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
		}else if (entity instanceof Hunter) {
			buttonBonus.setGraphic(new ImageView(Directory.GAME_TRAP));
			buttonBonus.setGraphic(new ImageView(Directory.GAME_WARD));
		}
	}*/
	
	public void setBonusAble() {
		//if()
	}
	
	public void setBonusDisable() {
		
	}

	public GridPane getCore() {
		return core;
	}
	
}
