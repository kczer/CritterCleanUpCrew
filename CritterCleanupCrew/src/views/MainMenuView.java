package views;


import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.ImageIcon;





import javax.swing.JPanel;

public class MainMenuView extends JPanel implements ActionListener {
	ButtonView startGame;
	ButtonView multiplayer;
	ButtonView highscores;
	ButtonView exit;
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	// ================
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// ===================
	public MainMenuView() {
		setBackground(Color.WHITE);
		startGame = new ButtonView("Start Game");
		multiplayer = new ButtonView("Multiplayer");
		highscores = new ButtonView("Highscores");
		exit = new ButtonView("Exit");

		add(startGame); //throw in the buttons
		add(multiplayer);
		add(highscores);
		add(exit);

	}

	@Override
	public void paintComponent(Graphics g) {
		
		startGame.setLocation((getWidth()-startGame.getWidth())/2, 450);
		multiplayer.setLocation((getWidth()-multiplayer.getWidth())/2, 480);
		highscores.setLocation((getWidth()-highscores.getWidth())/2, 510);
		exit.setLocation((getWidth()-exit.getWidth())/2, 540);
		
		Image bg = new ImageIcon("assets/mainMenu.png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}