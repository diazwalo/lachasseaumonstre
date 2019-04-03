package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.IMap;
import map.Mouvment;
import map.Position;
import map.SquareMap;

public class Deplacement {

	private IMap map;

    @Before
    public void beforeTest() {
    	map = new SquareMap(11, 11);
    	map.generationMap();
    }

    @Test
    public void testDeplacement() {
    	assertTrue(this.map.getHunter().isPosEnt(0, 0));
        //assertTrue(this.map.mvtValideHunter(Mouvment.EST));
        assertTrue(this.map.mvtValideHunter(Mouvment.SUD));
    }

}
