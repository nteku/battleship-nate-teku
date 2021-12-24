package view;



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
import javafx.scene.image.Image;
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
import model.GameResetSignal;



/**
 * This class is for the GUI view for BattleShip
 * @author Nathan Teku
 *
 */
@SuppressWarnings("deprecation")
public class BattleShipView extends Application implements Observer {

 
	// all attributes and arrays declared for the GUI
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
	private static int played = 0;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	 
		    // initialized model
		 	model = new BattleShipModel();
		 	// initialized controller
		 	controller = new BattleShipController(model);
		 	// made model the observer
		 	model.addObserver(this);
		 	// set the gridPane
		 	g = new GridPane();
		 
		   // implemented cross out image and fire image 
	       crossOut = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRfHkMPC57wqsBv-sSTrEqRnqblq8Yv-Ezw6kdIjErzRVDTLlWzHjlI0XYuhV29VtXlwbE&usqp=CAU");
	       fire = new Image("https://media.istockphoto.com/photos/fire-flame-isolated-on-white-clipping-path-included-picture-id1281017225?b=1&k=20&m=1281017225&s=170667a&w=0&h=mopf69WQTVymd-vZZ4eLYZEKGyIYMYSttO0ZLYS826g=");
	       
	        // created the imagePattern for crossout and fire images
	        crossOutImage = new ImagePattern(crossOut);
	        fireImage = new ImagePattern(fire);
	       
