package proj3;

import java.util.Random;

/**
 * 			  Creates a deck of cards that can be shuffled and dealt out to a tableau			  
 * 			  Class Invariants: deck is not empty or null once initialized, enum Ranks
 * 								& Suits are not empty or null
 * @version   Completed Nov 7 : Due Nov 7
 * @author    Thomas Hervey <h46@umbc.edu>
 * @project	  CMSC202 -Fall 2011 -Project 3
 * @section   01
 */
public class Deck {
	//Declaring a size for a standard deck of cards (minus the two Jokers)
	int deckSize = 52;
	//Declaring array for suits, ranks and deck of cards
	Suit[] suits;
	Rank[] ranks;
	Card[] deck;

	/**
	 * Constructor - new deck object that is an array of 52 cards
	 */
	public Deck() {
		//Adds all of the enumerated values for each array
		suits = Suit.values();
		ranks = Rank.values();
		//Creating 52 card objects with rank and suit in order
		deck = new Card[deckSize];
		//Declaring a counter to get all the way through 52 cards
		int counter = 0;
		//For each suit
		for(int x = 0; x < suits.length; x ++) {
			//For each rank
			for(int y = 0; y < ranks.length; y ++) {
				//Create a new card object with new rank and/or suit value
				deck[counter] = new Card(suits[x], ranks[y]);
				//Increment our counter (should be 52 by the end)
				counter += 1;
			}
		}
	}

	/**
	 * Shuffles the deck by using a random number generator
	 * created by the gui's seed
	 * Pre-condition: seed isn't null, deck isn't null or empty
	 * Post-condition: deck is shuffled
	 *
	 * @param  long seed - shuffle seed number
	 * @return none
	 */
	public void Shuffle(long seed) {
		if(deck == null || deck[0] == null) { System.out.println("Sorry, you have no deck to shuffle"); }
		else {
			// new random number generator
			Random randomNumberGenerator = new Random(seed);
			// temporary variable used for switching
			Card temp;
			int n = deck.length -1;
			// shuffle the deck
			for(int i = n; i > 0 ; i--) {
				int randomNumber = randomNumberGenerator.nextInt(i + 1);
				temp = deck[i];
				deck[i] = deck[randomNumber];
				deck[randomNumber] = temp;
			}
		}
	}

	/**
	 * Simulates drawing a card by removing the top card of our
	 * deck and placing it in the last spot of our tableau
	 * Pre-condition: deck isn't null or empty
	 * Post-condition: deck is one card smaller and that one card is in the tableau
	 *
	 * @param  none
	 * @return the top card of the deck (the first element of the deck array)
	 */
	public Card drawCard() {
		if(deck == null || deck[0] == null) { return null; }
		else {
			//Getting the card on the top of the deck
			Card topCard = deck[0];
			//Making the top of the deck null
			deck[0] = null;
			//For each of the remaining cards in our deck, push their location up one
			for(int i = 1; i < deck.length; i++)
			if(deck[i-1] == null) {
				deck[i-1] = deck[i];
				deck[i] = null;
			}
		return topCard;
		}
	}
	
	/**
	 * Main method for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Deck test = new Deck();
		System.out.println(test.drawCard());
		System.out.println(test.drawCard());
		System.out.println(test.drawCard());
		for(int i = 0; i < test.deck.length; i++)
			System.out.println(test.deck[i]);
		test.Shuffle(12345);
		for(int i = 0; i < test.deck.length; i++)
			System.out.println(test.deck[i]);
		System.out.println(test.drawCard());
		test.Shuffle(55555);
		System.out.println(test.drawCard());
	}
}
