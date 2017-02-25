/**
 * 
 */
package proj1;

/**
 * 			   This Garage class is the second Project 1 class designed
 * 			   to get us students used to object oriented programming,
 * 			   encapsulation, and general programming experience.
 * 			   This class is used to access and modify conditions
 * 			   concerning the garage objects' states including accessibility
 *             (if the garage object's door is open/closed), occupancy
 *             (occupied/vacant) and condition (working/broken).
 * 
 * @version    Mostly completed 9/27/11 : Due 10/5/11 
 * @author     Thomas Hervey <h46@umbc.edu>
 * @project    CMSC 202 -Fall 2011 -Project 1
 * @section    01
 */
public class Garage {

	// Three garage variables resulting in six states
	private boolean accessible;
	private boolean occupancy;
	private boolean condition;
	
	// garage constructor
	public Garage() {
		//A false state means that the door is closed
		accessible = false;
		//A false occupancy means that the garage is empty
		occupancy = false;
		//A true condition means that the garage is working properly
		condition = true;
	}	

	/**
	 * This method checks to see if we can open the garage's door and make it accessible. 
	 * Depending on the current accessibility and condition, the door will/will not open 
	 * and an appropriate string will be returned to be printed in the Project1 class.
	 * garageChoice should not be empty or null.
	 * 
	 * @param  garageChoice - string of user's garage number to promote understandability
	 * @return open - string of appropriate message as to the success of opening the door
	 */
	public String openDoor(String garageChoice) {
		//Declaring a string variable that will return the proper resulting text
		String open = "";
		if (accessible == true) {
			if (condition == false) {
				//Door is already open but the garage is broken
				open = ("Sorry, the door is already open but the garage is broken.");
			}
			else {
				//Door is already open
				open = ("Garage " + garageChoice + "'s door is already open silly.");
			}
			
		}
		else if (accessible == false) {
			//Door is closed but is broken
			if (condition == false) {
				open = ("Sorry, Garage " + garageChoice + " is broken so the "
						+ "door will remain down.");
			}
			else {
				//Door is closed and the garage isn't broken so the door can & is opened
				accessible = true;
				open = ("Garage " + garageChoice + " is now open.");
			}		
		}
		return open;
	}
		
	/**
	 * This method checks to see if we can close the garage's door and make it unaccessible. 
	 * Depending on the current accessibility and condition, the door will/will not close 
	 * and an appropriate string will be returned to be printed in the Project1 class.
	 * garageChoice should not be empty or null.
	 * 
	 * @param  garageChoice - string of user's garage number to promote understandability
	 * @return close - string of appropriate message as to the success of closing the door
	 */
	public String closeDoor(String garageChoice) {
		//Declaring a string variable that will return the proper resulting text
		String close = "";
		if (accessible == false) {
			//Door is already closed and the garage is broken
			if (condition == false) { close = 
				("Sorry, the door is already closed but the garage is broken."); }
			//Door is already closed
			else { close = ("Garage " + garageChoice + "'s door is already closed silly."); }
		}
		else if (accessible == true) {
			if (condition == false) {
				//Door is open but the garage is broken so the door cannot be closed
				close = ("Sorry the garage is broken so the door will remain open.");
			}
			else {
				//Door is open and the garage isn't broken so the door can & is closed
				accessible = false;
				close = ("Garage " + garageChoice + " is now closed.");
			}		
		}
		return close;
	}
		
	/**
	 * This method checks to see if we can pull a car into the garage and make it occupied. 
	 * Depending on the current accessibility and condition, a car can/cannot be pulled in 
	 * and an appropriate string will be returned to be printed in the Project1 class.
	 * garageChoice should not be empty or null.
	 * 
	 * @param  garageChoice - string of user's garage number to promote understandability
	 * @return pullIn - string of appropriate message as to the success of pulling a car in
	 */	
	public String pullCarIn(String garageChoice) {
		//Declaring a string variable that will return the proper resulting text
		String pullIn = "";
		if (accessible == false) {
			//Door is closed and the garage is broken
			if (condition == false) {
				pullIn = ("Sorry, you cannot pull in because Garage " + garageChoice 
						+ " is broken and the door is also down.");
			}
			//Door is closed but the garage isn't broken, thus pulling a car in breaks the door
			else {
				pullIn = ("Nice job, Garage " + garageChoice + "'s door was down so now\n" +
						"the garage's door is broken and you cannot pull the car in.");
				condition = false;
			}
		}
		else if (accessible == true) {
			//Door is open but the garage is broken
			if (condition == false) {
				pullIn = ("Sorry, you cannot pull in because Garage " + garageChoice 
						+ " is broken even though the door is open.");
			}
			else if (occupancy == true) {
				//Door is open but there was a car inside so pulling a car in causes an accident, breaking the garage
				pullIn = ("Nice job, Garage " + garageChoice + "'s door was open but\n"
						+ "there was a car inside so you crashed into it causing an\n"
						+"accident making the garage's condition broken and unusuable.");
				condition = false;
			}
			else {
				//Door is open and the garage is unoccupied and isn't broken so the car can & is pulled in
				occupancy = true;
				pullIn = ("You have pulled into Garage " + garageChoice + ".");
			}
		}
		return pullIn;
	}
		
