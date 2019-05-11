package src.ai.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import map.AbstractMap;
import org.junit.Before;
import org.junit.Test;

import ai.graph.Graph;
import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import config.Config;
import map.Position;
import map.SquareMap;

public class GraphTest
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
    public void testGenerateNode()
    {
    	String[] awaitedPosition = {
    	        "0:0", "0:1", "0:2", "0:3", "0:4",
                "1:0", "1:1", "1:2", "1:3", "1:4",
                "2:0", "2:1",        "2:3", "2:4", //There is a blank because there is an obstacle on map
                "3:0", "3:1", "3:2", "3:3", "3:4",
                "4:0", "4:1", "4:2", "4:3", "4:4"
    	};
        AbstractMap map = new SquareMap(this.config);
    	map.generationMap();

    	Graph graph = new Graph();
    	Map<String, Node> generatedNode = graph.generateNode(map);

    	int i = 0;
    	for (String name : generatedNode.keySet()) {
			assertEquals(name, awaitedPosition[i]);
			i++;
		}
    	
    	assertEquals(awaitedPosition.length, generatedNode.size());
    }
    
    @Test
    public void testGenerateEdge()
    {
        AbstractMap map = new SquareMap(this.config);
    	map.generationMap();

    	Graph graph = new Graph();
    	Map<String, Edge> generatedEdge = graph.generateEdge(map);

    	//There are 128 edges on a 5x5 graph
        /* Each numbers show the number of valid movement
         * 3+5+5+5+3 =21
         * 5+7+7+7+5 =31
         * 5+7+0+7+5 =24
         * 5+7+7+7+5 =31
         * 3+5+5+5+3 =21
         * =128
         */
    	assertEquals(128, generatedEdge.size());
    }
    
	@Test
	public void testPositionToEdge() {
		Position positionActual = new Position(1, 1);
		
		List<Position> positionArround = new ArrayList<Position>();
		
		Position pOne = new Position(0, 0);
        Position pTwo = new Position(0, 1);
        Position pThree = new Position(0, 2);
        Position pFour = new Position(1, 0);
        Position pFive = new Position(1, 2);
        Position pSix = new Position(2, 0);
        Position pSeven = new Position(2, 1);
        Position pEight = new Position(2, 2);

        positionArround.add(pOne);
        positionArround.add(pTwo);
        positionArround.add(pThree);
        positionArround.add(pFour);
        positionArround.add(pFive);
        positionArround.add(pSix);
        positionArround.add(pSeven);
        positionArround.add(pEight);
        
        Graph graph = new Graph();
        Map<String, Edge> createdEdge = graph.positionToEdge(positionActual, positionArround);
        
        assertEquals(createdEdge.size(), positionArround.size());
        
        String name = EdgeUtil.formatEdge(positionActual, pOne);
        assertEquals(createdEdge.get(name).getNodeOneName(), "1:1");
        assertEquals(createdEdge.get(name).getNodeTwoName(), "0:0");
        assertTrue(createdEdge.containsKey(name));
	}

}
