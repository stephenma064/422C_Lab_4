package assignment4;

/**
 * RockCritter
 * 
 * An inanimate critter. This critter obviously cannot move or reproduce 
 * (in the sense of how most animals do that is) but it can "erode" away
 * creating smaller, weaker parts of the original rock. Other critters 
 * will be intimidated by this critter's rock-solid appearance and endurance
 * and as a result will always get into 'fights' with other critters.
 * 
 * Created by Stephen on 10/18/2016.
 */
public class Critter3 extends Critter{
    @Override
    public String toString() {
        return "3";
    }

    public boolean fight(String not_used) {
        return true;
    }

    public void doTimeStep() {
        // Erosion
        Critter3 rock = new Critter3();
        reproduce(rock,Critter.getRandomInt(8));
    }
}