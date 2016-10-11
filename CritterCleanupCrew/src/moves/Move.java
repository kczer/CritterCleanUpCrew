package moves;

import model.GameState;
import model.Position;

public interface Move extends java.io.Serializable{
	
	/**
	 * Checks if the Move is a valid one for the 
	 * current state. If it is- it should produce
	 * well-defined, deterministic behavior. Return
	 * false if the move cannot be executed or has no
	 * meaning.
	 */
	public boolean isValid(GameState gameState);
	
	/**
	 * Executes the move on the state, therefore 
	 * changing it. This is where the game actually
	 * happens and progresses- it changes the static 
	 * state. 
	 * Returns true if successful, and it should always 
	 * return true, since you checked if the move is valid,
	 * right? Always check, because makeMove does not.
	 */
	public boolean makeMove(GameState gameState);
	
	/**
	 * Sets the position for this move, where should it be executed.
	 * Since position is there for almost every move, this is a requirement 
	 * in general
	 */
	public void setPosition(Position p);
}
