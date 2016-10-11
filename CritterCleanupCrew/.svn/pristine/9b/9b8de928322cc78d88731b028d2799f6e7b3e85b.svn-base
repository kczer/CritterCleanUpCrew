package moves;

import enitities.Entity;
import settables.Settable;
import model.Block;
import model.GameState;
import model.Position;

public class DigUpMove implements Move{

	Position digUpPosition;
	Block digBlock;

	public DigUpMove(){};
	
	public DigUpMove(Position p){
		digUpPosition = p;
	}
	public boolean isValid(GameState gameState) {
		int x = digUpPosition.getIntX();
		int y = digUpPosition.getIntY();
		
		if (gameState.getGameField().getAreaBlocks()[x][y]//get block
				.getEntityOnTile()!=null){ //if there's something on it
			return true;
		}
		return false;
	}

	@Override
	public boolean makeMove(GameState gameState) {
		int x = digUpPosition.getIntX();
		int y = digUpPosition.getIntY();
		
		
		Settable s = gameState.getGameField().getAreaBlocks()[x][y].getEntityOnTile(); //get previous
		gameState.setMoney(gameState.getMoney()+s.getParameters().get("DigUpMoney").intValue()); //add money for removal
		gameState.getGameField().getAreaBlocks()[x][y].setEntityOnTile(null); //keep nothing on tile.
		
		gameState.getPlants().remove(s); //remove from plants
		
		for (Entity i :gameState.getEntities()){ //reconsider target
			i.forceAction(gameState);
		}
		
		return true;
	}
	
	public void setPosition(Position p){
		digUpPosition = p;
	}

}
