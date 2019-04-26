package src.data.score;

import java.time.LocalDate;

public class Score
{

    /*
     * 1 : joueur vs IA chasseur
     * 2 : joueur vs IA monstre
     * 3 : joueur vs joueur
     */
    private int type;

    private String player1;

    private String player2;

    private int scorePlayer1;

    private int getScorePlayer2;

    private LocalDate date;

    public int getType()
    {
        return this.type;
    }

    public void setType(int type)
    {
        this.type = type;
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

    public int getScorePlayer1()
    {
        return this.scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1)
    {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getGetScorePlayer2()
    {
        return this.getScorePlayer2;
    }

    public void setGetScorePlayer2(int getScorePlayer2)
    {
        this.getScorePlayer2 = getScorePlayer2;
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
