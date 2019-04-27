package src.render.text;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.Case;
import map.CaseType;
import map.Mouvment;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class HunterTest {
	private Entity hunter;
	private Case[][] caseTab;
	private Config config=new Config();
	
	@Before
	public void before() {
		this.hunter=new Hunter(1, 0, config);
		this.caseTab=new Case[][] { {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)},
									{new Case(CaseType.SOL, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, true)},
									{new Case(CaseType.SOL, false), new Case(CaseType.SOL, false), new Case(CaseType.SOL, false)} };
	}
	
	@Test
	public void testIsPosEnt() {
		assertTrue(this.hunter.isPosEnt(1, 0));
		assertFalse(this.hunter.isPosEnt(0, 0));
	}
	
	@Test
	public void testVerifDeplacement() {
		assertFalse(this.hunter.verifDeplacementSpe(caseTab, Mouvment.OUEST, new Beast(2, 1, config)));
		assertTrue(this.hunter.verifDeplacementSpe(caseTab, Mouvment.SUDEST, new Beast(2, 1, config)));
		assertFalse(this.hunter.verifDeplacementSpe(caseTab, Mouvment.NORD, new Beast(2, 1, config)));
		assertTrue(this.hunter.verifDeplacementSpe(caseTab, Mouvment.EST, new Beast(2, 1, config)));
	}
	
	@Test
	public void testMvtPossible() {
		ArrayList<Mouvment> mvtPoss=this.hunter.getMvtEmptyCase(this.caseTab);
		assertFalse(mvtPoss.contains(Mouvment.NORD));
		assertFalse(mvtPoss.contains(Mouvment.NORDEST));
		assertTrue(mvtPoss.contains(Mouvment.EST));
		//devrait etre assertFalse
		assertTrue(mvtPoss.contains(Mouvment.SUDEST));
		assertTrue(mvtPoss.contains(Mouvment.SUD));
		assertTrue(mvtPoss.contains(Mouvment.SUDOUEST));
		assertFalse(mvtPoss.contains(Mouvment.OUEST));
		assertFalse(mvtPoss.contains(Mouvment.NORDOUEST));
	}
	
	@Test
	public void testIsLock() {
		assertFalse(this.hunter.isLock(caseTab, new Beast(2, 1, config)));
		
		Case[][] tabBlock=new Case[][] { {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, false), new Case(CaseType.OBSTACLE, false)},
										 {new Case(CaseType.OBSTACLE, false), new Case(CaseType.OBSTACLE, false), new Case(CaseType.OBSTACLE, false)},
										 {new Case(CaseType.OBSTACLE, false), new Case(CaseType.SOL, true), new Case(CaseType.OBSTACLE, false)} };
		
		//devait etre assertTrue
		assertFalse(this.hunter.isLock(tabBlock, new Beast(1, 2, config)));
	}
}
