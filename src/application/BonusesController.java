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
public class BonusesController 
{
	@FXML ToggleButton button1;
	@FXML ToggleButton button2;
	@FXML ToggleButton button3;
	@FXML Button nextButton;
	@FXML TextField customText;
	ArrayList<ToggleButton> pointButtons = new ArrayList<ToggleButton>();
	MainController mainController = new MainController();
	
	@FXML public void initialize()
	{
		nextButton.setOnAction(e -> nextClicked());
		
		//Create Array List of bonus 10 point buttons to iterate through once "next" is clicked to see if they were selected
		pointButtons.add(button1);
		pointButtons.add(button2);
		pointButtons.add(button3);
	}
	
	public void nextClicked()
	{
		//Keeps track of bonus score by running through point buttons to see if they were selected
		int bonusScore = 0;
		for(ToggleButton button : pointButtons)
		{
			if(button.isSelected()) bonusScore += 10;
		}
		
		//If user entered something into custom text and it is an actual int then set bonusScore to that
		if(!customText.getText().equals(""))
		{
			try
			{
				bonusScore = Integer.parseInt(customText.getText());
			}
			catch(NumberFormatException e)
			{
				System.out.println("That is not a number!");
			}
		}
		
		System.out.println("Bonus Data: " + MainController.scoringTeamBonusColumn + "$" + bonusScore);
		mainController.addBonusData(GameController.questionNumber, MainController.scoringTeamBonusColumn + "$" + bonusScore);
		
		if(Integer.parseInt(MainController.scoringTeamBonusColumn) < Integer.parseInt(MainController.nonScoringTeamBonusColumn))
		{
			System.out.println("Current team 1 Question Score: " + mainController.team1QuestionScore);
			MainController.team1Score += bonusScore;
			mainController.team1QuestionScore += bonusScore;
			if(mainController.team1QuestionScore != 0) mainController.addScoreData(GameController.questionNumber, (Integer.parseInt(MainController.scoringTeamBonusColumn) + 1) + "$" + mainController.team1Score);
			if(mainController.team2QuestionScore != 0) mainController.addScoreData(GameController.questionNumber, (Integer.parseInt(MainController.nonScoringTeamBonusColumn) + 1) + "$" + mainController.team2Score);
		}
		else
		{
			System.out.println("Current team 2 Question Score: " + mainController.team2QuestionScore);
			MainController.team2Score += bonusScore;
			mainController.team2QuestionScore += bonusScore;
			if(mainController.team2QuestionScore != 0) mainController.addScoreData(GameController.questionNumber, (Integer.parseInt(MainController.scoringTeamBonusColumn) + 1) + "$" + mainController.team2Score);
			if(mainController.team1QuestionScore != 0) mainController.addScoreData(GameController.questionNumber, (Integer.parseInt(MainController.nonScoringTeamBonusColumn) + 1) + "$" + mainController.team1Score);
		}
		
		System.out.println("Current score is " + mainController.team1Score + " to " + mainController.team2Score);
		
		
		//If the user enabled bonus categories, go to the category selector - otherwise, return back to the game
		if(mainController.game.bonusCatOn == true)
		{
			try 
			{
				Stage stage = (Stage) nextButton.getScene().getWindow();
				Scene oldScene = (Scene) nextButton.getScene();
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
		else
		{
			try 
			{
				Stage stage = (Stage) nextButton.getScene().getWindow();
				Scene oldScene = (Scene) nextButton.getScene();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Game.fxml"));
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
}
