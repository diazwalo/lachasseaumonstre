package render.ui.component;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import render.text.Entity;

public class Inventory {

	private GridPane core;
	
	public Inventory(Entity entity) {
		this.core = new GridPane();

		for (int i = 0; i < entity.getInventory().size(); i++) {
			Button buttonBonus = new Button();
			this.core.add(buttonBonus, 0, i);
		}
		
		System.out.println("Taille de l'inventaire : " + entity.getInventory().size());
	}

	public Inventory() {
		this.core = new GridPane();
	}

	public GridPane getCore() {
		return core;
	}
	
}
