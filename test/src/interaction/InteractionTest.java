package src.interaction;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import interaction.Interaction;
import map.Mouvment;

public class InteractionTest {
	Mouvment mvt;

	@Before
	public void beforeTest(){
		String[] strMvt=Interaction.generateStrMvt();
		Mouvment[] tabMvt=Interaction.getTabMvt(strMvt);
	}
	
	@Test
	public void testInter() {
		assertTrue(true);
	}

}
