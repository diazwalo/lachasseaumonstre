package src.ai.models;

import static org.junit.Assert.*;

import org.junit.Test;

import ai.models.Edge;

public class EdgeTest
{

	@Test
	public void testEdge()
	{
		Edge edge = new Edge("0:1", "1:1", 10);
		
		assertEquals("0:1", edge.getNodeOneName());
		assertEquals("1:1", edge.getNodeTwoName());
		assertEquals(10, edge.getWeight());
	}

}
