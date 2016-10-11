package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;


import animations.AnimationImageSequence;
import enitities.Entity;
import enitities.PlayerCharacter;
import settables.Settable;
import settables.UFO;
import model.Field;
import model.Position;
import moves.AttackMove;
import moves.PlacingMove;

public class InteractionView extends JPanel implements ComponentListener {

	int tileWidth = 25; // number of columns to be on screen
	int tileHeight = 13; // number of rows to be on screen

	int isoTileWidth = 100; // iso number of columns to be on screen
	int isoTileHeight = 50; // iso number of rows to be on screen

	double overHeadOffsetX = 0;
	double overHeadOffsetY = 0;

	double pastX;

	int windowHeight = getHeight(); // FIXME do only on resize
	int windowWidth = getWidth(); // FIXME do only on resize
	int centerX = windowWidth / 2;
	int centerY = windowHeight / 2;
	int hOffset = windowHeight * (tileWidth / 2);
	int vOffset = 0;

	int viewHeight;
	int viewWidth;
	int imgHeight;
	int imgWidth;

	int overlayLocation;

	boolean isIso;

	GameView mainView;

	Collection<AnimationImageSequence> animations;

	/**
	 * Constructor for the InteractionView. Takes the view which it is a part of
	 * as a parameter. Currently sets the background color to red. Sets Opacity
	 * to true.
	 */
	public InteractionView(GameView mainView) {
		super();
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.mainView = mainView;
		isIso = true;
		addComponentListener(this);
		animations = new ArrayList<AnimationImageSequence>();
	}

	public void paint(Graphics g) {
		super.paint(g); // print background and so forth
		paintBackground(g);
		paintOverlayEffect(g);
		if (isIso) {
			isoPaintField(g);
			isoPaintPlants(g);
			isoPaintEntities(g);
			isoPaintPlayerCharacters(g);
			isoPaintHealthbars(g);
		} else {
			paintField(g);
			paintPlants(g);
			paintEntities(g);
			paintHealthbars(g);
			paintPlayerCharacters(g);

		}
		paintAnimations(g);
		paintFace(g);

	}

	// /////////////////////////Isometric View
	// Painting////////////////////////////////////////

	private void paintAnimations(Graphics g) {
		Iterator<AnimationImageSequence> iter = animations.iterator(); // get
																		// the
																		// iterator
																		// over
																		// the
																		// sequence

		while (iter.hasNext()) { // while we still have something
			AnimationImageSequence cur = iter.next(); // get the next one
			Image toDraw = cur.getNextImage(); // get the drawing image
			if (toDraw == null) { // if nothing to draw
				iter.remove(); // remove it
			} else { // else start figuring it out
				Position ul = cur.getUpperLeftCorner(); // upper left
				Position br = cur.getBottomRightCorner(); // bottom right
				g.drawImage(
						toDraw, // what to draw
						ul.getIntX(),
						ul.getIntY(), // where to draw it
						br.getIntX() - ul.getIntX(),
						br.getIntY() - ul.getIntY(), null); // and no observer
			}
		}

	}

