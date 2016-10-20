package assignment4;

/**
 * LadiesCritter
 * 
 * This critter gets all the ladies. In fact, this critter is so popular that 
 * it will reproduce every time step! It has a chance of reproducing either 1 
 * or 2 critters per time step. However popular this critter is, it does not like
 * to fight. 

 * @author ericsu
 *
 */
public class Critter1 extends Critter {
	private int dir;
	
	public Critter1() {
		this.dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		walk(this.dir);
		int reproduceChance = Critter.getRandomInt(2);
		if (reproduceChance == 0 || reproduceChance == 1) {
			Critter1 lc = new Critter1();
			reproduce(lc, Critter.getRandomInt(8));
		}
		if (reproduceChance == 2) {
			Critter1 lc = new Critter1();
			Critter1 lc1 = new Critter1();
			reproduce(lc, Critter.getRandomInt(8));
			reproduce(lc1, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String oponent) {
		return false;
	}
	
	@Override
	public String toString() { return "1"; }
	
}
