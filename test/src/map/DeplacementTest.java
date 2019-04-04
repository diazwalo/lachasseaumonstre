package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.IMap;
import map.Mouvment;
import map.Position;
import map.SquareMap;

public class DeplacementTest {

	private IMap map;

    @Before
    public void beforeTest() {
    	map = new SquareMap(11, 11);
    	map.generationMap();
    	map.moveBeast(Mouvment.NORD);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].setBeastPas(true);
    	map.moveBeast(Mouvment.NORD);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].setBeastPas(true);
    	map.moveBeast(Mouvment.OUEST);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].setBeastPas(true);
    }

    @Test
    public void testDeplacement() {
    	assertTrue(this.map.getHunter().isPosEnt(0, 0));
        assertTrue(this.map.mvtValideHunter(Mouvment.EST));
        assertTrue(this.map.mvtValideHunter(Mouvment.SUD));
        assertFalse(this.map.mvtValideHunter(Mouvment.NORD));
        assertFalse(this.map.mvtValideHunter(Mouvment.OUEST));
        
        assertTrue(this.map.getBeast().isPosEnt(this.map.getTab().length-2, this.map.getTab()[this.map.getTab().length-2].length-3));
        assertFalse(this.map.mvtValideBeast(Mouvment.OUEST));
        assertFalse(this.map.mvtValideBeast(Mouvment.EST));
        
    }
}
