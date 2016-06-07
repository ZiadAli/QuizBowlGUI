package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class TournamentHomeController 
{
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane flowPane;
	@FXML private Button addTeamButton;
	@FXML private Button newGameButton;
	@FXML private Button writeButton;
	@FXML private Button resetButton;
	Save save = new Save();
	
	@FXML public void initialize()
	{		
		System.out.println("Number of statistics: " + MainController.mainTournament.getStatistics().size());
		flowPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(2));
		flowPane.prefHeightProperty().bind(scrollPane.heightProperty().subtract(2));
	
		Settings settings = new Settings();
		for(Team team : MainController.mainTournament.getTeams())
		{
			Button button = new Button(team.getTeamName());
			button.setMnemonicParsing(false); //Mnemonic parsing eliminates first underscore in string
			System.out.println("Button name: " + team.getTeamName());
			button.setPrefWidth(100);
			button.setPrefHeight(100);
			flowPane.getChildren().add(button);
			FlowPane.setMargin(button, new Insets(10,10,10,10));
			button.setOnAction(e -> loadTeam(team));
		}
		
		addTeamButton.setOnAction(e -> addTeam());
		newGameButton.setOnAction(e -> newGame());
		writeButton.setOnAction(e -> writeToExcel());
		resetButton.setOnAction(e -> reset());
	}
	
	public void addTeam()
	{
		try 
		{
			Stage newStage = (Stage) addTeamButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TeamCreator.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		save.save();
	}
	
	public void loadTeam(Team newTeam)
	{
		TeamHomeController.currentTeam = newTeam;
		
		try 
		{
			Stage stage = (Stage) addTeamButton.getScene().getWindow();
			Scene oldScene = (Scene) addTeamButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TeamHome.fxml"));
			Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void newGame()
	{
		try 
		{
			Stage newStage = (Stage) newGameButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/GameCreator.fxml"));
			Scene scene = new Scene(root,500,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			newStage.setScene(scene);
			newStage.setResizable(false);
			newStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createWorkbook()
	{
		ExcelWriter eWriter = new ExcelWriter();
		String eFile = MainController.mainTournament.getName();
		eWriter.createWorkbook(eFile);
		System.out.println(MainController.mainTournament.getName());
	}
	
	public void writeToExcel()
	{
		ExcelWriter eWriter = new ExcelWriter();
		String eFile = MainController.mainTournament.getName() + ".xlsx";
		File testExistFile = new File(eFile);
		if(!testExistFile.exists())
		{
			eWriter.createWorkbook(eFile);
			System.out.println("Made file");
		}
		eWriter.getWorkbook(eFile);
		eWriter.resetCategoriesStats(MainController.mainTournament.getCategories(), MainController.mainTournament.getStatistics());
		eWriter.masterPlayerPage("Master", 2, 3);
		
		int statsSize = 0;
		for(Statistic stat : MainController.mainTournament.getStatistics())
		{
			if(!stat.bonusStatistic)
			{
				statsSize++;
			}
		}
		eWriter.masterPlayerCategories("Master", 2, 5+statsSize);
		for(Team team : MainController.mainTournament.getTeams())
		{
			if(team.dataWritten == false)
			{
				eWriter.createSheet(team.getTeamName());
				eWriter.teamPage(team.getTeamName(), team, 5, 3, false);
				System.out.println("Wrote: " + team.getTeamName() + "'s data");
				team.dataWritten = true;
			}
			for(Player player : team.players)
			{
				if(player.dataWritten == false)
				{
					eWriter.playerPage(team.getTeamName(), player.getPlayerName(), eWriter.getRowFromReference(player.getData())+1, eWriter.getColumnFromReference(player.getData())+2, false);
					System.out.println("Wrote: " + player.getPlayerName() + "'s data");
					player.dataWritten = true;
				}
			}
			
		}
		for(Game game : MainController.mainTournament.getGames())
		{
			if(!game.writtenExcel)
			{
				eWriter.recordGame("Games", game);
				eWriter.readScore("Games", false);
				game.writtenExcel = true;
			}
		}
		eWriter.sortMasterPlayers("Master");
		eWriter.sortMasterPlayerCategory("Master");
		eWriter.closeWorkbook(eFile, true);
		
		save.save();
	}
	
	public void reset()
	{
		for(Team team : MainController.mainTournament.getTeams())
		{
			team.dataWritten = false;
			for(Player player : team.players)
			{
				player.dataWritten = false;
			}
		}
		
		for(Game game : MainController.mainTournament.getGames())
		{
			game.writtenExcel = false;
		}
		save.save();
	}
}
