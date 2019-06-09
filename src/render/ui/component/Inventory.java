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
import render.ui.component.gamePlay.AbstractGamePlay;
import render.ui.component.gamePlay.GamePlayBeast;
import render.ui.component.gamePlay.GamePlayHunter;
import render.ui.util.Directory;

public class Inventory {

	private GridPane core;
	public Button topButton;
	public Button botButton;
	
	public Inventory(Entity entity, AbstractGamePlay gameType) {
		this.core = new GridPane();
		topButton = new Button();
		botButton = new Button();
		
		if(entity instanceof Beast) {
			entity = (Beast)(entity);
			
			topButton.setGraphic(new ImageView(Directory.GAME_BAIT));
			topButton.setDisable(true);
			core.add(topButton, 0, 0);
			topButton.setOnAction(e -> {
				topButton.setDisable(true);
				((GamePlayBeast)(gameType)).ag.bonusChoiceBeast("2");
			});
			
			botButton.setGraphic(new ImageView(Directory.GAME_CAMOUFLAGE));
			botButton.setDisable(true);
			core.add(botButton, 0, 1);
			botButton.setOnAction(e -> {
				botButton.setDisable(true);
				((GamePlayBeast)(gameType)).ag.bonusChoiceBeast("1");
			});
			
		}else if (entity instanceof Hunter) {
			entity = (Hunter)(entity);
			
			topButton.setGraphic(new ImageView(Directory.GAME_TRAP));
			topButton.setDisable(true);
			core.add(topButton, 0, 0);
			topButton.setOnAction(e -> {
				topButton.setDisable(true);
				((GamePlayHunter)(gameType)).ag.bonusChoiceHunter("2");
			});
			
			botButton.setGraphic(new ImageView(Directory.GAME_WARD));
			botButton.setDisable(true);
			core.add(botButton, 0, 1);
			botButton.setOnAction(e -> {
				botButton.setDisable(true);
				((GamePlayHunter)(gameType)).ag.bonusChoiceHunter("1");
			});
		}
	}
	
	
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
	
	public void setBonusDisable() {
		botButton.setDisable(true);
		topButton.setDisable(true);
	}
	
	public GridPane getCore() {
		return core;
	}
	
}
