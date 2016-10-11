package moves;

import model.GameState;
import model.Position;

public class WaterMakingMove implements Move {
	
	private Position position;
	private int cost;
	
	public WaterMakingMove(){
		cost=20;
	};
	
	public WaterMakingMove(Position p){
		position = p;
		cost=20;
	}
	@Override
	public boolean isValid(GameState gameState) {
		if (gameState.getGameField().getAreaBlocks()
			[position.getIntX()][position.getIntY()].isWater()){ //if the target position is water
			return false; //it's not valid
		}
		
		if (gameState.getGameField().getAreaBlocks()
				[position.getIntX()][position.getIntY()].
				getEntityOnTile()!=null){ //if the target position has something on it
			return false; //it's not valid
		}
		
		if (gameState.getMoney()<cost){ //if we can't afford it
			return false;
		}
		return true; //otherwise, we should be fine
	}

	@Override
	public boolean makeMove(GameState gameState) { 
		gameState.getGameField().getAreaBlocks() //get the position
		[position.getIntX()][position.getIntY()].setWater(true); //set it to water
		gameState.setMoney(gameState.getMoney()-cost); //deduct the cost
		return true;
	}

	@Override
	public void setPosition(Position p) {
		position = p;

	}

}
