package model;

import settables.Settable;

public class Block implements java.io.Serializable{
	
	private Settable entityOnTile;
	private double elavation;
	private boolean isWater;

	/**
	 * No arg constructor. Elevation is 0. Not Water.
	 * Has no enitity of tile (null).
	 */
	public Block(){
		elavation=0;
		isWater=false;
		entityOnTile=null;
	}

	/**
	 * Activates the Settable that is on the tile to
	 * edit the GameState given (usually is "itself" or
	 * a copy). Returns true if anything was changed, false
	 * when nothing changes (or nothing to activate).
	 * 
	 */
	public boolean activateSettable(GameState gameState){
		return entityOnTile.doAction(gameState);
	}

	public Settable getEntityOnTile() {
		return entityOnTile;
	}

	public double getElavation() {
		return elavation;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setEntityOnTile(Settable entityOnTile) {
		this.entityOnTile = entityOnTile;
	}

	public void setElavation(double elavation) {
		this.elavation = elavation;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}


}
