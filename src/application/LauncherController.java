package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
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
public class LauncherController 
{
	@FXML Button newButton;
	@FXML ListView<String> loadList;
	@FXML Button selectButton;
	static boolean tournamentMode = false;
	
	@FXML public void initialize()
	{
		if(tournamentMode == false)
		{
			newButton.setText("New Practice");
		}
		else
		{
			newButton.setText("New Tournament");
		}
		File folder = new File("//Users//Ziad//Documents//workspace//QuizBowlGUI");
		ArrayList<String> filesForList = new ArrayList<String>();
		String[] files = folder.list();
		for(String file : files)
		{
			String fileType = "";
			if(file.length() > 3)
			{
				fileType = file.substring(file.length()-4, file.length());
			}
			if(fileType.equals(".xml"))
			{
				filesForList.add(file.substring(0, file.length()-4));
			}
		}
		loadList.setItems(FXCollections.observableArrayList(filesForList));
		newButton.setOnAction(e -> newClicked());
		selectButton.setOnAction(e -> selectClicked());
	}
	
	public void newClicked()
	{
		try 
		{
			Stage stage = (Stage) newButton.getScene().getWindow();
			Scene oldScene = (Scene) newButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Creater.fxml"));
			Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void selectClicked()
	{
		boolean itemSelected = false;
		for(int i=0; i<loadList.getItems().size(); i++)
		{
			if(loadList.getSelectionModel().isSelected(i))
			{
				itemSelected = true;
			}
		}
		
		if(itemSelected)
		{
			String selectedFile = loadList.getSelectionModel().getSelectedItem();
			System.out.println("Selected " + selectedFile);
			try 
			{
				File file = new File(selectedFile + ".xml");
				Save save = new Save();
				save.fileName = file.toString();
		        JAXBContext context = JAXBContext.newInstance(TournamentWrapper.class, Question.class, Game.class);
		        Unmarshaller um = context.createUnmarshaller();

		        // Reading XML from the file and unmarshalling.
		        TournamentWrapper tournamentWrapped = (TournamentWrapper) um.unmarshal(file);
		        Tournament tournament = tournamentWrapped.getTournament();
		        System.out.println(tournament.getName());
		        System.out.println(tournament.getArea());
		        System.out.println(tournament.getDifficulty());
		        MainController.mainTournament = tournament;
		        
		    } 
			catch (Exception e) 
			{ // catches ANY exception

		    }
			
			try 
			{
				Stage stage = (Stage) selectButton.getScene().getWindow();
				Scene oldScene = (Scene) selectButton.getScene();
				Parent root = FXMLLoader.load(getClass().getResource("/application/TournamentHome.fxml"));
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
