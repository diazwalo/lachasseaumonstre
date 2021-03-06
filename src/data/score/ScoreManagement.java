package data.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Cette classe s'occupe de traiter les nouveaux scores. Elle lit le fichier de score, ajoute un score et sauvegarde le fichier.
 * @author PHPierre
 *
 */
public class ScoreManagement
{
	private static final String SEPARATOR = ";"; // Char else we need to cast late when we use bw.write
	
	private List<Score> scoreList;
	
	public ScoreManagement()
	{
		this.scoreList = new ArrayList<Score>();
	}
	
	public List<Score> getScore()
	{
		return this.scoreList;
	}
	
	public void addScore(Score s)
	{
		this.scoreList.add(s);
	}
	
	public void addAllScore(List<Score> scores)
	{
		this.scoreList.addAll(scores);
	}
	
	public void loadScore(ScoreFile path)
	{
		String[] explode;
		String line;
		
		createFile(path);
		
		try(BufferedReader br = new BufferedReader(new FileReader(path.getPath()))) {

			line = br.readLine();
			while(line != null) {
				explode = line.split(SEPARATOR);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				formatter = formatter.withLocale(Locale.FRANCE);
				LocalDate date = LocalDate.parse(explode[5], formatter);
				
				Score s = new Score(
					explode[0], 
					explode[1], 
					Integer.parseInt(explode[2]), 
					Integer.parseInt(explode[3]), 
					Integer.parseInt(explode[4]),
					date
				);
				addScore(s);
				line = br.readLine();
			}
		} catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable"); e.printStackTrace();
		} catch(IOException e) {
			System.out.println("Erreur de lecture: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveScore(ScoreFile scoreFile)
	{
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile.getPath(), true))) {
			
			for(Score score : this.scoreList)
			{
				bw.write(score.getPlayer1() + SEPARATOR);
				bw.write(score.getPlayer2() + SEPARATOR);
				bw.write(score.getNbMouvment1() + SEPARATOR);
				bw.write(score.getNbMouvment2() + SEPARATOR);
				bw.write(score.getSize() + SEPARATOR);
				bw.write(score.getDate().toString());
				bw.newLine();
			}

		} catch(IOException e) {
			System.out.println("Erreur de lecture du fichier : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void createDataFolder()
	{
		new File(ScoreFile.FOLDER.getPath());
	}
	
	public void createFile(ScoreFile path)
	{
		File d = new File("data/");
		d.mkdir();
		
		File f = new File(path.getPath());
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean resetScore(ScoreFile path)
	{
		File f = new File(path.getPath());
		return f.delete();
	}

	public ObservableList<Score> getScoreObservbleList()
	{
		ObservableList<Score> list = FXCollections.observableArrayList();
		
		for (Score score : this.scoreList) {
			list.add(score);
		}
		
		return list;
	}
}
