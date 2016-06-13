package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class GameCreatorController 
{
	@FXML ChoiceBox<String> team1Box;
	@FXML ChoiceBox<String> team2Box;
	@FXML Button goButton;
	HashMap<String, String> team1PlayersMap = new HashMap<String, String>();
	HashMap<String, String> team2PlayersMap = new HashMap<String, String>();
	ArrayList<String> team1Players = new ArrayList<String>();
	ArrayList<String> team2Players = new ArrayList<String>();
	ArrayList<ComboBox<String>> team1Boxes = new ArrayList<ComboBox<String>>();
	ArrayList<ComboBox<String>> team2Boxes = new ArrayList<ComboBox<String>>();
	@FXML AnchorPane anchor;
	
	@FXML public void initialize()
	{
		team1Box.setOnAction(e -> teamSelected(team1Box.getValue(), true));
		team2Box.setOnAction(e -> teamSelected(team2Box.getValue(), false));
		goButton.setOnAction(e -> createGame());
		
		ArrayList<String> teamNames = new ArrayList<String>();
		for(Team team : MainController.mainTournament.getTeams())
		{
			teamNames.add(team.getTeamName());
		}
		team1Box.setItems(FXCollections.observableArrayList(teamNames));
		team2Box.setItems(FXCollections.observableArrayList(teamNames));
		
		int xCoord1 = 66;
		int yCoord1 = 177;
		int xCoord2 = 280;
		int yCoord2 = 177;
		for(int i=0; i < Settings.playersPerTeam; i++)
		{	
			ComboBox<String> playerBox = new ComboBox<String>();
			anchor.getChildren().add(playerBox);
			playerBox.setLayoutX(xCoord1);
			playerBox.setLayoutY(yCoord1);
			playerBox.setPrefWidth(150);
			yCoord1 += 30;
			team1Boxes.add(playerBox);
			System.out.println("Size of team1Boxes: " + team1Boxes.size());
			String id = "" + i;
			playerBox.setOnAction(e -> personSelected(playerBox, true));
			playerBox.setId("" + i); 
		}
		for(int i=0; i < Settings.playersPerTeam; i++)
		{	
			ComboBox<String> playerBox = new ComboBox<String>();
			anchor.getChildren().add(playerBox);
			playerBox.setLayoutX(xCoord2);
			playerBox.setLayoutY(yCoord2);
			playerBox.setPrefWidth(150);
			yCoord2 += 30;
			team2Boxes.add(playerBox);
			String id = "" + i;
			playerBox.setOnAction(e -> personSelected(playerBox, false));
			playerBox.setId("" + i);
		}
	}
	
	public void teamSelected(String teamName, boolean team1)
	{
		ObservableList<String> array = null;
		//Resets other team's boxes to null if selected team is the same as other team
		if(!team1 && team1Box.getValue() != null) //If team2 is selected
		{
			if(team1Box.getValue().equals(teamName))
			{
				team1Box.setValue(null);
				for(ComboBox<String> box : team1Boxes)
				{
					box.setValue(null);
					box.getItems().clear();
				}
			}
		}
		else if(team1 && team2Box.getValue() != null) //If team1 is selected
		{
			if(team2Box.getValue().equals(teamName))
			{
				team2Box.setValue(null);
				for(ComboBox<String> box : team2Boxes)
				{
					box.setValue(null);
					box.getItems().clear();
				}
			}
		}
		
		for(Team team : MainController.mainTournament.getTeams())
		{
			if(team.name.equals(teamName))
			{
				if (team1)
				{
					team1Players.clear();
					for(Player player : team.players)
					{
						team1PlayersMap.put(player.getPlayerName(), player.getData());
						team1Players.add(player.getPlayerName());
					}
					
					for(ComboBox<String> box : team1Boxes)
					{
						box.setItems(FXCollections.observableArrayList(team1Players));
					}
				}
				else
				{
					team2Players.clear();
					for(Player player : team.players)
					{
						team2PlayersMap.put(player.getPlayerName(), player.getData());
						team2Players.add(player.getPlayerName());
					}
					for(ComboBox<String> box : team2Boxes)
					{
						box.setItems(FXCollections.observableArrayList(team2Players));
					}
				}
			}
		}
		
	}
	
	public void personSelected(ComboBox<String> selectedBox, boolean team1)
	{
		String selectedName;
		if(selectedBox.getValue() != null)
		{
			selectedName = selectedBox.getValue();
			for(int i=0; i<team1Boxes.size(); i++)
			{
				ComboBox<String> box = team1Boxes.get(i);
				if(!team1)
				{
					box = team2Boxes.get(i);
				}
				if(box != selectedBox && box.getValue() != null)
				{
					System.out.println("Box " + box.getId() + " has a value");
					if(box.getValue().equals(selectedName))
					{
						box.setValue(null);
						System.out.println("Box " + box.getId() + " value set");
					}
				}
			}
		}
	}
	
	public void createGame()
	{
		Team team1 = new Team();
		Team team2 = new Team();
		
		//Adds ID for each team
		team1.setCode(team1Box.getValue() + "$B$5");
		team1.setTeamName(team1Box.getValue());
		team2.setCode(team2Box.getValue() + "$B$5");
		team2.setTeamName(team2Box.getValue());
		
		for(ComboBox<String> box : team1Boxes)
		{
			if(box.getValue() != null)
			{
				Player player = new Player(box.getValue(), "12", team1PlayersMap.get(box.getValue()));
				team1.addPlayer(player);
				System.out.println("Player added: " + player.name);
			}
		}
		if(team1.players.size() != 0)
		{
			for(ComboBox<String> box : team2Boxes)
			{
				if(box.getValue() != null)
				{
					Player player = new Player(box.getValue(), "12", team2PlayersMap.get(box.getValue()));
					team2.addPlayer(player);
					System.out.println("Player added: " + player.name);
				}
			}
			
			if(team2.players.size() != 0)
			{
				GameController.team1 = team1;
				GameController.team2 = team2;
				Game game = new Game(team1, team2, "Test Tournament", "Round 1", true, true, false);
				GameController.game = game;
				
				try 
				{
					Stage stage = (Stage) goButton.getScene().getWindow();
					Scene oldScene = (Scene) goButton.getScene();
					Parent root = FXMLLoader.load(getClass().getResource("/application/Game.fxml"));
					Scene scene = new Scene(root, 800, 600); //Keeps new scene at the same size
					stage.setScene(scene);
					stage.show();
				} 
				catch(Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			else
			{
				AlertBox.display("Not enough players", "There must be at least one player per team");
			}
		}
		
		else
		{
			AlertBox.display("Not enough players", "There must be at least one player per team");
		}
	}
}
