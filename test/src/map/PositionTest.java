package src.map;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import map.*;
import org.junit.Before;
import org.junit.Test;

import config.Config;

public class PositionTest {

	private Position position;
	private Config config;
	
	@Before
    public void beforeTest()
    {   
		this.config = new Config();
        this.config.setWidth(11);
        this.config.setHeight(11);
        this.position = new Position(2, 1);
    }

	@Test
	public void testIsPosition() {
		assertTrue(this.position.isPos(2, 1));
		assertFalse(this.position.isPos(1, 2));
	}

	@Test
	public void testPosition() {
		assertArrayEquals(this.position.getTabPosition(), new int[] {2, 1});
	}
	
	@Test
	public void testMovePosition() {
		Mouvment mvtTest=Mouvment.SUDEST;
		Position posTest=new Position((this.position.getPosX()+mvtTest.getMvtX()), (this.position.getPosY()+mvtTest.getMvtY()));
		
		this.position.movePosition(mvtTest);
		assertArrayEquals(posTest.getTabPosition(), this.position.getTabPosition());
	}
	
	@Test
	public void testGetModifPosTempo() {
		Mouvment mvtTest=Mouvment.SUDEST;
		int[] posModifTest=new int[] {(this.position.getPosX()+mvtTest.getMvtX()), (this.position.getPosY()+mvtTest.getMvtY())};
		
		assertArrayEquals(posModifTest, this.position.getModifPosTempo(mvtTest.getMvt()));
	}
	
	@Test
	public void testCheckPosition() {
		List<Position> positionAdjacent =new ArrayList<Position>();
        AbstractMap sm = new SquareMap(this.config);
		sm.generationMap();
		
		Position[] tabPosValide=new Position[] {new Position(0, 0), new Position(sm.getTab().length-1, sm.getTab()[0].length-1)};
		Position[] tabPosInvalide=new Position[] {new Position(-1, 0), new Position(0, -1), new Position(sm.getTab().length, sm.getTab()[0].length), new Position(2, 2)};
		Position[] tabPosToAdd=new Position[tabPosInvalide.length+tabPosValide.length];
		
		int idx=0;
		
		for (Position posToAdd : tabPosInvalide) tabPosToAdd[idx++]=posToAdd;
		for (Position posToAdd : tabPosValide) tabPosToAdd[idx++]=posToAdd;
		
		for (Position posToAdd : tabPosToAdd) {
			this.position.checkPosition(posToAdd, positionAdjacent, sm);
		}
		
		for (Position posValide : tabPosValide) assertTrue(positionAdjacent.contains(posValide));
		for (Position posInvalide : tabPosInvalide) assertFalse(positionAdjacent.contains(posInvalide));
	}
}