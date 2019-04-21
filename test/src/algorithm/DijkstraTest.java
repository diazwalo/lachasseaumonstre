package src.algorithm;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import map.IMap;
import map.Position;
import map.SquareMap;

public class DijkstraTest {

	@Test
	public void testShortestPath() {
		IMap map = new SquareMap(10, 10);
		map.generationMap();
		
		Graph graph = new Graph(map);
		Dijkstra dijkstra = new Dijkstra(graph);
		
		String nodeNameHunter = NodeUtil.formatNode(map.getHunter().getPos());
		String nodeNameBeast = NodeUtil.formatNode(map.getBeast().getPos());
	
		List<Position> shortestPath = dijkstra.shortestPathFromTo(nodeNameHunter, nodeNameBeast);
		
		//On verifie que la taille du chemin est bien le plus court
		assertEquals(shortestPath.size(), 10);
		
		//On verifie desormais si on atteind bien notre point final
		Position destination = new Position(9, 9);
		assertTrue(shortestPath.get(shortestPath.size()-1).equals(destination));
	}

}
