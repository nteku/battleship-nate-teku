package model;

public class BattleShipSlot {

	private boolean isGuessed;
	private boolean isMarkedBlack;
	
	public BattleShipSlot () {
		isGuessed = false;
		isMarkedBlack = false;
	}
	
	public void setGuessed(boolean guess) {
		this.isGuessed = guess;
	}
	
	public boolean getIsGuessed() {
		return isGuessed;
	}
	
	
	public void setIsMarkedBlack(boolean markedBlack) {
		this.isMarkedBlack = markedBlack;
	}
	
	public boolean getIsMarkedBlack() {
		return isMarkedBlack;
	}
}
