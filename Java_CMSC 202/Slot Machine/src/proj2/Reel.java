/**
 * 
 */
package proj2;

/**
 * 			   This class represents one instance of three Reels
 * 			   in our slot machine. In this class, our list of
 * 			   symbols (derived from the Symbols class) is created,
 * 			   which can be accessed by the getSymbols method.
 * 			   Class invariant- all instances of the Reel class
 * 			   must have an image that is not null from the enum
 * 			   Symbols class. The enum values are not null or 0
 * 
 * @version    Completed 10/21/11 : Due 10/24/11
 * @author     Thomas Hervey <h46@umbc.edu>
 * @project    CMSC 202 -Fall 2011 -Project 2
 * @section    01
 *
 */
public class Reel {

	Symbols [] symbolList = new Symbols [6];
	/**
	 * Constructor that sets the reels symbols
	 * Precondition -none
	 * Postcondition -none
	 * @param none
	 * @return none
	 */
	public Reel() {
		symbolList[0] = Symbols.SEVEN;
		symbolList[1] = Symbols.WATERMELON;
		symbolList[2] = Symbols.ORANGE;
		symbolList[3] = Symbols.PLUM;
		symbolList[4] = Symbols.LEMON;
		symbolList[5] = Symbols.CHERRY;
	}
	
	/**
	 * Accessor method for a reel's symbols
	 * Precondition -none
	 * Postcondition -none
	 * @param none
	 * @return specific reel's symbols
	 */
	public Symbols[] getSymbols() { return symbolList; }
	
	public static void main(String[] args) {
		//Create a test object to perform effective unit testing in Reel
		//Also, a unit test for the Reel constructor
		Reel test = new Reel();
		//Printing out the value for each index of symbolList
		for(int i = 0; i < test.symbolList.length; i++)
			System.out.println(test.symbolList[i]);
		
		//Check assignment changes to our array list indices 
		System.out.println(test.symbolList[1]);
		test.symbolList[2] = test.symbolList[1];
		System.out.println(test.symbolList[2]);
	}
}
