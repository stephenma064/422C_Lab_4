package assignment4;

/**
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
        dir = Critter.getRandomInt(8);
    }
    public boolean fight(String other) {
        return other.equals("4") || other.equals("4");
    }

    public void doTimeStep() {
        run(dir);
    }
}
