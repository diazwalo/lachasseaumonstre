package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.IMap;
import map.Mouvment;
import map.SquareMap;

public class DeplacementTest {

	private IMap map;
	private Config config;
	
	@Before
    public void beforeTest()
    {   
		this.config = new Config();
        this.config.setWidth(11);
        this.config.setHeight(11);
    }
	
	@Before
    public void before() {
    	map = new SquareMap(this.config);
    	map.generationMap();
    	map.moveBeast(Mouvment.NORD);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].modifBeastWalk(true);
    	map.moveBeast(Mouvment.NORD);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].modifBeastWalk(true);
    	map.moveBeast(Mouvment.OUEST);
    	map.getTab()[map.getBeast().getPos().getPosX()][map.getBeast().getPos().getPosY()].modifBeastWalk(true);
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
