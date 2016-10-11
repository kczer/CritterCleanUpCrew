package buttons;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;

import views.ToolboxView;

public abstract class GameButton extends JButton implements ActionListener, ComponentListener{
	protected ToolboxView toolboxView;
	protected int buttonWidth;
	protected int buttonHeight;
	protected boolean repainted=false;
	
	public GameButton(ToolboxView tbv){
		toolboxView= tbv;
		addComponentListener(this);
	}
	
	public void actionPerformed(ActionEvent event){
		toolboxView.getMainView().requestFocus();
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		buttonHeight = getHeight();
		buttonWidth = getWidth();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		
	}

	
}
