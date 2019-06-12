package render.ui.component.gamePlay;

import java.io.FileNotFoundException;

import ai.algorithm.Curiosity;
import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import core.game.AbstractGame;
import core.game.GameAI;
import core.game.GameStatus;
import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.AbstractMap;
import map.Mouvment;
import render.ui.view.EndScreen;

public class GamePlayIA extends AbstractGamePlay implements IScore{
	//1 si c'est au tour du Chasseur et a 2 si c'est au tour de la Bete
	int entityTurn;

	public GamePlayIA(AbstractMap map) throws FileNotFoundException {
		super(map);
		this.entityTurn = 1;
		super.refreshIAView(map);
		ag = new GameAI(map);
		((GameAI)this.ag).graph = new Graph(super.map);
		Curiosity curiosity = new Curiosity(((GameAI)this.ag).graph);
		
		((GameAI)this.ag).pathBeast = curiosity.getPath(NodeUtil.formatNode(this.map.getBeast().getPos()), super.map);
		ag.map.setBeastWalk();
	}

	@Override
	public boolean play(Mouvment mouvment) {
		return false;
	}

	@Override
	public void next() {
		if(entityTurn == 1) {
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
				((GameAI)ag).hunterTurn();
				ag.checkBeastRevealed();
				ag.updateEndGame();
				ag.checkGameStatus();
				ag.ramasserBonusHunter();
				super.refreshIAView(super.map);
			}else {
				System.out.println(super.map.isBeastWin());
				System.out.println(super.map.isHunterWin());
				System.out.println(AbstractGame.gameStatus);
			}
		}else if(entityTurn == 2) {
			
			if(! super.map.isBeastWin() && ! super.map.isHunterWin() && AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
				((GameAI)ag).beastTurn();
				ag.checkBeastRevealed();
				ag.updateEndGame();
				this.map.setBeastWalk();
				ag.checkGameStatus();
				ag.ramasserBonusBeast();
				super.refreshIAView(super.map);
			}
		}
		
		if(this.entityTurn == 1) {
			this.entityTurn = 2;
		}else {
			this.entityTurn = 1;
		}
	}

	@Override
	public void buildScore(String username)
	{
		Score s = new Score(Curiosity.NAME, Dijkstra.NAME);
		ag.saveScore(ScoreFile.AI, s);
	}
	
}
