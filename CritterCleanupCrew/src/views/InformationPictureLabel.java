package views;

import java.awt.Graphics;




import java.awt.Image;




import javax.swing.JLabel;

class InformationPictureLabel extends JLabel {
	public Image toDraw;
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(
				toDraw, //draw the image
				0,0, // start at edge
				getWidth(),getHeight(), //the whole way
				null); //no observer
				
	}
	
	
}
