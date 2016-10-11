package settables;




import java.util.Map;

import model.GameState;

import enitities.Catterpillar;
import enitities.Entity;
import enitities.MonarchButterfly;







public class MilkWeed extends Settable {
	
	protected static Map<String, Double> parameters; //where parameters are kept.
	
	public MilkWeed(){
		cost = parameters.get("Cost").intValue();
		changedHappiness = false;
		hitPoints = parameters.get("MaxHP");
		totalTime=0;
		timeOfNextAction = getParameters().get("SpawnTime"); //initial non-random spawn time
		nativeness = parameters.get("Native").intValue();
	}

	@Override
	public String getName() {
		return "Common Milkweed";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		return "Common milkweed at "+position.toString();
	}
	
	protected void setChangedHappiness(boolean i){
		changedHappiness=i;
	}
	
	public double getHappiness(){
		return parameters.get("Happiness");
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){ //if parameters have not been loaded
			loadParameters();
		}
		return parameters;
	}

	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/Common milkweed.txt"); //load them.
		
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

}
