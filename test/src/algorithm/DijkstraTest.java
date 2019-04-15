package src.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;

import ai.algorithm.Dijkstra;
import ai.graph.Graph;
import ai.util.NodeUtil;
import map.IMap;
import map.SquareMap;

public class DijkstraTest {

	@Test
	public void test() {
		IMap map = new SquareMap(10, 10);
		map.generationMap();
		
		Graph graph = new Graph(map);
		Dijkstra dijkstra = new Dijkstra(graph);
		
		String nodeNameHunter = NodeUtil.formatNode(map.getHunter().getPos());
		String nodeNameBeast = NodeUtil.formatNode(map.getBeast().getPos());
	
		dijkstra.shortestPathFromTo(nodeNameHunter, nodeNameBeast);
	}

}
