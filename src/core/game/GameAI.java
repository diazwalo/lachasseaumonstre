package core.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ai.algorithm.Curiosity;
import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import interaction.Interaction;
import map.AbstractMap;
import map.Position;

public class GameAI extends AbstractGame
{

	private int beastTurn = 0;
	private int hunterTurn = 0;
	private Graph graph;
	private List<Position> pathHunter;
	private List<Position> pathBeast;
	private Random r;
	
	public GameAI(AbstractMap map)
	{
		super(map);
		this.pathHunter = new ArrayList<Position>();
		this.r = new Random();
	}

	@Override
	public void launchGame()
	{
		this.graph = new Graph(super.map);
		Curiosity curiosity = new Curiosity(graph);
		System.out.println("lol"+this.graph.getListNode().size());
		
		this.pathBeast = curiosity.getPath(NodeUtil.formatNode(this.map.getBeast().getPos()), super.map);

		System.out.println("lol"+this.graph.getListNode().size());
		while (AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.map.getHunter().decrementBlinded();
			
			//Beast turn
			this.beastTurn();
			
			this.map.setBeastWalk();
			super.checkGameStatus();

			this.hunterTurn();
		}
		
		this.EndGame();
	}

	@Override
	public boolean beastTurn()
	{
		System.out.println(this.map.toString());
		
		super.map.moveBeast(Position.toMouvment(this.map.getBeast().getPos(), this.pathBeast.get(this.beastTurn)));
		this.beastTurn++;
		return true;
	}

	@Override
	public boolean hunterTurn()
	{
		Dijkstra dijkstra;
		//Hunter turn
		System.out.println(this.map.toString());
		if (this.pathHunter.isEmpty()) {
			this.graph = new Graph(super.map);
			dijkstra = new Dijkstra(this.graph);
			String nodeNameFrom = NodeUtil.formatNode(this.map.getHunter().getPos());
			String nodeNameTo = NodeUtil.formatNode(pathBeast.get(r.nextInt(pathBeast.size())));
			System.out.println(nodeNameTo);
			
			this.pathHunter.addAll(dijkstra.shortestPathFromTo(nodeNameFrom, nodeNameTo));
			this.hunterTurn = 0;
		}

		super.map.moveHunter(Position.toMouvment(this.map.getHunter().getPos(), pathHunter.get(0)));
		this.pathHunter.remove(0);
		this.hunterTurn++;
		
		//Interaction.pressEnter();
		return true;
	}

	@Override
	public void poserBonus()
	{
		// TODO Auto-generated method stub
		
	}

}
