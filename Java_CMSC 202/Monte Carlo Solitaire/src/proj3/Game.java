package proj3;

/**
 * 			  This class provides all methods used by the GUI for Monte Carlo Solitaire. 
 * 			  Acting as the skeleton structure of a game flow, a new game/replay can be
 * 			  called with the following effects of GUI clicking & removing (removeCards
 * 			  & Consolidate) and then dealing new cards to continue playing.
 * 			  Class Invariants: The tableau and our deck are not null
 * 
 * 			  Help from CMSC Help Center, 202 TAs, Zach Hullihen
 * @version   Completed Nov 7 : Due Nov 7
 * @author    Thomas Hervey <h46@umbc.edu>
 * @project	  CMSC202 -Fall 2011 -Project 3
 * @section   01
 */
public class Game extends java.lang.Object {

	//Total number of cards left in the deck
	private int cardsLeft;
	//Current player score
	private int ourScore;
	//Random number seed that simulates a shuffle
	private long seedNumber;
	//Creating our deck
	Deck ourDeck;
	//Creating our tableau dimensions
	int tableauRows;
	int tableauColumns;
	//Creating a queue to use for consolidating remaining cards
	//to the likely size of our tableau
	Card[] ourQueue = new Card[25];
	//Creating our tableau
	Card ourTableau[][];
	
	/**
	 * Constructor - new Monte Carlo Solitaire object
	 *
	 * @param  int tableauRows - number of table rows
	 * @param  int tableauColumns - number of table columns
	 * @return none
	 */
	public Game(int tableauRows, int tableauColumns) {
		cardsLeft = 52;
		ourScore = 0;
		//Initializing our deck
		ourDeck = new Deck();
		this.tableauRows = tableauRows;
		this.tableauColumns = tableauColumns;
		//Create our row x column (likely 5x5) 2D array to simulate our tableau
		ourTableau = new Card[tableauRows][tableauColumns];
	}

	/**
	 * Starts a new game of solitaire for the specified game number in accordance to project specifications.
	 * Pre-condition: gameNumber is not 0 or less
	 * Post-condition: a new game has started with a new tableau of cards based upon the seed number and shuffle
	 *
	 * @param  long gameNumber - game setup seed
	 * @return none
	 */
	public void newGame(long gameNumber) {
		if(gameNumber > 0){
			cardsLeft = 52;
			ourScore = 0;
			seedNumber = gameNumber;
			this.ourDeck = new Deck();
			//Shuffle the deck before dealing them to the tableau
			ourDeck.Shuffle(gameNumber);			
			//Filling our tableau with our deck of cards
			for(int a = 0; a < tableauRows; a++) { //By row
				for(int b = 0; b < tableauColumns; b++) { //By column
					ourTableau[a][b] = ourDeck.drawCard();
					cardsLeft -= 1;
				}
			}
		}
	}
	

	/**
	 * Returns the number of cards left in the deck
	 * Pre-condition: cardsLeft > 0
	 * Post-condition: none
	 *
	 * @param  none
	 * @return int cardsLeft - number of cards
	 */
	public int numberOfCardsLeft() {
		if(cardsLeft <= 0) { return 0; }
		else { return cardsLeft; }
	}

	/**
	 * Returns the player's score for the current game
	 * Pre-condition: ourScore > 0
	 * Post-condition: none
	 *
	 * @param  none
	 * @return int ourScore - the score of the game
	 */
	public int getScore() {
		if(ourScore <= 0) { return 0; }
		else { return ourScore; }
	}

	/**
	 * Returns a Suit enum representing the suit of the card at the specified coordinate in the tableau
	 * Pre-condition: the coordinate is not null
	 * Post-condition: none
	 *
	 * @param  Coordinate coordinate - coordinate to check
	 * @return Suit cardSuit - the suit of the card
	 */
	public Suit getSuit(Coordinate coordinate) {
		if(ourTableau[coordinate.getRow()][coordinate.getColumn()] == null) { return null; }
		else { return ourTableau[coordinate.getRow()][coordinate.getColumn()].getSuit(); }
	}

