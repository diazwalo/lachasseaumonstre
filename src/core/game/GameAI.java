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

public class GameAI extends AbstractGame
{

	private int beastTurn = 0;
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

		while (AbstractGame.gameStatus.equals(GameStatus.INGAME)) {
			this.map.getHunter().decrementBlinded();
			
			//Beast turn
			this.beastTurn();
			
			this.map.setBeastWalk();
			super.checkGameStatus();

			this.hunterTurn();
			super.checkGameStatus();
		}
		
		this.EndGame();
	}

	@Override
	public boolean beastTurn()
	{
		System.out.println(this.map.toString());

		try {
			super.map.moveBeast(Position.toMouvment(this.map.getBeast().getPos(), this.pathBeast.get(this.beastTurn)));
		}catch(Exception e) {
			this.map.setHunterWin(true);
		}
		
		this.beastTurn++;
		return true;
	}

	@Override
	public boolean hunterTurn()
	{
		Dijkstra dijkstra;
		String nodeNameTo = null;
		
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
		
		//Interaction.pressEnter();
		return true;
	}

	@Override
	public void poserBonus()
	{
		// TODO Auto-generated method stub
	}

}