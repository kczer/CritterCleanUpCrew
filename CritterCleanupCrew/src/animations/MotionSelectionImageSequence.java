package animations;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.util.ArrayList;

import views.GameView;
import model.Position;

public class MotionSelectionImageSequence extends AnimationImageSequence {

	public MotionSelectionImageSequence(GameView gv, Position cov) {
		super(gv, cov);
		imageSpeed = 5;
	}

	@Override
	public Position getUpperLeftCorner() {
		double x = iView.twoDToIsoX(centerOfImage.getX(), centerOfImage.getY())+1.*iView.getIsoTileWidth()/2;  //get the center location
		double y = iView.twoDToIsoY(centerOfImage.getX(), centerOfImage.getY()); 
		
		x-=(1.*iView.getIsoTileWidth()/2); //shift up
		y-=(1.*iView.getIsoTileHeight()/2); //shift up
		
		x-=iView.getOverHeadOffsetX(); //offset it
		y-=iView.getOverHeadOffsetY();
		
		return new Position(x, y); //return it
	}

	@Override
	public Position getBottomRightCorner() {
		double x = iView.twoDToIsoX(centerOfImage.getX(), centerOfImage.getY())+1.*iView.getIsoTileWidth()/2; //get the center location
		double y = iView.twoDToIsoY(centerOfImage.getX(), centerOfImage.getY()); 
		
		x+=(1.*iView.getIsoTileWidth()/2); //shift down
		y+=(1.*iView.getIsoTileHeight()/2); //shift down
		
		x-=iView.getOverHeadOffsetX(); //offset it
		y-=iView.getOverHeadOffsetY();
		
		return new Position(x, y); //return it
	}

	@Override
	protected void setUpImageParts() {
		imageParts=new ArrayList<Image>();
		int imageHeight= 512;
		int imageWidth= 1024;
		BufferedImage mainImage = mainView.getImages().get("motionSelectionAnimation");
		for (int i=0;i<4;i++){
			imageParts.add(mainImage.getSubimage(
					imageWidth*i, 0,
					imageWidth, imageHeight)); //add in the image
		}
		
	}

}
