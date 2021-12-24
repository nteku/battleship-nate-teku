package model;

import java.io.Serializable;


/**
 * This class is a message that holds a single BattleShip
 * @author Nathan Teku
 *
 */
@SuppressWarnings("serial")
public class BattleShip implements Serializable {

	// row attributes
	private int row1;
	private int row2;
	private int row3;
	
	// column attributes
	private int col1;
	private int col2;
	private int col3;
	
	/**
	 * Constructor that holds the coordinates of a single ship
	 * @param row1 , 1st row
	 * @param col1 , 1st column
	 * @param row2 , 2nd row
	 * @param col2 , 2nd column
	 * @param row3 , 3rd row
	 * @param col3 , 3rd column
	 */
	public BattleShip (int row1,int col1, int row2, int col2, int row3, int col3) {
		
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		
		
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
	}
	
	
	/**
	 * Getter for 1st row
	 * @return row1 value 
	 */
	public int getRow1() {
		return row1;
	}
	
	/**
	 * Getter for 2nd row
	 * @return row2 value 
	 */
	public int getRow2() {
		return row2;
	}
	
	/**
	 * Getter for 3rd row
	 * @return row3 value 
	 */
	public int getRow3() {
		return row3;
	}
	
	/**
	 * Getter for 1st column
	 * @return col1 value 
	 */
	public int getCol1() {
		return col1;
	}
	
	/**
	 * Getter for 2nd column
	 * @return col2 value 
	 */
	public int getCol2() {
		return col2;
	}
	
	/**
	 * Getter for 3rd column
	 * @return col3 value 
	 */
	public int getCol3() {
		return col3;
	}
}