	        // created Label for title, set the font, position, and added it to the gridPane
		    Label title = new Label("                                  Welcome to BattleShip");
		    title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
		    title.setAlignment(Pos.CENTER);
		    g.getChildren().add(0, title);
		 
		   
		    // created labels for user and CPU, set the font, position, and added it to the gridPane
		    Label labels = new Label ("                  You                                                         CPU");
		    labels.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 30));
		    labels.setAlignment(Pos.TOP_LEFT);
		    g.add(labels, 0, 2);
		    
		     // invoked the function to initialize both grids
		     initializeTwoGrids();
		 
		    // created playButton and positioned it
		    playButton = new Button(" Play ");
		    playButton.setPadding(new Insets(10,10,10,10));
		    playButton.setAlignment(Pos.BOTTOM_CENTER);
		    
		    // created infoButton and positioned it 
		    infoButton = new Button("   ?   ");
		    infoButton.setPadding(new Insets(10,10,10,10));
		    infoButton.setAlignment(Pos.BOTTOM_CENTER);
		    
		    
		    // adjusting the size of the pane and added some coloring
		 	g.setPrefHeight(600);
		 	g.setPrefWidth(900);
		 	g.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #4FC3F7,#4FC3F7)");
		
			
		 	// created HBox with both the playButton and infoButton
		 	HBox buttons = new HBox(15,playButton,infoButton);
		 	buttons.setAlignment(Pos.BOTTOM_CENTER);
		 	buttons.setPadding(new Insets(10,-140,10,10));
		    
		 	// aligned the playButton and added the HBox to the gridPane
		 	GridPane.setHalignment(playButton, HPos.CENTER); 
		 	GridPane.setValignment(playButton, VPos.BOTTOM);
		 	g.add(buttons, 0, 15);
		  
		   
		 	// implemented event handling for playButton
            playButton.setOnMouseClicked((click) ->{
           	 
            	// if the user has played already once
            	if (played != 0) {
            		 // invoke function to reset all of the game attributes
            		 resetGameAttributes();
            	}
            	
            	// assigning ships for both user and CPU
                for (int i = 0; i < 6; i++) {
            	     controller.assignShips();
                }
                // set played to 1
                played = 1;
                // invoke the playTheGame function
            	playTheGame();
            	
            	
            	 
            });
 
            // event handling for infoButton
		    infoButton.setOnMouseClicked((click) -> {
		    	
		    	// initializing the alert attribute and writing text to it
		    	alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Help");
		    	alert.setContentText("The purpose of this game is to sink all of the CPU's ships before\n the "
		    			+ "CPU sinks yours. The game will automatically assign your ships in your grid. The CPU's ships are"
		    			+ " hidden in order for you to locate them. If found a slot, the slot will be marked as a flame,"
		    			+ "if not, it will be crossed out. Good luck!" );
		    	alert.showAndWait();
		    });
	
		    // initializing the Scene and the Stage
		 	Scene scene = new Scene(g);
		 	Stage stage = new Stage();
		 	stage.setTitle("BattleShip Nathan Teku");
		 	stage.setScene(scene);
		 	stage.show();
	}
	
	/**
	 * This function plays the game as the user can guess the CPU slots
	 */
	public void playTheGame() {
		
		// set the attribute to true
		gameStarted = true;
		 
	 
		// event handling for the second grid
		g2.setOnMouseClicked((click) -> {
			
				 
					   
					   // if the game is over, stop the event handling
					   if (gameOver == true) {
						   return;
					   }
					   
					   // capture x and y coordinates
					   y = getYCoordinates(click.getY());
					   x = getXCoordinates(click.getX());
					   
					   // if either x or y has an inaccurate coordinate
					   if(x == 100 || y == 100) {
						   
						   // create an error alert 
						   alert = new Alert(AlertType.ERROR);
						   alert.setTitle("Error");
						   alert.setContentText("Inaccurate slot");
						   alert.showAndWait();
					   }
					   else {
						   // make the move
						   controller.makingMove(y,x);
						   // if the game is over, stop the event handling
						   if (gameOver == true) {
							   return;
						   }
						   
						   // randomize the row and column for the CPU and make the move
						   Random random = new Random();
						   int randomY = random.nextInt(10);
						   int randomX = random.nextInt(10);
						   controller.makingAIMove(randomY, randomX);
					   }
					   
			
		});
	}
	
	/**
	 * This function calculates the X coordinate of where it was clicked in the grid
	 * @param xCoordinate, value where it was clicked in the grid
	 * @return the specified column
	 */
	public int getXCoordinates(double xCoordinate) {
		
				// return 0 if the value of the x coordinate is between 546 and 576
				if (546 <= xCoordinate && xCoordinate <= 576) {
					return 0;
				}
				// return 1 if the value of the x coordinate is between 578 and 607
				if (xCoordinate >= 578 && xCoordinate <= 607) {
					return 1;
				}
				// return 2 if the value of the x coordinate is between 610 and 639
				if (xCoordinate >= 610 && xCoordinate <= 639) {
					return 2;
				}
				// return 3 if the value of the x coordinate is between 640 and 670
				if (xCoordinate >= 640 && xCoordinate <= 670) {
					return 3;
				}
				// return 4 if the value of the x coordinate is between 671 and 701
				if (xCoordinate >= 671 && xCoordinate <= 701) {
					return 4;
				}
				// return 5 if the value of the x coordinate is between 702 and 733
				if (xCoordinate >= 702 && xCoordinate <= 733) {
					return 5;
				}
				// return 6 if the value of the x coordinate is between 734 and 764
				if (xCoordinate >= 734 && xCoordinate <= 764) {
					return 6;
				}
				// return 7 if the value of the x coordinate is between 765 and 795
				if (xCoordinate >= 765 && xCoordinate <= 795) {
					return 7;
				}
				// return 8 if the value of the x coordinate is between 796 and 826
				if (xCoordinate >= 796 && xCoordinate <= 826) {
					return 8;
				}
				// return 9 if the value of the x coordinate is between 827 and 857
				if (xCoordinate >= 827 && xCoordinate <= 857) {
					return 9;
				}
				return 100;
	}
	
	/**
	 * This function calculates the Y coordinate of where it was clicked in the grid
	 * @param yCoordinate, value where it was clicked in the grid
	 * @return the specified row
	 */
	public int getYCoordinates(double yCoordinate) {
		// return 0 if the value of the y coordinate is between 49 and 79
		if (49 <= yCoordinate && yCoordinate <= 79) {
			return 0;
		}
		// return 1 if the value of the y coordinate is between 80 and 110
		if (yCoordinate >= 80 && yCoordinate <= 110) {
			return 1;
		}
		// return 2 if the value of the y coordinate is between 111 and 141
		if (yCoordinate >= 111 && yCoordinate <= 141) {
			return 2;
		}
		// return 3 if the value of the y coordinate is between 142 and 172
		if (yCoordinate >= 142 && yCoordinate <= 172) {
			return 3;
		}
		// return 4 if the value of the y coordinate is between 173 and 203
		if (yCoordinate >= 173 && yCoordinate <= 203) {
			return 4;
		}
		// return 5 if the value of the y coordinate is between 204 and 234
		if (yCoordinate >= 204 && yCoordinate <= 234) {
			return 5;
		}
		// return 6 if the value of the y coordinate is between 235 and 265
		if (yCoordinate >= 235 && yCoordinate <= 265) {
			return 6;
		}
		// return 7 if the value of the y coordinate is between 266 and 296
		if (yCoordinate >= 266 && yCoordinate <= 296) {
			return 7;
		}
		// return 8 if the value of the y coordinate is between 297 and 327
		if (yCoordinate >= 297 && yCoordinate <= 327) {
			return 8;
		}
		// return 9 if the value of the y coordinate is between 328 and 358
		if (yCoordinate >= 328 && yCoordinate <= 358) {
			return 9;
		}
		return 100;
	}
	
	/**
	 * This function calculates and places the two game boards
	 */
	public void initializeTwoGrids() {
		
		// 1st grid initialized
		g1 = new GridPane();
		// Centering the first grid
		g1.setAlignment(Pos.CENTER_LEFT);
		// setting the padding for the 1st grid
		g1.setPadding(new Insets(20, 20, 20, 20));
		
		// 2nd grid initialized 
		g2 = new GridPane();
		// Centering the second grid
		g2.setAlignment(Pos.BASELINE_RIGHT);
		// setting the padding for the 2nd grid
		g2.setPadding(new Insets(30, -125, 20, 10));
		
		// setting up the nested loop
		for (int i = 0; i < rowAndColumnSize;i++) {
			 
			// create the number
			Label num = new Label("" + tenNumbers[i]);
			// set the font
			num.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
			// set the position
			num.setAlignment(Pos.CENTER);
			// add to the first grid
			g1.add(num, 0, i);
			
			// second loop for the columns 
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
						
					 
					    // creating the slot and adding it to the gridPane
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
		
		// setting up nested loop for second gridPane
		for (int i = 0; i < rowAndColumnSize;i++) {
			
			// adding the numbers
			Label num = new Label("" + tenNumbers[i]);
			num.setAlignment(Pos.CENTER);
	 
			num.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 14));
			g2.add(num, 0, i);
			// inner loop
			for (int j = 0; j < rowAndColumnSize; j++) {
			 
				
				if (i == 0) {
					// setting up the VBox with the letters
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
					// setting up the VBox
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
 
		
	  
	 
	   // positioning both gridPanes
       g1.setAlignment(Pos.BOTTOM_LEFT);
       g2.setAlignment(Pos.CENTER_RIGHT);
       
       // adding the gridPanes to the big gridPane
       g.add(g1, 0, 10);
       g.add(g2, 0, 10);
		 
	}
	
	/**
	 * Checking to see if all of player 1's slots are filled
	 * @return
	 */
	public boolean player1IsFull() {
		
		// iterating through user's grid
		for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player1[i][j].getFill() == Color.WHITE) {
					return false;
				}
				
			}
		}
		
		return true;
	}
	
	/**
	 * Checking to see if all of CPU's slots are filled
	 * @return
	 */
    public boolean player2IsFull() {
		
    	// iterating through CPU grid  
		for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player2[i][j].getFill() == Color.WHITE) {
					return false;
				}
				
			}
		}
		
		return true;
	}
 
	
   /**
    * This function resets all of the boards back to default 
    */
   public void resetBoards() {
	   // iterating through both grids and reset them back to white
	   for (int i = 0; i < rowAndColumnSize; i++) {
		   for (int j = 0; j < rowAndColumnSize; j++) {
			   player1[i][j].setFill(Color.WHITE);
			   player2[i][j].setFill(Color.WHITE);
		   }
	   }
   }
    
   /**
    * This function resets all of the game attributes if user wants to play game again
    */
   public void resetGameAttributes() {
	   // reseting all of the attributes
	    gameStarted = false;
		gameOver = false;
		shipsAssigned = 0;
		
		// creating a new Model and making it the observer
		BattleShipModel newModel = new BattleShipModel();
		model = newModel;
		model.addObserver(this);

		// creating a new Controller
		controller = new BattleShipController(model);
		// initializing the two grids again
		initializeTwoGrids();
   }
   
   
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		
				if (arg.getClass() == GameResetSignal.class) {
					// It's a new game so clear the game boards
					 resetBoards();
				}
				else {
						 // model created 
						  model = (BattleShipModel) o;
						   
						  // if the game hasn't started 
						  if (gameStarted == false) {
							  
						      // filling the slots black as they are the ships
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
							  
							  // if the user is playing 
							  if (msg.getCurrentPlayer() == 1) {
								  			  
								              // if the slot hasn't been guessed but it is not part of the ship
											  if (msg.getStatus() == 1) {
												  
												  
												  // cross out that slot		  
												  player2[msg.getRow()][msg.getColumn()].setFill (crossOutImage);
												         // checking if the board is full
														 if (player2IsFull() == true) {
															 alert = new Alert(AlertType.INFORMATION);
															 alert.setTitle("Grid is Full");
															 alert.setContentText("All of the CPU's slots have been guessed.Therefore, CPU wins");
															 alert.showAndWait();
															 gameOver = true;
														 }
												 
												  
											  }
											  // if the slot has already been guessed 
											  else if (msg.getStatus() == 2) {
													  if (player2[msg.getRow()][msg.getColumn()].getFill() != Color.WHITE ) {
														  // set an Alert 
														  alert = new Alert(AlertType.ERROR);
														  alert.setTitle("Slot already guessed");
														  alert.setContentText("This slot has already been guessed. Try again");
														  alert.showAndWait();
											      }
											  }
											  // if the slot is part of the ship
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
							  // if it is the AI making the move
							  else {
								    
								  // if the slot has been guessed  
								  if (msg.getStatus() == 1) {
									  
									  
									  if (player1[msg.getRow()][msg.getColumn()].getFill() != Color.WHITE) {
										      // create the error alert
											  alert = new Alert(AlertType.ERROR);
											  alert.setTitle("Slot already guessed");
											  alert.setContentText("This slot has already been guessed. Try again");
											  alert.showAndWait();
									  }
									  else {
										     // cross out the slot
											  player1[msg.getRow()][msg.getColumn()].setFill( crossOutImage);
											  // if all of player 1's slots have been chosen
											 if (player1IsFull() == true) {
												 // create the alert
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
								  // if the slot is part of the ship
								  else {
									  // create the fire image
									  player1[msg.getRow()][msg.getColumn()].setFill(fireImage);
									  
									  // if the CPU got all of the player's ships
									  if (model.gotAllPlayer1Ships() == true) {
										     // create the alert 
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

	
}
