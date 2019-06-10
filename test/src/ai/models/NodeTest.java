package src.ai.models;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import ai.models.Node;

public class NodeTest
{

	@Test
	public void testDistanceFromOrigin()
	{
		Node node = new Node();
		node.setDistanceFromOrigin(10);
		
		assertEquals(10, node.getDistanceFromOrigin());
	}
	
	@Test
	public void testAdjacentEdges()
	{
		Map<String, Node> listNodes = new HashMap<String, Node>();
		
		Node nodeOne = new Node();
		Node nodeTwo = new Node();
		Node nodeThree = new Node();
		listNodes.put("1:0", nodeTwo);
		listNodes.put("1:1", nodeThree);
		
		
		Node node = new Node();
		node.addAdjacentNode("0:1", nodeOne);
		node.addAdjacentNode(listNodes);
		
		assertTrue(node.isAdjacent("0:1"));
		assertTrue(node.isAdjacent("1:0"));
		assertTrue(node.isAdjacent("1:1"));
		assertFalse(node.isAdjacent("1:2"));
		assertFalse(node.isAdjacent("0:0"));
	}

}
