package data.score;

import java.time.LocalDate;

import ai.graph.Graph;
import config.GameMode;
import map.AbstractMap;

public class Score
{
    /*
     * 1 : joueur vs IA chasseur
     * 2 : joueur vs IA monstre
     * 3 : joueur vs joueur
     */
    private GameMode gameMode;

    private String player1;

    private String player2;

    private double scorePlayer1;

    private double scorePlayer2;

    private LocalDate date;
    
    public Score(GameMode gamemode, String p1, String p2, int nbMouvment1, double nbMouvment2, AbstractMap map) {
    	this.player1 = p1;
    	this.player2 = p2;
    	this.date = LocalDate.now();
    	
    	Graph graph = new Graph();
    	int size = graph.getListNode().size();
    	
    	this.scorePlayer1 = (nbMouvment1*100)/size;
    	this.scorePlayer2 = (nbMouvment2*100)/size;
    }
    

    public GameMode getGameMode()
    {
        return this.gameMode;
    }

    public void setGameMode(GameMode gameMode)
    {
        this.gameMode = gameMode;
    }

    public String getPlayer1()
    {
        return this.player1;
    }

    public void setPlayer1(String player1)
    {
        this.player1 = player1;
    }

    public String getPlayer2()
    {
        return this.player2;
    }

    public void setPlayer2(String player2)
    {
        this.player2 = player2;
    }

    public double getScorePlayer1()
    {
        return this.scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1)
    {
        this.scorePlayer1 = scorePlayer1;
    }

    public double getScorePlayer2()
    {
        return this.scorePlayer2;
    }

    public void setScorePlayer2(int getScorePlayer2)
    {
        this.scorePlayer2 = getScorePlayer2;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    } 
    
}
