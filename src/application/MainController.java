package application;

import java.util.ArrayList;

import javafx.fxml.FXML;

public class MainController 
{
	public static Tournament mainTournament = new Tournament();
	protected static Game game;
	protected static ArrayList<Category> enabledCategoryList = new ArrayList<Category>();
	protected static ArrayList<Statistic> enabledStatisticList = new ArrayList<Statistic>();
	protected static ArrayList<Question> questions = new ArrayList<Question>();
	Settings settings = new Settings();

	@FXML public void initialize()
	{
		System.out.println("Application Started");
	}
	
	public void defaultCategories()
	{
		for(Category category : settings.categoryList)
		{
			enabledCategoryList.add(category);
		}
	}
	
	public void defaultStats()
	{
		for(Statistic stat : settings.statisticList)
		{
			enabledStatisticList.add(stat);
		}
	}
	
	public void setCategories(ArrayList<Category> categories)
	{
		boolean foundAll = false; //Adds ALL category in case it isn't present
		enabledCategoryList.clear();
		for(Category category : categories)
		{
			if(category.categoryName.equals("ALL"))
			{
				foundAll = true;
			}
		}
		if(!foundAll)
		{
			enabledCategoryList.add(settings.categoryList.get(0));
		}
		
		for(Category category : categories)
		{
			enabledCategoryList.add(category);
		}
		mainTournament.setCategories(enabledCategoryList);
	}
	
	public void setStatistics(ArrayList<Statistic> stats)
	{
		enabledStatisticList.clear();
		for(Statistic stat : stats)
		{
			enabledStatisticList.add(stat);
		}
		mainTournament.setStatistics(enabledStatisticList);
	}
	
	public void createGame(Team team1, Team team2)
	{
		game = new Game(team1, team2, "Test Tournament", "Round 1", true, false, false);
	}
	
	public void clearGame()
	{
		game = new Game();
		game.questions = new ArrayList<Question>();
	}
	
	public void addGame(Game newGame)
	{
		Game addGame = newGame;
		System.out.println("Game has " + addGame.questions.size() + " questions");
		mainTournament.addGame(addGame);
	}
	
	public ArrayList<Category> getCategories()
	{
		return enabledCategoryList;
	}
	
	public void addQuestion(Question question)
	{
		questions.add(question);
	}
	
	public void addQuestions()
	{
		game.addQuestions(questions);
	}
	
	public void clearQuestions()
	{
		questions.clear();
	}
	
	public void setTossupCategory(int questionNumber, String tossupAbbreviation)
	{
		Question question = questions.get(questionNumber-1);
		question.setTossupAbbrev(tossupAbbreviation);
	}
	
	public void setTournament(Tournament tournament)
	{
		mainTournament = tournament;
	}
	
	public Tournament getTournament()
	{
		return mainTournament;
	}
}
