package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Console
		TextField console = new TextField("0");
		console.setPrefSize(370, 120);
		console.setAlignment(Pos.CENTER_RIGHT);
		console.setStyle("-fx-font: 42 Arial");
		console.requestFocus();
		console.addEventFilter(KeyEvent.KEY_TYPED, e -> {
			if (!e.getCharacter().matches("\\d")) {
				e.consume();
			}
		});
		
		// Buttons Layout
		GridPane buttons = new GridPane(5, 5);
		
		// Buttons
		
		for (int index = 9; index >= 0; index--) {
			final int i = index;
			
			Button slaveButton = new Button();
			slaveButton.setText(String.valueOf(i));
			slaveButton.setPrefSize(120, 60);
			slaveButton.setOnAction(e -> {
				if (console.getText().equalsIgnoreCase("0")) {
					console.replaceText(0, console.getText().length(), Integer.toString(i));
				} else {
					console.appendText(Integer.toString(i));
				}
				
			});
			
			buttons.add(slaveButton, (9-i)%3, (9-i)/3);
		}
		
		Button plusMinus = new Button("+/-");
		plusMinus.setPrefSize(120, 60);
		plusMinus.setOnAction(e -> {
			switch ((int) Double.parseDouble(console.getText())) {
			case 0:
				break;
			default:
				double tmp = 0.00;
				tmp = Double.parseDouble(console.getText()) * -1;
				
				console.setText(Double.toString(tmp).replaceAll("0+$", "").replaceAll("\\.$", ""));
				break;
			}
		});
		buttons.add(plusMinus, 1, 3);
		
		Button divide = new Button("/");
		divide.setPrefSize(120, 60);
		buttons.add(divide, 3, 0);
		
		Button multiply = new Button("*");
		multiply.setPrefSize(120, 60);
		buttons.add(multiply, 3, 1);
		
		Button addition = new Button("+");
		addition.setPrefSize(120, 60);
		buttons.add(addition, 3, 2);
		
		Button substraction = new Button("-");
		substraction.setPrefSize(120, 60);
		buttons.add(substraction, 3, 3);
		
		Button comma = new Button(".");
		comma.setPrefSize(120, 60);
		comma.setOnAction(e -> {
			if (console.getText().contains(".")) {
				
			} else {
				console.appendText(".");
			}
		});
		buttons.add(comma, 2, 3);
		
		Button clear = new Button("C");
		clear.setPrefSize(120, 60);
		clear.setOnAction(e -> {
			console.clear();
		});
		buttons.add(clear, 0, 4, 2, 1);
		
		Button equal = new Button("=");
		equal.setPrefSize(120, 60);
		buttons.add(equal, 3, 4, 2, 1);
		
		// Root Layout
		VBox mainContainer = new VBox(5);
		mainContainer.getChildren().addAll(console, buttons);	
		mainContainer.setPadding(new Insets(10));
		
		// Create Scene
		Scene scene = new Scene(mainContainer);
		
		primaryStage.setTitle("Simple Calculator");
		primaryStage.setResizable(false);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
