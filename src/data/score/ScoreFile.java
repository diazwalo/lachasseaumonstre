package data.score;

public enum ScoreFile
{
	AI("ai_score.score"),
	HUNTER("hunter_score.score"),
	BEAST("beast_score.score"),
	MULTI("multi_score.score");
	
	private String fileName;
	
	private ScoreFile(String fileName)
	{
		this.fileName = fileName;
	}
	
	private String getFileName()
	{
		return this.fileName;
	}
}
