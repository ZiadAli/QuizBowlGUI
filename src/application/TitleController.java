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

@SuppressWarnings("unused")
public class TitleController 
{
	@FXML Button tournamentButton;
	@FXML Button practiceButton;
	
	@FXML public void initialize()
	{
		tournamentButton.setOnAction(e -> tournamentClicked());
		practiceButton.setOnAction(e -> practiceClicked());
	}
	
	public void tournamentClicked()
	{
		LauncherController.tournamentMode = true;
		
		try 
		{
			Stage stage = (Stage) tournamentButton.getScene().getWindow();
			Scene oldScene = (Scene) tournamentButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Launcher.fxml"));
			Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
			stage.setScene(scene);
			stage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void practiceClicked()
	{
		LauncherController.tournamentMode = false;
		
		try 
		{
			Stage stage = (Stage) practiceButton.getScene().getWindow();
			Scene oldScene = (Scene) practiceButton.getScene();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Launcher.fxml"));
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
