package model;

import java.io.Serializable;

public class BattleShip implements Serializable {

	private int row1;
	private int row2;
	private int row3;
	
	
	private int col1;
	private int col2;
	private int col3;
	
	public BattleShip (int row1,int col1, int row2, int col2, int row3, int col3) {
		
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
		
		
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
	}
	
	
	
	public int getRow1() {
		return row1;
	}
	
	public int getRow2() {
		return row2;
	}
	
	public int getRow3() {
		return row3;
	}
	
	public int getCol1() {
		return col1;
	}
	
	public int getCol2() {
		return col2;
	}
	
	public int getCol3() {
		return col3;
	}
}