	private void paintBackground(Graphics g) {
		Image img = mainView.getImages().get("background"); // get the
															// background
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * Paints the field, pretty much only water and dirt.
	 */
	private void isoPaintField(Graphics g) {
		int grassCounter = 0;
		Field world = mainView.getCurrentGame().getState().getGameField(); // for
																			// consise
																			// code.

		for (int col = 0; col < Field.fieldWidth; col++) { // print
															// out
															// field,
															// water
															// /
															// dirt
			for (int row = 0; row < Field.fieldHeight; row++) {

				if (world.getAreaBlocks()[col][row].isWater()) {
					g.drawImage(
							mainView.getImages().get("isoWater3"), // image
							(int) (twoDToIsoX(col, row) - overHeadOffsetX),
							(int) (twoDToIsoY(col, row) - overHeadOffsetY), // location
							isoTileWidth, isoTileHeight, // size
							null); // no observer
				} else {
					g.drawImage(
							mainView.getImages().get("isoSoil2"), // image
							(int) (twoDToIsoX(col, row) - overHeadOffsetX),
							(int) (twoDToIsoY(col, row) - overHeadOffsetY), // location
							isoTileWidth, isoTileHeight, // size
							null); // no observer
				}
			}
		}
	}

	/*
	 * These two methods are what take the actual i and j position in our 2D
	 * array of blocks and converts that two a visual, isometric, representation
	 * in the view.
	 */

	public double twoDToIsoX(double x, double y) {
		double isoX;
		isoX = ((x * isoTileWidth / 2) - (y * isoTileWidth / 2));
		return isoX;
	}

	public double twoDToIsoY(double x, double y) {
		double isoY;
		isoY = ((x * isoTileHeight / 2) + (y * isoTileHeight / 2));
		return isoY;
	}

	/*
	 * These two methods are going to be used to convert the isometric, visual,
	 * coordinates to the coordinates within our actual field array. These would
	 * be used with the calculation of bounds for what's to be printed on screen
	 * but I haven't quite figured out their implementation yet.
	 */

	public double isoToI(double isoX, double isoY) {
		double twoDX = (isoX / (isoTileWidth) + isoY / (isoTileHeight));// -
																		// 0.5);
		return twoDX;
	}

	public double isoToJ(double isoX, double isoY) {
		double twoDY = (isoY / (isoTileHeight) - isoX / (isoTileWidth));// +
																		// 0.5);
		return twoDY;
	}

	/**
	 * Paints the plants on the field. Everything.
	 */
	private void isoPaintPlants(Graphics g) { // TODO REALLY TEMPORARY
		for (Settable cur : mainView.getCurrentGame().getState().getPlants()) {
			if (cur instanceof UFO
					&& !AttackMove.checkSettableAttack(mainView
							.getCurrentGame().getState(), cur)) {
				g.drawImage(
						mainView.getImages().get(cur.getName() + "1"), // image
						(int) (twoDToIsoX(cur.getPosition().getX() - 0.5, (cur
								.getPosition().getY() - 1)) - overHeadOffsetX),
						(int) (twoDToIsoY(cur.getPosition().getX() - 0.5, (cur
								.getPosition().getY() - 1)) - overHeadOffsetY), /*
																				 * location
																				 */
						isoTileWidth / 2, isoTileHeight * 2 / 2, // size
						null); // no observer

			} else
				g.drawImage(
						mainView.getImages().get(cur.getName()), // image
						(int) (twoDToIsoX(cur.getPosition().getX() - 0.5, (cur
								.getPosition().getY() - 1)) - overHeadOffsetX),
						(int) (twoDToIsoY(cur.getPosition().getX() - 0.5, (cur
								.getPosition().getY() - 1)) - overHeadOffsetY), /*
																				 * location
																				 */
						isoTileWidth / 2, isoTileHeight * 2 / 2, // size
						null); // no observer
		}

	}

	private void isoPaintEntities(Graphics g) {

		for (Entity cur : mainView.getCurrentGame().getState().getEntities()) {
			g.drawImage(
					mainView.getImages().get(cur.getName()), // image
					(int) (twoDToIsoX(cur.getPosition().getX() - 0.5, (cur
							.getPosition().getY() - 1)) - overHeadOffsetX),
					(int) (twoDToIsoY(cur.getPosition().getX() - 0.5, (cur
							.getPosition().getY() - 1)) - overHeadOffsetY), // location
					isoTileWidth / 2, isoTileHeight * 2 / 2, // size
					null); // no observer
		}
	}

	private void isoPaintPlayerCharacters(Graphics g) {

		for (String curName : mainView.getCurrentGame().getState()
				.getPlayerCharacters().keySet()) {
			PlayerCharacter cur = mainView.getCurrentGame().getState()
					.getPlayerCharacters().get(curName);
			g.drawImage(
					mainView.getImages().get(cur.getName()), // image
					(int) (twoDToIsoX((cur.getPosition().getX() - 0.5), (cur
							.getPosition().getY() - 1)) - overHeadOffsetX), // 0.5
																			// for
																			// centering
					(int) (twoDToIsoY((cur.getPosition().getX() - 0.5), (cur
							.getPosition().getY() - 1)) - overHeadOffsetY), // location
					isoTileWidth / 2, isoTileHeight * 2 / 2, // size
					null); // no observer

			// paint their reloading bars
			int height = 5; // height of HP bar
			int width = isoTileWidth / 2; // width of HP bar

			double curProgress = System.currentTimeMillis()
					- cur.getCurrentWeapon().getTimeOfLastFire(); // time
																	// elapsed
			double maxProgress = cur.getCurrentWeapon().getParameters()
					.get("ReloadTime"); // time needed

			int curX = (int) (twoDToIsoX((cur.getPosition().getX() - 0.5), (cur
					.getPosition().getY() - 1)) - overHeadOffsetX);
			int curY = (int) (twoDToIsoY((cur.getPosition().getX() - 0.5), (cur
					.getPosition().getY() - 1)) - overHeadOffsetY);

			double timePercent = curProgress / maxProgress;
			timePercent = timePercent > 1 ? 1 : timePercent; // cap it at 1
			g.setColor(Color.RED); // red emptiness
			g.fillRect(curX, curY, width, height);
			g.setColor(Color.BLUE);
			g.fillRect(curX, curY, (int) (width * timePercent), height);

		}

	}

	private void isoPaintHealthbars(Graphics g) {

		int hpHeight = 5; // height of HP bar
		int hpWidth = isoTileWidth / 2; // width of HP bar

		for (Entity x : mainView.getCurrentGame().getState().getEntities()) {
			double curHealth = x.getHitPoints();
			double maxHealth = x.getParameters().get("MaxHP");
			int curX = (int) (twoDToIsoX((x.getPosition().getX() - 0.5), (x
					.getPosition().getY() - 1)) - overHeadOffsetX);
			int curY = (int) (twoDToIsoY((x.getPosition().getX() - 0.5), (x
					.getPosition().getY() - 1)) - overHeadOffsetY);
			double hpPercent = curHealth / maxHealth;
			if (hpPercent < 0.99) {
				g.setColor(Color.RED);
				g.fillRect(curX, curY, hpWidth, hpHeight);
				g.setColor(Color.GREEN);
				g.fillRect(curX, curY, (int) (hpWidth * hpPercent), hpHeight);
			}
		}

		for (Settable x : mainView.getCurrentGame().getState().getPlants()) {
			double curHealth = x.getHitPoints();
			double maxHealth = x.getParameters().get("MaxHP");
			int curX = (int) (twoDToIsoX((x.getPosition().getX() - 0.5), (x
					.getPosition().getY() - 1)) - overHeadOffsetX);
			int curY = (int) (twoDToIsoY((x.getPosition().getX() - 0.5), (x
					.getPosition().getY() - 1)) - overHeadOffsetY); // for
			// scrolling
			double hpPercent = curHealth / maxHealth;
			if (hpPercent < 0.99) {
				g.setColor(Color.RED);
				g.fillRect(curX, curY, hpWidth, hpHeight);
				g.setColor(Color.GREEN);
				g.fillRect(curX, curY, (int) (hpWidth * hpPercent), hpHeight);
			}

		}
	}

	// /////////////////////////OverHead View
	// Painting////////////////////////////////////////

	private void paintPlants(Graphics g) { 

		for (Settable cur : mainView.getCurrentGame().getState().getPlants()) {
			BufferedImage img = mainView.getImages().get(cur.getName());
			if (img==null){ //quick check if image exists, so that it's not invisible
				img = mainView.getImages().get("TextureNotFound");
			}
			if(cur instanceof UFO && !AttackMove.checkSettableAttack(mainView.getCurrentGame().getState(),cur))
			{
				g.drawImage(
						mainView.getImages().get(cur.getName() + "1"), // image
						(int) ((cur.getPosition().getX()-0.5) * imgWidth - overHeadOffsetX), //location X
						(int) ((cur.getPosition().getY()-0.5) * imgHeight - overHeadOffsetY), // location Y
						imgWidth, imgHeight, // size
						null); // no observer
				
			}
			else
			g.drawImage(img, // image
					(int) ((cur.getPosition().getX()-0.5) * imgWidth - overHeadOffsetX), //location X
					(int) ((cur.getPosition().getY()-0.5) * imgHeight - overHeadOffsetY), // location Y
					imgWidth, imgHeight, // size
					null); // no observer
		}
	}

	public void paintField(Graphics g) {
		Field world = mainView.getCurrentGame().getState().getGameField();

		boolean elevation = true;

		for (int col = 0; col < Field.fieldWidth; col++) { // print
			for (int row = 0; row < Field.fieldHeight; row++) {
				if (elevation) {
					if (world.getAreaBlocks()[col][row].isWater()) {

						Color c = new Color(
								0, // no red
								(int) (70 * world.getAreaBlocks()[col][row]
										.getElavation()), // some green, more in
															// shallow
								(int) (200 * (1 - world.getAreaBlocks()[col][row]
										.getElavation()))); // more blue, more
															// in deeper
						g.setColor(c);
						g.fillRect((int) (col * imgWidth - overHeadOffsetX),
								(int) (row * imgHeight - overHeadOffsetY),
								imgWidth, imgHeight);

					} else {
						Color c = new Color(
								(int) (200 * world.getAreaBlocks()[col][row]
										.getElavation()),
								70, 0); // shade of brown
						g.setColor(c);
						g.fillRect((int) (col * imgWidth - overHeadOffsetX),
								(int) (row * imgHeight - overHeadOffsetY),
								imgWidth, imgHeight);

					}
				} else {
					if (world.getAreaBlocks()[col][row].isWater()) {
						g.drawImage(
								mainView.getImages().get("water"), // image
								(int) (col * imgWidth - overHeadOffsetX),
								(int) (row * imgHeight - overHeadOffsetY), // location
								imgWidth, imgHeight, // size
								null); // no observer
					} else {
						g.drawImage(
								mainView.getImages().get("dirt"), // image
								(int) (col * imgWidth - overHeadOffsetX),
								(int) (row * imgHeight - overHeadOffsetY), // location
								imgWidth, imgHeight, // size
								null); // no observer
					}
				}
			}
		}
	}

	private void paintEntities(Graphics g) {

		int imgHeight = viewHeight / tileHeight;
		int imgWidth = viewWidth / tileWidth;

		for (Entity cur : mainView.getCurrentGame().getState().getEntities()) {
			BufferedImage img = mainView.getImages().get(cur.getName());
			if (img == null) { // quick check if image exists, so that it's not
								// invisible
				img = mainView.getImages().get("TextureNotFound");
			}
			g.drawImage(
					img, // image
					(int) ((cur.getPosition().getX() - 0.5) * imgWidth - overHeadOffsetX), // 0.5
																							// for
																							// centering
					(int) ((cur.getPosition().getY() - 0.5) * imgHeight - overHeadOffsetY), // location
					imgWidth, imgHeight, // size
					null); // no observer
		}
	}

	private void paintHealthbars(Graphics g) {

		int hpHeight = 5; // height of HP bar
		int hpWidth = imgWidth; // width of HP bar

		for (Entity x : mainView.getCurrentGame().getState().getEntities()) {
			double curHealth = x.getHitPoints();
			double maxHealth = x.getParameters().get("MaxHP");
			int curX = (int) (x.getPosition().getX() * imgWidth // center of
																// image
					- imgWidth / 2 // push back to corner
			- overHeadOffsetX); // offset for scrolling
			int curY = (int) (x.getPosition().getY() * imgHeight - imgHeight
					/ 2 // push back to corner
			- overHeadOffsetY); // offset for scrolling
			double hpPercent = curHealth / maxHealth;
			if (hpPercent < 0.99) {
				g.setColor(Color.RED);
				g.fillRect(curX, curY, hpWidth, hpHeight);
				g.setColor(Color.GREEN);
				g.fillRect(curX, curY, (int) (hpWidth * hpPercent), hpHeight);
			}
		}

		for (Settable x : mainView.getCurrentGame().getState().getPlants()) {
			double curHealth = x.getHitPoints();
			double maxHealth = x.getParameters().get("MaxHP");
			int curX = (int) (x.getPosition().getX() * imgWidth // center of
					- imgWidth / 2 // image
			- overHeadOffsetX); // offset for scrolling
			int curY = (int) (x.getPosition().getY() * imgHeight - imgHeight
					/ 2 - overHeadOffsetY); // offset
			// for
			// scrolling
			double hpPercent = curHealth / maxHealth;
			if (hpPercent < 0.99) {
				g.setColor(Color.RED);
				g.fillRect(curX, curY, hpWidth, hpHeight);
				g.setColor(Color.GREEN);
				g.fillRect(curX, curY, (int) (hpWidth * hpPercent), hpHeight);
			}

		}
	}

	private void paintPlayerCharacters(Graphics g) {

		for (String curName : mainView.getCurrentGame().getState()
				.getPlayerCharacters().keySet()) {
			PlayerCharacter cur = mainView.getCurrentGame().getState()
					.getPlayerCharacters().get(curName);
			BufferedImage img = mainView.getImages().get(cur.getName());
			if (img == null) { // quick check if image exists, so that it's not
								// invisible
				img = mainView.getImages().get("TextureNotFound");
			}
			g.drawImage(
					img, // image
					(int) ((cur.getPosition().getX() - 0.5) * imgWidth - overHeadOffsetX), // 0.5
																							// for
																							// centering
																							// location
					(int) ((cur.getPosition().getY() - 0.5) * imgHeight - overHeadOffsetY),
					imgWidth, imgHeight, // size
					null); // no observer

			// paint their reloading bars
			int height = 5; // height of HP bar
			int width = imgWidth; // width of HP bar

			double curProgress = System.currentTimeMillis()
					- cur.getCurrentWeapon().getTimeOfLastFire(); // time
																	// elapsed
			double maxProgress = cur.getCurrentWeapon().getParameters()
					.get("ReloadTime"); // time needed

			int curX = (int) (cur.getPosition().getX() * imgWidth // center of
																	// image
					- imgWidth / 2 // push back to corner
			- overHeadOffsetX); // offset for scrolling

			int curY = (int) (cur.getPosition().getY() * imgHeight - imgHeight
					/ 2 // push back to corner
			- overHeadOffsetY); // offset for scrolling

			double timePercent = curProgress / maxProgress;
			timePercent = timePercent > 1 ? 1 : timePercent; // cap it at 1
			g.setColor(Color.RED); // red emptiness
			g.fillRect(curX, curY, width, height);
			g.setColor(Color.BLUE);
			g.fillRect(curX, curY, (int) (width * timePercent), height);
		}
	}

	/*
	 * This method places a moving, oversized, transparent image which has a
	 * speed and "density" based on happiness to act as another indication of
	 * happiness changing
	 */

	private void paintOverlayEffect(Graphics g) {
		if (mainView.getCurrentGame().getState().getHappiness() <= .15) {

			g.drawImage(mainView.getImages().get("level1SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			g.drawImage(mainView.getImages().get("level2SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			g.drawImage(mainView.getImages().get("level3SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			overlayLocation = overlayLocation + 9;
		}
		if (mainView.getCurrentGame().getState().getHappiness() <= .30
				&& mainView.getCurrentGame().getState().getHappiness() > .15) {

			g.drawImage(mainView.getImages().get("level3SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			g.drawImage(mainView.getImages().get("level2SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			overlayLocation = overlayLocation + 5;
		}
		if (mainView.getCurrentGame().getState().getHappiness() <= .40
				&& mainView.getCurrentGame().getState().getHappiness() > .30) {

			g.drawImage(mainView.getImages().get("level2SmogEffect"), // image
					overlayLocation, overlayLocation, // location
					10000, 10000, // size
					null);
			overlayLocation = overlayLocation + 3;
		}
		if (overlayLocation > 0) {
			overlayLocation = -5000;
		}
	}

	private void paintFace(Graphics g) {
		if (mainView.getButtonMove() instanceof PlacingMove) {
			PlacingMove m = (PlacingMove) mainView.getButtonMove(); // cast it
																	// to a move
			if (mainView.getCurrentGame().getState().getPlacedPlantCount()
					.get(m.getPlant().getName()) != null) { // if it's been
															// placed before,
															// then do stuff
				String imageToDraw; // name of image to draw
				if (m.getPlant().getNativeness() == 1) { // if native
					imageToDraw = "happyFace"; // it's happy
				} else if (m.getPlant().getNativeness() == 0) {
					imageToDraw = "neutralFace";
				} else {
					imageToDraw = "sadFace";
				}
				g.drawImage(mainView.getImages().get(imageToDraw), // image
						getWidth()/2-200, 0, // location
						400, getHeight(), // size
						null);
			}
		}

	}

	public int getBlockHeight() {
		return getHeight() / tileHeight;
	}

	public int getBlockWidth() {
		return getWidth() / tileWidth;
	}

	public boolean isIso() {
		return isIso;
	}

	/**
	 * Switches between the views. The listeners must be done somewhere else.
	 */
	public void switchIso() {
		isIso = !isIso;
	}

	public double getOverHeadOffsetX() {
		return overHeadOffsetX;
	}

	public double getOverHeadOffsetY() {
		return overHeadOffsetY;
	}

	public void setOverHeadOffsetX(double overHeadOffsetX) {
		this.overHeadOffsetX = overHeadOffsetX;
	}

	public void setOverHeadOffsetY(double overHeadOffsetY) {
		this.overHeadOffsetY = overHeadOffsetY;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		viewHeight = getHeight();
		viewWidth = getWidth();
		imgHeight = viewHeight / tileHeight;
		imgWidth = viewWidth / tileWidth;

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	/**
	 * Returns the position of the grid in the center of the screen at the
	 * moment. Automatically includes the iso/normal view.
	 */
	public Position getCenterPosition() {
		double locationX; // allocate variables
		double locationY;

		double X = (overHeadOffsetX + 1.*viewWidth / 2); // find the center of the
														// screen in pixels
		double Y = (overHeadOffsetY + 1.*viewHeight / 2); // relative to the offset

		if (isIso) { // if we are in the isometric view
			locationX = isoToI(X, Y); // convert to isometric grid coordinates
			locationY = isoToJ(X, Y);
		} else {
			locationX = X / imgWidth; // divide location by number of blocks to
										// it
			locationY = Y / imgHeight;
		}
		return new Position(locationX, locationY); // return the position
	}

	/**
	 * Centers the view around the given position of the game grid.
	 * Automatically includes iso/normal view relationships.
	 */
	public void centerAtPosition(Position p) {
		double newXOffset; // define offset variables;
		double newYOffset;

		double locationX = p.getX(); // get x component
		double locationY = p.getY(); // get Y component
		if (isIso) { // if isometric
			newXOffset = twoDToIsoX(locationX, locationY) - // get printing spot
															// in iso
					1.*viewWidth / 2; // adjust to corner

			newYOffset = twoDToIsoY(locationX, locationY) - // get printing spot
															// in iso
					1.*viewHeight / 2; // adjust to corner
		} else {
			newXOffset = locationX * // location
					imgWidth - // now we have corner, adjust to center:
					1.*viewWidth / 2; // adjustment to corner

			newYOffset = locationY * // location
					imgHeight - // now we have corner, adjust to center:
					1.*viewHeight / 2; // adjustment to corner
		}
		setOverHeadOffsetX(newXOffset);
		setOverHeadOffsetY(newYOffset);
	}

	public Collection<AnimationImageSequence> getAnimations() {
		return animations;
	}

	public int getIsoTileWidth() {
		return isoTileWidth;
	}

	public int getIsoTileHeight() {
		return isoTileHeight;
	}

	// public void addNotify(){
	// super.addNotify();
	// requestFocus();
	// }

}
