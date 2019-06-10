package render.ui.component;

import java.util.List;

import core.game.AbstractGame;
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
import render.ui.component.gamePlay.AbstractGamePlay;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
import render.ui.component.gamePlay.GamePlayMulti;
import render.ui.util.Directory;

public class Inventory {

	private GridPane core;
	public Button topButton;
	public Button botButton;
	
	
	/**
	 * Instancie  l'Inventory
	 * @param entity
	 * @param gameType
	 */
	
	public Inventory(Entity entity, AbstractGamePlay gameType) {
		/**
		 * Le plus propre
		 */
		
		/*this.core = new GridPane();
		topButton = new Button();
		botButton = new Button();*/
		
		AbstractGame ag = null;
		if(gameType instanceof GamePlayHunter) {
			ag =((GamePlayHunter)(gameType)).ag;
		}else if(gameType instanceof GamePlayBeast) {
			ag =((GamePlayBeast)(gameType)).ag;
		}else if(gameType instanceof GamePlayMulti) {
			ag =((GamePlayMulti)(gameType)).ag;
		}
		
		if(ag != null) {
			this.setInventory(entity, gameType, ag);
		}
	}
	
	/**
	 * Initialise l'inventaire
	 * @param entity
	 * @param gameType
	 * @param ag
	 */
	
	public void setInventory(Entity entity, AbstractGamePlay gameType, AbstractGame ag) {
		this.core = new GridPane();
		topButton = new Button();
		botButton = new Button();
		
		if(entity instanceof Beast) {
			System.out.println("youhou c'est une instance de bete");
			entity = (Beast)(entity);
			
			topButton.setGraphic(new ImageView(Directory.GAME_BAIT));
			topButton.setDisable(true);
			core.add(topButton, 0, 0);
			topButton.setOnAction(e -> {
				topButton.setDisable(true);
				ag.bonusChoiceBeast("2");
			});
			
			botButton.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
			botButton.setDisable(true);
			core.add(botButton, 0, 1);
			botButton.setOnAction(e -> {
				botButton.setDisable(true);
				ag.bonusChoiceBeast("1");
			});
		}else if (entity instanceof Hunter) {
			System.out.println("youhou c'est une instance de chasseur");
			entity = (Hunter)(entity);
			
			topButton.setGraphic(new ImageView(Directory.GAME_TRAP));
			topButton.setDisable(true);
			core.add(topButton, 0, 0);
			topButton.setOnAction(e -> {
				topButton.setDisable(true);
				ag.bonusChoiceHunter("2");
			});
			
			botButton.setGraphic(new ImageView(Directory.GAME_WARD));
			botButton.setDisable(true);
			core.add(botButton, 0, 1);
			botButton.setOnAction(e -> {
				botButton.setDisable(true);
				ag.bonusChoiceHunter("1");
			});
		}
	}
	
	/**
	 * Rend la liste de bonus passez en paramètre disponible pour l'utilisater
	 * @param listIBonus
	 */
	public void setBonusAble(List<IBonus> listIBonus) {
		this.setBonusDisable();
		for (IBonus b : listIBonus) {
			if(b instanceof Bait || b instanceof Trap) {
				topButton.setDisable(false);
			}
			if(b instanceof Camouflage || b instanceof Ward) {
				botButton.setDisable(false);
			}
		}
	}
	
	/**
	 * Rend les bonus indisponible pour l'utilisateur
	 */
	public void setBonusDisable() {
		botButton.setDisable(true);
		topButton.setDisable(true);
	}
	
	/**
	 * Retoune le Node principale de l'objet
	 * @return
	 */
	
	public GridPane getCore() {
		return core;
	}
	
}
