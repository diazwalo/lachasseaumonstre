package src.map;

import static org.junit.Assert.*;

import map.Position;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

    private Position position;

    @Before
    public void beforeTest()
    {
        this.position = new Position(1, 1);
    }

    @Test
    public void testIsPosition()
    {
        assertTrue(this.position.isPos(1, 1));
    }

    @Test
    public void testPosition()
    {
        assertArrayEquals(this.position.position(), new int[]{1, 1});
    }

}
