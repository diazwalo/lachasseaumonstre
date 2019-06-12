package core.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ai.algorithm.Curiosity;
import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import data.score.IScore;
import data.score.Score;
import data.score.ScoreFile;
import map.AbstractMap;
import map.Mouvment;
import map.Position;
import render.bonus.IBonus;
import src.ai.algorithm.DijkstraTest;

/**
 * Cette classe permet de lancer une partie ou joueront les deux IA ensemble.
 * Le monstre utilisera l'IA Curiosity pour se deplacer sur tout le plateau.
 * Le chasseur utilisera l'IA de Dijkstra pour se rendre le plus rapidement possible a certaines cases
 * du plateau afin de faire la demonstration d'une partie.
 * Le but de ce mode de jeu est donc uen presentation automatique des fonctionnalites et non une victoire rapide.
 * @author PHPierre
 *
 */
public class GameAI extends AbstractGame implements IScore
{

	private int beastTurn = 0;
	public Graph graph;
	public List<Position> pathHunter;
	public List<Position> pathBeast;
	public Random r;
	
	/**
	 * Instancie une partie entre deux intelligences controles par l'ordinateur.
	 * @param map la map de la partie en cours
	 */
	public GameAI(AbstractMap map)
	{
		super(map);
		this.pathHunter = new ArrayList<Position>();
		this.r = new Random();
	}

	/**
	 * Lance une partie IA beast contre IA hunter
	 */
	@Override
	public void launchGame()
	{
		this.graph = new Graph(super.map);
		Curiosity curiosity = new Curiosity(graph);
		
		this.pathBeast = curiosity.getPath(NodeUtil.formatNode(this.map.getBeast().getPos()), super.map);

		System.out.println(this.map);
		
		while (AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			/*this.map.getHunter().decrementBlinded();
			
			//Beast turn
			this.beastTurn();
			
			this.map.setBeastWalk();
			super.checkGameStatus();

			this.hunterTurn();
			super.checkGameStatus();*/
			System.out.println(this.map);
			
			this.beastTurn();
			this.updateStartGame();
			this.checkGameStatus();
			this.map.setBeastWalk();
			
			System.out.println(this.map);
			this.hunterTurn();
			
			this.checkGameStatus();
			this.updateEndGame();
			//this.map.setBeastWalk();
			
		}
		buildScore("");
		this.endGame();
	}

	/**
	 * L'IA effectue le tour de la bete
	 * @return boolean
	 */
	public boolean beastTurn()
	{
		this.setBonusIABeast();

		try {
			super.map.moveBeast(Position.toMouvment(this.map.getBeast().getPos(), this.pathBeast.get(this.beastTurn)));
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		super.incrementNbTurnEntityOne();
		this.beastTurn++;
		return true;
	}

	/**
	 * L'IA effectue le tour du Chasseur 
	 * @return boolean
	 */
	public boolean hunterTurn()
	{
		Dijkstra dijkstra;
		String nodeNameTo = null;
		this.setBonusIAHunter();  
		//Hunter turn

		if (this.pathHunter.isEmpty()) {
			this.graph = new Graph(super.map);
			dijkstra = new Dijkstra(this.graph);
			String nodeNameFrom = NodeUtil.formatNode(this.map.getHunter().getPos());
			do {
				nodeNameTo = NodeUtil.formatNode(pathBeast.get(r.nextInt(pathBeast.size())));
			}while(nodeNameTo.equals(nodeNameFrom));
			
			this.pathHunter.addAll(dijkstra.shortestPathFromTo(nodeNameFrom, nodeNameTo));
		}

		super.map.moveHunter(Position.toMouvment(this.map.getHunter().getPos(), pathHunter.get(0)));
		this.pathHunter.remove(0);

		super.incrementNbTurnEntityTwo();
		return true;
	}
	
	/**
	 * beastTurn retourne true lorsque le mouvement de la bete et valide, cependant si la fonction retourne false alors l'I.A n'a plus de mouvment disponible
	 * @return boolean
	 */
	public boolean beastTurnIfNoChoice() {
		this.map.getBeast().setUntrapped();
		IBonus bo=super.checkBeastTrapped();
		
		List<Mouvment> mvtBeastDispo = super.map.getBeast().getMvtToEmptyCase(super.map.getTab());

		if(this.map.getBeast().getTrapped()) {
			super.triggerTrap();
			return true;
		}
		else {
			if(mvtBeastDispo.size()>0) {
				Mouvment mvtBeast = super.choseMvtNotOnHunter(mvtBeastDispo);
				while(! super.map.moveBeast(mvtBeast)) {
					mvtBeast = super.choseMvtNotOnHunter(mvtBeastDispo);
				}
				super.map.setBeastWalk();
				super.checkGameStatus();
				
				setBonusIABeast();
				
				ramasserBonusBeast();
				
				return true;
			}
			else {
				AbstractGame.gameStatus=GameStatus.BEASTBLOCK;
				return false;
			}
		}
	}
	
	@Override
	public void buildScore(String username)
	{
		Score s = new Score(Curiosity.NAME, Dijkstra.NAME);
		super.saveScore(ScoreFile.AI, s);
	}
}
