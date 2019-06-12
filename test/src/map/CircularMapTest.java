package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.AbstractMap;
import map.CircularMap;
import map.Mouvment;
import map.Position;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class CircularMapTest {

	public AbstractMap cm;
	private Config config;
	
	
	@Before
	public void initMap() {
		this.config = new Config();
        this.config.setWidth(20);
        this.config.setHeight(20);
        
        this.cm = new CircularMap(this.config);
        this.cm.generationMap();
	}
	
	@Test
	public void verifPlacementEntity() {
		assertEquals(new Position(19,14),cm.getBeast().getPos() );
		assertEquals(new Position(0,5), cm.getHunter().getPos() );
	}
	
	@Test
	public void testMvtValideBeast() {
		assertFalse(cm.mvtValideBeast(Mouvment.SUD));
		assertFalse(cm.mvtValideBeast(Mouvment.EST));
		assertTrue(cm.mvtValideBeast(Mouvment.NORD));
		assertTrue(cm.mvtValideBeast(Mouvment.OUEST));
		
	}
	
	@Test
	public void testMvtValideHunter() {
		assertFalse(cm.mvtValideHunter(Mouvment.NORD));
		assertFalse(cm.mvtValideHunter(Mouvment.OUEST));
		assertTrue(cm.mvtValideHunter(Mouvment.SUD));
		assertTrue(cm.mvtValideHunter(Mouvment.EST));
	}
	
	@Test
	public void testMoveBeast() {
		assertFalse(cm.moveBeast(Mouvment.SUD));
		assertEquals(cm.getBeast().getPos(), new Position(19,14));

		assertFalse(cm.moveBeast(Mouvment.EST));
		assertEquals(cm.getBeast().getPos(), new Position(19,14));

		assertTrue(cm.moveBeast(Mouvment.NORD));
		assertEquals(cm.getBeast().getPos(), new Position(19,13));

	
		assertTrue(cm.moveBeast(Mouvment.OUEST));
		assertEquals(cm.getBeast().getPos(), new Position(18,13));
		
	}
	

	@Test
	public void testMoveHunter() {
		assertTrue(cm.moveHunter(Mouvment.SUD));
		assertEquals(cm.getHunter().getPos(), new Position(0,6));

		assertTrue(cm.moveHunter(Mouvment.EST));
		assertEquals(cm.getHunter().getPos(), new Position(1,6));
		
		assertTrue(cm.moveHunter(Mouvment.NORD));
		assertEquals(cm.getHunter().getPos(), new Position(1,5));
		
		assertTrue(cm.moveHunter(Mouvment.OUEST));
		assertEquals(cm.getHunter().getPos(), new Position(0,5));
		
		assertFalse(cm.moveHunter(Mouvment.NORD));
		assertEquals(cm.getHunter().getPos(), new Position(0,5));
		
	}
	
}