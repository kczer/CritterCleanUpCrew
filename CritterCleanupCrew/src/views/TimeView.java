package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;








import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameState;

public class TimeView extends JPanel {
	GameView mainView; 
	JLabel timeLabel = new JLabel(); 
	Timer timer;
        // to make sure it doesn't wait one second at the start  
     	public TimeView(GameView mainView){
		super();
		this.setOpaque(true);
		this.mainView = mainView;
		this.add(timeLabel);
	}
	 
	
	public void paint(Graphics g){
		super.paint(g);
		
		int pictureWidth =40;
		Image toDraw;
		
		double timePassed = System.currentTimeMillis()-
				mainView.getCurrentGame().getState().getTimeOfStateBeginning();
		double percent;
		Color one;
		if (timePassed<GameState.BUILD_TIME){
			timeLabel.setText("BUILD");
			percent = timePassed/GameState.BUILD_TIME;
			one = Color.blue;
			toDraw = mainView.getImages().get("Hammer");
		}
		else{
			timeLabel.setText("ATTACK");
			percent = timePassed/GameState.PLAY_TIME;
			one=Color.red;
			toDraw = mainView.getImages().get("Sword");
		}
		
		percent=1-percent;
		g.drawImage(toDraw, 
				0,0,
				pictureWidth, getHeight(),
				null);
		double barWidth = (getWidth()-pictureWidth) * percent;
		g.setColor(one); //sets color of rectangle to red, takes up the whole bar
		g.fillRect(pictureWidth, 0, getWidth(), getHeight());
		
		Color greenColor = makeGreenColor(percent); //the color for the "green" bar
		
		g.setColor(greenColor); //sets color green
		g.drawRect(pictureWidth, 0,(int) barWidth, getHeight()); //draw the outline of a rectangle that is green
		g.fillRect(pictureWidth, 0,(int) barWidth, getHeight()); //however fill it with green in scale to happiness, see *HealthBarWidth.
		
	}
	
	private Color makeGreenColor(double percent) {
		int green=255; //there is default 255 of both
		int red = 255;
		if (percent>0.5){ //if we have more than a half, 
			red*=(1-percent)/0.5; //reduce the number of red
		}
		else{
			green*=percent/0.5; //reduce the number of green
		}
		return new Color(red, green, 0); //return the color
	}     

}