	/**
	 * This method checks to see if we can back a car out of the garage and make it vacant. 
	 * Depending on the current accessibility, vacancy and condition, a car can/cannot be pulled 
	 * in and an appropriate string will be returned to be printed in the Project1 class.
	 * garageChoice should not be empty or null.
	 * 
	 * @param  garageChoice - string of user's garage number to promote understandability
	 * @return backOut - string of appropriate message as to the success of backing a car out
	 */	
	public String backCarOut(String garageChoice){
		//Declaring a string variable that will return the proper resulting text
		String backOut = "";
		//There isn't a car in the garage to pull out
		if (occupancy == false) {
			backOut = ("There is no car in Garage " + garageChoice + " silly. You can't " 
					+ "back your car out.");
		}
		else if (accessible == false){
			//Door is closed and the garage is broken so no car can pull out
			if (condition == false) {
				backOut = ("Sorry, you cannot back out because Garage " + garageChoice 
						+ "\nis broken and the door is also down.");
			}
			else{
				//Door is closed but the garage isn't broken, thus pulling the car our broke the door
				condition = false;
				backOut = ("Nice job, Garage " + garageChoice + "'s door was down so now\n" 
						+ "the garage's door is broken and you cannot back the car out.");
			}
		}
		else if (accessible == true){
			//Door is open but the garage is broken/unaccessible because there was an accident
			if (condition == false) {
				backOut = ("Sorry, you cannot back the car out because Garage " + garageChoice 
						+ "\nhad an accident and you cannot get out even \nthough the door is open.");
			}
			else{
				//Door is open and the garage isn't broken, thus the car can be backed out
				backOut = ("You have backed out of Garage " + garageChoice);
				occupancy = false;
			}
		}
		return backOut;
	}
		
	/**
	 * Used to check if the calling garage's occupancy is empty and able to be used.
	 * usable - vacant and working, or if it is not usable - occupied and/or broken
	 * 
	 * @param  none
	 * @return true/false - if a garage is empty and working or not
	 */
	public boolean findEmptySpace(){
		if (occupancy == false) {
			// No car in the garage, but the garage is broken/unaccessible
			if (condition == false) { return false; }
			/* No car, garage isn't broken, provided a user makes sure 
			 * the door opens, they could pull a car in */
			else { return true; }
		}
		//There is a car in the garage
		else { return false; }
	}
		
	/**
	 * Displays a message to the user about all of the known
	 * conditions of the garage that calls it including accessibility,
	 * occupancy and working condition.
	 * 
	 * @param  garageChoice - string of user's garage number to promote understandability
	 * @return spaceState - string of appropriate message as to the garage's state of
	 *         accessibility(open/close), vacancy(occupied/vacant) and condition(working/broken)
	 */
	public String displaySpaceState(String garageChoice){
		// Boolean garage variables
		String open = "open";
		String occupied = "occupied";
		String working = "working";
		String accident = "no accidents";
		String spaceState = "";
		/* If any of the garage variables are currently false, change the according 
		 * string variable to a false real-life equivalent. For condition, check to
		 * see if the garage is broken or had an accident (because of a down door or
		 * another car) making its condition false either way.
		*/
		if (accessible == false) { open = "closed"; }
		if (occupancy == false)  { occupied = "vacant"; }
		if (condition == false) {
			if (occupancy == true)
				if (accessible == true) {
					accident = "there is an accident";
					working = "broken";
				}
			else {
				working = "broken";
				spaceState = ("Garage " + garageChoice + " is "  + open + ", " 
					+ occupied + ", " + working + " and " + accident +".");
			}
		}
		return spaceState;
	}
}
