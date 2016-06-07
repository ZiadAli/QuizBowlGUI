package application;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayerCreatorController 
{
	@FXML TextField nameField;
	@FXML Button submitButton;
	@FXML Button doneButton;
	@FXML TextField ageField;
	@FXML ChoiceBox<String> yearBox;
	@FXML ChoiceBox<String> sexBox;
	
	@FXML public void initialize()
	{
		String[] sexList = {"", "M", "F", "Oth"};
		String[] yearList = {"", "6", "7", "8", "9", "10", "11", "12", "Fr.", "So.", "Ju.", "Se.", "Gr.", "Oth"};
		
		submitButton.setOnAction(e -> submit());
		doneButton.setOnAction(e -> done());
		yearBox.setItems(FXCollections.observableArrayList(yearList));
		sexBox.setItems(FXCollections.observableArrayList(sexList));
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
			Player newPlayer = new Player(nameField.getText().trim(), yearBox.getValue().trim());
			TeamHomeController.currentTeam.addPlayer(newPlayer);
			nameField.setText("");
			ageField.setText("");
			yearBox.setValue("");
			sexBox.setValue("");
		}
	}
	
	private void done()
	{
		if(nameField.getText().trim().equals("") || nameField.getText().trim().equals(null))
		{
			System.out.println("Name Field Text: " + nameField.getText());
		}
		
		else
		{
			Player newPlayer = new Player(nameField.getText().trim(), yearBox.getValue().trim());
			TeamHomeController.currentTeam.addPlayer(newPlayer);
			nameField.setText("");
			ageField.setText("");
			yearBox.setValue("");
			sexBox.setValue("");
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
			Parent root = FXMLLoader.load(getClass().getResource("/application/TeamHome.fxml"));
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
