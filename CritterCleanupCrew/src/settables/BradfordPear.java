package settables;

import java.util.Map;

import model.GameState;
import enitities.Entity;
import enitities.MonarchButterfly;

public class BradfordPear extends Settable {
	private static Map<String, Double> parameters;
	@Override
	public void loadParameters() {
		parameters = Settable.loadParameters("data/Bradford Pear.txt");

	}

	@Override
	public String getName() {
		return "Bradford Pear";
	}

	@Override
	public int getCycleTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Double> getParameters() {
		if (parameters==null){
			loadParameters();
		}
		return parameters;
	}

	@Override
	protected Entity chooseSpawnEntity(GameState g) {
		return new MonarchButterfly(null);
	}

}
