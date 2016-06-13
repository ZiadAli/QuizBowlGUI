package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		Settings settings = new Settings();
		settings.addAllCategories();
		settings.addAllStatistics();
		MainController mainController = new MainController();
		mainController.defaultCategories();
		mainController.defaultStats();
		
		for(Statistic stat : settings.bigStatisticList)
		{
			System.out.println("" + stat.getStatisticName() + " has fractions: " + stat.fractionOn + " and has bonus: " + stat.bonusStatistic);
		}
		
		System.out.println("Total number of stats: " + settings.statisticList.size());
		
		System.out.println("Settings category has size: " + settings.categoryList.size());
		
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/Title.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.setResizable(false);
			primaryStage.show();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
