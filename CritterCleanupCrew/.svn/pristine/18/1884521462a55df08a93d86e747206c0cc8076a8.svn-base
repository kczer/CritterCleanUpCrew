package settables;

import java.util.Map;

import model.GameState;
import enitities.Catterpillar;
import enitities.Entity;


public class EnglishIvy extends Settable{
	
	protected static Map<String, Double> parameters; //where parameters are kept.
	
	@Override
	public String getName() {
		return "English Ivy";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		return "EnglishIvy at "+position.toString();
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if parameters haven't been loaded
			loadParameters(); //load them
		}
		return parameters;
	}

	@Override
	protected Entity chooseSpawnEntity(GameState g) {
		return new Catterpillar(null);
	}

	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/English Ivy.txt"); //load them.
		
	}

}
