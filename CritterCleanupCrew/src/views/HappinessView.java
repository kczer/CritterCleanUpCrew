package views;

import java.awt.Color;
import java.awt.Graphics;




import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;



public class HappinessView extends JPanel {
	GameView mainView;
	double happiness = 0.5;
	double healthBarX = 0;
	double healthBarY = 0;
	double healthWidth = 650;
	double healthBarWidth = healthWidth * happiness; 
	double healthBarHeight = 1000.00;
	
	int imageWidth=40;
	
	/*
	 * float barX = (entityX + entityWidth/2f) - barWidth/2f;
	 * float barY = entityY - BAR_SPACING;
	 * this is for putting health bars on mob , just a general idea
	 * TODO: Place health bars over our entities and characters
	 */
	public HappinessView(GameView mainView){
		super();
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.mainView = mainView;
		this.happiness = mainView.getCurrentGame().getState().getHappiness();
		JLabel happinessLabel = new JLabel("Happiness: " + (happiness*100)); 
		this.add(happinessLabel);
		
		
		
	}
	
	public void paint(Graphics g) {
		
		//paint health bar
		happiness = mainView.getCurrentGame().getState().getHappiness();
		healthBarWidth = getWidth() * happiness;
		g.setColor(Color.RED); //sets color of rectangle to red, takes up the whole bar
		g.fillRect(imageWidth+(int)healthBarX, (int)healthBarY, getWidth()-imageWidth, (int)healthBarHeight);
		g.setColor(makeGreenColor(happiness)); //sets color green
		//g.drawRect((int)healthBarX, (int)healthBarY, (int) healthBarWidth, (int)healthBarHeight); //draw the outline of a rectangle that is green
		g.fillRect(imageWidth+(int)healthBarX, (int)healthBarY, (int) healthBarWidth-imageWidth, (int)healthBarHeight); //however fill it with green in scale to happiness, see *HealthBarWidth.
		
		//draw face
		BufferedImage face = getFaceImage(happiness); //the the face image from the happiness
		g.drawImage(face, //the face that we got
				0,0, //corner
				imageWidth, getHeight(), //image size and view size
				null); //no observer
	}
	/*public void paint(Graphics g){
		super.paint(g);
		
		try{
			BufferedImage b = ImageIO.read(new File("assets/.png"));
			System.out.print(b);
			g.fillRect((int)healthBarX, (int)healthBarY, (int)healthBarWidth, (int)healthBarHeight);
			g.setColor(Color.pink);
			g.drawImage(b, 0, 0, Color.gray, this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void MCompononent {
		public void paint(Graphics g) {
			
			g.drawRect((int)healthBarX, (int)healthBarY, (int)healthWidth, (int)healthBarHeight);
			g.setColor(Color.RED);
			g.drawRect((int)healthBarX, (int)healthBarY, (int)healthBarWidth, (int)healthBarHeight);
			g.setColor(Color.GREEN);
		}
	} 
	*/
	
	private BufferedImage getFaceImage(double h) {
		if (h<0.33){
			return mainView.getImages().get("sad");
		}
		else if (h<0.66){
			return mainView.getImages().get("neutral");
		}
		else{
			return mainView.getImages().get("happy");
		}
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

 

