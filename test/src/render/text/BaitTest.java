package src.render.text;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import config.Config;
import map.Position;
import render.bonus.Bait;
import render.bonus.Ward;
import render.text.Hunter;

public class BaitTest {

	private Bait leurre;
	private Bait leurre2;
	private Hunter h;
	
	@Before 
	public  void beforeTest(){
		leurre = new Bait();
		leurre2 = new Bait();
		h = new Hunter(5 ,5 , new Config());
	}

	
	@Test 
	public void testInstall() {
		assertNull(leurre.getPos());
		leurre.install(5, 5);
		assertTrue(leurre.getPos().equals(new Position(5,5)));
		leurre.install(5, 6);
		assertTrue(leurre.getPos().equals(new Position(5,6)));
	}
	
	@Test
	public void testUnInstall() {
		leurre.install(5, 5);
		leurre.unInstall(h);
		assertNull(leurre.getPos());
		leurre2.install(5, 6);
		leurre2.unInstall(h);
		assertNotNull(leurre2.getPos());
	}	

}
