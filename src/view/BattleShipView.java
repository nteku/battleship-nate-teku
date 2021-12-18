package view;

import java.util.Observable;
import java.util.Observer;

import controller.BattleShipController;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.BattleShipModel;

public class BattleShipView extends Application implements Observer {

	private BorderPane b;
	private GridPane g1;
	private GridPane g2;
	private BattleShipModel model;
	private BattleShipController controller;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		 model = new BattleShipModel();
		 controller = new BattleShipController(model);
		 b = new BorderPane();
		 	
	}
	
	public void initializeTwoGrids() {
		g1 = new GridPane();
		g2 = new GridPane();
		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	 

	
}
