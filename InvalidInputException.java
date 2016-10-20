package assignment4;

/**
 * Invalid input exception.
 * @author ericsu
 *
 */
public class InvalidInputException extends Exception {
	String invalidInput;
	
	public InvalidInputException(String invalidInput) {
		this.invalidInput = invalidInput;
	}
	
	public String toString() {
		return "error processing: " + invalidInput;
	} 
}
