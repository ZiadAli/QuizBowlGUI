package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class AlertBox 
{
	public static void display(String title, String message)
	{
		Stage window = new Stage();
		Label alertLabel = new Label(message);
		Button closeButton  = new Button("Close Alert Box");
		VBox layout = new VBox();
		
		window.initModality(Modality.APPLICATION_MODAL); //Prevents user from moving to another window until alert box has been dealt with
		window.setTitle(title);
		window.setMinWidth(250);
		
		closeButton.setOnAction(e -> window.close());
		layout.getChildren().addAll(alertLabel, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene alertScene = new Scene(layout, 300, 300);
		window.setScene(alertScene);
		window.showAndWait(); //Needs to be closed before you can return
		
	}
}
