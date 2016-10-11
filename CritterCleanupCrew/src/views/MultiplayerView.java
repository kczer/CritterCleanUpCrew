package views;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MultiplayerView extends JPanel implements ActionListener {
	
	ButtonView previousPanel;
	ButtonView joinLobby;
	ButtonView createLobby;
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public MultiplayerView(){
		setBackground(Color.WHITE);
		joinLobby = new ButtonView("Join!");
		previousPanel = new ButtonView("Back");
		createLobby = new ButtonView("CreateLobby");

		add(joinLobby);
		add(previousPanel);
		add(createLobby);
	}
	
	public void paintComponent(Graphics g) {
		
		previousPanel.setLocation(0, getHeight()-previousPanel.getHeight());
		joinLobby.setLocation(getWidth()-joinLobby.getWidth(), getHeight()-joinLobby.getHeight()); //put it in very corner
		createLobby.setLocation((getWidth()-createLobby.getWidth())/2, getHeight()-createLobby.getHeight()); //put it in the center
		
		Image bg = new ImageIcon("assets/Multiplayer.png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}