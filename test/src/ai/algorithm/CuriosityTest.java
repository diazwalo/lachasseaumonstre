package src.ai.algorithm;

import ai.algorithm.Curiosity;
import ai.algorithm.Kruskal;
import ai.graph.Graph;
import config.Config;
import map.AbstractMap;
import map.SquareMap;
import org.junit.Before;
import org.junit.Test;

/**
 * Ce projet est abandonné car il ne fera pas un bon algo de decouverte de graphe.
 * @author PHPierre
 *
 */
public class CuriosityTest
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
		Curiosity curiosity = new Curiosity(graph);
		
		System.out.println(curiosity.getPath("9:9", "0:0", map));
	}
}
