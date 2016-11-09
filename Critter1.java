package assignment5;

import javafx.scene.paint.Color;
/* CRITTERS GUI <Critter1.java>
 * EE422C Project 4b submission by
 * Replace <...> with your actual data.
 * Stephen Ma
 * szm99
 * 16480
 * Slip days used: <1>
 * Eric Su
 * es25725
 * 16475
 * Slip days used: <2>
 * Fall 2016
 */

/**
 * LadiesCritter
 * 
 * This critter gets all the ladies. In fact, this critter is so popular that 
 * it will reproduce every time step! It has a chance of reproducing either 1 
 * or 2 critters per time step. However popular this critter is, it does not like
 * to fight. 
 *
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
		
		this.dir = Critter.getRandomInt(8);
	}

	@Override
	public boolean fight(String opponent) {
		return false;
	}
	
	@Override
	public String toString() { return "1"; }

	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}

	@Override
	public javafx.scene.paint.Color viewColor() {
		return Color.MAGENTA;
	}
}
