package listeners;


import java.awt.event.MouseEvent;







import model.Position;




import views.*;

public class IsoInteractionMouseListener extends InteractionMouseListener{

	public IsoInteractionMouseListener(GameView gameView) {
		super(gameView);
	}

	/**
	 * Returns the position of the event (usually click) on the InteractionView
	 * itself.
	 */
	protected Position positionOfEvent(MouseEvent event) {

		double x = iView.isoToI(event.getX() + iView.getOverHeadOffsetX(),
				event.getY() + iView.getOverHeadOffsetY());
		double y = iView.isoToJ(event.getX() + iView.getOverHeadOffsetX(),
				event.getY() + iView.getOverHeadOffsetY());

		Position pos = new Position(x-0.5, y+0.5);
		System.out.println(pos);

		return pos;
	}
}