	/**
	 * Returns a Rank enum representing the rank of the card at the specified coordinate in the tableau
	 * Pre-condition: the coordinate is not null
	 * Post-condition: none
	 *
	 * @param  Coordinate coordinates - coordinate of check
	 * @return Rank cardRank - the rank of the card
	 */
	public Rank getRank(Coordinate coordinate) {
		if(ourTableau[coordinate.getRow()][coordinate.getColumn()] == null) { return null; }
		else { return ourTableau[coordinate.getRow()][coordinate.getColumn()].getRank(); }
	}
	

	/**
	 * Returns a help message explaining the rules of the game.
	 * Pre-condition: none
	 * Post-condition: the user has at least a slight idea of how to play
	 *
	 * @param  none
	 * @return rules - the rules of the game
	 */
	public java.lang.String getHelpText() {
		String rules = "";
		rules += "-----Monte Carlo Solitare Help Menu-----";
		rules += "\nThis program is a simulated game of the Solitare version made popular in \nMonte Carlo.";
		rules += "To play this game, eliminate adjacent pairs of cards (by rank) \nby clicking on both cards.";
		rules += "Pairs are considered adjacent if they are \nneighbors above/below, next to or diagonal to one";
		rules += "another. Once you have \neliminated all of the present pairs, click the remaining card stack to";
		rules += "\ncondolidate the remaining cards(moves them up in line filling the new gaps) \nand fill new";
		rules += "cards in. As you match up piars, your score will increase by two \npoints and your remaining";
		rules += "card number will decrease by that same amount. \nKeep playing until you either run out of pairs,";
		rules += " or you have eliminated all of \nthe cards. If you click on a pair that are not a match, the";
		rules += " pair will deselect \nand nothing will change.";
		return rules;
	}

	/**
	 * Restarts the current game using the same seed
	 * Therefore, the cards are shuffled the same way
	 * Pre-condition: seedNumber > 0
	 * Post-condition: the current game is restarted using the same shuffle
	 *
	 * @param  none
	 * @return none
	 */
	public void replay() {
		if(seedNumber > 0) { newGame(seedNumber); }
	}

	/**
	 * Moves cards in the tableau towards the top (left and up) to replace the cards that were removed. 
	 * Then deals cards from the deck to refill the empty spaces at the bottom of the tableau.
	 * Pre-condition: none
	 * Post-condition: the tableau has had every value pushed up and to the left and new cards have been filled in
	 *
	 * @param  none
	 * @return none
	 */
	public void consolidate() {
		//Declaring a counter to know how far we have gone through our tableau
		int queueCounter = 0;
		//Loop through the remaining cards in our tableau and appends them to ourQueue
		for(int a = 0; a < tableauRows; a++) {
			for(int b = 0; b < tableauColumns; b++) {
				if(ourTableau[a][b] != null){
					ourQueue[queueCounter] = ourTableau[a][b];
					//System.out.println("T---");
					//System.out.println(ourTableau[a][b]);
					//System.out.println("Q+++");
					//System.out.println(ourQueue[queueCounter]);
					ourTableau[a][b] = null;
					//System.out.println("/////");
					//System.out.println(ourTableau[a][b]);
					queueCounter ++;
				}
			}	
		}
		/*System.out.println("**********");
		for(int i = 0; i < ourQueue.length; i ++)
			System.out.println(ourQueue[i]);
		System.out.println("**************");
		*/

		//Declaring a counter to go through all of ourQueue to place beck into the tableau
		int queueCounter2 = 0;
		//Replacing the queued cards back into the tableau
		for(int x = 0; x < tableauRows; x ++) {
			for(int y = 0; y < tableauColumns; y ++) {
				if(queueCounter > 0)
					ourTableau[x][y] = ourQueue[queueCounter2];
				else{
					ourTableau[x][y] = ourDeck.drawCard();
					cardsLeft --;
				}
				//System.out.println(ourTableau[x][y]);
				//System.out.println(ourQueue[queueCounter2]);
				queueCounter --;
				queueCounter2 ++;
			}	
		}		
	}

