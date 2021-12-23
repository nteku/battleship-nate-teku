package view;

import java.io.FileInputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import battleShipMessages.BattleShipMoveMessage;
import controller.BattleShipController;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.BattleShip;
import model.BattleShipModel;

public class BattleShipView extends Application implements Observer {

 

	private GridPane g;
	private GridPane g1;
	private GridPane g2;
	private boolean gameStarted = false;
	private boolean gameOver = false;
	private BattleShipModel model;
	private final int rowAndColumnSize = 10;
	private BattleShipController controller;
	private Rectangle player1 [][]= new Rectangle[10][10];
	private Rectangle player2 [][]= new Rectangle [10][10];
	private char tenLetters[] = {'A','B','C','D','E','F','G','H','I','J'};
	private int tenNumbers[] = {1,2,3,4,5,6,7,8,9,10};
	private Button playButton;
	private Button infoButton;
	private static int shipsAssigned = 0;
	private Alert alert;
	private int x;
	private int y;
	private Image crossOut;
	private Image fire;
	private ImagePattern crossOutImage;
	private ImagePattern fireImage;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	 
		 	model = new BattleShipModel();
		 	 
		 	controller = new BattleShipController(model);
		 	
		 	model.addObserver(this);
		 	g = new GridPane();
		 
		 	 
	       crossOut = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfHkMPC57wqsBv-sSTrEqRnqblq8Yv-Ezw6kdIjErzRVDTLlWzHjlI0XYuhV29VtXlwbE&usqp=CAU");
	       fire = new Image("https://media.istockphoto.com/photos/fire-flame-isolated-on-white-clipping-path-included-picture-id1281017225?b=1&k=20&m=1281017225&s=170667a&w=0&h=mopf69WQTVymd-vZZ4eLYZEKGyIYMYSttO0ZLYS826g=");
	       
	       crossOutImage = new ImagePattern(crossOut);
	       fireImage = new ImagePattern(fire);
	       
		   Label title = new Label("                                  Welcome to BattleShip");
		    
		    
		    title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
		    title.setAlignment(Pos.CENTER);
		    g.getChildren().add(0, title);
		 
		   
		    
		    Label labels = new Label ("                  You                                                         CPU");
		 
		    labels.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
		    labels.setAlignment(Pos.TOP_LEFT);
		    g.add(labels, 0, 2);
		    
		 
		     initializeTwoGrids();
		 
		 
		    playButton = new Button(" Play ");
		    playButton.setPadding(new Insets(10,10,10,10));
		    playButton.setAlignment(Pos.BOTTOM_CENTER);
		    
		    infoButton = new Button("   ?   ");
		    
		    infoButton.setPadding(new Insets(10,10,10,10));
		    infoButton.setAlignment(Pos.BOTTOM_CENTER);
		    
		    
		 	g.setPrefHeight(600);
		 	g.setPrefWidth(900);
		 	g.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #4FC3F7,#4FC3F7)");
		
			
		 	
		 	HBox buttons = new HBox(15,playButton,infoButton);
		 	buttons.setAlignment(Pos.BOTTOM_CENTER);
		 	buttons.setPadding(new Insets(10,-140,10,10));
		    
		 	GridPane.setHalignment(playButton, HPos.CENTER); 
		 	GridPane.setValignment(playButton, VPos.BOTTOM);
		 	g.add(buttons, 0, 15);
		  
		  
            playButton.setOnMouseClicked((click) ->{
           	 
                for (int i = 0; i < 6; i++) {
            	     controller.assignShips();
                }
                
            	playTheGame();
            	
            });
 
	 
		    infoButton.setOnMouseClicked((click) -> {
		    	
		    	alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Help");
		    	alert.setContentText("The purpose of this game is to sink all of the CPU's ships before\n the "
		    			+ "CPU sinks yours. The game will automatically assign your ships in your grid. The CPU's ships are"
		    			+ " hidden in order for you to locate them. If found a slot, the slot will be marked as a flame,"
		    			+ "if not, it will be crossed out. Good luck!" );
		    	alert.showAndWait();
		    });
	
		 	Scene scene = new Scene(g);
		 	
