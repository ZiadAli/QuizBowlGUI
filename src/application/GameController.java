package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class GameController 
{	
	@FXML private ToggleButton power;
	@FXML private ToggleButton ten;
	@FXML private ToggleButton neg;
	@FXML private Button continueButton;
	@FXML private Button doneButton;
	@FXML private Label scoreA;
	@FXML private Label scoreB;
	
	@FXML private HBox buttonBox;
	@FXML private HBox buttonBoxB;
	@FXML private HBox scoreBox;
	@FXML private HBox continueBox;
	@FXML private Button testButton;
	ArrayList<ToggleButton> buttonList = new ArrayList<ToggleButton>();
	ArrayList<ToggleButton> scoreList = new ArrayList<ToggleButton>();
	MainController mainController = new MainController();
	
	private Team ecgA = new Team("Early College at Guilford A", "ECGA$BD$5");
	private Team ncState = new Team("NC State", "Test5$BD$5");
	public Team gameTeam1 = new Team();
	public Team gameTeam2 = new Team();
	
	public static Team team1, team2;
	public static Game game;
	private ArrayList<ToggleButton> team1Buttons = new ArrayList<ToggleButton>();
	private ArrayList<ToggleButton> team2Buttons = new ArrayList<ToggleButton>();
	
	private String scorePlayer = "";
	
	public static int questionNumber = 0;
	
	Save save = new Save();
	ExcelWriter eWriter = new ExcelWriter();
	
	private boolean neg5Clicked = false;
	Question question = new Question();
		
	@FXML public void initialize()
	{
		questionNumber += 1;
		System.out.println("Application loaded");
		
		//Centers components in HBox's 
		buttonBox.setAlignment(Pos.CENTER);
		buttonBoxB.setAlignment(Pos.CENTER);
		scoreBox.setAlignment(Pos.CENTER);
		continueBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBoxB.setSpacing(20);
		buttonBox.setSpacing(20);
		scoreBox.setSpacing(20);
		continueButton.setId("Drop");
		continueButton.setId("buttontest");
		
		if(team1 != null && team2 != null)
		{
			for(int i=0; i<team1.players.size(); i++)
			{
				ToggleButton playerButton = new ToggleButton(team1.players.get(i).name);
				playerButton.setId(team1.players.get(i).getData());
				playerButton.setUserData(buttonBox);
				
				Player newPlayer = new Player();
				newPlayer.setPlayerName(team1.players.get(i).name);
				newPlayer.setData(team1.players.get(i).getData());
				gameTeam1.addPlayer(newPlayer);
				
				buttonBox.getChildren().add(playerButton);
				buttonList.add(playerButton);
				team1Buttons.add(playerButton);
			}
			
			for(int i=0; i<team2.players.size(); i++)
			{
				ToggleButton playerButton = new ToggleButton(team2.players.get(i).name);
				playerButton.setId(team2.players.get(i).getData());
				playerButton.setUserData(buttonBoxB);
				
				Player newPlayer = new Player();
				newPlayer.setPlayerName(team2.players.get(i).name);
				newPlayer.setData(team2.players.get(i).getData());
				gameTeam2.addPlayer(newPlayer);
				
				buttonBoxB.getChildren().add(playerButton);
				buttonList.add(playerButton);
				team2Buttons.add(playerButton);
			}
		}
		
		
		//Makes ArrayList of all players	
		/*player1.setId("PTest5$D$5");
		player2.setId("PTest5$Q$5");
		player3.setId("PTest5$AD$5");
		player4.setId("PTest5$AQ$5");
		player1b.setId("PECGA$D$5");
		player2b.setId("PECGA$Q$5");
		player3b.setId("PECGA$AD$5");
		player4b.setId("PECGA$AQ$5");*/
				
		/*buttonList.add(player1);
		buttonList.add(player2);
		buttonList.add(player3);
		buttonList.add(player4);
		buttonList.add(player1b);
		buttonList.add(player2b);
		buttonList.add(player3b);
		buttonList.add(player4b);*/
		
		for(ToggleButton button : buttonList)
		{
			button.setOnAction(e -> playerClicked(button));
		}
		
		//Makes ArrayList of score buttons
		power.setId("15");
		ten.setId("10");
		neg.setId("-5");
		
		scoreList.add(power);
		scoreList.add(ten);
		scoreList.add(neg);
		for(ToggleButton button: scoreList) //Assigns scoreClicked method to all score toggle buttons
		{
			button.setOnAction(e -> scoreClicked(button));
			button.setDisable(true); //Initially disables all score buttons
		}
		
		continueButton.setOnAction(e -> nextScene());
		doneButton.setOnAction(e -> done());
		
		//Initializes test game, move later to different class
		/*ncState.addPlayer(new Player("Ziad", "13", "PTest5$D$5"));
		ncState.addPlayer(new Player("Manu", "14", "PTest5$Q$5"));
		ncState.addPlayer(new Player("Jared", "13", "PTest5$AD$5"));
		ncState.addPlayer(new Player("Malay", "13", "PTest5$AQ$5"));
		ecgA.addPlayer(new Player("Pratham", "12", "PECGA$D$5"));
		ecgA.addPlayer(new Player("Jack", "12", "PECGA$Q$5"));
		ecgA.addPlayer(new Player("Grey", "12", "PECGA$AD$5"));
		ecgA.addPlayer(new Player("Tejas", "10", "PECGA$AQ$5"));*/
		mainController.createGame(gameTeam1, gameTeam2);
	}
	
	public void playerClicked(ToggleButton button)
	{
		System.out.println("Player ID: " + button.getId() + " Row: " + eWriter.getRowFromReference(button.getId()) + " Column: " + eWriter.getColumnFromReference(button.getId()));
		unclick(buttonList, button); //Deselects all other players
		scorePlayer = button.getText(); //Gets player's name
		for(ToggleButton button2 : scoreList) //Enables all score buttons
		{
			button2.setDisable(false);
			if(button2.isSelected()) //If user clicks player after clicking another player, resets all score buttons to not selected
			{
				button2.setSelected(false);
			}
		}
		
		boolean oneSelected = false;
		for(ToggleButton player : buttonList) //If user de-toggles selected player, disables all score buttons
		{
			if(player.isSelected())
			{
				oneSelected = true;
			}
		}
		if(!oneSelected)
		{
			for(ToggleButton button2 : scoreList)
			{
				button2.setDisable(true);
			}
		}
		continueButton.setText("Drop");
	}
	
	public void scoreClicked(ToggleButton button) //Deselects all other score buttons and prints score
	{
		System.out.println(scorePlayer + " scored a " + button.getText());
		
		boolean buttonSelected = false;
		boolean neg5Selected = false;

		if(button.getId().equals("-5")) 
		{
			continueButton.setText("Submit");
			neg5Selected = true;
		}
		
		for(ToggleButton scoreButton : scoreList)
		{
			if(scoreButton.isSelected() == true) buttonSelected = true;
		}
		if(buttonSelected && !neg5Selected) continueButton.setText("Next");
		else if(!buttonSelected) continueButton.setText("Drop");
		
		unclick(scoreList, button);
	}
	
	public void unclick(ArrayList<ToggleButton> list, ToggleButton buttonClicked) //Deselects buttons in given array list
	{
		for(ToggleButton button : list)
		{
			if(button != buttonClicked)
			{
				button.setSelected(false);
			}
		}
	}
	
	public void nextScene()
	{	
		//If -5 was clicked don't create a new question, keep the existing one
		if(!neg5Clicked) question = new Question(); 
		
		ToggleButton selectedButton = new ToggleButton();
		selectedButton.setId("0");
		String score = "";
		String noScore = "";
		for(ToggleButton player : buttonList)
		{
			if(player.isSelected())
			{
				score += MainController.game.playerMap.get(player.getId()); //Adds player column to score data
				player.setSelected(false);
				player.setDisable(true);
				HBox teamButtonBox = (HBox) player.getUserData();
				for(int i=0; i<teamButtonBox.getChildren().size(); i++)
				{
					teamButtonBox.getChildren().get(i).setDisable(true);
				}
				
			}
		}
		for(ToggleButton scoreButton : scoreList)
		{
			if(scoreButton.isSelected())
			{
				score += "$" + scoreButton.getId();
				selectedButton = scoreButton;
				selectedButton.setSelected(false);
			}
		}
		
		if(!selectedButton.getId().equals("-5"))
		{
			for(ToggleButton player : buttonList)
			{
				if(!player.isSelected() && !player.isDisabled()) //Marks down data of all players who didn't score
				{
					noScore = "";
					noScore += MainController.game.playerMap.get(player.getId());
					noScore += "$0";
					question.addData(noScore);
				}
			}
		}
		
		question.setQNumber(questionNumber);
		if(!score.equals("")) question.addData(score);
		
		for(Question questions : MainController.questions)
		{
			System.out.println("Question number: " + questions.qNumber);
			System.out.println("Question category: " + questions.tossupAbbrev);
			for(int i=0; i<questions.questionData.size(); i++)
			{
				System.out.println("Question data: " + questions.questionData.get(i) + " ");
			}
		}
		
		//Checks if both teams negged. If so, overrides usual neg protocols and still moves on to category window
		boolean bothTeamsNegged = true;
		for(int i=0; i<buttonBox.getChildren().size(); i++)
		{
			if(buttonBox.getChildren().get(i).isDisabled() == false) bothTeamsNegged = false;
		}
		for(int i=0; i<buttonBoxB.getChildren().size(); i++)
		{
			if(buttonBoxB.getChildren().get(i).isDisabled() == false) bothTeamsNegged = false;
		}
		
		if(selectedButton.getId().equals("-5") && bothTeamsNegged == false)
		{
			neg5Clicked = true;
		}
		
		else
		{
			neg5Clicked = false;
			mainController.addQuestion(question);
			try 
			{
				Stage stage = (Stage) continueButton.getScene().getWindow();
				Scene oldScene = (Scene) continueButton.getScene();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Categories.fxml"));
				Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void done()
	{
		questionNumber = 0;		
		mainController.addQuestions();	
		mainController.addGame(MainController.game);
		mainController.clearGame();
		mainController.clearQuestions();
		//MainController.mainTournament.setCategories(MainController.enabledCategoryList);
		
		save.save();
		
		try 
		{
			Stage stage = (Stage) continueButton.getScene().getWindow();
			Scene oldScene = (Scene) continueButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TournamentHome.fxml"));
			Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		/*try 
		{
			File file = new File("tournament.xml");
	        JAXBContext context = JAXBContext.newInstance(TournamentWrapper.class, Tournament.class, GameController.class, Question.class, Game.class);
	        Unmarshaller um = context.createUnmarshaller();

	        // Reading XML from the file and unmarshalling.
	        TournamentWrapper tournamentWrapped = (TournamentWrapper) um.unmarshal(file);
	        Tournament tournament = tournamentWrapped.getTournament();
	        writeTournament = tournament;
	    } 
		catch (Exception e) 
		{ // catches ANY exception

	    }
		
		System.out.println("WRITING TOURNAMENT DATA: ");
		
		for(Game game : writeTournament.getGames())
		{
			System.out.println("Found a game!");
			System.out.println(game.team1Name);
		}
		
		for(Question question : writeTournament.getGames().get(0).questions)
		{
			System.out.println("Question number: " + question.qNumber);
			System.out.println("Question category: " + question.tossupAbbrev);
			for(int i=0; i<question.questionData.size(); i++)
			{
				System.out.println("Question data: " + question.questionData.get(i) + " ");
			}
		}*/
		
		//ExcelWriter eWriter = new ExcelWriter();
		//String eFile = "//Users//Ziad//Documents//QuizBowlProgram//GUITest.xlsx";
		//eWriter.getWorkbook(eFile);
		//eWriter.resetCategoriesStats(MainController.enabledCategoryList);
		//eWriter.createSheet("Games");
		//eWriter.recordGame("Games", writeTournament.getGames().get(0), 2, 0);
		//Need to include master pages, use all of this to create player and team pages
		//eWriter.playerPage("ECGA", "Testing", 100, 5, false);
		/*eWriter.masterPlayerPage("Master", 2, 3);
		eWriter.masterPlayerCategories("Master", 2, 15);
		eWriter.teamPage("Test5", ncState, 5, 57, false);
		eWriter.playerPage("Test5", "Ziad", 5, 5, false);
		eWriter.playerPage("Test5", "Manu", 5, 18, false);
		eWriter.playerPage("Test5", "Jared", 5, 31, false);
		eWriter.playerPage("Test5", "Malay", 5, 44, false);
		eWriter.teamPage("ECGA", ecgA, 5, 57, false);
		eWriter.playerPage("ECGA", "Pratham", 5, 5, false);
		eWriter.playerPage("ECGA", "Jack", 5, 18, false);
		eWriter.playerPage("ECGA", "Grey", 5, 31, false);
		eWriter.playerPage("ECGA", "Tejas", 5, 44, false);*/
		//eWriter.readScore("Games", 4, 0, false);
		//eWriter.sortMasterPlayers("Master");
		//eWriter.sortMasterPlayerCategory("Master");
		//eWriter.closeWorkbook(eFile);
	}
}
