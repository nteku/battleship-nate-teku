package view;

import java.util.Observable;
import java.util.Observer;

import battleShipMessages.BattleShipMoveMessage;
import controller.BattleShipController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import model.BattleShip;
import model.BattleShipModel;

public class BattleShipView extends Application implements Observer {

 
	private BorderPane g;
	private GridPane g1;
	private GridPane g2;
	private boolean gameStarted = false;
	private boolean gameOver = false;
	private BattleShipModel model;
	private final int rowAndColumnSize = 10;
	private BattleShipController controller;
	private Rectangle player1 [][]= new Rectangle[10][10];
	private Rectangle player2 [][]= new Rectangle [10][10];
	private char tenAlphabets[] = {'A','B','C','D','E','F','G','H','I','J'};
	private Button playButton;
	private static int shipsAssigned = 0;
	private Alert alert;
	
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
           	 
                for (int i = 0; i < 6; i++) {
            	     controller.assignShips();
                }
                
            	playTheGame();
            	
            });
 
	 
		    
	
		 	Scene scene = new Scene(g);
		 	
		 	Stage stage = new Stage();
		 	stage.setTitle("BattleShip Nathan Teku");
		 	stage.setScene(scene);
		 	stage.show();
	}
	
	
	public void playTheGame() {
		
		gameStarted = true;
		Label l = new Label("Predict a coordinate, where the CPU's battleship (s) may be");
		//g.setRight(l);
		
		g1.setOnMouseClicked((click) -> {
			
		   if (gameOver == true) {
			   return;
		   }
		   controller.makingMove(getYCoordinates(click.getY()),getXCoordinates(click.getX()));
			
		});
	}
	
	public int getXCoordinates(double xCoordinate) {
		
				// return 0 if the value of the x coordinate is between 20 and 50
				if (20 <= xCoordinate && xCoordinate <= 50) {
					return 0;
				}
				// return 1 if the value of the x coordinate is between 51 and 81
				if (xCoordinate >= 51 && xCoordinate <= 81) {
					return 1;
				}
				// return 2 if the value of the x coordinate is between 83 and 113
				if (xCoordinate >= 83 && xCoordinate <= 113) {
					return 2;
				}
				// return 3 if the value of the x coordinate is between 114 and 144
				if (xCoordinate >= 114 && xCoordinate <= 144) {
					return 3;
				}
				// return 4 if the value of the x coordinate is between 145 and 175
				if (xCoordinate >= 145 && xCoordinate <= 175) {
					return 4;
				}
				// return 5 if the value of the x coordinate is between 176 and 206
				if (xCoordinate >= 176 && xCoordinate <= 206) {
					return 5;
				}
				// return 6 if the value of the x coordinate is between 295 and 336
				if (xCoordinate >= 207 && xCoordinate <= 237) {
					return 6;
				}
				// return 7 if the value of the x coordinate is between 238 and 268
				if (xCoordinate >= 238 && xCoordinate <= 268) {
					return 7;
				}
				// return 8 if the value of the x coordinate is between 268 and 300
				if (xCoordinate >= 269 && xCoordinate <= 300) {
					return 8;
				}
				// return 9 if the value of the x coordinate is between 301 and 332
				if (xCoordinate >= 301 && xCoordinate <= 332) {
					return 9;
				}
				return 100;
	}
	
	
	public int getYCoordinates(double yCoordinate) {
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
	
	
	public boolean player1IsFull() {
		
		for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player1[i][j].getFill() == Color.WHITE) {
					return false;
				}
				
			}
		}
		
		return true;
	}
	
    public boolean player2IsFull() {
		
		for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player2[i][j].getFill() == Color.WHITE) {
					return false;
				}
				
			}
		}
		
		return true;
	}
    
    
    public boolean gotAllPlayer1Ships() {
    	for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player1[i][j].getFill()== Color.BLACK) {
					return false;
				}
				
			}
		}
		
		return true;
    }
	
    
    public boolean gotAllPlayer2Ships() {
    	for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player2[i][j].getFill()== Color.BLACK) {
					return false;
				}
				
			}
		}
		
		return true;
    }
    
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
	   
		  model = (BattleShipModel) o;
		  
		  if (gameStarted == false) {
			  
		      
			  BattleShip ship = (BattleShip) arg;
			  
			    if (shipsAssigned < 3) {
					player1[ship.getRow1()][ship.getCol1()].setFill(Color.BLACK);
					player1[ship.getRow2()][ship.getCol2()].setFill(Color.BLACK);
					player1[ship.getRow3()][ship.getCol3()].setFill(Color.BLACK);
					shipsAssigned++;
			    }
			    else {
			    	player2[ship.getRow1()][ship.getCol1()].setFill(Color.BLACK);
					player2[ship.getRow2()][ship.getCol2()].setFill(Color.BLACK);
					player2[ship.getRow3()][ship.getCol3()].setFill(Color.BLACK);
					
					 
			    }
				 
			
		  }
		  else {
			  BattleShipMoveMessage msg = (BattleShipMoveMessage) arg;
			  
			  if (msg.getCurrentPlayer() == 1) {
							  if (msg.getStatus() == 1) {
								  player2[msg.getRow()][msg.getColumn()].setFill(Color.DARKGREY);
								 if (player2IsFull() == true) {
									 alert = new Alert(AlertType.INFORMATION);
									 alert.setTitle("Grid is Full");
									 alert.setContentText("All of the CPU's slots have been guessed. CPU wins");
									 alert.showAndWait();
									 gameOver = true;
								 }
							  }
							  else if (msg.getStatus() == 2) {
								  
							  }
							  else {
								  player2[msg.getRow()][msg.getColumn()].setFill(Color.RED);
								  
								  if (gotAllPlayer2Ships() == true) {
									     alert = new Alert(AlertType.INFORMATION);
										 alert.setTitle("You Won");
										 alert.setContentText("You got all the CPU's ships. You won!");
										 alert.showAndWait();
										 gameOver = true;
								  }
							  }
			  
			  
			  
			  }
			  else {
				  
				  
				  
			  }
		  }
		
	}

	 

	
}
