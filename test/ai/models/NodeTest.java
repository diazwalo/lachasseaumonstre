package ai.models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testDistanceFromOrigin() {
		Node node = new Node();
		node.setDistanceFromOrigin(10);
		
		assertEquals(node.getDistanceFromOrigin(), 10);
	}
	
	@Test
	public void testAdjacentEdges()
	{
		List<Edge> listEdges = new ArrayList<Edge>();
		
		Edge edgeOne = new Edge("0:0", "0:1", 1);
		Edge edgeTwo = new Edge("0:0", "1:0", 1);
		Edge edgeThree = new Edge("0:0", "1:1", 1);
		listEdges.add(edgeTwo);
		listEdges.add(edgeThree);
		
		
		Node node = new Node();
		node.addAdjacentEdge(edgeOne);
		node.addAdjacentEdge(listEdges);
		
		assertTrue(node.isAdjacent("0:1"));
		assertTrue(node.isAdjacent("1:0"));
		assertTrue(node.isAdjacent("1:1"));
		assertFalse(node.isAdjacent("1:2"));
		assertFalse(node.isAdjacent("0:0"));
	}

}
