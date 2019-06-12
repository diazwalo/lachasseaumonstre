package data.score;

public enum ScoreFile
{
	FOLDER(""),
	AI("ai_score.score"),
	HUNTER("hunter_score.score"),
	BEAST("beast_score.score"),
	MULTI("multi_score.score");
	
	public static final String PATH = "data/";
	
	private String fileName;
	
	private ScoreFile(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}
	
	public String getPath()
	{
		return ScoreFile.PATH + this.fileName;
	}
}
