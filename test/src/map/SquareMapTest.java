package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.IMap;
import map.Mouvment;
import map.SquareMap;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class SquareMapTest {
	public IMap sm;
	public Entity beast;
	public Entity hunter;
	private Config config;

	@Before
	public void initMap() {
		this.config = new Config();
        this.config.setWidth(11);
        this.config.setHeight(11);
        
        this.sm = new SquareMap(config);
		this.sm.generationMap();
		this.beast = new Beast(10, 10);
		this.hunter = new Hunter(0, 0);
	}

	@Test
	public void testMvtValideBeast() {
		assertFalse(sm.mvtValideBeast(Mouvment.SUD));
		assertFalse(sm.mvtValideBeast(Mouvment.EST));
		assertTrue(sm.mvtValideBeast(Mouvment.NORD));
		assertTrue(sm.mvtValideBeast(Mouvment.OUEST));
	}

	@Test
	public void testMvtValideHunter() {
		assertFalse(sm.mvtValideHunter(Mouvment.NORD));
		assertFalse(sm.mvtValideHunter(Mouvment.OUEST));
		assertTrue(sm.mvtValideHunter(Mouvment.SUD));
		assertTrue(sm.mvtValideHunter(Mouvment.EST));
	}

	@Test
	public void testMoveBeast() {
		assertFalse(sm.moveBeast(Mouvment.SUD));
		assertArrayEquals(sm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		assertFalse(sm.moveBeast(Mouvment.EST));
		assertArrayEquals(sm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		assertTrue(sm.moveBeast(Mouvment.NORD));
		this.beast.setPos(Mouvment.NORD);
		assertArrayEquals(sm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());

		assertTrue(sm.moveBeast(Mouvment.OUEST));
		this.beast.setPos(Mouvment.OUEST);
		assertArrayEquals(sm.getBeast().getPos().getTabPosition(), this.beast.getPos().getTabPosition());
	}

	@Test
	public void testMoveHunter() {
		assertFalse(sm.moveHunter(Mouvment.NORD));
		assertArrayEquals(this.sm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertFalse(sm.moveHunter(Mouvment.OUEST));
		assertArrayEquals(this.sm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertTrue(sm.moveHunter(Mouvment.SUD));
		this.hunter.setPos(Mouvment.SUD);
		assertArrayEquals(this.sm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());

		assertTrue(sm.moveHunter(Mouvment.EST));
		this.hunter.setPos(Mouvment.EST);
		assertArrayEquals(this.sm.getHunter().getPos().getTabPosition(), this.hunter.getPos().getTabPosition());
	}
}
