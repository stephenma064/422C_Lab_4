package assignment4;

/**
 * Created by Stephen on 10/18/2016.
 */
public class Rock extends Critter{
    @Override
    public String toString() {
        return "R";
    }

    public boolean fight(String not_used) {
        return true;
    }

    public void doTimeStep() {
        // erosion
        Rock rock = new Rock();
        reproduce(rock,Critter.getRandomInt(8));
    }
}
