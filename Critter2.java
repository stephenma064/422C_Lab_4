package assignment5;

import javafx.scene.paint.Color;

/**
 * DankCritter
 * 
 * When in an encounter and tipsFedora is false, this critter will always fight 
 * for the glory of winning imaginary internet points. When this critter’s dank 
 * level reaches 420, the critter can reproduce with a cost of ⅔ of its it’s 
 * dank level, and will set tipsFedora variable to true. However, when 
 * tipsFedora has been set to true, then this critter will never fight. 
 * Like other critters, this critter will also traverse in random directions.
 *
 * @author ericsu
 *
 */
public class Critter2 extends Critter {
	private int direction;
	private int dankLevel;
	private boolean tipsFedora;
	
	public Critter2() {
		this.direction = Critter.getRandomInt(8);
		this.dankLevel = 400;
		this.tipsFedora = false;
	}
	
	@Override
	public String toString() { return "2"; }
	
	@Override
	public void doTimeStep() {
		walk(this.direction);
		if (this.dankLevel == 420) {
			this.tipsFedora = true;		
			this.dankLevel *= 0.67;
			Critter2 dc = new Critter2();
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

	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}

	@Override
	public javafx.scene.paint.Color viewColor() {
		return Color.BLUE;
	}
	
}
