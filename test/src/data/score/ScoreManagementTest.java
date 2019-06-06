package src.data.score;

import static org.junit.Assert.*;

import org.junit.Test;

import data.score.Score;
import data.score.ScoreManagement;

public class ScoreManagementTest
{
	
	@Test
	public void testWriteAIScore()
	{
		Score s1 = new Score("IA Dijkstra", "IA Curiosity", 17, 18, 50);
		Score s2 = new Score("IA Dijkstra", "IA Curiosity", 33, 34, 50);
		Score s3 = new Score("IA Dijkstra", "IA Curiosity", 33, 34, 120);
		
		ScoreManagement sm = new ScoreManagement();
		sm.addScore(s1);
		sm.addScore(s2);
		sm.addScore(s3);
		
		sm.saveScore("data/ia_score.score");
	}
	
	@Test
	public void testLoadAIScore()
	{
		ScoreManagement sm = new ScoreManagement();
		sm.loadScore("data/ia_score.score");
		
		
	}

}
