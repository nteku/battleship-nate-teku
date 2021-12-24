package controller;

import model.BattleShipModel;

/**
 * This class is the Controller for the BattleShip Game
 * @author Nathan Teku
 *
 */
public class BattleShipController {

	// attribute for Model object
	private BattleShipModel m;
	
	/**
	 * Constructors holds a copy of the Model object
	 * @param model from the BattleShipModel class
	 */
	public BattleShipController(BattleShipModel model) {
		this.m = model;
	}
	
	/**
	 * This function calls the function to assign the ships for the user and the CPU
	 */
	public void assignShips() {
		m.assigningShips();
	}
	
	/**
	 * This function calls the function to make the move for the user
	 * @param row , that was clicked on 
	 * @param col , that was clicked on
	 */
	public void makingMove(int row, int col) {
		m.makeMove(row,col);
	}
	
	/**
	 * This function calls the function to make the move for the AI
	 * @param row , that was clicked on
	 * @param col , that was clicked on
	 */
	public void makingAIMove(int row, int col) {
		m.makeAIMove(row, col);
	}
}
