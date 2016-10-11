package moves;

import settables.Settable;
import weapons.Weapon;
import enitities.Entity;
import enitities.PlayerCharacter;
import model.Actionable;
import model.GameState;
import model.Position;

public class AttackMove implements Move {
	private Actionable target;
	private Weapon attackingWeapon;
	private PlayerCharacter attacker;
	
	public AttackMove(Actionable target){
		this.target = target;
		attacker = null;
		attackingWeapon = null;
	}
	
	public AttackMove(Actionable target, PlayerCharacter pc){
		this.target = target;
		attacker = pc;
		attackingWeapon = pc.getCurrentWeapon();
	}

	/**
	 * Can this weapon attack the selected entity (in range,
	 * of correct type).
	 */
	public boolean isValid(GameState gameState) {
		if (!(attackingWeapon.getParameters().get("Range")> //if not in range
			attacker.getPosition().distanceTo(target.getPosition()))){ //is greater than the distance between them
			return false;
		}
		
		if (attackingWeapon.getParameters().get("ReloadTime")+attackingWeapon.getTimeOfLastFire()> //if time of next attack is less than
			System.currentTimeMillis()){ //than the current time
			return false; //since we haven't reloaded yet
		}
		
		if (target instanceof Settable){ //if we're trying to attack an enemy
			if (!checkSettableAttack(gameState)){ //we can't attack
				return false; //decline
			}
		}
		
		return true; //otherwise we've passed the tests
	}
	/**
	 * Checks if we can attack this enemy building. Returns true
	 * if we can.
	 */
	private boolean checkSettableAttack(GameState gameState) {
		int nativeCounter=0;
		for (int i=-1;i<2;i++){ //check direct radius
			for (int j=-1;j<2;j++){
				try{
					Settable onTile = gameState.getGameField().getAreaBlocks() //the field
							[target.getPosition().getIntX()+i][target.getPosition().getIntY()+j] //the position relative
									.getEntityOnTile(); //the settable on the tile
					
					if(onTile!=null&&onTile.getNativeness()==1){  //if there is a thing there AND it's native
						nativeCounter++; //count the natives
					}
				}
				catch(ArrayIndexOutOfBoundsException e){ //lazy bounds checking
					//well we knew it could happen
				}
				
			}
		}
		return nativeCounter>=2; //if we have 2 or more- can attack
	}

	/**
	 * Checks if we can attack a specified enemy building. Returns true
	 * if we can.
	 */
	public static boolean checkSettableAttack(GameState gameState, Actionable act) {
		int nativeCounter=0;
		for (int i=-1;i<2;i++){ //check direct radius
			for (int j=-1;j<2;j++){
				try{
					Settable onTile = gameState.getGameField().getAreaBlocks() //the field
							[act.getPosition().getIntX()+i][act.getPosition().getIntY()+j] //the position relative
									.getEntityOnTile(); //the settable on the tile
					
					if(onTile!=null&&onTile.getNativeness()==1){  //if there is a thing there AND it's native
						nativeCounter++; //count the natives
					}
				}
				catch(ArrayIndexOutOfBoundsException e){ //lazy bounds checking
					//well we knew it could happen
				}
			}
		}
		return nativeCounter>=2; //if we have 3 or more- can attack
	}
	
	/**
	 * Attack the selected entity, do some damage.
	 */
	public boolean makeMove(GameState gameState) {
		double damage = attackingWeapon.getParameters().get("BaseDamage")+//base damage
				attackingWeapon.getParameters().get("DamageSpread")* //the spread
				gameState.getGenerator().nextGaussian(); //the random
		if (target instanceof Entity){ //if we're attacking an entity
			attackEntity(gameState, damage); //use this method
		}
		else{ //target instanceof Settable
			attackSettable(gameState, damage);
		}
		attackingWeapon.setTimeOfLastFire(System.currentTimeMillis()); //we've just fired
		return true;
	}

	@Override
	public void setPosition(Position p) {
		// TODO Auto-generated method stub
		
	}

	public Actionable getTarget() {
		return target;
	}
	
	public void setTarget(Actionable tar) {
		target = tar;
	}

	public Weapon getAttackingWeapon() {
		return attackingWeapon;
	}

	public PlayerCharacter getAttacker() {
		return attacker;
	}
	
	/**
	 * Used for attacking an Entity. Specify damage dealt
	 */
	private void attackEntity(GameState gameState, double damage){
		for (Entity each:gameState.getEntities()){
			if (!(each.isFriendly()&&attackingWeapon.getParameters().get("HitFriendly")==0)){ //unless we don't hit friendlies and it is one, attack
				if (each.getPosition().distanceTo(target.getPosition())< //if the distance from the target to any near entities
						attackingWeapon.getParameters().get("BlastRadius")){ //is less than the blast radius of this baby
					each.setHitPoints(each.getHitPoints()- //the hitpoints to the old
							damage); //minus the damage dealt
				}
			}
		}
	}
	/**
	 * Used for attacking Settables. Specify damage dealt.
	 */

	private void attackSettable(GameState gameState, double damage) {
		target.setHitPoints(target.getHitPoints()-damage); //just reduce hp
		attackEntity(gameState, damage/2); //atack the surrounding entites for half damage, if you can
	}
	

}
