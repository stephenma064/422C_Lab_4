package assignment4;

/**
 * Created by Stephen on 10/17/2016.
 *
 */
public class Steve extends Critter{

    @Override
    public String toString() {
        return "$";
    }

    private int dir;

    public Steve() {
        dir = Critter.getRandomInt(8);
    }
    public boolean fight(String other) {
        return other.equals("$") || other.equals("A");
    }

    public void doTimeStep() {
        run(dir);
    }
}
