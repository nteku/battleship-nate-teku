package controller;

import model.BattleShipModel;

public class BattleShipController {

	private BattleShipModel m;
	
	public BattleShipController(BattleShipModel model) {
		this.m = model;
	}
	
	
	public void assignShips() {
		m.assigningShips();
	}
	
	public void makingMove(int row, int col) {
		m.makeMove(row,col);
	}
}
