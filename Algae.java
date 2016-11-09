/* CRITTERS GUI <Algae.java>
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
package assignment5;
/*
 * Do not change this file.
 */
import assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}

	@Override
	public javafx.scene.paint.Color viewColor() {
		return Color.GREENYELLOW;
	}
}
