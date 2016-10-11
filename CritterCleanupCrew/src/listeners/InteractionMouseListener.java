package listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;









import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import animations.MotionSelectionImageSequence;
import players.Player;
import enitities.Entity;
import enitities.PlayerCharacter;
import model.Actionable;
import model.Position;
import moves.AttackMove;
import moves.MotionMove;
import moves.Move;


import settables.Settable;
import views.*;

public class InteractionMouseListener implements MouseInputListener {
	GameView gameView;
	InteractionView iView;
	Position lastDragPosition; //position on screen, not the grid!
	boolean dragBarrierPassed;
	
	public InteractionMouseListener(GameView gameView){
		this.gameView = gameView;
		iView = (InteractionView)gameView.getSubviews().get("Interaction");
	}

	@Override
	public void mousePressed(MouseEvent event) {
		lastDragPosition = new Position(event.getX(), event.getY()); //in case we're dragging
		dragBarrierPassed = false; //we reinstall the drag barrier
		
		Actionable clicked = getClickedActionable(event);
		
		if (SwingUtilities.isLeftMouseButton(event)){ //if it's a left button click
			if (gameView.getButtonMove()!=null){ //if there is a class to place
				launchButtonMove(event);
				
				//reset cursor
				gameView.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			else{ //I guess we want something else done?
				if (clicked!=null){ //if we clicked on an object
					((InformationView)gameView.getSubviews().get("Information")).setShownActionable(clicked);
					if (clicked instanceof PlayerCharacter){ //if we clicked on a player character, feel free to switch players around
						String charName = clicked.getName();
						
						Player p1 = gameView.getCurrentGame().getPlayers().get("HumanPlayer"); //first player
						PlayerCharacter c2 = gameView.getCurrentGame().getState().getPlayerCharacters().get(charName); //second (target) character
						
						Player p2 = c2.getCurrentController(); //second player
						PlayerCharacter c1 = gameView.getCurrentGame().getState()
										.getPlayerCharacters().get(p1.getPlayerCharacterControlled()); //first character (current)
						
						try{ //FIXME, for crashes when no players exist
							p2.setPlayerCharacterToControl(c1.getName());//tell 2 to control 1
							c1.setCurrentController(p2); //tell it who's in control
						}catch(NullPointerException e){e.printStackTrace();}
						p1.setPlayerCharacterToControl(c2.getName()); //give character to player
						c2.setCurrentController(p1); //tell who's controlling
						
						Position center = c2.getPosition();
						InteractionView iView = (InteractionView)gameView.getSubviews().get("Interaction");
						iView.centerAtPosition(center); //center view at the guy
						((InformationView)gameView.getSubviews().get("Information")).setShownActionable(c2); //set to show the new character
						
						p2.setNextMove(p1.getTargetMove()); //give the AI your last move
						p1.setNextMove(null); //stop moving
					}
				}
			}
		}
		else if (SwingUtilities.isRightMouseButton(event)){ //if it's  right mouse button click.
			if (gameView.getButtonMove()!=null){ //if there is a class to place
				gameView.setButtonMove(null); //removing placing.
				
				//reset Cursor
				gameView.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			
			if (clicked == null){  //if didn't click actionable
				setPlayerInMotion(event); //run
			}
			else{ //else we did click something
				attackClick(clicked, event); //try attacking or doing whatever
			}
		}

	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		lastDragPosition = null; //definitely done dragging

	}

	@Override
	public void mouseDragged(MouseEvent event) {
		Position newPos = new Position(event.getX(), event.getY());
		if (dragBarrierPassed){ //if we've passed the drag barrier, just move around freely
			
			iView.setOverHeadOffsetX(iView.getOverHeadOffsetX()+ //standard
					lastDragPosition.getX()- //before (difference)
					event.getX()); //now
			
			iView.setOverHeadOffsetY(iView.getOverHeadOffsetY()+ //standard
					lastDragPosition.getY()- //before (difference)
					event.getY()); //now
			lastDragPosition = newPos; //new position for pivot
		}
		else{ //else check if we're about to pass
			dragBarrierPassed = lastDragPosition.distanceTo(newPos)>20; //check if we've moved enough
		}

	}

	@Override
	public void mouseMoved(MouseEvent event) {

	}
	/**
	 * Returns the position of the event (usually click)
	 * on the InteractionView itself.
	 */
	protected Position positionOfEvent(MouseEvent event){
		
		int height = iView.getBlockHeight();
		int width = iView.getBlockWidth();
		double x = (event.getX()+iView.getOverHeadOffsetX())/width;
		double y = (event.getY()+iView.getOverHeadOffsetY())/height;
		
		Position pos = new Position(x,y);
		
		return pos;
	}
	/**
	 * Places the plant selected in the mainView to the position in the event.
	 * To be called when the string containing the name of the class of the plant
	 * to be planted is non-null and valid.
	 */
//	protected void placePlant(MouseEvent event){
//		try {
//			Class toPlace = Class.forName(gameView.getPlaceClass());
//			Settable plant = (Settable) toPlace.newInstance();
//			
//			Position pos  = positionOfEvent(event).intPosition();
//			System.out.println(pos);
//			System.out.println(plant.getName());
//			
//			PlacingMove pm = new PlacingMove(plant, pos);
//			if (pm.isValid(gameView.getCurrentGame().getState())){
//				pm.makeMove(gameView.getCurrentGame().getState());
//			}
//			gameView.setPlaceClass(null); //say "we got it"
//		} catch (ClassNotFoundException e) {
//			
//			System.out.println(gameView.getPlaceClass()); //not found? Rrally?
//			e.printStackTrace();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 
	 * Gives the button move pressed a position and attempting making it work.
	 */
	private void launchButtonMove(MouseEvent event) {
		Position pos = positionOfEvent(event);
		Move theMove = gameView.getButtonMove(); //get the move
		gameView.setButtonMove(null); //move done
		theMove.setPosition(pos); //give it the position
		if (theMove.isValid(gameView.getCurrentGame().getState())){ //if valid
			theMove.makeMove(gameView.getCurrentGame().getState()); //do it!
		}
		
		
	}
	
	/**
	 * Sets the human player running to the location of the click in game;
	 */
	protected void setPlayerInMotion(MouseEvent event){
		Position p = positionOfEvent(event); //get position
		Move motionMove = new MotionMove(p); //Make a motionMove to go there
		gameView.getCurrentGame().getPlayers().get("HumanPlayer").setNextMove(motionMove); //give that motion move to the player
		iView.getAnimations().add(new MotionSelectionImageSequence(gameView, p));
	}
	
	/**
	 * Returns the Actionable that was clicked on the screen. Will return
	 * null if nothing was clicked. When two or some things are overlapping,
	 * only one of the is returned. The priority of being clicked is the same
	 * as when is drawn:
	 * PlayerCharacter - HIGHEST
	 * Entity 
	 * Settable- LOWEST
	 */
	protected Actionable getClickedActionable(MouseEvent event){
		Actionable clicked = null;
		double threshold = 0.7;
		Position click = positionOfEvent(event);
		
		for (Settable i: gameView.getCurrentGame().getState().getPlants()){ //for every plant
			if (i.getPosition().distanceTo(click)<threshold){ //check if we're close enough
				clicked = i;
			}
		}
		
		for (Entity i: gameView.getCurrentGame().getState().getEntities()){ //for every entity
			if (i.getPosition().distanceTo(click)<threshold){ //check if we're close enough
				clicked = i;
			}
		}
		
		
		
		for (String name: gameView.getCurrentGame().getState().getPlayerCharacters().keySet()){ //for every name
			PlayerCharacter i =  gameView.getCurrentGame().getState().getPlayerCharacters().get(name); //get char
			if (i.getPosition().distanceTo(click)<threshold){ //check if we're close enough
				clicked = i;
			}
		}
		
		return clicked;
	}
	
	/**
	 * To be called when a right-click (attack) is done on an entity.
	 * It plainly moves to a Settable and PlayerCharacter, attacks an 
	 * enemy Entity.
	 */
	private void attackClick(Actionable clicked, MouseEvent event) {
		if (clicked instanceof Entity){ //if it isn't an entity
			if (((Entity)clicked).isFriendly()){ //if it's friendly - run
				setPlayerInMotion(event); //make the player run
			}
			else{ //else attack
				 //FIXME make work in general
				
				AttackMove attackMove = new AttackMove(clicked, //make a move with the entity to be attacked
						gameView.getCurrentGame().getState().getPlayerCharacters() //get char
						.get(gameView.getCurrentGame().getPlayers().get("HumanPlayer").getPlayerCharacterControlled())); //from human control
				gameView.getCurrentGame().getPlayers().get("HumanPlayer").setNextMove(attackMove); //tell the player that it needs to do that
				
			}
		}	
		else if (clicked instanceof Settable){ //else it's a plant/building
			if (((Settable)clicked).getNativeness()!=-2){ //if it's not an enemy
				setPlayerInMotion(event); //make the player run
			}else{
				AttackMove attackMove = new AttackMove(clicked, //make a move with the entity to be attacked
						gameView.getCurrentGame().getState().getPlayerCharacters() //get char
						.get(gameView.getCurrentGame().getPlayers().get("HumanPlayer").getPlayerCharacterControlled())); //from human control
				gameView.getCurrentGame().getPlayers().get("HumanPlayer").setNextMove(attackMove); //tell the player that it needs to do that
			}
		}
		
	}

	
	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event){

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}
}

	