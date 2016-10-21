// Main.java
/** CRITTERS 
 * EE422C Project 4 submission by
 * Stephen Ma szm99
 * Eric Su es25725
 * 
 * Stephen Ma Slip days used: <1>
 * Eric Su Slip days used: <2>
 * Fall 2016
 */
package assignment4; // cannot be in default package

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;    // scanner connected to keyboard input, or input file
    private static String inputFile;    // input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;    // if test specified, holds all console output
    private static String myPackage;    // package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;    // if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     *
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name,
     *             and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        label:
        while (true) {
            System.out.print("critters>");
            String input = kb.nextLine().trim();
            String[] inputArray = input.split(" ");
            try {
                String command = inputArray[0];
                switch (command) {
                    case "quit":
                    	if (inputArray.length != 1) {
                    		throw new InvalidInputException(input);
                    	}
                    	break label;
                    case "show":
                    	if (inputArray.length != 1) {
                    		throw new InvalidInputException(input);
                    	}
                    	Critter.displayWorld();
                    	break;
                    case "step":
                    	if (inputArray.length > 2) {
                    		throw new InvalidInputException(input);
                    	}
                    	// If no int specified, then defaults to 1 step
                    	if (inputArray.length == 1) {
                    		Critter.worldTimeStep();
                    		break;
                    	}
                    	// Attempt to parse specified step count. Invalid input 
                    	// Exception will be thrown and caught below if parsing fails
                    	int j = Integer.parseInt(inputArray[1]);
                    	for (int i = 0; i < j; i++) {
                    		Critter.worldTimeStep();
                    	}
                        break;
                    case "seed":
                    	if (inputArray.length != 2) {
                    		throw new InvalidInputException(input);
                    	}                  	
                        Long seed = new Long(inputArray[1]);
                        Critter.setSeed(seed);
                        break;
                    case "make":
                    	if (inputArray.length < 2 || inputArray.length > 3) {
                    		throw new InvalidInputException(input);
                    	}
                    	// If no count is specified, defaults to 1
                    	if (inputArray.length == 2) {
                    		Critter.makeCritter(inputArray[1]);
                    		break;
                    	}
                    	// Attempt to parse specified make count. Invalid input 
                    	// Exception will be thrown and caught below if parsing fails
                    	int k = Integer.parseInt(inputArray[2]);
                    	if (k < 0) {
                    		throw new InvalidInputException(input);
                    	}
                        for (int i = 0; i < k; i++) {
                            Critter.makeCritter(inputArray[1]);
                        }
                        break;
                    case "stats":
                    	if (inputArray.length != 2) {
                    		throw new InvalidInputException(input);
                    	}
                        Class<?> temp = Class.forName(myPackage+"."+inputArray[1]);
                        Class[] types = {List.class};
                        List<Critter> list = Critter.getInstances(inputArray[1]);
                        Method m = temp.getMethod("runStats",types);
                        m.invoke(null, list);
                        break;
                    default:
                        System.out.println("invalid command: " + input);
                        break;
                }
            } catch (NoClassDefFoundError | InvalidInputException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | 
            		ClassNotFoundException | NumberFormatException | InvalidCritterException | IndexOutOfBoundsException e) {
                System.out.println("error processing: " + input);
            }
        }
    }
}
