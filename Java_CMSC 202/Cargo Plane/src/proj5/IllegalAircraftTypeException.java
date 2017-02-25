package proj5;

public class IllegalAircraftTypeException extends Exception{
	private String i;
	
	/**
	 * 
	 * @param i
	 */
	public IllegalAircraftTypeException(String i){
		this.i = i;
	}
	
	/**
	 * Accessor method for illegal argument number exception messages that'll get logged when the error occurs
	 * Pre-condition:  there is a message to be returned
	 * Post-condition: the message is displayed (or in this case logged to our logFile)
	 * @param  none
	 * @return i - the message passed to the constructor 
	 */
	public String getMessage(){
		return i;
	}
}
