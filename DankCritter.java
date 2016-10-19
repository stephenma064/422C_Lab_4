package assignment4;

/**
 * 
 * @author ericsu
 *
 */
public class DankCritter extends Critter {
	private int direction;
	private int dankLevel;
	private boolean tipsFedora;
	
	public DankCritter() {
		this.direction = Critter.getRandomInt(8);
		this.dankLevel = 400;
		this.tipsFedora = false;
	}
	
	@Override
	public String toString() { return "D"; }
	
	@Override
	public void doTimeStep() {
		walk(this.direction);
		if (this.dankLevel == 420) {
			this.tipsFedora = true;		
			this.dankLevel *= 0.67;
			DankCritter dc = new DankCritter();
			reproduce(dc, Critter.getRandomInt(8));
		} 
		else {
			this.dankLevel += 1;
		}
	}

	@Override
	public boolean fight(String oponent) {
		if (this.tipsFedora) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
