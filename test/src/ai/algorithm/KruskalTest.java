package src.ai.algorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ai.algorithm.Kruskal;
import ai.graph.Graph;
import config.Config;
import map.IMap;
import map.SquareMap;

/**
 * TODO : to finish
 * @author PHPierre
 *
 */
public class KruskalTest
{

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
		
		System.out.println(kruskal.getPath().size());
		//assertEquals(kruskal, kruskal.getPath());
		
		//Ceci n'est pas encore finie.
	}
}
