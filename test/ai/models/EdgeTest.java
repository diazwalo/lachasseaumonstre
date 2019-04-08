package ai.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdgeTest {

	@Test
	public void testEdge() {
		Edge edge = new Edge("0:1", "1:1", 10);
		
		assertEquals(edge.getNodeOneName(), "0:1");
		assertEquals(edge.getNodeTwoName(), "1:1");
		assertEquals(edge.getWeight(), 10);
	}

}
