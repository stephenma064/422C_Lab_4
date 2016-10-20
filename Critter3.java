package assignment4;

/**
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
        // erosion
        Critter3 rock = new Critter3();
        reproduce(rock,Critter.getRandomInt(8));
    }
}