	/**
	 * Pre-condition: none
	 * Post-condition: getHint is active or not
	 *
	 * @param  none
	 * @return true - if the hint feature is functional / false - otherwise
	 */
	public boolean isHintImplemented(){
		return false;
	}
	

	/**
	 * Finds two matching cards according to the rules of the game 
	 * Pre-condition: there is at least one pair of removable cards on the tableau
	 * Post-condition: the user has some idea as to what they are doing
	 *
	 * @param  none
	 * @return an array of 2 Coordinates for the matching cards if found, null if no match is found
	 */
	public Coordinate[] getHint(){
		return null;
	}

	/**
	 * Determines if the cards at the specified tableau locations are a match according to the rules of 
	 * Monte Carlo Solitaire and if so, removes them from the tableau
	 * Pre-condition: points' coordinates are not off of the tableau
	 * Post-condition: two cards will be removed if they are able to, otherwise they will remain in their place
	 *
	 * @param Coordinate coordinate1 - card #1
	 * @param Coordinate coordinate2 - card #2
	 * @return true - if cards can be removed / false - otherwise
	 */
	public boolean removeCards(Coordinate coordinate1, Coordinate coordinate2) {
		//Creating instance variables for the x and y coordinates of each of the two points
		int x = coordinate1.getRow();
		int y = coordinate1.getColumn();
		int i = coordinate2.getRow();
		int j = coordinate2.getColumn();

		//Checking to see if a location outside of the tableau has been selected for removal checking
		if(x < 0 || x > tableauRows || y < 0 || y > tableauColumns 
			|| i < 0 || i > tableauRows || j < 0 || j > tableauColumns) {
			System.out.println("One or more of your coordinates is not within the tableau boundaries");
			return false;
		}
		//Checks to see if the two points have the same suit and rank (we cannot have two of the same card)
		else if(ourTableau[x][y].getSuit() == ourTableau[i][j].getSuit())   //If the two suits are the same
			return false;
		//Checks to see if the two points have different ranks
		else if(ourTableau[x][y].getRank() != ourTableau[i][j].getRank())    //If the two ranks are different
			return false;
		else {
			//If the two points have different coordinates and ranks, check to see if they are neighbors
			if(isNeighbor(x, y, i, j) == true) {
				//If they are neighbors, increase our score by 2 (one for each card removed, and decrease our
				ourScore += 2;
				ourTableau[x][y] = null;
				ourTableau[i][j] = null;
				return true;
			}
			//The two points are not neighbors
			else { return false; }
		}
	}
	
