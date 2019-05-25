package src.render.bonus;
/*package src.render.text;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import map.Position;
import render.bonus.Trap;
import render.text.Beast;

public class TrapTest {

	private Beast bete;
	private Trap piege;
	private Trap piege2;
	
	@Before
	 public void beforeTest(){   
		piege = new Trap();
		piege2 = new Trap(5 , 4 );
		bete = new Beast(5, 4 , new Config());
    }

	@Test
	public void testInstall() {
		assertNull(piege.getPos());
		piege.install(5, 5);
		assertTrue(piege.getPos().equals(new Position(5,5)));
		piege.install(5, 6);
		assertTrue(piege.getPos().equals(new Position(5,6)));
	}
	 
	@Test
	public void testIsUsed() {
		assertNotNull(piege2.getPos());
		piege2.isUsed();
		assertNull(piege2.getPos());
	}
	
	
	@Test 
	public void testIsActivated() {
		piege.install(6, 7);
		piege.isActivate(bete);
		assertFalse(piege.getActivate());
		assertNotNull(piege.getPos());
		piege2.isActivate(bete);
		assertTrue(piege2.getActivate());
		assertNull(piege2.getPos());
	}

}*/
