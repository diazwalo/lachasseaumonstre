package src.ai.algorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.algorithm.Dijkstra;
import ai.algorithm.Kruskal;
import ai.graph.Graph;
import config.Config;
import map.IMap;
import map.SquareMap;

public class KruskalTest {

private Config config;
	
	@Before
    public void beforeTest()
    {   
		this.config = new Config();
        this.config.setWidth(10);
        this.config.setHeight(10);
    }

	@Test
	public void testPath()
	{
		IMap map = new SquareMap(this.config);
		map.generationMap();
		
		Graph graph = new Graph(map);
		Kruskal kruskal = new Kruskal(graph);
		
		kruskal.getPath();
	}
}
