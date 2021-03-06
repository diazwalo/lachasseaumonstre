package src.render.bonus;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.AbstractMap;
import map.Position;
import map.SquareMap;
import render.bonus.Trap;
import render.text.Beast;

public class TrapTest {

	
	private Trap piege;
	private Trap piege2;
	private AbstractMap sm;
	
	@Before
	 public void beforeTest(){   
		piege = new Trap();
		piege2 = new Trap(5 , 4 );
		sm = new SquareMap(new Config());
		
    } 

	@Test
	public void testInstall() {
		assertNull(piege.getPos());
		piege.install(5, 5);
		sm.addBonusActif(piege2);
		assertTrue(piege.getPos().equals(new Position(5,5)));
		piege.install(5, 6);
		assertTrue(piege.getPos().equals(new Position(5,6)));
	}
	 
	@Test
	public void testIsUsed() {
		assertNotNull(piege2.getPos());
		piege2.setTriggered();
		piege2.nextTurnBonus();
		assertNull(piege2.getPos());
	}
	
	


}
