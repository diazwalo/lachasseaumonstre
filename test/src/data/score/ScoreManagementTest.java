package src.data.score;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ai.algorithm.Curiosity;
import ai.algorithm.Dijkstra;
import data.score.Score;
import data.score.ScoreFile;
import data.score.ScoreManagement;

public class ScoreManagementTest
{
	List<Score> scores;
	
	@Before 
	public void testConstructor()
	{
		this.scores = new ArrayList<Score>();
		
		Score s1 = new Score(Dijkstra.NAME, Curiosity.NAME, 17, 18, 50);
		Score s2 = new Score(Dijkstra.NAME, Curiosity.NAME, 33, 34, 50);
		Score s3 = new Score(Dijkstra.NAME, Curiosity.NAME, 33, 34, 120);
		
		this.scores.add(s1);
		this.scores.add(s2);
		this.scores.add(s3);
	}
	
	@Test
	public void resetTest()
	{
		ScoreManagement sm = new ScoreManagement();		
		assertTrue(sm.resetScore(ScoreFile.AI));
	}
	
	@Test
	public void testWriteAIScore()
	{		
		ScoreManagement sm = new ScoreManagement();
		sm.addAllScore(this.scores);
		
		sm.saveScore(ScoreFile.AI);
		
		File f = new File(ScoreFile.AI.getPath());
		
		assertTrue(f.exists());
		assertFalse(f.isDirectory());
	}
	
	@Test
	public void testLoadAIScore()
	{
		ScoreManagement sm = new ScoreManagement();
		sm.resetScore(ScoreFile.AI);
		sm.addAllScore(this.scores);
		sm.saveScore(ScoreFile.AI);
		
		sm = new ScoreManagement();
		sm.loadScore(ScoreFile.AI);
		
		assertEquals(3, sm.getScore().size());
		
		assertEquals(Dijkstra.NAME, sm.getScore().get(0).getPlayer1());
		assertEquals(Curiosity.NAME, sm.getScore().get(0).getPlayer2());
		assertEquals(17, sm.getScore().get(0).getNbMouvment1());
		assertEquals(18, sm.getScore().get(0).getNbMouvment2());
		assertEquals(50, sm.getScore().get(0).getSize());
	}
	
	@Test
	public void testWriteNewAIScore()
	{
		Score s4 = new Score(Dijkstra.NAME, Curiosity.NAME, 1, 2, 50);
		
		ScoreManagement sm = new ScoreManagement();	
		sm.resetScore(ScoreFile.AI);
		
		sm.addAllScore(this.scores);
		sm.saveScore(ScoreFile.AI);
		
		sm = new ScoreManagement();
		sm.addScore(s4);
		sm.saveScore(ScoreFile.AI);
		
		sm = new ScoreManagement();
		sm.loadScore(ScoreFile.AI);
		
		assertEquals(4, sm.getScore().size());
	}

}
