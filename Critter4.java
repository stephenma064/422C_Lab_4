package assignment5;

import javafx.scene.paint.Color;
// Critter4.java
/** CRITTERS 
 * EE422C Project 4 submission by
 * Stephen Ma szm99
 * Eric Su es25725
 * 
 * Stephen Ma Slip days used: <1>
 * Eric Su Slip days used: <2>
 * Fall 2016
 */

/**
 * StephenCritter
 * 
 * This Stephen critter does not reproduce. However, this critter moves quickly, as it will run 
 * every move. The critter will turn and change directions if there is a critter within a step from it. 
 * This critter does not like Craig critters and LadiesCritter, and will get triggered
 * and always fight these critters. However, all other critters the Stephen critter will not 
 * fight.
 * 
 * Created by Stephen on 10/17/2016.
 *
 */
public class Critter4 extends Critter{

    @Override
    public String toString() {
        return "4";
    }

    private int dir;

    public Critter4() {
        this.dir = Critter.getRandomInt(8);
    }
    public boolean fight(String other) {
        return other.equals("C") || other.equals("1");
    }

    public void doTimeStep() {
    	if (this.look(this.dir, false) != null) {
    		this.dir += 4;
    		run(dir);
    	} else {
    		run(dir);
    	}
    }
    
    @Override
    public CritterShape viewShape() {
        return CritterShape.DIAMOND;
    }

    @Override
    public javafx.scene.paint.Color viewColor() {
        return Color.VIOLET;
    }
}
