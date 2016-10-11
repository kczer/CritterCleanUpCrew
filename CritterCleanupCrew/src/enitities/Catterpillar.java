package enitities;

import java.util.Map;



import model.Position;

public class Catterpillar extends Entity{
	
	public Catterpillar(Position p) {
		super(p);
	}

	protected static Map<String, Double> parameters;

	@Override
	public String getName() {
		return "Catterpillar";
	}
	
	public String toString(){
		return "Catterpillar at "+position.toString();
	}
	
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if weren't loaded, load.
			loadParameters();
		}
		return parameters;
	}

	@Override
	public void loadParameters() {
		parameters=Entity.loadParameters("data/Catterpillar.txt");
		
	}
	
	


}
