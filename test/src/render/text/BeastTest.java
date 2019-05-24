package src.render.text;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import config.Config;
import map.Case;
import map.CaseType;
import map.Mouvment;
import render.text.Beast;
import render.text.Entity;
import render.text.Hunter;

public class BeastTest {
	private Entity beast;
	private Case[][] caseTab;
	private Config config=new Config();
	
	@Before
	public void before() {
		this.beast=new Beast(1, 0, config);
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
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.OUEST, new Hunter(2, 1, config)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDEST, new Hunter(2, 1, config)));
		assertFalse(this.beast.verifDeplacementSpe(caseTab, Mouvment.NORD, new Hunter(2, 1, config)));
		assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.EST, new Hunter(2, 1, config)));
		assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDOUEST, new Hunter(2, 1, config)));
	}
	
	@Test
	public void testVerifDeplacementSpe() {
		Mouvment mvt=Mouvment.EST;
		this.caseTab[this.beast.getPos().getPosX()+mvt.getMvtX()][this.beast.getPos().getPosY()+mvt.getMvtY()].setBeastWalk(42);
		assertFalse(this.beast.verifDeplacementSpe(caseTab, mvt, new Hunter(2, 1, config)));
		assertTrue(this.beast.verifDeplacementSpe(caseTab, Mouvment.SUDOUEST, new Hunter(2, 1, config)));
	}
	
	@Test
	public void testMvtPossible() {
		List<Mouvment> mvtPoss=this.beast.getMvtToEmptyCase(caseTab);
		assertFalse(mvtPoss.contains(Mouvment.NORD));
		assertFalse(mvtPoss.contains(Mouvment.NORDEST));
		assertTrue(mvtPoss.contains(Mouvment.EST));
		assertTrue(mvtPoss.contains(Mouvment.SUDEST));
		assertTrue(mvtPoss.contains(Mouvment.SUD));
		assertTrue(mvtPoss.contains(Mouvment.SUDOUEST));
		assertFalse(mvtPoss.contains(Mouvment.OUEST));
		assertFalse(mvtPoss.contains(Mouvment.NORDOUEST));
	}
	
	@Test
	public void testIsLock() {
		assertFalse(this.beast.isLock(caseTab, new Hunter(2, 1, config)));
		
		for (int i = 0; i < this.caseTab.length; i++)
			for (int j = 0; j < this.caseTab[i].length; j++)
				this.caseTab[i][j].setBeastWalk(42);
		
		assertTrue(this.beast.isLock(caseTab, new Hunter(2, 1, config)));
	}
}
