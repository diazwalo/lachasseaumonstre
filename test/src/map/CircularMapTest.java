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
	public Entity beast;
	public Entity hunter;
	private Config config;
	
	
	@Before
	public void initMap() {
		this.config = new Config();
        this.config.setWidth(20);
        this.config.setHeight(20);
        
        this.cm = new CircularMap(this.config);
        this.cm.generationMap();
        this.beast = new Beast(18, 15, new Config());
		this.hunter = new Hunter(1, 4, new Config());
	}
	
	@Test
	public void verifPlacementEntity() {
		assertEquals(new Position(18,15),cm.getBeast().getPos() );
		assertEquals(new Position(1,4), cm.getHunter().getPos() );
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
		assertArrayEquals(cm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		assertFalse(cm.moveBeast(Mouvment.EST));
		assertArrayEquals(cm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		assertTrue(cm.moveBeast(Mouvment.NORD));
		this.beast.setPos(Mouvment.NORD);
		assertArrayEquals(cm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		
		assertFalse(cm.moveBeast(Mouvment.OUEST));
		assertArrayEquals(cm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());
		
	}
	

	@Test
	public void testMoveHunter() {
		assertFalse(cm.moveHunter(Mouvment.NORD));
		assertArrayEquals(this.cm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertFalse(cm.moveHunter(Mouvment.OUEST));
		assertArrayEquals(this.cm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertTrue(cm.moveHunter(Mouvment.SUD));
		this.hunter.setPos(Mouvment.SUD);
		assertArrayEquals(this.cm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertFalse(cm.moveHunter(Mouvment.EST));
		assertArrayEquals(this.cm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());
	}
	
}
