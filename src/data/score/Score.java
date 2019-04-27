package data.score;

import java.time.LocalDate;

import config.GameMode;

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

    private int scorePlayer1;

    private int getScorePlayer2;

    private LocalDate date;

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
