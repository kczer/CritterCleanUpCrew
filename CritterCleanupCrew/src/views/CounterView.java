package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CounterView extends JPanel {
	GameView mainView;
	
	public CounterView(GameView mainView){
		super();
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.mainView = mainView;
	}
	

}
