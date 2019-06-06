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
    //private GameMode gameMode;

    private String player1;

    private String player2;

    private int nbMouvment1;

    private int nbMouvment2;
    
    private int size;

    private LocalDate date;
    
    public Score(String p1, String p2, int nbMouvment1, int nbMouvment2, int size, LocalDate date) {
    	this.player1 = p1;
    	this.player2 = p2;
    	this.nbMouvment1 = nbMouvment1;
    	this.nbMouvment2 = nbMouvment2;//(nbMouvment2*100)/size
    	this.size = size; 	
    	this.date = date;   	
    }
    
    public Score(String p1, String p2, int nbMouvment1, int nbMouvment2, int size) {
    	this(p1, p2, nbMouvment1, nbMouvment2, size, LocalDate.now());
    	
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

    public int getNbMouvment1()
	{
		return this.nbMouvment1;
	}

	public void setNbMouvment1(int nbMouvment1)
	{
		this.nbMouvment1 = nbMouvment1;
	}

	public int getNbMouvment2()
	{
		return this.nbMouvment2;
	}

	public void setNbMouvment2(int nbMouvment2)
	{
		this.nbMouvment2 = nbMouvment2;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
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
