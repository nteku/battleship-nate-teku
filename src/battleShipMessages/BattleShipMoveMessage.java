package battleShipMessages;
import java.io.Serializable;

/**
 * The purpose for this class is to send a Signal to the View of the correct coordinates for the slot
 * @author Nathan Teku
 *
 */
public class BattleShipMoveMessage implements Serializable {

	// attributes include the serialID, row,col, status and the current player
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int status;
	private int currentPlayer;
	
	/**
	 * Constructor is used to send the signal to the View from the Model
	 * @param row , that was clicked
	 * @param col , that was clicked
	 * @param status , state of the slot
	 * @param currentPlayer , which player is making the move 
	 */
	public BattleShipMoveMessage(int row, int col, int status, int currentPlayer) {
		this.row = row;
		this.col = col;
		this.status = status;
		this.currentPlayer = currentPlayer;
	}
	
	/**
	 * This function returns the value of the current player 
	 * @return the currentPlayer value
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * This function returns the value of the row
	 * @return the row value 
	 */
	public int getRow() {
		return row;
	}

	/**
	 * This function returns the value of the column
	 * @return the column value 
	 */
	public int getColumn() {
		return col;
	}

	/**
	 * This function returns the value of the status
	 * @return the status value 
	 */
	public int getStatus() {
		return status;
	}
}
