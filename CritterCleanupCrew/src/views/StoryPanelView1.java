package views;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StoryPanelView1 extends JPanel implements ActionListener {
	ButtonView nextPanel;
	ButtonView previousPanel;
	ButtonView skipToGame;
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	long timePerImage = 250;
	long timeOfStart;
	int currentImage;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public StoryPanelView1(){
		setBackground(Color.WHITE);
		nextPanel = new ButtonView("Next");
		previousPanel = new ButtonView("Previous");
		skipToGame = new ButtonView("Skip Story!");
		
		add(nextPanel);
		add(previousPanel);
		add(skipToGame); 
		
		currentImage=1;
	}
	
	public void paintComponent(Graphics g) {
		
		nextPanel.setLocation(getWidth()-nextPanel.getWidth(), getHeight()-nextPanel.getHeight()); //put it in very corner
		previousPanel.setLocation(0, getHeight()-previousPanel.getHeight()); //put it in the corner
		skipToGame.setLocation((getWidth()-skipToGame.getWidth())/2, getHeight()-skipToGame.getHeight()); //set in middle
		
		currentImage = (int) ((System.currentTimeMillis()-timeOfStart)/timePerImage+1);
		currentImage = currentImage>40?40:currentImage; //cap at 40.
		
		
		Image bg = new ImageIcon("assets/story/Story"+currentImage+".png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight()-nextPanel.getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		repaint(); //ask for a repaint
	}
	
	public boolean requestFocusInWindow(){
		timeOfStart = System.currentTimeMillis(); //get the time when starting the animation
		return super.requestFocusInWindow();
	}

}
