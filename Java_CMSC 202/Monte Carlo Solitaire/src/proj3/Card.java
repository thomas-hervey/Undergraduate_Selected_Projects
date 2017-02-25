package proj3;

/**
 * 			  Creates a card object with a rank and a suit that is used to 
 * 			  represent one instance of a card in a deck using encapsulation.
 * 			  Class Invariants: the suit and rank of the card are not null, 
 * 								the Card objects are immutable
 * @version   Completed Nov 7 : Due Nov 7
 * @author    Thomas Hervey <h46@umbc.edu>
 * @project	  CMSC202 -Fall 2011 -Project 3
 * @section   01
 */
public class Card {

	//Creating instance variables for a card's suit and rank
	private Suit suit;
	private Rank rank;
	
	/**
	 * Constructor - new card object that has a suit and a rank
	 *
	 * @param suit
	 * @param rank
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * Accessor for retrieving the card's suit
	 * Pre-condition: suit isn't null
	 * Post-condition: none
	 *
	 * @param  none
	 * @return Suit suit - card's suit
	 */
	public Suit getSuit() {
		if(suit == null) { return null; }
		else { return suit; }
	}
	
	/**
	 * Accessor for retrieving the card's rank
	 * Pre-condition: rank isn't null
	 * Post-condition: none
	 *
	 * @param  none
	 * @return Rank rank - card's rank
	 */
	public Rank getRank() {
		if(rank == null) { return null; }
		else { return rank; }
	}
	
	/**
	 * toString unit test element
	 *
	 * @param  none
	 * @return String ourString - converted string
	 */
	public String toString() {
		String ourString = "";
		ourString += this.getSuit();
		ourString += " ";
		ourString += this.getRank();
		return ourString;
	}
	
	/**
	 * Main method for unit testing
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Card test = new Card(Suit.HEARTS, Rank.ACE);
		System.out.println(test.getSuit());
		System.out.println(test.getRank());
		test.rank = Rank.ACE;
		System.out.println(test.getRank());
		test.suit = Suit.DIAMONDS;
		System.out.println(test.getSuit());
		test.suit = null;
		System.out.println(test.suit);
		System.out.println(test.getSuit());
	}
}
