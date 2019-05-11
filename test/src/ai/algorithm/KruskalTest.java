package src.ai.algorithm;

import map.AbstractMap;
import org.junit.Before;
import org.junit.Test;

import ai.algorithm.Kruskal;
import ai.graph.Graph;
import config.Config;
import map.SquareMap;

/**
 * Ce projet est abandonne car il ne fera pas un bon algo de decouverte de graphe.
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
        this.config.setWidth(5);
        this.config.setHeight(5);
    }

	@Test
	public void testPath()
	{
        AbstractMap map = new SquareMap(this.config);
		map.generationMap();
		
		Graph graph = new Graph(map);
		Kruskal kruskal = new Kruskal(graph);
		
		kruskal.getPath();
	}
}
