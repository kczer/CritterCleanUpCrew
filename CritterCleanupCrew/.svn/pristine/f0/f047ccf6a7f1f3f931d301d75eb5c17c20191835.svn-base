package animations;

import java.awt.Image;
import java.util.ArrayList;

import model.Position;
import views.GameView;
import views.InteractionView;

public abstract class AnimationImageSequence {
	protected ArrayList<Image> imageParts;
	protected int imageSpeed;
	protected int ticksPassed;
	protected GameView mainView;
	protected InteractionView iView;
	protected Position centerOfImage;
	
	public AnimationImageSequence(GameView gv, Position cov){
		mainView=gv;
		centerOfImage =  cov;
		ticksPassed=-1; //before first
		iView = (InteractionView)mainView.getSubviews().get("Interaction");
		setUpImageParts();
	}
	
	/**
	 * Returns the next image in the sequence of animations.
	 * Returns null after last image has been sent.
	 */
	public Image getNextImage(){
		ticksPassed++; //a tick has passed.
		int loc = ticksPassed/imageSpeed; //where we are in the sequence
		
		if (loc<imageParts.size()){ //if we can still get images
			return imageParts.get(loc); //return this image
		}
		else{
			return null; //nope
		}
	};
	
	/**
	 * Used to set up your image parts if you have any.
	 */
	protected abstract void setUpImageParts();
	
	/**
	 * Tells the upper left-hand corner of where to draw the image
	 */
	public abstract Position getUpperLeftCorner();
	
	/**
	 * Tells the bottom right-hand corner of where to draw the image
	 */
	public abstract Position getBottomRightCorner();
}
