package settables;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import enitities.Entity;
import model.Actionable;
import model.Block;
import model.GameState;
import model.Position;

public abstract class Settable implements Actionable {
	protected double hitPoints; // number of hitpoints (remaining)
	protected Position position; // position of plant on field
	protected int cost; // cost to plant
	protected double totalTime; // total time alive
	protected double timeOfNextAction; // self explanatory, for spawning
										// wildlife
	protected int nativeness; // -1 - invasive, 0 - non native, 1- native
	protected boolean changedHappiness; // if it has done its happiness impact
										// yet
	protected String fileName;

	public static final int NATIVE = 1;
	public static final int NON_NATIVE = 0;
	public static final int INVASIVE = -1;

	public Settable() {
		cost = getParameters().get("Cost").intValue();
		changedHappiness = false;
		hitPoints = getParameters().get("MaxHP");
		totalTime = 0;
		timeOfNextAction = getParameters().get("SpawnTime"); // initial
																// non-random
																// spawn time
		nativeness = getParameters().get("Native").intValue();
	}

	/**
	 * Returns the name of this Settable in card form This name appears in the
	 * game UI itself, so choose wisely.
	 */

	public abstract String getName();

	/**
	 * Tells how often this settable can do its stuff.
	 */
	public abstract int getCycleTime();

