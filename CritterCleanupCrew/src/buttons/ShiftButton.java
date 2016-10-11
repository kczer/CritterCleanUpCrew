package buttons;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import views.ToolboxView;

public class ShiftButton extends GameButton {

	int shift;
	public ShiftButton(ToolboxView tbv, int shift) {
		super(tbv);
		this.shift = shift;
		addActionListener(this); //make itself listen
	}

	public void actionPerformed(ActionEvent event){
		super.actionPerformed(event); //for refocus and others
		toolboxView.shiftPanels(shift); //shift in the needed direction
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); //super call
		String imageName = //the name of the image is
				shift>0? //if we are doing a right shift
				"rightShift": //it's right shift
				"leftShift"; //else it's a left shift
		g.drawImage(toolboxView.getMainView().getImages().get(imageName), //get the image
				 0, 0, //corner at 0 0 
				 getWidth(), getHeight(), //end at sizes of button
				 null); //no observer
	}
}