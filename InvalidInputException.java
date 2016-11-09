package assignment5;
/* CRITTERS GUI <InvalidInputException.java>
 * EE422C Project 4b submission by
 * Replace <...> with your actual data.
 * Stephen Ma
 * szm99
 * 16480
 * Slip days used: <1>
 * Eric Su
 * es25725
 * 16475
 * Slip days used: <2>
 * Fall 2016
 */
/**
 * Invalid input exception.
 * @author ericsu
 *
 */
public class InvalidInputException extends Exception {
	private String invalidInput;
	
	public InvalidInputException(String invalidInput) {
		this.invalidInput = invalidInput;
	}
	
	public String toString() {
		return "error processing: " + invalidInput;
	} 
}
