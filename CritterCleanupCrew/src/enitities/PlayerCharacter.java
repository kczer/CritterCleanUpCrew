package enitities;

import java.util.Collection;
import java.util.Map;
import model.Field;



import java.util.Random;

import players.Player;
import weapons.Weapon;
import model.Actionable;
import model.GameState;
import model.Position;
import moves.Move;

public class PlayerCharacter extends Entity{
	
	private Player currentController; //who is currently in control
	private static Collection<Weapon> weapons; //the set of weapons
	private String name;
	private Map<String, Double> parameters;
	private Weapon currentWeapon;
	
	public PlayerCharacter(String name, Player con){
		Random r = new Random();
		this.name = name;
		currentController = con;
		position = new Position (r.nextDouble()*Field.fieldWidth,r.nextDouble()*Field.fieldHeight);
	}

	/**
	 * In-game character name. Choose wisely!
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see Actionable#doAction(GameState)
	 */
	public boolean doAction(GameState g) {
		Move m = currentController.getNextMove(g); //get the next move
		if (m.isValid(g)){ //if the move is valid
			m.makeMove(g); //make the move on the state
		}
		return true;
	}
	
	
	/**
	 * Switches to the weapon with number num.
	 * Returns true if switch was successful, otherwise
	 * false.
	 */
	public boolean switchWeapons(int num){
		return false;
	}

	@Override
	public boolean forceAction(GameState g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){
			loadParameters();
		}
		return parameters;
	}
	
	public void setCurrentWeapon(Weapon w){
		currentWeapon = w;
	}
	
	public Weapon getCurrentWeapon(){
		return currentWeapon;
	}

	public Player getCurrentController() {
		return currentController;
	}

	public void setCurrentController(Player currentController) {
		this.currentController = currentController;
	}

	@Override
	public void loadParameters() {
		parameters = Entity.loadParameters("data/"+name+".txt");
		
	}
	
	
	

}
