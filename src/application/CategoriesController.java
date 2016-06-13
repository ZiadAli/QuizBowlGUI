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
public class CategoriesController 
{
	@FXML private FlowPane categoryPane1;
	@FXML private FlowPane categoryPane2;
	@FXML private Button nextButton;
	MainController mainController = new MainController();
	ArrayList<ToggleButton> mainCategoryButtons = new ArrayList<ToggleButton>();
	ArrayList<ToggleButton> subcategoryButtons = new ArrayList<ToggleButton>();
	
	@FXML public void initialize()
	{
		categoryPane1.setAlignment(Pos.CENTER);
		categoryPane1.setHgap(20);
		categoryPane1.setVgap(20);
		
		categoryPane2.setAlignment(Pos.CENTER);
		categoryPane2.setHgap(20);
		categoryPane2.setVgap(20);
		
		nextButton.setDisable(true);
			
		ArrayList<String> mainCategories = new ArrayList<String>();
		for(Category category : MainController.mainTournament.getCategories()) //Adds main categories to top pane
		{
			if(!category.categoryName.equals("ALL") && !mainCategories.contains(category.parentCategory))
			{
				ToggleButton toggleButton = new ToggleButton(category.categoryName);
				toggleButton.setId(category.categoryAbbreviation);
				categoryPane1.getChildren().add(toggleButton);
				mainCategoryButtons.add(toggleButton);
				toggleButton.setOnAction(e -> mainCategoryClicked(toggleButton));
				mainCategories.add(category.parentCategory);
			}
		}
	}
	
	public void showSubCategories(String parentCategory)
	{
		categoryPane2.getChildren().clear(); //Clears sub category pane
		for(Category subCategory : MainController.mainTournament.getCategories())
		{
			if(subCategory.parentCategory.equals(parentCategory) && !subCategory.categoryName.equals(parentCategory))
			{
				System.out.println(subCategory.categoryName);
				ToggleButton toggleButton = new ToggleButton(subCategory.getCategoryName());
				toggleButton.setId(subCategory.categoryAbbreviation);
				toggleButton.setOnAction(e -> subcategoryClicked(toggleButton));
				subcategoryButtons.add(toggleButton);
				categoryPane2.getChildren().add(toggleButton);
			}
		}
	}
	
	public void mainCategoryClicked(ToggleButton toggleButton)
	{
		System.out.println(toggleButton.getText());
		showSubCategories(toggleButton.getText());
		unclick(mainCategoryButtons, toggleButton);
		nextButton.setDisable(true);
	}
	
	public void subcategoryClicked(ToggleButton toggleButton)
	{
		System.out.println(toggleButton.getText());
		unclick(subcategoryButtons, toggleButton);
		if(toggleButton.isSelected())
		{
			nextButton.setDisable(false);
		}
		else
		{
			nextButton.setDisable(true);
		}
	}
	
	public void unclick(ArrayList<ToggleButton> list, ToggleButton buttonClicked) //Deselects buttons in given array list
	{
		for(ToggleButton button : list)
		{
			if(button != buttonClicked)
			{
				button.setSelected(false);
			}
		}
	}
	
	public void nextScene()
	{
		//Gets the selected subcategory to pass to the question
		String categoryAbbrev = "";
		for(ToggleButton button : subcategoryButtons)
		{
			if(button.isSelected())
			{
				categoryAbbrev = button.getId();
			}
		}
		if(mainController.checkIfTossupCategorySet(GameController.questionNumber) == false)
			mainController.setTossupCategory(GameController.questionNumber, categoryAbbrev);
		else
			mainController.setBonusCategory(GameController.questionNumber, categoryAbbrev);
		
		//If one team earned a bonus, go to the bonus screen
		if(MainController.readBonus == true)
		{
			MainController.readBonus = false;
			try 
			{
				Stage stage = (Stage) nextButton.getScene().getWindow();
				Scene oldScene = (Scene) nextButton.getScene();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Bonuses.fxml"));
				Scene scene = new Scene(root, oldScene.getWidth(), oldScene.getHeight()); //Keeps new scene at the same size
				stage.setScene(scene);
				stage.show();
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		//If not, go back to the game screen
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
