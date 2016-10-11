package buttons;

import java.awt.Color;

import java.awt.event.ActionEvent;








import views.ToolboxView;

public class BottomButton extends GameButton{ 
	protected int panelToOpen;
	protected String name;

	public BottomButton(ToolboxView g, String name, int panelToOpen){
		super(g);
		this.name = name; //save name 
		this.panelToOpen = panelToOpen; //and panel number
		setText(name); //set the text to have the respective name
		setBackground(Color.GREEN); //background ccolor?
		addActionListener(this); //make it listen to itself
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e); //for refocus and others
		toolboxView.setCurrentPanel(panelToOpen); //tells which panel to make visible on the toolbox.
		
	}

}