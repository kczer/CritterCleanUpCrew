package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EndGameView extends JPanel implements ActionListener {
	
	ButtonView mainMenu;
	
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public EndGameView(){
		setBackground(Color.WHITE);
		mainMenu = new ButtonView("MainMenu");
		
		JPanel cardPanel = new JPanel(); // cardlayout panel
		cardPanel.setLayout(new GridLayout(4, 1, 0, screenHeight / 100));
		cardPanel.add(mainMenu);
		add(cardPanel);
	}
	
	public void paintComponent(Graphics g) {
		Image bg = new ImageIcon("assets/EndGameScreen.png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}