	/**
	 * Checks to see if the given coordinates are neighbors in our tableau.
	 * This checks to see if, based on the coordinates of our first point,
	 * if the second point is a neighbor on our tableau. This includes the
	 * same coordinates, edge cases (checks only on the tableau and not beyond)
	 * and regular locations (first point isn't on an edge).
	 * Pre-condition: our x & y ordered pairs for both points are not null or
	 * 				  in the same location
	 * Post-condition: known if the two points are neighbors or not
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true - two points are neighbors / false - if they are not
	 */
	private boolean isNeighbor(int x, int y, int i, int j) {
		//The two points have the same coordinate
		if(x == i && y == j) { return false; }
		
		//If coordinate1 is an edge case
		
		//Coordinate1 is in the top row (row 0) 
		else if(x == 0) {
			//If coordinate1 is in the left most column (col 0) (top left corner)
			if(y == 0) {
				//If coordinate2 is to the right, lower right diagonal, or bottom of coordinate1
				if((isRight(x,y,i,j)) || (isLowerRightDiagonal(x,y,i,j)) || (isBottom(x,y,i,j)))
					return true;
				else
					return false;
			}
			//If coordinate1 is in the right most column (tableauColumns) (top right corner)
			else if(y == tableauColumns) {
				//If coordinate2 is to the left, lower left diagonal, or bottom of coordinate1
				if((isLeft(x,y,i,j)) || (isLowerLeftDiagonal(x,y,i,j)) || (isBottom(x,y,i,j)))
					return true;
				else
					return false;
			}
			//If coordinate1 is not in either of the top corners but is on the top row
			else {
				//If coordinate2 is to the left, lower left diagonal, bottom, lower right diagonal, or right of coordinate1
				if((isLeft(x,y,i,j)) || (isLowerLeftDiagonal(x,y,i,j)) || (isBottom(x,y,i,j)) || 
						(isLowerRightDiagonal(x,y,i,j)) || (isRight(x,y,i,j))) { return true; }
				else { return false; }
			}
		}
		//Coordinate1 is in the bottom row (tableauRows)
		else if(x == tableauRows) {
			//If coordinate1 is in the left most column (col 0) (bottom left corner)
			if(y == 0){
				//If coordinate2 is above, upper right diagonal, or right of coordinate1
				if(isUpper(x,y,i,j) || (isUpperRightDiagonal(x,y,i,j)) || (isRight(x,y,i,j))) { return true; }
				else { return false; }
			}
			//If coordinate1 is in the right most column (tableauColumns) (bottom right corner)
			else if(y == tableauColumns) {
				//if coordinate2 is above, upper left diagonal, or left of coordinate1
				if((isUpper(x,y,i,j)) || (isUpperLeftDiagonal(x,y,i,j)) || (isLeft(x,y,i,j))) { return true; }
				else { return false; }
			}
			//If coordinate1 is not in either of the bottom corners but is on the bottom row
			else{
				//If coordinate2 is left, upper left diagonal, upper, upper right diagonal, right of coordinate 1
				if((isLeft(x,y,i,j)) || (isUpperLeftDiagonal(x,y,i,j)) || (isUpper(x,y,i,j)) || 
						(isUpperRightDiagonal(x,y,i,j)) || (isRight(x,y,i,j))) { return true; }
				else { return false; }
			}
		}
		//Because all of the corners have been taken care of, we only have to check the left and right sides
		//Coordinate1 is in the first column (col 0)
		else if(y == 0){
			//If coordinate2 is above, upper right, right, lower right diagonal, below coordinate1
			if((isUpper(x,y,i,j)) || (isUpperRightDiagonal(x,y,i,j)) || (isRight(x,y,i,j)) || 
					(isLowerRightDiagonal(x,y,i,j)) || (isBottom(x,y,i,j))) { return true; }
			else { return false; }
		}
		//Coordinate1 is in the last column (tableauColumns)
		else if(y == tableauColumns){
			//If coordinate2 is above, upper left diagonal, left, lower left diagonal, below coordinate1
			if((isUpper(x,y,i,j)) || (isUpperLeftDiagonal(x,y,i,j)) || (isLeft(x,y,i,j)) || 
					(isLowerLeftDiagonal(x,y,i,j)) || (isBottom(x,y,i,j))) { return true; }
			else { return false; }
		}
		
		//If coordinate1 is not an edge case
		
		else if((isLeft(x,y,i,j)) || (isLowerLeftDiagonal(x,y,i,j)) || (isBottom(x,y,i,j)) || 
				(isLowerRightDiagonal(x,y,i,j)) || (isRight(x,y,i,j)) || (isUpperRightDiagonal(x,y,i,j)) || 
				isUpper(x,y,i,j) || isUpperLeftDiagonal(x,y,i,j)) { return true; }
		
		//The two coordinates are not neighbors
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is directly to the left of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not left
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly to the left of the first point)
	 * 		   false (if the second point isn't to the left)
	 */
	private boolean isLeft(int x, int y, int i, int j) {
		if(j == y - 1) {
			if(i == x) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is diagonally to the lower left of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not lower left diagonal
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is a lower left diagonal neighbor to the first point)
	 * 		   false (if the second point isn't lower left diagonal)
	 */
	private boolean isLowerLeftDiagonal(int x, int y, int i, int j) {
		if(j == y - 1) {
			if(i == x + 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is directly below coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not below
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly below the first point)
	 * 		   false (if the second point isn't below)
	 */
	private boolean isBottom(int x, int y, int i, int j) {
		if(j == y) {
			if(i == x + 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is diagonally to the lower right of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not lower right diagonal
	* @param   int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is a lower right diagonal neighbor to the first point)
	 * 		   false (if the second point isn't lower right diagonal)
	 */
	private boolean isLowerRightDiagonal(int x, int y, int i, int j) {
		if(j == y + 1) {
			if(i == x + 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is directly to the right of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not to the right
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly to the right of the first point)
	 * 		   false (if the second point isn't to the right)
	 */
	private boolean isRight(int x, int y, int i, int j) {
		if(j == y + 1) {
			if(i == x) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is diagonally to the upper right of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not upper right diagonal
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly to the top right of the first point)
	 * 		   false (if the second point isn't to the top right)
	 */
	private boolean isUpperRightDiagonal(int x, int y, int i, int j) {
		if(j == y + 1) {
			if(i == x - 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is directly above coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition:coordinate2 is or is not above
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly above the first point)
	 * 		   false (if the second point isn't above)
	 */
	private boolean isUpper(int x, int y, int i, int j) {
		if(j == y) {
			if(i == x - 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Checks to see if coordinate2 is diagonally to the upper left of coordinate1
	 * Pre-condition: x,y,i,j are not null, negative or beyond the tableau boundary
	 * Post-condition: coordinate2 is or is not upper left diagonal
	 * @param  int x - card #1 x-coord
	 * @param  int y - card #1 y-coord
	 * @param  int i - card #2 x-coord
	 * @param  int j - card #2 y-coord
	 * @return true (if the second point is directly to the upper left of the first point)
	 * 		   false (if the second point isn't to the upper left)
	 */
	private boolean isUpperLeftDiagonal(int x, int y, int i, int j) {
		if(j == y - 1) {
			if(i == x - 1) { return true; }
			else { return false; }
		}
		else { return false; }
	}
	
	/**
	 * Determines if the player has won the game by removing all the cards and thereby achieving a score of 52
	 * Pre-condition: none
	 * Post-condition: the winner has won or not yet won the game
	 *
	 * @param  none
	 * @return true - if the player has won / false - otherwise
	 */
	public boolean isWin() {
		if(cardsLeft == 0 && ourScore == 52) {
			System.out.println("You have won!");
			return true;
		}
		else { return false; }
	}

	/**
	 * Main method for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args){
		Game test = new Game(4,4);
		System.out.println(test.cardsLeft);
		System.out.println(test.ourScore);
		System.out.println(test.seedNumber);
		System.out.println(test.numberOfCardsLeft());
		test.cardsLeft = -3;
		System.out.println(test.numberOfCardsLeft());
		System.out.println(test.getScore());
		test.ourScore = -5;
		System.out.println(test.getScore());
		Coordinate testCoordinate = new Coordinate(0, 1);
		System.out.println(test.getSuit(testCoordinate));
		Coordinate testCoordinate2 = new Coordinate(-4, 1);
		System.out.println(test.getRank(testCoordinate2));
		System.out.println(test.getHelpText());
		test.cardsLeft = 5;
		System.out.println(test.cardsLeft);
		test.replay();
		System.out.println(test.cardsLeft);
		test.newGame(200);
		for(int a = 0; a < test.tableauRows; a++){ //By row
			for(int b = 0; b < test.tableauColumns; b++){ //By column
				System.out.println(test.ourTableau[a][b]);
			}
		}
		test.ourTableau[2][2] = null;
		System.out.println(test.ourTableau[2][2]);
		test.consolidate();
		for(int a = 0; a < test.tableauRows; a++){ //By row
			for(int b = 0; b < test.tableauColumns; b++){ //By column
				System.out.println(test.ourTableau[a][b]);
			}
		}
		Coordinate coordinate3 = new Coordinate(2, 2);
		Coordinate coordinate4 = new Coordinate(3, 3);
		System.out.println(test.removeCards(coordinate3, coordinate4));
		Coordinate coordinate5 = new Coordinate(2, 2);
		System.out.println(test.removeCards(coordinate3, coordinate5));
		Coordinate coordinate6 = new Coordinate(-3, 2);
		System.out.println(test.removeCards(coordinate6, coordinate5));
		System.out.println(test.isWin());
		test.cardsLeft = 0;
		test.ourScore = 52;
		System.out.println(test.isWin());
		test.ourScore = 25;
		System.out.println(test.isWin());			
	}
}
