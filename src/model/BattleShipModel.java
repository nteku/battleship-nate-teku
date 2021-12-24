package model;

import java.util.Observable;
import java.util.Random;
import java.io.Serializable;

import battleShipMessages.BattleShipMoveMessage;
import javafx.scene.paint.Color;

public class BattleShipModel extends Observable {

	private final int rowAndColumnSize = 10;
	private BattleShipSlot player1 [][];
	private BattleShipSlot player2 [][];
	private static int shipsAssigned;
	
	public BattleShipModel () {
		
		player1 = new BattleShipSlot [rowAndColumnSize][rowAndColumnSize];
		player2 = new BattleShipSlot [rowAndColumnSize][rowAndColumnSize];
		
		
		for (int i = 0; i <rowAndColumnSize; i++) {
			
			for (int j = 0; j < rowAndColumnSize; j++) {
				BattleShipSlot slot1 = new BattleShipSlot();
				BattleShipSlot slot2 = new BattleShipSlot();
				
				player1[i][j] = slot1;
				player2[i][j] = slot2;
			}
		}
		setChanged();
		notifyObservers(new GameResetSignal());
		shipsAssigned = 0;
	}
	
	public void assigningShips() {
		
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
		else {
			Random random1 = new Random();
			int randomRow= random1.nextInt(8);
			int randomColumn = random1.nextInt(8);
			player2[randomRow][randomColumn].setIsMarkedBlack(true);
			player2[randomRow][randomColumn + 1].setIsMarkedBlack(true);
			player2[randomRow][randomColumn + 2].setIsMarkedBlack(true);
			
			shipsAssigned++; 
			setChanged();
			//notifyObservers(new BattleShip (randomRow,randomColumn,randomRow,randomColumn + 1, randomRow, randomColumn + 2));
		}
	}
	
	public void makeMove(int row, int col) {
		
		if ( (player2[row][col].getIsMarkedBlack() == false) && (player2[row][col].getIsGuessed() == false)) {
			player2[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,1,1));
			
		}
		else if ((player2[row][col].getIsMarkedBlack() == false) && (player2[row][col].getIsGuessed() == true)) {
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,2,1));
		}
		
		else if ((player2[row][col].getIsMarkedBlack() == true) && (player2[row][col].getIsGuessed() == false)) {
			player2[row][col].setIsMarkedBlack(false);
			player2[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,3,1));
		}
		else {
			 
		}
	}
	
	public void makeAIMove(int row, int col) {
		if ( (player1[row][col].getIsMarkedBlack() == false) && (player1[row][col].getIsGuessed() == false)) {
			player1[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,1,2));
			
		}
		else if ((player1[row][col].getIsMarkedBlack() == false) && (player1[row][col].getIsGuessed() == true)) {
			
			Random newRandom = new Random();
			int randomRow = newRandom.nextInt(10);
			int randomCol = newRandom.nextInt(10);
			
			while (player1[randomRow][randomCol].getIsGuessed() == true) {
				randomRow = newRandom.nextInt(10);
				randomCol = newRandom.nextInt(10);
			}
			setChanged();
			notifyObservers(new BattleShipMoveMessage(randomRow,randomCol,2,2));
		}
		
		else if ((player1[row][col].getIsMarkedBlack() == true) && (player1[row][col].getIsGuessed() == false)) {
			player1[row][col].setIsMarkedBlack(false);
			player1[row][col].setGuessed(true);
			setChanged();
			notifyObservers(new BattleShipMoveMessage(row,col,3,2));
		}
		else {
			 
		}
	}
	
    
    
    public boolean gotAllPlayer1Ships() {
    	for (int i = 0; i <rowAndColumnSize; i++) {
			for (int j = 0; j < rowAndColumnSize; j++) {
				if (player1[i][j].getIsMarkedBlack()== true) {
					return false;
				}
				
			}
		}
		
		return true;
    }
	
	
	
	   public boolean gotAllPlayer2Ships() {
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
