package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class TeamHomeController 
{
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane flowPane;
	@FXML private Button addPlayerButton;
	@FXML private Button saveButton;
	@FXML private Button returnButton;
	public static Team currentTeam;
	Save save = new Save();
	Settings settings = new Settings();
	ExcelWriter eWriter = new ExcelWriter();
	
	@FXML public void initialize()
	{		
		flowPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(2));
		flowPane.prefHeightProperty().bind(scrollPane.heightProperty().subtract(2));
	
		Settings settings = new Settings();
		for(Player player : currentTeam.players)
		{
			Button button = new Button(player.getPlayerName());
			button.setOnAction(e -> playerClicked(player));
			button.setPrefWidth(100);
			button.setPrefHeight(100);
			flowPane.getChildren().add(button);
			FlowPane.setMargin(button, new Insets(10,10,10,10));
		}
		
		addPlayerButton.setOnAction(e -> addPlayer());
		returnButton.setOnAction(e -> returnPressed());
		saveButton.setOnAction(e -> savePressed());
	}
	
	public void addPlayer()
	{
		try 
		{
			Stage newStage = (Stage) addPlayerButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/PlayerCreator.fxml"));
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
	
	public void returnPressed()
	{
		savePressed();
		try 
		{
			Stage newStage = (Stage) addPlayerButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TournamentHome.fxml"));
			Scene scene = new Scene(root,800,600);
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
	
	public void savePressed()
	{
		int playersWithData = 0;
		int statSize = 0;
		for(Player player : currentTeam.players)
		{
			if(!player.getData().equals(""))
			{
				playersWithData++;
			}
		}
		for(Statistic stat : MainController.mainTournament.getStatistics())
		{
			System.out.println("" + stat.getStatisticName() + " has fractions: " + stat.fractionOn + " and has bonus: " + stat.bonusStatistic);
			if(!stat.bonusStatistic) //If statistic is for players only, add it to statsize
			{
				statSize++;
			}
		}
		System.out.println("Player stat size: " + statSize);
		System.out.println("Total stat size: " + MainController.mainTournament.getStatistics().size());
		
		for(Player player : currentTeam.players)
		{
			int dataStart = 4 + MainController.mainTournament.getStatistics().size() + (statSize+3)*playersWithData; //Sets starting point for next player address
			if(player.getData().equals(""))
			{
				player.setData("P" + currentTeam.getTeamName() + eWriter.convertReference(4, dataStart));
				playersWithData++;
			}
		}
		save.save();
	}
	
	public void playerClicked(Player player)
	{
		System.out.println(player.getData());
	}
}
