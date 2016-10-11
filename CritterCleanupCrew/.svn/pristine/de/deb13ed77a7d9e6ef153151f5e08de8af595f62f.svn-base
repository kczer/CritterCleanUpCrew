package players;

import java.io.Serializable;

import model.GameState;
import moves.Move;

public interface Player extends Serializable{
	
	/**
	 * Returns the next move that should be executed.
	 * Can return a null value when no moves are
	 * avalaible/desired.
	 */
	public Move getNextMove(GameState gameState);
	
	/**
	 * Returns a unique for every player ID
	 * that allows to distinguish players in-game
	 */
	public String getID();

	
	/**
	 * Used by the controller and view to "put"
	 * a move done by the player into the object, to
	 * later pass to the model for actions.
	 */
	public void setNextMove(Move nextMove);
	
	/**
	 * Returns at least the name of the character which the player
	 * is controlling.
	 */
	public String getPlayerCharacterControlled();
	
	/**
	 * Tell at least the name of the character which the player
	 * should control. Returns true if begins controlling, false if not.
	 */
	public boolean setPlayerCharacterToControl(String nameOfCharacter);
	
	/**
	 * To return whatever we're actually trying to do right now
	 */
	public Move getTargetMove();
	
	
}
