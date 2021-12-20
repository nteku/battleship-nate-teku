package battleShipMessages;
import java.io.Serializable;

public class BattleShipMoveMessage implements Serializable {

	public static int RED = 1;
	public static int BLACK = 2;
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private int status;
	
	
	public BattleShipMoveMessage(int row, int col, int status) {
		this.row = row;
		this.col = col;
		this.status = status;
	}
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return col;
	}

	public int getStatus() {
		return status;
	}
}
