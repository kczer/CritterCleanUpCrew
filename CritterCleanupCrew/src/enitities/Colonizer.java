package enitities;

import java.util.Map;

import settables.UFO;
import model.Block;
import model.Field;
import model.GameState;
import model.Position;

public class Colonizer extends Entity {
	private static Map<String, Double> parameters;
	private long timeReached;

	public Colonizer(Position p){
		super(p);
		timeReached=-1; //we haven't reached the target
	}
	
	@Override
	public void loadParameters() {
		parameters = Entity.loadParameters("data/Colonizer.txt");
	}

	@Override
	public String getName() {
		return "Colonizer";
	}
	
	public boolean doAction(GameState g){
		if (target==null){ //if we don't have a target
			obtainTarget(g); //find itself a target
		}
		if (!inRangeOfTarget(g)){ //if we have not reached our target
			moveToTarget(g); //move towards the target
		}
		else{ //else we've reached the target
			if (timeReached==-1){ //if we just arrived
				timeReached=System.currentTimeMillis(); //get the time of arrivals
			}
			if ((System.currentTimeMillis()-timeReached)> //if the time elapsed
				getParameters().get("ColonizingTime")*1000){ //is more than required for colonization, colonize
				hitPoints=-1; //commit suicide.
				
				int x = position.getIntX(); //get the position
				int y = position.getIntY(); //in both coordinates
				
				Block landingBlock = g.getGameField().getAreaBlocks()[x][y]; //get the the landing block
				if (landingBlock.getEntityOnTile()!=null){ //if there's something there, placed while flying to it
					landingBlock.getEntityOnTile().setHitPoints(-1); //kill it! What else can we possibly do? We've squashed it
				}
				
				UFO landed = new UFO(); //the UFO to be placed
				landed.setPosition(new Position(x+0.5, y+0.5));  //give it it's position with the half-ints
				landingBlock.setEntityOnTile(landed); //set the UFO on the block
				g.getPlants().add(landed); //tell the state that we have a UFO
			}
		}
		
		return true;
	}

	/**
	 * Gets itself a target position to go to colonize.
	 */
	private void obtainTarget(GameState g) {
		
		double x=position.getX()+g.getGenerator().nextGaussian()*getParameters().get("RangeToLook"); //calculate x position away
		double y=position.getY()+g.getGenerator().nextGaussian()*getParameters().get("RangeToLook"); //calculate y position away
		
		x = Math.abs(x); //for below zero
		y = Math.abs(y); //for below zero
		if (x>Field.fieldWidth){ //for over
			x= Field.fieldWidth-1;
		}
		if (y>Field.fieldHeight){ //for over
			y=Field.fieldHeight-1;
		}
		Position startingSearch = new Position(x,y); //make a new search
		Position targetPos = g.findLandingPositionInProximity(startingSearch); //get a larget location (sure, used the state method)
		target = new Catterpillar(targetPos); //make an imaginary target
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){
			loadParameters();
		}
		return parameters;
	}

}
