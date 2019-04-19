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

public class HunterTest {
	private Entity hunter;
	private Case[][] caseTab;
	
	@Before
	public void before() {
		this.hunter=new Hunter(1, 0);
		this.caseTab=new Case[][] { {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)},
									{new Case(CaseType.SOL, true), new Case(CaseType.SOL, true), new Case(CaseType.SOL, true)},
									{new Case(CaseType.SOL, true), new Case(CaseType.SOL, true), new Case(CaseType.SOL, true)} };
	}
	
	@Test
	public void testIsPosEnt() {
		assertTrue(this.hunter.isPosEnt(1, 0));
		assertFalse(this.hunter.isPosEnt(0, 0));
	}
	
	@Test
	public void testVerifDeplacement() {
		assertFalse(this.hunter.verifDeplacementSpe(caseTab, Mouvment.OUEST, new Beast(0, 1)));
		assertFalse(this.hunter.verifDeplacementSpe(caseTab, Mouvment.SUDOUEST, new Beast(0, 1)));
		assertFalse(this.hunter.verifDeplacementSpe(caseTab, Mouvment.NORD, new Beast(0, 1)));
		assertTrue(this.hunter.verifDeplacementSpe(caseTab, Mouvment.EST, new Beast(0, 1)));
	}
}
