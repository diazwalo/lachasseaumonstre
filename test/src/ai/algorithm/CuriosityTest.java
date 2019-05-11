package src.ai.algorithm;

import ai.algorithm.Curiosity;
import ai.graph.Graph;
import config.Config;
import map.AbstractMap;
import map.Position;
import map.SquareMap;

import java.util.List;

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
        this.config.setWidth(10);
        this.config.setHeight(10);
    }

	@Test
	public void testPath()
	{
        AbstractMap map = new SquareMap(this.config);
		map.generationMap();
		
		Graph graph = new Graph(map);
		Curiosity curiosity = new Curiosity(graph);
		
		List<Position> r = curiosity.getPath("9:9", "0:0", map);
		System.out.println(r);
		System.out.println(r.size());
	}
}
