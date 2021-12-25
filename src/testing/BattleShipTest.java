package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleShipMessages.BattleShipMoveMessage;
import controller.BattleShipController;
import model.BattleShip;
import model.BattleShipModel;

/**
 * Unit test cases for BattleShip Game 
 * @author Nathan Teku
 *
 */
class BattleShipTest {

	@Test
	void test() {
	 
		// created model and controller 
	   BattleShipModel model = new BattleShipModel();
	   BattleShipController controller = new BattleShipController(model);
	   
	   
	   // testing the assignShips method
	   for (int i =0; i < 6; i ++) {
		   controller.assignShips();
	   }
	   
	   // testing the makingMove method
	   controller.makingMove(2, 2);
	   controller.makingMove(1, 4);
	   controller.makingMove(1,4);
	   
	   // testing the makingAIMove method
	   controller.makingAIMove(2, 2);
	   controller.makingAIMove(1, 4);
	   controller.makingAIMove(1,4);
	   
	  // testing BattleShipMoveMessage class
	  BattleShipMoveMessage m = new BattleShipMoveMessage(1,1,1,1);
	  assertEquals(1, m.getColumn());
	  assertEquals(1, m.getCurrentPlayer() );
	  assertEquals(1,m.getRow());
	  assertEquals(1,m.getStatus());
	   
	  // testing BattleShip class 
	  BattleShip b = new BattleShip(1,1,1,1,1,1);
	  assertEquals(1, b.getCol1());
	  assertEquals(1,b.getCol2());
	  assertEquals(1,b.getCol3());
	  assertEquals(1,b.getRow1());
	  assertEquals(1,b.getRow2());
	  assertEquals(1,b.getRow3());
	   
	  // creating new model and new controller 
	   BattleShipModel newModel = new BattleShipModel();
	   BattleShipController newController = new BattleShipController(newModel);
	   
	   // assigning ships for both user and CPU
	   for(int i = 0; i < 6; i++) {
		   newController.assignShips();
	   }
	   
	  
	   
	   // testing the gotAllPlayer1Ships and gotAllPlayer2Ships at first 
	  assertFalse(newModel.gotAllPlayer1Ships());
	  assertFalse(newModel.gotAllPlayer2Ships());
	   
	   // testing the gotAllPlayer1Ships() method and the gotAllPlayer2Ships() method 
	   for (int i =0; i < 10; i++) {
			   for(int j = 0; j < 10; j++) {
				   
				   newController.makingMove(i, j);
				   newController.makingAIMove(i, j);
			   }
		   
		   
	   }
	   
	   assertTrue(newModel.gotAllPlayer1Ships());
	   assertTrue(newModel.gotAllPlayer2Ships());
	   
	}

}
