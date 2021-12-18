package view;

import java.util.Observable;
import java.util.Observer;

import controller.BattleShipController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.BattleShipModel;

public class BattleShipView extends Application implements Observer {

	private BorderPane b;
	private GridPane g1;
	private GridPane g2;
	private BattleShipModel model;
	private final int rowAndColumnSize = 10;
	private BattleShipController controller;
	private Rectangle player1 [][]= new Rectangle[10][10];
	private Rectangle player2 [][]= new Rectangle [10][10];
	private char tenAlphabets[] = {'A','B','C','D','E','F','G','H','I','J'};
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		 model = new BattleShipModel();
		 controller = new BattleShipController(model);
		 b = new BorderPane();
		 
		 initializeTwoGrids();
		  
		 	b.setPrefHeight(600);
		 	b.setPrefWidth(900);
		 	 
		 
			
		 	Scene scene = new Scene(b);
		 	
		 	Stage stage = new Stage();
		 	stage.setTitle("BattleShip Nathan Teku");
		 	stage.setScene(scene);
		 	stage.show();
	}
	
	public void initializeTwoGrids() {
		g1 = new GridPane();
		g2 = new GridPane();
		
		for (int i = 0; i < rowAndColumnSize;i++) {
			
		
				for (int j = 0; j < rowAndColumnSize; j++) {
					Label letterLabel  = new Label();
					VBox container = new VBox();
					letterLabel.setText(Character.toString(tenAlphabets[j]));
					 
					
					Rectangle r = new Rectangle(25,25);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK); 
					container.getChildren().add(r);
					 
					 player1[i][j] = r;
					 g1.add(container, j, i);
				 
				}
		
		}
	 

		for (int i = 0; i < rowAndColumnSize;i++) {
			
		
				for (int j = 0; j < rowAndColumnSize; j++) {
					Label letterLabel  = new Label();
					VBox container = new VBox();
					letterLabel.setText(Character.toString(tenAlphabets[j]));
					 
					
					Rectangle r = new Rectangle(25,25);
					r.setFill(Color.WHITE);
					r.setStroke(Color.BLACK); 
					container.getChildren().add(r);
					 
					 player2[i][j] = r;
					 g2.add(container, j, i);
				 
				}
		
		}
		
		b.setLeft(g1);
		b.setRight(g2);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	 

	
}
