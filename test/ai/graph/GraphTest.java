package ai.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ai.algorithm.Graph;
import ai.models.Edge;
import ai.models.Node;
import ai.util.EdgeUtil;
import map.IMap;
import map.Position;
import map.SquareMap;

public class GraphTest {

    @Before
    public void beforeTest()
    {    	
        
    }
    
    @Test
    public void testGenerateNode()
    {
    	String[] awaitedPosition = {"0:0", "0:1", "0:2", "1:0", "1:1", "1:2", "2:0", "2:1", "2:2"};
    	IMap map = new SquareMap(3, 3);
    	map.generationMap();

    	Graph graph = new Graph();
    	Map<String, Node> generatedNode = graph.generateNode(map.getTab());

    	int i = 0;
    	for (String name : generatedNode.keySet()) {
			assertEquals(name, awaitedPosition[i]);
			i++;
		}
    	
    	assertEquals(awaitedPosition.length, generatedNode.size());
    }
    
    /*@Test
    public void testGenerateEdge()
    {
    	IMap map = new SquareMap(3, 3);
    	map.generationMap();

    	Graph graph = new Graph();
    	Map<String, Edge> generatedEdge = graph.generateEdge(map);
    	
    	System.out.println("RETOUR:"+generatedEdge.size());
    	assertEquals(40, generatedEdge.size());
    }*/
    
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
        
        Map<String, Edge> resultedEdge = new HashMap<String, Edge>();
        Graph graph = new Graph();
        Map<String, Edge> createdEdge = graph.positionToEdge(positionActual, positionArround);
        
        assertEquals(createdEdge.size(), positionArround.size());
        
        assertTrue(createdEdge.containsKey(EdgeUtil.formatEdge(positionActual, pTwo)));
	}

}
