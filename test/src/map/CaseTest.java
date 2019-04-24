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
		this.c1.setBeastWalk(-1);
		this.c2.setBeastWalk(-1);
		this.c3.setBeastWalk(0);
		this.c4.setBeastWalk(0);
	}
	
	@Test
	public void testModifBeastPasWithPosBeastFalse() {
		c1.setBeastWalk(0);
		c2.modifBeastWalk(true);
		assertEquals(c1.getBeastWalk(), c2.getBeastWalk());
	}
	
	@Test
	public void testModifBeastPasWithPosBeastTrue() {
		c3.setBeastWalk(c3.getBeastWalk()+1);
		c4.modifBeastWalk(false);
		assertEquals(c3.getBeastWalk(), c4.getBeastWalk());
	}

}
