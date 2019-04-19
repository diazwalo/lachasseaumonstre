package src.map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.Case;
import map.CaseType;

public class CaseTest {
	private Case c1=new Case(CaseType.SOL, false);
	private Case c2=new Case(CaseType.SOL, false);
	private Case c3=new Case(CaseType.SOL, true);
	private Case c4=new Case(CaseType.SOL, true);
	
	@Before
	public void before() {
		this.c1.setBeastPas(-1);
		this.c2.setBeastPas(-1);
		this.c3.setBeastPas(0);
		this.c4.setBeastPas(0);
	}
	
	@Test
	public void testModifBeastPasWithPosBeastFalse() {
		c1.setBeastPas(0);
		c2.modifBeastPas(true);
		assertEquals(c1.getBeastPas(), c2.getBeastPas());
	}
	
	@Test
	public void testModifBeastPasWithPosBeastTrue() {
		c3.setBeastPas(c3.getBeastPas()+1);
		c4.modifBeastPas(false);
		assertEquals(c3.getBeastPas(), c4.getBeastPas());
	}

}
