package src.render.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.Position;
import render.bonus.Ward;

public class WardTest {
	
	private  Ward balise ;
	

	@Before 
	public  void beforeTest(){
		balise = new Ward();
	}

	
	@Test 
	public void testInstall() {
		assertNull(balise.getPos());
		balise.install(5, 5);
		assertTrue(balise.getPos().equals(new Position(5,5)));
		balise.install(5, 6);
		assertTrue(balise.getPos().equals(new Position(5,6)));
	}
	
	@Test
	public void testRefresh() {
		balise.install(5, 5);
		balise.refresh();
		balise.setTriggered();
		assertNotNull(balise.getPos());
		assertEquals(3, balise.getTimer());
		balise.refresh();
		assertEquals(2, balise.getTimer());
		balise.refresh();
		assertEquals(1, balise.getTimer());
		balise.refresh();
		assertEquals(0, balise.getTimer());
		assertNull(balise.getPos());
	}

}
