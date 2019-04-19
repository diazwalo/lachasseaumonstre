package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.IMap;
import map.Mouvment;
import map.SquareMap;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class SquareMapTest {
	public IMap sm=new SquareMap(11, 11);
	public Entity beast=new Beast(10, 10);
	public Entity hunter=new Hunter(0, 0);

	@Before
	public void initMap() {
		sm.generationMap();
		beast=new Beast(10, 10);
		hunter=new Hunter(0, 0);
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
		assertArrayEquals(sm.getBeast().getPos().position(), this.beast.getPos().position());

		assertFalse(sm.moveBeast(Mouvment.EST));
		assertArrayEquals(sm.getBeast().getPos().position(), this.beast.getPos().position());

		assertTrue(sm.moveBeast(Mouvment.NORD));
		this.beast.setPos(Mouvment.NORD);
		assertArrayEquals(sm.getBeast().getPos().position(), this.beast.getPos().position());

		assertTrue(sm.moveBeast(Mouvment.OUEST));
		this.beast.setPos(Mouvment.OUEST);
		assertArrayEquals(sm.getBeast().getPos().position(), this.beast.getPos().position());
	}

	@Test
	public void testMoveHunter() {
		assertFalse(sm.moveHunter(Mouvment.NORD));
		assertArrayEquals(this.sm.getHunter().getPos().position(), this.hunter.getPos().position());

		assertFalse(sm.moveHunter(Mouvment.OUEST));
		assertArrayEquals(this.sm.getHunter().getPos().position(), this.hunter.getPos().position());

		assertTrue(sm.moveHunter(Mouvment.SUD));
		this.hunter.setPos(Mouvment.SUD);
		assertArrayEquals(this.sm.getHunter().getPos().position(), this.hunter.getPos().position());

		assertTrue(sm.moveHunter(Mouvment.EST));
		this.hunter.setPos(Mouvment.EST);
		assertArrayEquals(this.sm.getHunter().getPos().position(), this.hunter.getPos().position());
	}
}
