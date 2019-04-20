package src.render.text;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.Case;
import map.CaseType;
import map.Mouvment;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class BeastTest {
	private Entity beast;
	private Case[][] caseTab;
	
	@Before
	public void before() {
		this.beast=new Beast(1, 0);
		this.caseTab=new Case[][] { {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)},
									{new Case(CaseType.SOL, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)},
									{new Case(CaseType.SOL, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)} };
	}

	@Test
	public void testIsPosEnt() {
		assertTrue(this.beast.isPosEnt(1, 0));
		assertFalse(this.beast.isPosEnt(0, 0));
	}
	
	@Test
	public void testVerifDeplacement() {					
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.OUEST, new Hunter(2, 1)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDEST, new Hunter(2, 1)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.NORD, new Hunter(2, 1)));
		assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.EST, new Hunter(2, 1)));
		assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDOUEST, new Hunter(2,1)));
	}
	
	@Test
	public void testVerifDeplacementSpe() {
		this.caseTab[0][2].setBeastPas(42);
		assertEquals(this.caseTab[0][2].getBeastPas(), 42);
		assertTrue(this.beast.isPosEnt(1, 0));
		//assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.EST, new Hunter(2, 1)));
	}
}
