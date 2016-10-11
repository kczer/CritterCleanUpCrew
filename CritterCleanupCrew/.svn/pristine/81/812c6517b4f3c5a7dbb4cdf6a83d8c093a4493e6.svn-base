package views;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TutorialView2 extends JPanel implements ActionListener {
	
	ButtonView previous;
	ButtonView next;
	ButtonView skip;
	JPanel cards;
	GameView mainView;
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public TutorialView2(){
		setBackground(Color.WHITE);
		previous = new ButtonView("Previous");
		next = new ButtonView("Next");
		skip = new ButtonView("Skip!");
	
		
		add(next); //add the buttons in
		add(skip);  
		add(previous);
	}
	//ToDO: Need to maybe add text for how to move a character
	//Adding a tutorialstate of the game for 10 seconds to get a feel for the environment
	//Or we could make a phamplet before they play the game
	//smaller grid
	//make it crazy too, let them put down plants if they want that spawn or hurt really quick!
	public void paintComponent(Graphics g) {
		
		previous.setLocation(0, getHeight()-next.getHeight());
		next.setLocation(getWidth()-next.getWidth(), getHeight()-next.getHeight()); //put it in very corner
		skip.setLocation((getWidth()-skip.getWidth())/2, getHeight()-next.getHeight()); //put it in the center
		
		Image bg = new ImageIcon("assets/secondTutorialScreen.png").getImage();
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight()-next.getHeight(), this);
		// BufferedImage img = mainView.getImages().get("mainMenu");
		// paint the background image and scale it to fill the entire space
		// g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}