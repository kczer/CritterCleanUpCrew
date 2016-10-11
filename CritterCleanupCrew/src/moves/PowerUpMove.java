package moves;

import powerups.PowerUp;
import model.GameState;
import model.Position;

public class PowerUpMove implements Move {
	
	PowerUp usedPowerUp; //powerup being used
	Position place; //where being used

	/**
	 * Can the PowerUp be launched at that location?
	 * Usually true, unless the PowerUp is very position
	 * dependent.
	 */
	public boolean isValid(GameState gameState) {
		return false;
	}

	/**
	 * Unleash hell.
	 */
	public boolean makeMove(GameState gameState) {
		return false;
	}

	@Override
	public void setPosition(Position p) {
		place = p;
		
	}

}
