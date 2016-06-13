package application;

import java.util.*;

public class Game 
{
	public ArrayList<String> columnHeadings = new ArrayList<String>();
	public ArrayList<Question> questions = new ArrayList<Question>();
	public ArrayList<Integer> playerIndices = new ArrayList<Integer>();
	public HashMap<String, Integer> playerMap = new HashMap<String, Integer>();
	public ArrayList<Integer> bonusColumnIndices = new ArrayList<Integer>();
	public ArrayList<String> playerData = new ArrayList<String>();
	public ArrayList<String> bonusData = new ArrayList<String>();
	public boolean tossupCatOn = true;
	public boolean bonusCatOn = true;
	public int team1Players = 0;
	public int team2Players = 0;
	public String team1Name = "";
	public String team2Name = "";
	public int team1Score = 0;
	public int team2Score = 0;
	public Team realTeam1;
	public Team realTeam2;
	public boolean bouncebacksOn = false;
	public boolean writtenExcel = false;
	
	public Game()
	{
		
	}
	
	public Game(Team team1, Team team2, String tournamentName, String round, boolean tCatOn, boolean bCatOn, boolean bbacksOn)
	{
		realTeam1 = team1;
		realTeam2 = team2;
		tossupCatOn = tCatOn;
		bonusCatOn = bCatOn;
		team1Players = team1.players.size();
		team2Players = team2.players.size();
		team1Name = team1.getTeamName();
		team2Name = team2.getTeamName();
		//team1Score = team1Points;
		//team2Score = team2Points;
		bouncebacksOn = bbacksOn;
		String team1Code = "";
		String team2Code = "";
		
		columnHeadings.add("#");
		if(tossupCatOn)
		{
			columnHeadings.add("Tossup");
		}
		for(Player player : team1.players)
		{
			columnHeadings.add(player.getPlayerName());
			if(team1.getCode() != "")
			{
				team1Code = "!" + team1.getCode();
			}
			playerData.add(player.getData() + team1Code + "@" + team2Name);
			int playerIndex = columnHeadings.indexOf(player.getPlayerName());
			playerIndices.add(playerIndex);
			playerMap.put(player.getData(), playerIndex);
		}
		if(bbacksOn)
		{
			columnHeadings.add("BB Points");
			columnHeadings.add("BB Heard");
		}
		columnHeadings.add("B Points");
		if(team1.getCode() != "")
		{
			bonusData.add("B" + team1.getCode());
			int bonusIndex = columnHeadings.indexOf("B Points");
			bonusColumnIndices.add(bonusIndex);
			playerMap.put(team1.getCode(), bonusIndex);
		}
		columnHeadings.add("Score");
		if(bonusCatOn)
		{
			columnHeadings.add("Bonus");
			System.out.println("ADDED BONUS CATEGORIES!!!!!!");
		}
		for(Player player : team2.players)
		{
			columnHeadings.add(player.getPlayerName());
			if(team2.getCode() != "")
			{
				team2Code = "!" + team2.getCode();
			}
			playerData.add(player.getData() + team2Code + "@" + team1Name);
			int playerIndex = columnHeadings.indexOf(player.getPlayerName());
			playerIndices.add(playerIndex);
			playerMap.put(player.getData(), playerIndex);
		}
		if(bbacksOn)
		{
			columnHeadings.add("BB Points");
			columnHeadings.add("BB Heard");
		}
		columnHeadings.add("B Points");
		if(team2.getCode() != "")
		{
			bonusData.add("B" + team2.getCode());
			int bonusIndex = columnHeadings.lastIndexOf("B Points");
			bonusColumnIndices.add(bonusIndex);
			playerMap.put(team2.getCode(), bonusIndex);
		}
		columnHeadings.add("Score");
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}
	
	public void addQuestions(ArrayList<Question> questions2)
	{
		for(Question question : questions2)
		{
			questions.add(question);
		}
	}
	
	public boolean isTCatEnabled()
	{
		return tossupCatOn;
	}
	
	public boolean isBCatEnabled()
	{
		return bonusCatOn;
	}
}