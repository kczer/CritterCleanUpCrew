package views;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StoryPanelView2 extends JPanel implements ActionListener {
	ButtonView nextPanel;
	ButtonView previousPanel;
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public StoryPanelView2(){
		setBackground(Color.WHITE);
		nextPanel = new ButtonView("Ready to learn how to Save the world?");
		previousPanel = new ButtonView("Previous");
		
		add(nextPanel);
		add(previousPanel);
	}
	
	public void paintComponent(Graphics g) {
		
		nextPanel.setLocation(getWidth()-nextPanel.getWidth(), getHeight()-nextPanel.getHeight()); //put it in very corner
		previousPanel.setLocation(0, getHeight()-previousPanel.getHeight()); //put it in the corner
		
		Image bg = new ImageIcon("assets/Story2.png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight()-nextPanel.getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}