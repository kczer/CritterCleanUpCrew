package settables;

import java.util.Map;

import model.GameState;
import enitities.Colonizer;
import enitities.Entity;
import enitities.Gnome;

public class UFO extends Settable {

	protected static Map<String, Double> parameters; //where parameters are kept.
	
	@Override
	public String getName() {
		return "UFO";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if parameters have not been loaded
			loadParameters();
		}
		return parameters;
	}

	@Override
	protected Entity chooseSpawnEntity(GameState g) {
		double i = g.getGenerator().nextDouble(); //get random number
		if (i>0.9){
			return new Colonizer(null);
		}
		else{
			return new Gnome(null);
		}
		
	}

	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/UFO.txt"); //load them	
	}

}
