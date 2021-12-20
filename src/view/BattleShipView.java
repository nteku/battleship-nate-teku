package view;

import java.util.Observable;
import java.util.Observer;

import controller.BattleShipController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.BattleShipModel;

public class BattleShipView extends Application implements Observer {

 
	private BorderPane g;
	private GridPane g1;
	private GridPane g2;
	private boolean gameStarted = false;
	private BattleShipModel model;
	private final int rowAndColumnSize = 10;
	private BattleShipController controller;
	private Rectangle player1 [][]= new Rectangle[10][10];
	private Rectangle player2 [][]= new Rectangle [10][10];
	private char tenAlphabets[] = {'A','B','C','D','E','F','G','H','I','J'};
	private Button playButton;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	 
		 	model = new BattleShipModel();
		 	 
		 	controller = new BattleShipController(model);
		 	
		 	model.addObserver(this);
		 	g = new BorderPane();
	 
		 
		    Label title = new Label("Welcome to BattleShip");
		  title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
		    BorderPane.setAlignment(title, Pos.CENTER);
		    g.setTop(title);
		    
		 initializeTwoGrids();
		  
		 
		 
		    playButton = new Button("Play");
		    playButton.setAlignment(Pos.CENTER);
		 	g.setPrefHeight(600);
		 	g.setPrefWidth(900);
		 	g.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #4FC3F7,#4FC3F7)");
		 	  BorderPane.setAlignment(playButton, Pos.BOTTOM_CENTER);
			g.setBottom(playButton);
			    
            playButton.setOnMouseClicked((click) ->{
           	 
           
            	controller.assignShips();
            	playTheGame();
            	
            });
 
	 
		    
	
		 	Scene scene = new Scene(g);
		 	
		 	Stage stage = new Stage();
		 	stage.setTitle("BattleShip Nathan Teku");
		 	stage.setScene(scene);
		 	stage.show();
	}
	
	
	public void playTheGame() {
		Label l = new Label("Predict a coordinate, where the CPU's battleship (s) may be");
		g.setRight(l);
		
		g1.setOnMouseClicked((click) -> {
			
			 System.out.println("X coordinate: " + click.getX() + " Y coordinate: " + click.getY());
			
		});
	}
	
	public int getXCoordinates(int xCoordinate) {
		
				// return 0 if the value of the x coordinate is between 20 and 50
				if (20 <= xCoordinate && xCoordinate <= 50) {
					return 0;
				}
				// return 1 if the value of the x coordinate is between 51 and 81
				if (xCoordinate >= 51 && xCoordinate <= 81) {
					return 1;
				}
				// return 2 if the value of the x coordinate is between 103 and 143
				if (xCoordinate >= 83 && xCoordinate <= 113) {
					return 2;
				}
				// return 3 if the value of the x coordinate is between 152 and 192
				if (xCoordinate >= 152 && xCoordinate <= 192) {
					return 3;
				}
				// return 4 if the value of the x coordinate is between 200 and 240
				if (xCoordinate >= 200 && xCoordinate <= 240) {
					return 4;
				}
				// return 5 if the value of the x coordinate is between 248 and 288
				if (xCoordinate >= 248 && xCoordinate <= 288) {
					return 5;
				}
				// return 6 if the value of the x coordinate is between 295 and 336
				if (xCoordinate >= 295 && xCoordinate <= 336) {
					return 6;
				}

				return 100;
	}
	
	
	public int getYCoordinates(int yCoordinate) {
		// return 0 if the value of the y coordinate is between 108 and 138
		if (108 <= yCoordinate && yCoordinate <= 138) {
			return 0;
		}
		// return 1 if the value of the y coordinate is between 139 and 169
		if (yCoordinate >= 139 && yCoordinate <= 169) {
			return 1;
		}
		// return 2 if the value of the y coordinate is between 170 and 200
		if (yCoordinate >= 170 && yCoordinate <= 200) {
			return 2;
		}
		// return 3 if the value of the y coordinate is between 201 and 230
		if (yCoordinate >= 201 && yCoordinate <= 230) {
			return 3;
		}
		// return 4 if the value of the y coordinate is between 232 and 262
		if (yCoordinate >= 232 && yCoordinate <= 262) {
			return 4;
		}
		// return 5 if the value of the y coordinate is between 263 and 292
		if (yCoordinate >= 263 && yCoordinate <= 292) {
			return 5;
		}
		// return 6 if the value of the y coordinate is between 293 and 325
		if (yCoordinate >= 293 && yCoordinate <= 325) {
			return 6;
		}
		// return 7 if the value of the y coordinate is between 326 and 356
		if (yCoordinate >= 326 && yCoordinate <= 356) {
			return 7;
		}
		// return 8 if the value of the y coordinate is between 357 and 387
		if (yCoordinate >= 357 && yCoordinate <= 387) {
			return 8;
		}
		// return 9 if the value of the y coordinate is between 388 and 418
		if (yCoordinate >= 388 && yCoordinate <= 418) {
			return 9;
		}
		return 100;
	}
	
	
	public void initializeTwoGrids() {
		g1 = new GridPane();
		g1.setAlignment(Pos.CENTER_LEFT);
		g1.setPadding(new Insets(20, 20, 20, 20));
		
		g2 = new GridPane();
		g2.setAlignment(Pos.CENTER_LEFT);
		g2.setPadding(new Insets(20, 20, 20, 20));
		
		for (int i = 0; i < rowAndColumnSize;i++) {
			
		
				for (int j = 0; j < rowAndColumnSize; j++) {
					 
					VBox container = new VBox();
				 
					 
					
					Rectangle r = new Rectangle(30,30);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK); 
					container.getChildren().add(r);
					 
					 player1[i][j] = r;
					 
					 g1.add(container, j, i);
				 
				}
		
		}
	 
		
		for (int i = 0; i < rowAndColumnSize;i++) {
			
			
			for (int j = 0; j < rowAndColumnSize; j++) {
				Label letterLabel  = new Label();
				VBox container = new VBox();
				letterLabel.setText(Character.toString(tenAlphabets[j]));
				 
				
				Rectangle r = new Rectangle(30,30);
				r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK); 
				container.getChildren().add(r);
				 
				 player2[i][j] = r;
				 g2.add(container, j, i);
			 
			}
	
	}
 
		
	 
	 
		g.setCenter(g1);
		g.setRight(g2);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	 
		  
		  if (gameStarted == false) {
				player1[0][0].setFill(Color.BLACK);
				player1[0][1].setFill(Color.BLACK);
			    g.setRight(null);
				
			
		  }
		  else {
			  
		  }
		
	}

	 

	
}
