package application;

import java.util.ArrayList;

public class Tournament 
{
	private String name;
	private String area;
	private String difficulty;
	private ArrayList<Game> games = new ArrayList<Game>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	private ArrayList<Statistic> statistics = new ArrayList<Statistic>();
	private ArrayList<Team> teams = new ArrayList<Team>();
	public boolean dataWritten = false;
	
	public Tournament()
	{
		Settings settings = new Settings();
		for(Category category : settings.categoryList)
		{
			categories.add(category);
		}
	}
	
	public Tournament(String tName, String tarea, String tDifficulty)
	{
		this.setName(tName);
		this.setArea(tarea);
		this.setDifficulty(tDifficulty);
		Settings settings = new Settings();
		for(Category category : settings.categoryList)
		{
			categories.add(category);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public ArrayList<Game> getGames()
	{
		return games;
	}
	
	public void setGames(ArrayList<Game> newGames)
	{
		games = newGames;
	}
	
	public void addGame(Game game)
	{
		games.add(game);
	}
	
	public ArrayList<Category> getCategories()
	{
		return categories;
	}
	
	public void setCategories(ArrayList<Category> newCategories)
	{
		categories = newCategories;
	}
	
	public void addCategory(Category category)
	{
		categories.add(category);
	}
	
	
	public ArrayList<Statistic> getStatistics()
	{
		return statistics;
	}
	
	public void setStatistics(ArrayList<Statistic> newStatistics)
	{
		statistics = newStatistics;
	}
	
	public void addStatistic(Statistic stat)
	{
		statistics.add(stat);
	}
	
	
	public ArrayList<Team> getTeams()
	{
		return teams;
	}
	
	public void setTeams(ArrayList<Team> newTeams)
	{
		teams = newTeams;
	}
	
	public void addTeam(Team team)
	{
		teams.add(team);
	}
}
