package src.render.bonus;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import map.Position;
import render.bonus.Camouflage;

public class CamouflageTest {

		private Camouflage c1;
		private Camouflage c2;

		
		@Before 
		public  void beforeTest(){
			c1 = new Camouflage();
			c2 = new Camouflage();
		}

		
		@Test 
		public void testInstall() {
			assertNull(c1.getPos());
			c1.install(5, 5);
			assertTrue(c1.getPos().equals(new Position(5,5)));
			c1.install(5, 6);
			assertTrue(c1.getPos().equals(new Position(5,6)));
			c1.install(new Position(7,7));
			assertTrue(c1.getPos().equals(new Position(7,7)));
			c1.install(new Position(1,7));
			assertTrue(c1.getPos().equals(new Position(1,7)));
		}
		
		@Test
		public void testUnInstall() {
			c1.install(5, 5);
			c1.unistall();
			assertNull(c1.getPos());
			c2.install(5, 6);
			c2.unistall();
			assertNull(c2.getPos());
		}	

	}
