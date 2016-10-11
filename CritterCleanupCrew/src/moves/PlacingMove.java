package moves;

import settables.Settable;
import model.Block;
import model.GameState;
import model.Position;

public class PlacingMove implements Move {
	
	private Position place; //where it's being placed
	private Block blockPlace; //where it's being placed
	private Settable plant; //what's being placed
	
	public PlacingMove(Settable ent, Position pos){
		plant = ent;
		place = pos;
	}

	/**
	 * Can the Settable (plant) be placed at the 
	 * desired location (in the Block)? 
	 */
	public boolean isValid(GameState gameState) {
		int x = (int)place.getX(); //get integer coordinates
		int y = (int)place.getY();
		try{
			if (gameState.getGameField().getAreaBlocks()[x][y].isWater()){ //can't place on water
				return false;
			}
			if (gameState.getGameField().getAreaBlocks()[x][y].getEntityOnTile()!=null){ //can't place on taken
				return false;
			}
			if (gameState.getMoney()<plant.getCost()){ //if not enough money
				return false;
			}
		}catch(IndexOutOfBoundsException e){return false;} //if we are out of bounds, we can't place there.
		return true;
	}

	/**
	 * If so (you checked it), place it in the block
	 * using the Position.
	 */
	public boolean makeMove(GameState gameState) {
		//System.out.print("We're here");
		int x = (int)place.getX(); //get integer coordinates
		int y = (int)place.getY(); 
		gameState.getGameField().getAreaBlocks()[x][y].setEntityOnTile(plant); //place it on field.
		gameState.getPlants().add(plant); //add plant to collections
		plant.setPosition(new Position(x+0.5, y+0.5)); //set the plant its position. Make int and shift by 0.5
		gameState.setMoney(gameState.getMoney()-plant.getCost()); //take away money
		
		Integer prev = gameState.getPlacedPlantCount().get(plant.getName()); //how many times has this plant been put in before?
		if (prev==null){ //if hasn't
			gameState.getPlacedPlantCount().put(plant.getName(), 1); //placed once
		}
		else{ //has been put in
			gameState.getPlacedPlantCount().put(plant.getName(), prev+1); //placed once more
		}
		return true;
	}

	@Override
	public void setPosition(Position p) {
		place = p;
		
	}
	
	public Settable getPlant(){
		return plant;
	}

}
