package enitities;

import java.util.Map;

import model.Position;

public class Bee extends Entity {
	protected static Map<String, Double> parameters;

	public Bee (Position p){
		super(p);
	}

	public String getName() {
		return "Bee";
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if they weren't loaded, load
			loadParameters();
		}
		return parameters;
	}

	@Override
	public void loadParameters() {
		parameters=Entity.loadParameters("data/Bee.txt");
		
	}

}
