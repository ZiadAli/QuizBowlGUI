package application;

import java.io.IOException;
import java.util.ArrayList;

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

import javax.xml.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("unused")
public class CreaterController 
{
	@FXML TextField name;
	@FXML TextField area;
	@FXML ChoiceBox<String> difficulty;
	@FXML Button create;
	MainController mainController = new MainController();
	
	@FXML public void initialize() throws Exception
	{
		name.setPromptText("Tournament/Practice Name");
		area.setPromptText("Tournament/Practice Location");
		
		difficulty.setItems(FXCollections.observableArrayList("Middle School", "High School", "College", "Open"));
		difficulty.getSelectionModel().select("High School");
		
		create.setOnAction(e -> create());
		
	}
	
	public void create()
	{
		boolean nameWritten = true;
		File file = null;
		System.out.println(name.getText());
		if(name.getText() == null)
		{
			nameWritten = false;
			file = new File("no.xml");
		}
		else
		{
			file = new File(name.getText() + ".xml");
		}
		
		if(!file.exists() || !nameWritten)//Do all of this if the file doesn't already exist
		{
			Save save = new Save();
			save.fileName = file.toString();
			
			try 
			{
		        JAXBContext context = JAXBContext.newInstance(TournamentWrapper.class, Question.class, Game.class);
		        Marshaller m = context.createMarshaller();
		        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		        // Wrapping our person data.
		        TournamentWrapper tournament = new TournamentWrapper();
		        mainController.setTournament(new Tournament(name.getText(), area.getText(), difficulty.getValue()));
		        tournament.setTournament(new Tournament(name.getText(), area.getText(), difficulty.getValue()));

		        // Marshalling and saving XML to the file.
		        m.marshal(tournament, file);

		    } 
			catch (Exception e) 
			{ // catches ANY exception
				
		    }
			
			try 
			{
		        JAXBContext context = JAXBContext.newInstance(TournamentWrapper.class, Question.class, Game.class);
		        Unmarshaller um = context.createUnmarshaller();

		        // Reading XML from the file and unmarshalling.
		        TournamentWrapper tournamentWrapped = (TournamentWrapper) um.unmarshal(file);
		        Tournament tournament = tournamentWrapped.getTournament();
		        System.out.println(tournament.getName());
		        System.out.println(tournament.getArea());
		        System.out.println(tournament.getDifficulty());
		    } 
			catch (Exception e) 
			{ // catches ANY exception

		    }
			
			try 
			{
				Stage stage = (Stage) create.getScene().getWindow();
				Scene oldScene = (Scene) create.getScene();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Settings.fxml"));
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
			AlertBox.display("Invalid Tournament Name", "Files with this name already exist or cannot be made.\nPlease choose a different name.");
		}
		
	}
}
