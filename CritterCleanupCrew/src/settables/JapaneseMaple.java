package settables;

import java.util.Map;


import enitities.Catterpillar;
import enitities.Entity;
import enitities.MonarchButterfly;

import model.GameState;


public class JapaneseMaple extends Settable{
	
	protected static Map<String, Double> parameters; //where parameters are kept.
	private boolean changedHappiness; //if it has done its happiness impact yet.
	private int totalTicks;

	@Override
	public String getName() {
		return "Japanese Maple";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if parameters have not been loaded
			loadParameters(); //load them
		}
		return parameters;
	}

	@Override
	protected Entity chooseSpawnEntity(GameState g) {
		double rand = g.getGenerator().nextDouble();
		if (rand>0.5){ 
			return new MonarchButterfly(null);
		}else{
			return new Catterpillar(null);
		}
	}

	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/Japanese Maple.txt"); //load them.
		
	}
}