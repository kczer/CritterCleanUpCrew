package moves;


import enitities.PlayerCharacter;
import model.Field;
import model.GameState;
import model.Position;

public class MotionMove implements Move {
	
	private Position newPosition;
	private PlayerCharacter moved;
	
	public MotionMove(Position nPosition){
		newPosition = nPosition;
	}
	
	public MotionMove(Position nPosition, PlayerCharacter moved){
		newPosition = nPosition;
		this.moved = moved;
	}

	/**
	 * Can the player move there?
	 */
	public boolean isValid(GameState gameState) {
		if (newPosition.getX()<0|| //if hit below
				newPosition.getX()>Field.fieldWidth) //or hit above
			return false; //then the move is not valid
		
		//same for Y
		if (newPosition.getY()<0|| //if hit below
				newPosition.getY()>Field.fieldWidth) //or hit above
			return false; //then the move is not valid
		return true; //hell yeah //FIXME
	}

	/**
	 * Move the plater to the specified location.
	 */
	public boolean makeMove(GameState gameState) {
		moved.setPosition(newPosition);
		return true;
	}
	
	public Position getPosition(){
		return newPosition;
	}

	@Override
	public void setPosition(Position p) {
		newPosition = p;
		
	}
	
	

}
