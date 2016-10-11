package settables;

import java.util.Map;

import model.GameState;
import enitities.Bee;
import enitities.Catterpillar;
import enitities.Entity;
import enitities.MonarchButterfly;

public class WildCherry extends Settable {
	private static Map<String, Double> parameters;
	
	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/Wild Cherry.txt");
	}

	@Override
	public String getName() {
		return "Wild Cherry";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Double> getParameters() {
		if (parameters==null){ //if parameters not loaded
			loadParameters(); //load them
		}
		return parameters;
	}

	@Override
	protected Entity chooseSpawnEntity(GameState g) {
		double r = g.getGenerator().nextDouble();
		if (r<0.33){
			return new Catterpillar(null);
		}
		else if (r<0.66){
			return new Bee(null);
		}
		else{
			return new MonarchButterfly(null);
		}
	}
	
	public boolean doAction(GameState g){
		super.doAction(g); //do the super
		
		healSurroundingEntities(g);
		
		return true;
	}

	private void healSurroundingEntities(GameState g) {
		for (Entity ent:g.getEntities()){
			if (ent.isFriendly()&&ent.getPosition().distanceTo(position)<getParameters().get("HealingRadius")){ //if he's friendly and in range
				ent.setHitPoints(ent.getHitPoints()+getParameters().get("HealingRate")*g.getTickSpeed()/1000); //heal a bit.
			}
		}
		
	}

}
