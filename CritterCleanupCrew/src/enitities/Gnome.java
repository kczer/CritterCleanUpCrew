package enitities;

import java.util.Map;



import model.GameState;
import model.Position;

public class Gnome extends Entity{
	
	protected static Map<String, Double> parameters;
	
	
	public Gnome(Position p){
		super(p);
	}

	@Override
	public boolean forceAction(GameState g) {
		return super.forceAction(g);
	}

	@Override
	public String getName() {
		return "Gnome";
	}
	
	public String toString(){
		return "Gnome at "+position.toString();
	}

	public Map<String, Double> getParameters() {
		if (parameters==null){ //if weren't loaded, load.
			loadParameters();
		}
		return parameters;
	}

	@Override
	public void loadParameters() {
		parameters=Entity.loadParameters("data/Gnome.txt");	
	}


}
