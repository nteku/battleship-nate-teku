package model;

import java.util.Observable;

public class BattleShipModel extends Observable {

	private final int rowAndColumnSize = 10;
	private BattleShipSlot player1 [][];
	private BattleShipSlot player2 [][];
	
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
	}
	
	public void assigningShips() {
		
		player1[0][0].setIsMarkedBlack(true);
		player1[0][1].setIsMarkedBlack(true);
		
		
		setChanged();
		notifyObservers();
	}
	
	public void makeMove(int row, int col) {
		
	}
	
	
}
