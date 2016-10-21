// InvalidCritterException.java
/** CRITTERS 
 * EE422C Project 4 submission by
 * Stephen Ma szm99
 * Eric Su es25725
 * 
 * Stephen Ma Slip days used: <1>
 * Eric Su Slip days used: <2>
 * Fall 2016
 */

package assignment4;

public class InvalidCritterException extends Exception {
	String offending_class;
	
	public InvalidCritterException(String critter_class_name) {
		offending_class = critter_class_name;
	}
	
	public String toString() {
		return "Invalid Critter Class: " + offending_class;
	}

}