	/**
	 * Returns position of settable
	 * 
	 * @return
	 */
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position pos) {
		position = pos;
	}

	public int getCost() {
		return cost;
	}

	/**
	 * Loads the parameters in a settable-specific way and returns them in a
	 * map.
	 */
	public static Map<String, Double> loadParameters(String filename) {
		Scanner inFile;
		Map<String, Double> parameters = new HashMap<String, Double>();
		try {
			inFile = new Scanner(new File(filename));
			while (inFile.hasNextLine()) {
				String line = inFile.nextLine(); // each line should contain
													// parameter and value
				String[] param = line.split("[ ]");
				parameters.put(param[0], Double.parseDouble(param[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		inFile.close();
		return parameters;
	}

	/**
	 * Simple, type in
	 */
	public boolean forceAction(GameState g) {
		spawnEntity(g);
		return true;
	}

	@Override
	public double getHitPoints() {
		return hitPoints;
	}

	@Override
	public void setHitPoints(double number) {
		hitPoints = number;
	}

	/**
	 * Says how much happiness the plant adds to the game, or removes from it.
	 */
	public double getHappiness() {
		return getParameters().get("Happiness");
	};

	/**
	 * Returns whether the plant is native or not. 1 for native, 0 for
	 * non-native, -1 for invasive -2 for enemy
	 */
	public int getNativeness() {
		return getParameters().get("Native").intValue();
	}

	/**
	 * Returns the static parameter map associated with the class.
	 */
	public abstract Map<String, Double> getParameters();

	/**
	 * Really does something once- when the object is made. Changes the
	 * happiness by what its supposed to by parameters.
	 */
	protected void changeHappiness(GameState g) {
		if (!changedHappiness) {
			g.setHappiness(g.getHappiness() + getParameters().get("Happiness"));
			changedHappiness = true;
		}
	}

	protected void setChangedHappiness(boolean i) {
		changedHappiness = i;
	}

	/**
	 * Figures out where to spawn an entity when needed. Does not spawn it, only
	 * gives you where to put it
	 */
	protected Position spawnEntityLocation(GameState g) {
		// figure out where to spawn
		double xToSpawn, yToSpawn;
		// get raw spawn
		xToSpawn = position.getX() + g.getGenerator().nextGaussian()
				* getParameters().get("SpawnLocSpread");
		yToSpawn = position.getY() + g.getGenerator().nextGaussian()
				* getParameters().get("SpawnLocSpread");
		// fix the spot to be in-game
		xToSpawn = Math.abs(xToSpawn); // for below zero
		yToSpawn = Math.abs(yToSpawn); // for below zero
		if (xToSpawn > g.getGameField().getAreaBlocks()[0].length) { // for over
			xToSpawn = g.getGameField().getAreaBlocks()[0].length * 2
					- xToSpawn;
		}
		if (yToSpawn > g.getGameField().getAreaBlocks().length) { // for over
			yToSpawn = g.getGameField().getAreaBlocks()[0].length * 2
					- yToSpawn;
		}

		// make position
		return new Position(xToSpawn, yToSpawn);
	}

	/**
	 * Spawns an entity and puts in into the game. Called when it is time.
	 */
	protected void spawnEntity(GameState g) {
		Position posToSpawn = spawnEntityLocation(g);
		// spawn
		Entity ent = chooseSpawnEntity(g);

		ent.setPosition(posToSpawn); // give it the needed position

		// stick in game
		g.getEntities().add(ent);

		timeOfNextAction = totalTime
				+ ((g.getGenerator().nextGaussian())
						* getParameters().get("SpawnTimeSpread") + getParameters()
						.get("SpawnTime")) * g.perTickMultiplier();
	}

	/**
	 * Make the plant invade (actually puts it on the board and everything). The
	 * invading plant keeps its type (an English Ivy stays an English Ivy).
	 */
	protected void invasiveSpread(GameState g) {
		double xSpread = (getParameters().get("MultiplySpread") * ((g
				.getGenerator().nextDouble() - 0.5) * 2));
		double ySpread = (getParameters().get("MultiplySpread") * ((g
				.getGenerator().nextDouble() - 0.5) * 2));
		// System.out.println(xSpread+" "+ySpread);

		double xPos = position.getX() + xSpread;
		double yPos = position.getY() + ySpread;

		xPos = Math.abs(xPos); // for below zero
		yPos = Math.abs(yPos); // for below zero
		if (xPos > g.getGameField().getAreaBlocks()[0].length) { // for over
			xPos = g.getGameField().getAreaBlocks()[0].length * 2 - xPos;
		}
		if (yPos > g.getGameField().getAreaBlocks().length) { // for over
			yPos = g.getGameField().getAreaBlocks()[0].length * 2 - yPos;
		}

		Position newPlantPos = new Position(xPos, yPos);

		Block loc = g.getGameField().getAreaBlocks()[newPlantPos.getIntX()][newPlantPos
				.getIntY()];

		if (!loc.isWater()) { // if isn't water
			// expand (killing any other plant there).
			try {
				Settable np = this.getClass().newInstance(); // make instance of
																// this

				if (loc.getEntityOnTile() != null) {
					loc.getEntityOnTile().setHitPoints(-1); // kill, will get
															// removed. If gets
															// same location,
															// pretty much full
															// heal

					if (loc.getEntityOnTile().getClass() == this.getClass()) { // if
																				// it
																				// is
																				// a
																				// full
																				// heal
						np.setChangedHappiness(true); // don't take away
														// happiness
					}
				}
				np.setPosition(new Position(newPlantPos.getIntX() + 0.5,
						newPlantPos.getIntY() + 0.5)); // shift by 0,5 from int
				loc.setEntityOnTile(np); // add to tile
				g.getToAdd().add(np);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets in how much time should the next entity spawn.
	 */
	protected double getNextSpawnWaitTime(GameState g) {
		return (g.getGenerator().nextGaussian())
				* getParameters().get("SpawnTimeSpread") + // time spread
				getParameters().get("SpawnTime"); // figure out when next to
													// spawn, normal time
	}

	/**
	 * General doing of action for a generic plant. Changes the happiness of the
	 * state upon creation. Keeps track of the time of living. WHen time comes-
	 * spawns entities. If it is invasive - it expands when it needs to.
	 */
	public boolean doAction(GameState g) {

		changeHappiness(g); // change the happiness

		totalTime += g.getTickSpeed() / 1000.; // increment time

		if (totalTime > timeOfNextAction) { // if we've exceeded that time, then
											// spawn

			spawnEntity(g);

			timeOfNextAction = totalTime + getNextSpawnWaitTime(g); // figure
																	// out when
																	// the next
																	// action
																	// should
																	// happen
		}

		if (nativeness == INVASIVE) { // if invasive
			if (totalTime % getParameters().get("PlantMultiplyTime").intValue() < // multiply
																					// if
																					// time
																					// has
																					// reached
			(g.getTickSpeed() / 1000.)) { // time limit.

				invasiveSpread(g); // spread
			}
		}
		return true;
	}

	/**
	 * Tells what kind of entity should spawn when needed.
	 */
	protected abstract Entity chooseSpawnEntity(GameState g);
}
