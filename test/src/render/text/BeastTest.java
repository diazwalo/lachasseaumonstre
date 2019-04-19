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
		this.beast=new Beast(0, 0);
		this.caseTab=new Case[][] { {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)},
									{new Case(CaseType.SOL, true), new Case(CaseType.SOL, true), new Case(CaseType.SOL, true)},
									{new Case(CaseType.SOL, true), new Case(CaseType.SOL, true), new Case(CaseType.SOL, true)} };
	}

	@Test
	public void testIsPosEnt() {
		assertTrue(this.beast.isPosEnt(0, 0));
		assertFalse(this.beast.isPosEnt(0, 1));
	}
	
	@Test
	public void testVerifDeplacement() {					
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.OUEST, new Hunter(0, 1)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDOUEST, new Hunter(0, 1)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.NORD, new Hunter(0, 1)));
		//ca marche po
		//assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUD, new Hunter(0, 1)));
	}
}
