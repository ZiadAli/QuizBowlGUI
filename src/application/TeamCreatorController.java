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
public class TeamCreatorController 
{
	@FXML TextField nameField;
	@FXML TextField coachField;
	@FXML TextField cityField;
	@FXML TextField divisionField;
	@FXML ChoiceBox<String> stateBox;
	@FXML Button submitButton;
	@FXML Button doneButton;	
	int height = 300;
	int width = 500;
	MainController mainController = new MainController();
	
	@FXML public void initialize()
	{
		String[] stateList = {"", "AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","GU","HI","IA","ID", "IL",
				"IN","KS","KY","LA","MA","MD","ME","MH","MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV",
				"NY", "OH","OK","OR","PA","PR","PW","RI","SC","SD","TN","TX","UT","VA","VI","VT","WA","WI","WV","WY"};
		submitButton.setOnAction(e -> submit());
		doneButton.setOnAction(e -> done());
		stateBox.setItems(FXCollections.observableArrayList(stateList));
	}
	
	private void submit()
	{
		if(nameField.getText().trim().equals("") || nameField.getText().trim().equals(null))
		{
			System.out.println("Name Field Text: " + nameField.getText());
			AlertBox.display("Missing Items", "One or more required fields were not filled in.\n Please fill in all required fields.\n");
		}
		
		else
		{
			Team newTeam = new Team(removeSpace(nameField.getText().trim()), coachField.getText().trim(), cityField.getText().trim(), stateBox.getValue().trim(), divisionField.getText().trim());
			System.out.println("Team name without spaces: " + removeSpace(nameField.getText().trim()));
			MainController.mainTournament.addTeam(newTeam);
			System.out.println(nameField.getText() + coachField.getText() + cityField.getText() + stateBox.getValue());
			nameField.setText("");
			coachField.setText("");
			cityField.setText("");
			stateBox.setValue("");
			divisionField.setText("");
		}
	}
	
	private String removeSpace(String string)
	{
		for(int i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) == ' ')
			{
				string = string.substring(0, i) + "_" + string.substring(i+1, string.length());
			}
		}
		return string;
	}
	
	private void done()
	{
		if(nameField.getText().trim().equals("") || nameField.getText().trim().equals(null))
		{
			System.out.println("Name Field Text: " + nameField.getText());
		}
		
		else
		{
			Team newTeam = new Team((nameField.getText().trim()), coachField.getText().trim(), cityField.getText().trim(), stateBox.getValue().trim(), divisionField.getText().trim());
			System.out.println("Team name without spaces: " + removeSpace(nameField.getText().trim()));
			newTeam.setCode(newTeam.getTeamName() + "$B$5");
			MainController.mainTournament.addTeam(newTeam);
			System.out.println(nameField.getText() + coachField.getText() + cityField.getText() + stateBox.getValue());
			nameField.setText("");
			coachField.setText("");
			cityField.setText("");
			stateBox.setValue("");
			divisionField.setText("");
		}
		
		try 
		{
			File file = new File("tournament.xml");
	        JAXBContext context = JAXBContext.newInstance(TournamentWrapper.class, Question.class, Game.class);
	        Marshaller m = context.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrapping our person data.
	        TournamentWrapper tournament = new TournamentWrapper();
	        tournament.setTournament(MainController.mainTournament);

	        // Marshalling and saving XML to the file.
	        m.marshal(tournament, file);

	    } 
		catch (Exception e) 
		{ // catches ANY exception
			
	    }
		
		try 
		{
			Stage stage = (Stage) submitButton.getScene().getWindow();
			Scene oldScene = (Scene) submitButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/TournamentHome.fxml"));
			Scene scene = new Scene(root, 800, 600); //Keeps new scene at the same size
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}
