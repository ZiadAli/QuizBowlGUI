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
public class SettingsController 
{
	@FXML private FlowPane layout;
	@FXML private Button nextButton;
	@FXML MainController mainController;
	ArrayList<CheckBox> checkboxList = new ArrayList<CheckBox>();
	ArrayList<CheckBox> statBoxList = new ArrayList<CheckBox>();
	ArrayList<Category> checkedCategories = new ArrayList<Category>();
	ArrayList<Statistic> checkedStats = new ArrayList<Statistic>();
	Settings settings = new Settings();

	@FXML public void initialize()
	{
		mainController = new MainController();
		
		layout.setAlignment(Pos.CENTER);
		layout.setHgap(5);
		layout.setVgap(20);
		
		nextButton.setOnAction(e -> nextScene());
		
		for(Category category : settings.categoryList)
		{
			CheckBox checkbox = new CheckBox(category.categoryName);
			if(category.categoryName.equals(category.parentCategory) || category.categoryName.equals("ALL"))
			{
				checkbox.setOnAction(e -> unclickCheckbox(checkbox));	
			}
			else
			{
				checkbox.setOnAction(e -> regularCheckbox(checkbox));
			}
			checkboxList.add(checkbox);
			checkbox.setSelected(true);
			if(category.categoryName.equals("ALL"))
			{
				checkbox.setOnAction(e -> allCheckbox(checkbox));
			}
			layout.getChildren().add(checkbox);
		}
		
		for(Statistic stat : settings.statisticList)
		{
			CheckBox checkbox = new CheckBox(stat.getStatisticName());
			checkbox.setOnAction(e -> statClicked(checkbox));
			checkbox.setSelected(true);
			layout.getChildren().add(checkbox);
			statBoxList.add(checkbox);
		}
	}
	
	public void unclickCheckbox(CheckBox checkbox)
	{
		if(!checkbox.isSelected()) //Disables all checkboxes under parent category if parent is de-selected
		{
			checkboxList.get(0).setSelected(false);
			for(CheckBox checkbox2 : checkboxList)
			{
				int checkboxIndex = checkboxList.indexOf(checkbox); //Index of parent checkbox
				int checkbox2Index = checkboxList.indexOf(checkbox2); //Index of other checkboxes
				String checkbox2Parent = settings.categoryList.get(checkbox2Index).parentCategory; //Parent category of other checkboxes
				String checkbox2Category = settings.categoryList.get(checkbox2Index).categoryName; //Main category of other checkboxes
				String checkboxCategory = settings.categoryList.get(checkboxIndex).categoryName; //Main category of parent checkbbox
				if(checkbox2Parent.equals(checkboxCategory))
				{
					checkbox2.setDisable(true);
					checkbox2.setSelected(false);
					checkbox.setDisable(false);
				}
			}
		}
		
		if(checkbox.isSelected()) //Enables all checkboxes under parent category
		{
			for(CheckBox checkbox2 : checkboxList)
			{
				int checkboxIndex = checkboxList.indexOf(checkbox); //Index of parent checkbox
				int checkbox2Index = checkboxList.indexOf(checkbox2); //Index of other checkboxes
				String checkbox2Parent = settings.categoryList.get(checkbox2Index).parentCategory; //Parent category of other checkboxes
				String checkbox2Category = settings.categoryList.get(checkbox2Index).categoryName; //Main category of other checkboxes
				String checkboxCategory = settings.categoryList.get(checkboxIndex).categoryName; //Main category of parent checkbbox
				if(checkbox2Parent.equals(checkboxCategory))
				{
					checkbox2.setDisable(false);
				}
			}
		}
	}
	
	public void regularCheckbox(CheckBox checkbox)
	{
		System.out.println("Checkbox clicked");
		if(!checkbox.isSelected())
		{
			checkboxList.get(0).setSelected(false);
		}
		if(checkbox.isSelected())
		{
			boolean allSelected = true;
			for(int i = 1; i < checkboxList.size(); i++)
			{
				if(!checkboxList.get(i).isSelected())
				{
					allSelected = false;
				}
			}
			if(allSelected)
			{
				System.out.println("All selected!");
				checkboxList.get(0).setSelected(true);
			}
		}
	}
	
	public void allCheckbox(CheckBox checkbox)
	{
		if(checkbox.isSelected())
		{
			for(CheckBox checkbox2 : checkboxList)
			{
				checkbox2.setDisable(false);
				checkbox2.setSelected(true);
			}
		}
		
		else
		{
			for(CheckBox checkbox2 : checkboxList)
			{
				checkbox2.setSelected(false);
				int checkbox2Index = checkboxList.indexOf(checkbox2);
				String checkbox2Category = settings.categoryList.get(checkbox2Index).categoryName;
				String checkbox2Parent = settings.categoryList.get(checkbox2Index).parentCategory;
				if(!checkbox2Category.equals(checkbox2Parent))
				{
					checkbox2.setDisable(true);
				}
			}
		}
	}
	
	public void statClicked(CheckBox checkbox)
	{
		
	}
	
	public void nextScene()
	{
		try 
		{
			for(CheckBox checkbox : checkboxList)
			{
				if(checkbox.isSelected())
				{
					int checkboxIndex = checkboxList.indexOf(checkbox);
					checkedCategories.add(settings.categoryList.get(checkboxIndex)); //Adds selected category to checkedCaegories list
					System.out.println(checkbox.getText());
				}
			}
			
			for(CheckBox checkbox : statBoxList)
			{
				if(checkbox.isSelected())
				{
					int checkboxIndex = statBoxList.indexOf(checkbox);
					checkedStats.add(settings.statisticList.get(checkboxIndex));
					System.out.println(checkbox.getText());
				}
			}
			mainController.setCategories(checkedCategories);
			mainController.setStatistics(checkedStats);
			//MainController.mainTournament.setCategories(checkedCategories);
			Save save = new Save();
			save.save();
			Stage stage = (Stage) nextButton.getScene().getWindow();
			Scene oldScene = (Scene) nextButton.getScene();
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
