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
    	IMap map = new SquareMap(3, 3);
    	map.generationMap();
    	
    	Graph graph = new Graph(map);
    	Map<String, Node> generatedNode = graph.generateNode(map.getTab());
    	
    	
    	//assertEquals(expected, actual);
    }
	/*@Test
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
        
        Edge eOne = new Edge();
        
        Graph graph = new Graph();
        Map<String, Edge> createdEdge = graph.positionToEdge(positionActual, positionArround);
        
	}*/

}
