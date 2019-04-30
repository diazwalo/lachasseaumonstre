package src.ai.util;

import static org.junit.Assert.*;

import org.junit.Test;

import ai.util.EdgeUtil;
import map.Position;

public class EdgeUtilTest {

	@Test
	public void testFornatEdgePosition() {
		Position positionOne = new Position(1, 1);
        Position positionTwo = new Position(4, 2);
        
        assertEquals("1:1|4:2", EdgeUtil.formatEdge(positionOne, positionTwo));
	}
	
	@Test
	public void testFornatEdgeString() {
		String s1 = "1:1";
		String s2 = "4:2";
        
        assertEquals("1:1|4:2", EdgeUtil.formatEdge(s1, s2));
	}

}
