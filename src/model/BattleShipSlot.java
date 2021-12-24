package model;

/**
 * This class represents the state of a slot in the Grid
 * @author Nathan Teku
 *
 */
public class BattleShipSlot {

	// attributes include isGuessed and isMarkedBlack
	private boolean isGuessed;
	private boolean isMarkedBlack;
	
	/**
	 * Construct initializes default values of attributes
	 */
	public BattleShipSlot () {
		isGuessed = false;
		isMarkedBlack = false;
	}
	
	/**
	 * Setter for guess value
	 * @param guess value
	 */
	public void setGuessed(boolean guess) {
		this.isGuessed = guess;
	}
	
	/**
	 * Getter for isGuessed value 
	 * @return isGuessed value
	 */
	public boolean getIsGuessed() {
		return isGuessed;
	}
	
	/**
	 * Setter for isMarkedBlack value 
	 * @param markedBlack value 
	 */
	public void setIsMarkedBlack(boolean markedBlack) {
		this.isMarkedBlack = markedBlack;
	}
	
	/**
	 * Getter for isMarkedBlack value 
	 * @return isMarkedBack value 
	 */
	public boolean getIsMarkedBlack() {
		return isMarkedBlack;
	}
}
