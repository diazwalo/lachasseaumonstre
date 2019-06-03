package core.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ai.algorithm.Curiosity;
import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import map.AbstractMap;
import map.Position;

/**
 * Cette classe permet de lancer une partie ou joueront les deux IA ensemble.
 * Le monstre utilisera l'IA Curiosity pour se deplacer sur tout le plateau.
 * Le chasseur utilisera l'IA de Dijkstra pour se rendre le plus rapidement possible a certaines cases
 * du plateau afin de faire la demonstration d'une partie.
 * Le but de ce mode de jeu est donc uen presentation automatique des fonctionnalites et non une victoire rapide.
 * @author PHPierre
 *
 */
public class GameAI extends AbstractGame
{

	private int beastTurn = 0;
	private Graph graph;
	private List<Position> pathHunter;
	private List<Position> pathBeast;
	private Random r;
	
	/**
	 * Instancie une partie entre deux intelligences controles par l'ordinateur.
	 * @param map
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

		while (AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.map.getHunter().decrementBlinded();
			
			//Beast turn
			this.beastTurn();
			
			this.map.setBeastWalk();
			super.checkGameStatus();

			this.hunterTurn();
			super.checkGameStatus();
		}
		
		this.endGame();
	}

	/**
	 * L'IA effectue le tour de la bete
	 * @return boolean
	 */
	public boolean beastTurn()
	{
		this.setBonusIABeast();
		System.out.println(this.map.toString());

		try {
			super.map.moveBeast(Position.toMouvment(this.map.getBeast().getPos(), this.pathBeast.get(this.beastTurn)));
		}catch(Exception e) {
			this.map.setHunterWin(true);
		}
		
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
		System.out.println(this.map.toString());
		
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

		return true;
	}

	@Override
	public void poserBonus() {
		// TODO Auto-generated method stub
	}

}
