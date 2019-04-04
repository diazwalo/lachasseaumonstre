package ai.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import map.Position;

public class NodeUtilTest {
	
	@Test
    public void testIsPosition()
    {
        Position positionOne = new Position(1, 1);
        Position positionTwo = new Position(4, 2);
        Position positionThree = new Position(50, 70);
        
        String nameOne = NodeUtil.formatNode(positionOne);
        String nameTwo = NodeUtil.formatNode(positionTwo);
        String nameThree = NodeUtil.formatNode(positionThree);
        
        assertEquals("1:1", nameOne);
        assertEquals("4:2", nameTwo);
        assertEquals("50:70", nameThree);
    }

}
