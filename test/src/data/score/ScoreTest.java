package src.data.score;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import data.score.Score;

public class ScoreTest {

	@Test
	public void test() {
		Score s = new Score("Pierre", "Virgil", 8, 9, 50);
		
		assertEquals("Pierre", s.getPlayer1());
		assertEquals("Virgil", s.getPlayer2());
		assertEquals(8, s.getNbMouvment1());
		assertEquals(9, s.getNbMouvment2());
		assertEquals(50, s.getSize());
		assertEquals(LocalDate.now(), s.getDate());
		assertEquals(16, s.getScore1());
		assertEquals(18, s.getScore2());
	}

}
