/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
    private static String myPackage;
    private static List<Critter> population = new java.util.ArrayList<Critter>();
    private static List<Critter> babies = new java.util.ArrayList<Critter>();

    // Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static java.util.Random rand = new java.util.Random();

    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new java.util.Random(new_seed);
    }
    // test test

    /* a one-character long string that visually depicts your critter in the ASCII interface */
    public String toString() {
        return "";
    }

    private int energy = 0;

    protected int getEnergy() {
        return energy;
    }

    private int x_coord;
    private int y_coord;

    /**
     * This method is called within critters and moves the critter one spot
     * it also updates the energy
     *
     * @param direction the direction in which to move the critter. takes values
     *                  [0, 7] and moves it in that direction. 0 is to the right 2
     *                  is up 4 is to the left etc...
     */
    protected final void walk(int direction) {
        // This block modifies the x axis
        if (direction == 0 || direction == 1 || direction == 7) {
            x_coord = (x_coord + 1) % Params.world_width;
        } else if (direction == 3 || direction == 4 || direction == 5) {
            x_coord -= 1;
            if (x_coord < 0) {
                x_coord += Params.world_width;
            }
        }
        // This block modifies the y axis
        if (direction == 5 || direction == 6 || direction == 7) {
            y_coord = (y_coord + 1) % Params.world_height;
        } else if (direction == 1 || direction == 2 || direction == 3) {
            y_coord -= 1;
            if (y_coord < 0) {
                y_coord += Params.world_height;
            }
        }
        // update the energy
        energy -= Params.walk_energy_cost;
    }

    protected final void run(int direction) {

    }

    protected final void reproduce(Critter offspring, int direction) {
    }

    public abstract void doTimeStep();

    public abstract boolean fight(String oponent);

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
     * an InvalidCritterException must be thrown.
     * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
     * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
     * an Exception.)
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void makeCritter(String critter_class_name) throws InvalidCritterException {
        Critter critter;
        try {
            Class c = Class.forName("assignment4." + critter_class_name);
            critter = (Critter) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new InvalidCritterException(critter_class_name);
        }
        critter.x_coord = getRandomInt(Params.world_width);
        critter.y_coord = getRandomInt(Params.world_height);
        critter.energy = Params.start_energy;
        population.add(critter);
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
        List<Critter> result = new java.util.ArrayList<Critter>();

        return result;
    }

    /**
     * Prints out how many Critters of each type there are on the board.
     *
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            Integer old_count = critter_count.get(crit_string);
            if (old_count == null) {
                critter_count.put(crit_string, 1);
            } else {
                critter_count.put(crit_string, old_count.intValue() + 1);
            }
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();
    }

    /* the TestCritter class allows some critters to "cheat". If you want to
     * create tests of your Critter model, you can create subclasses of this class
     * and then use the setter functions contained here.
     *
     * NOTE: you must make sure that the setter functions work with your implementation
     * of Critter. That means, if you're recording the positions of your critters
     * using some sort of external grid or some other data structure in addition
     * to the x_coord and y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {
        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }


        /*
         * This method getPopulation has to be modified by you if you are not using the population
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /*
         * This method getBabies has to be modified by you if you are not using the babies
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.  Babies should be added to the general population
         * at either the beginning OR the end of every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
    }

    /**
     * This method is simulates one step for every critter in the world of our critters
     */
    public static void worldTimeStep() {
        // Run the time step on the whole population
        // Don't forget the rest energy cost
        for (Critter c : population) {
            c.doTimeStep();
            c.energy -= Params.rest_energy_cost;
        }
        Set<Critter> temp = new HashSet<Critter>();
        /*
        This next block goes through the whole population, finds conflicts, and resolves them
         */
        for (int i = 0; i < population.size(); i++) {
            for (int j = i + 1; j < population.size(); j++) {
                if (sameSquare(population.get(i), population.get(j))) {
                    temp.add(population.get(i));
                    temp.add(population.get(j));
                }
            }
            if (temp.size() != 0) {
                encounter(temp);
                temp.clear();
            }
        }
        // add the babies
        for (Critter b : babies) {
            population.add(b);
        }
        // cull the dead
        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).energy <= 0) {
                population.remove(i);
                i--;
            }
        }
    }

    /**
     * Checks if two critters a and b occupy the same spot
     *
     * @param a first critter
     * @param b second critter
     * @return true if in the same square, false if not
     */
    public static boolean sameSquare(Critter a, Critter b) {
        return a.x_coord == b.x_coord && a.y_coord == b.y_coord;
    }

    /**
     * given a set of critters resolve their encounters.
     * critters can cointain more than one critter when resolving
     *
     * @param critters a set of critters that are in the same square
     *                 critters will always have more than 2 Critters
     * @return a set of critters that are failures
     */
    public static void encounter(Set<Critter> critters) {
        Object[] array = critters.toArray();
        Critter[] crit = new Critter[critters.size()];
        /*
        crit is an array of all the critters we are trying to resolve
        we can modify the values of crit since they are pass by value?
         */
        for (int i = 0; i < critters.size(); i++) {
            crit[i] = (Critter) array[i];
        }
        // the index of the winner , this is for when there are multiple conflicts
        int winner = 0;
        for (int i = 1; i < array.length; i++) {
            int aFightNum = 0, bFightNum = 0;
            if (crit[winner].fight(crit[i].toString())) {
                aFightNum = getRandomInt(crit[winner].energy);
            }
            if (crit[i].fight(crit[winner].toString())) {
                bFightNum = getRandomInt(crit[i].energy);
            }
            if (aFightNum >= bFightNum) {
                crit[winner].energy += crit[i].energy / 2;
                crit[i].energy = 0;
            } else {
                crit[i].energy += crit[winner].energy / 2;
                crit[winner].energy = 0;
                winner = i;
            }
        }
    }


    public static void displayWorld() {
    }
}