		 	Stage stage = new Stage();
		 	stage.setTitle("BattleShip Nathan Teku");
		 	stage.setScene(scene);
		 	stage.show();
	}
	
	
	public void playTheGame() {
		
		gameStarted = true;
		 
	 
		
		g2.setOnMouseClicked((click) -> {
			
						//System.out.println("X coordinate: " + click.getX() + " Y coordinate: " + click.getY() );
					   
					   
					   if (gameOver == true) {
						   return;
					   }
					   y = getYCoordinates(click.getY());
					   x = getXCoordinates(click.getX());
					   
					   if(x == 100 || y == 100) {
						   alert = new Alert(AlertType.ERROR);
						   alert.setTitle("Error");
						   alert.setContentText("Inaccurate slot");
						   alert.showAndWait();
					   }
					   else {
						   controller.makingMove(y,x);
						   if (gameOver == true) {
							   return;
						   }
						   Random random = new Random();
						   int randomY = random.nextInt(10);
						   int randomX = random.nextInt(10);
						   controller.makingAIMove(randomY, randomX);
					   }
					 
			
		});
	}
	
	public int getXCoordinates(double xCoordinate) {
		
				// return 0 if the value of the x coordinate is between 20 and 50
				if (546 <= xCoordinate && xCoordinate <= 576) {
					return 0;
				}
				// return 1 if the value of the x coordinate is between 51 and 81
				if (xCoordinate >= 578 && xCoordinate <= 607) {
					return 1;
				}
				// return 2 if the value of the x coordinate is between 83 and 113
				if (xCoordinate >= 610 && xCoordinate <= 639) {
					return 2;
				}
				// return 3 if the value of the x coordinate is between 114 and 144
				if (xCoordinate >= 640 && xCoordinate <= 670) {
					return 3;
				}
				// return 4 if the value of the x coordinate is between 145 and 175
				if (xCoordinate >= 671 && xCoordinate <= 701) {
					return 4;
				}
				// return 5 if the value of the x coordinate is between 176 and 206
				if (xCoordinate >= 702 && xCoordinate <= 733) {
					return 5;
				}
				// return 6 if the value of the x coordinate is between 295 and 336
				if (xCoordinate >= 734 && xCoordinate <= 764) {
					return 6;
				}
				// return 7 if the value of the x coordinate is between 238 and 268
				if (xCoordinate >= 765 && xCoordinate <= 795) {
					return 7;
				}
				// return 8 if the value of the x coordinate is between 268 and 300
				if (xCoordinate >= 796 && xCoordinate <= 826) {
					return 8;
				}
				// return 9 if the value of the x coordinate is between 301 and 332
				if (xCoordinate >= 827 && xCoordinate <= 857) {
					return 9;
				}
				return 100;
	}
	
	
	public int getYCoordinates(double yCoordinate) {
		// return 0 if the value of the y coordinate is between 108 and 138
		if (29 <= yCoordinate && yCoordinate <= 59) {
			return 0;
		}
		// return 1 if the value of the y coordinate is between 139 and 169
		if (yCoordinate >= 60 && yCoordinate <= 90) {
			return 1;
		}
		// return 2 if the value of the y coordinate is between 170 and 200
		if (yCoordinate >= 91 && yCoordinate <= 121) {
			return 2;
		}
		// return 3 if the value of the y coordinate is between 201 and 230
		if (yCoordinate >= 122 && yCoordinate <= 152) {
			return 3;
		}
		// return 4 if the value of the y coordinate is between 232 and 262
		if (yCoordinate >= 153 && yCoordinate <= 183) {
			return 4;
		}
		// return 5 if the value of the y coordinate is between 263 and 292
		if (yCoordinate >= 184 && yCoordinate <= 214) {
			return 5;
		}
		// return 6 if the value of the y coordinate is between 293 and 325
		if (yCoordinate >= 215 && yCoordinate <= 245) {
			return 6;
		}
		// return 7 if the value of the y coordinate is between 326 and 356
		if (yCoordinate >= 246 && yCoordinate <= 276) {
			return 7;
		}
		// return 8 if the value of the y coordinate is between 357 and 387
		if (yCoordinate >= 277 && yCoordinate <= 307) {
			return 8;
		}
		// return 9 if the value of the y coordinate is between 388 and 418
		if (yCoordinate >= 308 && yCoordinate <= 338) {
			return 9;
		}
		return 100;
	}
	
	
	public void initializeTwoGrids() {
		g1 = new GridPane();
		g1.setAlignment(Pos.CENTER_LEFT);
		g1.setPadding(new Insets(20, 20, 20, 20));
		
		g2 = new GridPane();
		g2.setAlignment(Pos.BASELINE_RIGHT);
		g2.setPadding(new Insets(30, -125, 20, 10));
		
	  /*
		for (int i = 0; i < rowAndColumnSize;i++) {
			 
		     
				for (int j = 0; j < rowAndColumnSize; j++) {
					 
					if (i == 0) {
							VBox container = new VBox();
							
							Label letter = new Label("    " + Character.toString(tenLetters[j]));
							letter.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
							Rectangle r = new Rectangle(30,30);
							r.setFill(Color.WHITE);
							r.setStroke(Color.BLACK); 
							letter.setAlignment(Pos.CENTER_RIGHT);
						  
							container.getChildren().add(letter);
							container.getChildren().add(r);
							 
							 player1[i][j] = r;
							 g1.add(container, j, i);
							
					}
					else {
							 
						VBox container = new VBox ();
							Rectangle r = new Rectangle(30,30);
							r.setFill(Color.WHITE);
							r.setStroke(Color.BLACK); 
							 
							container.getChildren().add(r);
							 
							 player1[i][j] = r;
							 
							 g1.add(container, j, i);
							
				  
					}
				}
		
		}
	  
	  */
		
		for (int i = 0; i < rowAndColumnSize;i++) {
			 
			Label num = new Label("" + tenNumbers[i]);
			g1.add(num, 0, i);
			for (int j = 0; j < rowAndColumnSize; j++) {
				 
				if (i == 0) {
						VBox container = new VBox();
						
						Label letter = new Label("    " + Character.toString(tenLetters[j]));
						letter.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
						Rectangle r = new Rectangle(30,30);
					    r.setFill(Color.WHITE);
						r.setStroke(Color.BLACK); 
						letter.setAlignment(Pos.CENTER_RIGHT);
					  
						container.getChildren().add(letter);
						container.getChildren().add(r);
						 
						 player1[i][j] = r;
						 g1.add(container, j + 1, i);
						
				}
				else {
						
					 
					  
					    VBox container = new VBox ();
						Rectangle r = new Rectangle(30,30);
						r.setFill(Color.WHITE);
						r.setStroke(Color.BLACK); 
						 
						container.getChildren().add(r);
						 
						 player1[i][j] = r;
						 
						 g1.add(container, j + 1, i);
					   
			  
				}
			}
			
			 
	
	}
		for (int i = 0; i < rowAndColumnSize;i++) {
			
			Label num = new Label("" + tenNumbers[i]);
			num.setAlignment(Pos.CENTER_LEFT);
			num.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
			g2.add(num, 0, i);
			for (int j = 0; j < rowAndColumnSize; j++) {
			 
				if (i == 0) {
					VBox container = new VBox();
					Label letter = new Label("    " + Character.toString(tenLetters[j]));
					letter.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
					Rectangle r = new Rectangle(30,30);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK); 
					letter.setAlignment(Pos.CENTER_RIGHT);
					container.getChildren().add(letter);
					container.getChildren().add(r);
					 player2[i][j] = r;
					 
					 g2.add(container, j + 1, i);
				}
				else {
					VBox container = new VBox();
					Rectangle r = new Rectangle(30,30);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK); 
					container.getChildren().add(r);
					 
					 player2[i][j] = r;
					 g2.add(container, j + 1, i);	
				}
				 
			 
			}
	
	}
 
		
	  
	 
	   
       g1.setAlignment(Pos.BOTTOM_LEFT);
       g2.setAlignment(Pos.CENTER_RIGHT);
       
       g.add(g1, 0, 10);
       g.add(g2, 0, 10);
		//g.setCenter(g1);
		//g.setRight(g2);
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
			    
					
					 
			    }
				 
			
		  }
		  else {
			  BattleShipMoveMessage msg = (BattleShipMoveMessage) arg;
			  
			  if (msg.getCurrentPlayer() == 1) {
							  if (msg.getStatus() == 1) {
								  
								  
										  
								  player2[msg.getRow()][msg.getColumn()].setFill (crossOutImage);
										 if (player2IsFull() == true) {
											 alert = new Alert(AlertType.INFORMATION);
											 alert.setTitle("Grid is Full");
											 alert.setContentText("All of the CPU's slots have been guessed.Therefore, CPU wins");
											 alert.showAndWait();
											 gameOver = true;
										 }
								 
								  
							  }
							  else if (msg.getStatus() == 2) {
									  if (player2[msg.getRow()][msg.getColumn()].getFill() != Color.WHITE ) {
										  alert = new Alert(AlertType.ERROR);
										  alert.setTitle("Slot already guessed");
										  alert.setContentText("This slot has already been guessed. Try again");
										  alert.showAndWait();
							      }
							  }
							  else {
								  player2[msg.getRow()][msg.getColumn()].setFill(fireImage);
								  
								  if (model.gotAllPlayer2Ships() == true) {
									     alert = new Alert(AlertType.INFORMATION);
										 alert.setTitle("You Won");
										 alert.setContentText("You got all the CPU's ships. You won!");
										 alert.showAndWait();
										 gameOver = true;
								  }
							  }
			  
			  
			  
			  }
			  else {
				    
				  if (msg.getStatus() == 1) {
					  
					  if (player1[msg.getRow()][msg.getColumn()].getFill() != Color.WHITE) {
							  alert = new Alert(AlertType.ERROR);
							  alert.setTitle("Slot already guessed");
							  alert.setContentText("This slot has already been guessed. Try again");
							  alert.showAndWait();
					  }
					  else {
							  player1[msg.getRow()][msg.getColumn()].setFill( crossOutImage);
							 if (player1IsFull() == true) {
								 alert = new Alert(AlertType.INFORMATION);
								 alert.setTitle("Grid is Full");
								 alert.setContentText("All of your slots have been guessed. Therefore, you win!");
								 alert.showAndWait();
								 gameOver = true;
							 }
					 
					  }
				  }
				  else if (msg.getStatus() == 2) {
					  
				  }
				  else {
					  player1[msg.getRow()][msg.getColumn()].setFill(fireImage);
					  
					  if (model.gotAllPlayer1Ships() == true) {
						     alert = new Alert(AlertType.INFORMATION);
							 alert.setTitle("You Won");
							 alert.setContentText("CPU got all your player's ships. CPU wins!");
							 alert.showAndWait();
							 gameOver = true;
					  }
				  }
				  
				  
			  }
		  }
		
	}

	 

	
}
