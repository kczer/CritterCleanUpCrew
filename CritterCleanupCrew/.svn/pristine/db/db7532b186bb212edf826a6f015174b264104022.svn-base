package listeners;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import model.Field;
import model.Position;
import views.GameView;
import views.InteractionView;
import views.MinimapView;

public class MinimapMouseListener implements MouseInputListener {

	
	private GameView gameView;
	private InteractionView iView;
	private MinimapView mmView;

	public MinimapMouseListener(GameView gameView){
		this.gameView = gameView;
		this.iView = (InteractionView) gameView.getSubviews().get("Interaction");
		this.mmView = (MinimapView) gameView.getSubviews().get("Minimap");
	}
	
	
	@Override
	public void mouseDragged(MouseEvent event) {
		recenterInteractionView(event);

	}
	
	
	@Override
	public void mousePressed(MouseEvent event) {
		recenterInteractionView(event);

	}
	
	private void recenterInteractionView(MouseEvent event){
		double xPart = 1.*event.getX()/mmView.getWidth(); //get coordinates relative to the map
		double yPart = 1.*event.getY()/mmView.getHeight();
		
		double locationX = Field.fieldWidth*xPart; //the locations on the board
		double locationY = Field.fieldHeight*yPart;
		
		iView.centerAtPosition(new Position(locationX,locationY)); //center the screen
	}
	
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
