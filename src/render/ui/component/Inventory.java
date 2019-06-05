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
	public List<IBonus> inventory = new ArrayList<>();
	
	public Inventory(Entity entity) {
		this.core = new GridPane();
		List<IBonus> inventory = new ArrayList<>();
		/*if(entity instanceof Beast) {
			System.out.println("dedans");
			Beast b = (Beast)entity;
			inventory.addAll(b.getCamouflages());
			inventory.addAll(b.getbaits());
		}else if (entity instanceof Hunter) {
			Hunter h = (Hunter)entity;
			inventory.addAll(h.getTraps());
			inventory.addAll(h.getWards());
		}

		for (int i = 0; i < inventory.size(); i++) {
			IBonus bonus = inventory.get(i);
		
			Button buttonBonus = new Button();
			
			if(bonus instanceof Trap) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_TRAP));
			}else if(bonus instanceof Ward) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_WARD));
			}else if(bonus instanceof Bait) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_BAIT));
			}else if(bonus instanceof Camouflage) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
			}
			this.core.add(buttonBonus, 0, i);
		}
		
		System.out.println("Taille de l'inventaire : " + entity.getInventory().size());*/
	}
	
	public void putInInventory(Entity entity) {
		if(entity instanceof Beast) {
			System.out.println("dedans");
			Beast b = (Beast)entity;
			inventory.addAll(b.getCamouflages());
			inventory.addAll(b.getbaits());
		}else if (entity instanceof Hunter) {
			Hunter h = (Hunter)entity;
			inventory.addAll(h.getTraps());
			inventory.addAll(h.getWards());
		}

		for (int i = 0; i < inventory.size(); i++) {
			IBonus bonus = inventory.get(i);
		
			Button buttonBonus = new Button();
			
			if(bonus instanceof Trap) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_TRAP));
			}else if(bonus instanceof Ward) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_WARD));
			}else if(bonus instanceof Bait) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_BAIT));
			}else if(bonus instanceof Camouflage) {
				buttonBonus.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
			}
			this.core.add(buttonBonus, 0, i);
		}
	}

	public GridPane getCore() {
		return core;
	}
	
}
