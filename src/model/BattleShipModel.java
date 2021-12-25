package model;

import java.util.Observable;

import java.util.Random;

import battleShipMessages.BattleShipMoveMessage;
 

/**
 * This class is the model of the BattleShip game, which carries the state of the game
 * @author Nathan Teku
 *
 */
@SuppressWarnings("deprecation")
public class BattleShipModel extends Observable {

	// attributes include both user and CPU's arrays, ships assigned, and constant variable which holds as the size of array
	private final int rowAndColumnSize = 10;
	private BattleShipSlot player1 [][];
	private BattleShipSlot player2 [][];
	private static int shipsAssigned;
	
	
	/**
	 * Constructor initializes both user and CPU's arrays
	 */
	public BattleShipModel () {
		
		// initializes arrays
		player1 = new BattleShipSlot [rowAndColumnSize][rowAndColumnSize];
		player2 = new BattleShipSlot [rowAndColumnSize][rowAndColumnSize];
		
		// filling both arrays with BattleShipSlot objects
		for (int i = 0; i <rowAndColumnSize; i++) {
			
			for (int j = 0; j < rowAndColumnSize; j++) {
				BattleShipSlot slot1 = new BattleShipSlot();
				BattleShipSlot slot2 = new BattleShipSlot();
				
				player1[i][j] = slot1;
				player2[i][j] = slot2;
			}
		}
		
		// notifying the View
		setChanged();
		notifyObservers(new GameResetSignal());
		shipsAssigned = 0;
	}
	
	/**
	 * This function assigns the ships for both the user and the CPU
	 */
	public void assigningShips() {
		
		// initializing the ships for the user
		if (shipsAssigned < 3) {
				Random random1 = new Random();
				int randomRow= random1.nextInt(8);
				int randomColumn = random1.nextInt(8);
				player1[randomRow][randomColumn].setIsMarkedBlack(true);
				player1[randomRow][randomColumn + 1].setIsMarkedBlack(true);
				player1[randomRow][randomColumn + 2].setIsMarkedBlack(true);
				
				shipsAssigned++;
				setChanged();
				notifyObservers(new BattleShip (randomRow,randomColumn,randomRow,randomColumn + 1, randomRow, randomColumn + 2));
		
		}
		// initializing the ships for the CPU
		else {
			Random random1 = new Random();
			int randomRow= random1.nextInt(8);
			int randomColumn = random1.nextInt(8);
			player2[randomRow][randomColumn].setIsMarkedBlack(true);
			player2[randomRow][randomColumn + 1].setIsMarkedBlack(true);
			player2[randomRow][randomColumn + 2].setIsMarkedBlack(true);
			
			shipsAssigned++; 
			setChanged();
			 
		}
	}
	
	/**
	 * This function makes the move for the user
	 * @param row , that was clicked
	 * @param col , that was clicked
	 */
	public void makeMove(int row, int col) {
		
		// if the slot hasn't been guessed and is not part of the ship
		if ( (player2[row][col].getIsMarkedBlack() == false) && (player2[row][col].getIsGuessed() == false)) {
			// set it as guessed
			player2[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,1,1));
			
		}
		// if the slot has been guessed and is not part of the ship
		else if ((player2[row][col].getIsMarkedBlack() == false) && (player2[row][col].getIsGuessed() == true)) {
			// notify the View
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,2,1));
		}
		// if the slot hasn't been guessed and is part of the ship
		else if ((player2[row][col].getIsMarkedBlack() == true) && (player2[row][col].getIsGuessed() == false)) {
			// set is as hit and guessed
			player2[row][col].setIsMarkedBlack(false);
			player2[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,3,1));
		}
		else {
			 
		}
	}
	
	/**
	 * This function makes the move for the CPU
	 * @param row , that was clicked
	 * @param col , that was clicked 
	 */
	public void makeAIMove(int row, int col) {
		// if the slot hasn't been guessed and is not part of the ship
		if ( (player1[row][col].getIsMarkedBlack() == false) && (player1[row][col].getIsGuessed() == false)) {
			// set it as guessed 
			player1[row][col].setGuessed(true);
		 
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,1,2));
			
		}
		
		// if the slot has been guessed and is not part of the ship
		else if ((player1[row][col].getIsMarkedBlack() == false) && (player1[row][col].getIsGuessed() == true)) {
			
				// make another random row and col
				Random newRandom = new Random();
				int randomRow = newRandom.nextInt(10);
				int randomCol = newRandom.nextInt(10);
				
				// keep making a random row and col till it has not been guessed 
				while (player1[randomRow][randomCol].getIsGuessed() == true) {
					randomRow = newRandom.nextInt(10);
					randomCol = newRandom.nextInt(10);
				}
				
				// if the randomized row and column are part of the ship
				if (player1[randomRow][randomCol].getIsMarkedBlack() == true) {
					// set it to false 
					player1[randomRow][randomCol].setIsMarkedBlack(false);
				}
				// marked it as guessed 
				player1[randomRow][randomCol].setGuessed(true);
				
			 
				setChanged();
				notifyObservers(new BattleShipMoveMessage(randomRow,randomCol,2,2));
		}
		
		// if the slot hasn't been guessed and is part of the ship
		else if ((player1[row][col].getIsMarkedBlack() == true) && (player1[row][col].getIsGuessed() == false)) {
				// mark as hit 
				player1[row][col].setIsMarkedBlack(false);
				// set as guessed 
				player1[row][col].setGuessed(true);
			 
				setChanged();
				notifyObservers(new BattleShipMoveMessage(row,col,3,2));
		}
		else {
			 
		}
	}
	
    
    /**
     * This function checks if all of the user's ships have been hit
     * @return true if been hit, false if not been hit 
     */
    public boolean gotAllPlayer1Ships() {
    	    // iterating through user grid
	    	for (int i = 0; i <rowAndColumnSize; i++) {
				for (int j = 0; j < rowAndColumnSize; j++) {
					if (player1[i][j].getIsMarkedBlack()== true) {
						return false;
					}
					
				}
			}
			
			return true;
    }
	
	
     /**
      * This function checks if all of the user's ships have been hit
      * @return true if been hit, false if not been hit 
      */
	   public boolean gotAllPlayer2Ships() {
		    // iterating through the CPU grid
	    	for (int i = 0; i <rowAndColumnSize; i++) {
				for (int j = 0; j < rowAndColumnSize; j++) {
					if (player2[i][j].getIsMarkedBlack() == true) {
						return false;
					}
					
				}
			}
			
			return true;
	    }
 
    
    
}
