package src.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import map.Mouvment;
import map.SquareMap;

class SquareMapTest {
	private SquareMap sm=new SquareMap(11, 11);
	
	@Before
	public void before() {
		//a faire
	}
	
	@Test
	public void testMvtValideBeast() {
		//a modif (faux)
		assertFalse(sm.mvtValideBeast(Mouvment.SUD));
	}
}
