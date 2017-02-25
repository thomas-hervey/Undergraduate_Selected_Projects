/**
 * Write a class invariant for both classes
 * make sure all of the unit testing is correct (general, error, boundary etc.)
 * make sure all pre/post are tested
 * write a unit test for isWinner
 * reread all of the project description to make sure nothing is left out
 * double check the program again
 * cite that I got help from the help center
 *
 */
package proj2;

/**
 * 			   This class represents an instance of a slot machine
 * 			   which is implemented in the Project2 class (the gui).
 * 			   Here, most of the methods are simple accessors and
 * 			   mutators for the statistics and details of our slot
 * 			   machine. From here, we also determine if the user is
 * 			   a winner depending on their combination, and the
 * 			   appropriate statistics are changed.
 * 			   Class invariant- all instances of the SlotMachine
 * 			   class are of type Reel and are not null or empty
 *
 * @version    Completed 10/21/11 : Due 10/24/11
 * @author     Thomas Hervey <h46@umbc.edu>
 * @project    CMSC 202 -Fall 2011 -Project 2
 * @section    01
 *
 */
public class SlotMachine {

	private int userCredit = 10;
	private int userBet;
	private int playNumber;
	private int winNumber;
	private int lossNumber;
	private int totalPayout;
	public final int MAX_BET = 3;
	public final int TOTAL_MAX_BET = 10;
	Reel [] reelList = new Reel [3];

	/**
	 * Constructor that creates an array containing our three reels
	 * and sets them equal to
	 * Precondition -Number of reels >=2
	 * Postcondition -none
	 * @param none
	 * @return none
	 */
	public SlotMachine(){
		reelList [0] = new Reel();
		reelList [1] = new Reel();
		reelList [2] = new Reel();
	}

	/**
	 * Mutator method for increasing the user's current bet by a
	 * specific amount.
	 * Precondition- Amount > 0 and <= number of player credits
	 * @param amt -amount to increase player's bet by
	 * @return none
	 */
	public void increaseBet(int amt){
		//ADDED - ERROR HANDLING FOR BET OF 0 OR LESS
		if(amt <=0){
			System.out.println("You cannot bet 0 or anything less than 0.");
			System.exit(0);
		}
		//If the user tries to bet more than the max total bet of 10, don't let them
		else if(getBetAmount() >= TOTAL_MAX_BET)
			return;
		//If the user tries to bet a value that will have the bet and the current bet
		//be greater than the max total bet of 10
		else if(getBetAmount() + amt >= TOTAL_MAX_BET){
			userBet += (TOTAL_MAX_BET - getBetAmount());
			userCredit -= (TOTAL_MAX_BET - getBetAmount());
		}
		//Otherwise, increase the bet by the amount
		else{
			userBet += amt;
			userCredit -= amt;
		}
	}

	/**
	 * This method adds a specific amount to the player's current
	 * credit number.
	 * Precondition- amount is greater than 0.
	 * Postcondition- total user credit amount increased by the
	 * desired amount.
	 * @param amount
	 * @return none
	 */
	public void addCredits(int amount){
		if(amount >= 0)
			userCredit += amount;
		else
			return;
	}

	/**
	 * Accessor method for the player's bet amount.
	 * Precondition- none
	 * Postcondition- none
	 * @param none
	 * @return Player's bet amount
	 */
	public int getBetAmount(){
		return userBet;
	}

	/**
	 * Accessor method for the number of player's
	 * current total credit number in the machine.
	 * Precondition- none
	 * Postcondition- none
	 * @param none
	 * @return Player's credit number
	 */
	public int getCredits(){
		return userCredit;
	}

	/**
	 * Accessor method that provides help text on how to
	 * play the slot machine
	 * Precondition- none
	 * Postcondition- hopefully the user knows what he/she
	 * is doing
	 * @param none
	 * @return Text help string
	 */
	public String getHelpText(){
		String helpText = "";
		helpText += "In order to play, you must have a credit balance. Think of this as your";
		helpText += "\nnmoney. Each turn or 'play, you can bet away anywhere from 0-3 (min-max)";
		helpText += "\ncredits that you think you can/want to gamble with. Click either bet one";
		helpText += "\n(may be clicked several times) or bet max buttons to let the machine know";
		helpText += "\nhow much you want to bet.";
		helpText += "\nIf you place an incorrect bet that you don't want click reset to return.";
		helpText += "\nThen, press the spin button to start the game.";
		helpText += "\nThe three reels will begin to turn. Click on each reel to stop it. Try to";
		helpText += "\nmatch up all of the reels with the same symbol to win. If you match all";
		helpText += "\nthree, you will get your according bet amount times the symbol's value.";
		helpText += "\nIf you match up two, you will get your according amount times 1/2 the value";
		helpText += "\nSEVEN =12, WATERMELON =10, ORANGE =8";
		helpText += "\nPLUM =6, LEMON =4, CHERRY =2";
		helpText += "\nIf you do not match up all three symbols, you will lose your bet and the";
		helpText += "\nmachine will be that much richer! Once you have finished that turn, press";
		helpText += "\nreset to play another turn.";
		helpText += "\nYou can access the machine statistics and add credits in the program menu";
		helpText += "\nbar at any time. Good luck!";
		return helpText;
	}

	/**
	 * Accessor method that computes and returns all of the
	 * player and use statistics of the machine.
	 * Precondition- none
	 * Postcondition- none
	 * @param none
	 * @return A string containing the number of wins, loses
	 * 		   and cash-to-play ratio
	 */
	public String getMachineStatistics(){
		//If for some reason winNumber or lossNumber or playNumber are 0 or negative,
		//they get set as 0
		if(winNumber <= 0 || playNumber <= 0)
			winNumber = 0;
		if(lossNumber <= 0 || playNumber <= 0)
			lossNumber = 0;
		if(playNumber <= 0)
			playNumber = 0;
		String statistics = "";
		statistics += "Number of wins: " + winNumber;
		statistics += "\nNumber of losses: " + lossNumber;
		statistics += "\nMachine average cash-per-play ratio: ";
		statistics += "\n(+) = machine has gained this average";
		statistics += "\n(-) = machine has lost this average: ";
		//statistics += "\n bet amount " + getBetAmount();
		//statistics += "\n total payout " + totalPayout;
		//statistics += "\n games played " + playNumber;


		//If the user tries to access the statistics without playing a game first
		if(playNumber == 0){
			statistics += "\n0.0 :No games have been played";
		}
		//If the user tries to access the statistics after playing at least one game
		else{
			double cashPlayRatio = (double)totalPayout / (double)playNumber;
			statistics += "\n" + cashPlayRatio;
		}
		//statistics += "\nPlay number: " + playNumber;
		return statistics;
	}

	/**
	 * Accessor method for a particular instance of the reel class.
	 * Precondition- Requested reel number must be >=0 and < number
	 * 				 of reels in the machine (3 in this case)
	 * Postcondition- none
	 * @param index
	 * @return Reel requested integer
	 */
	public Reel getReel(int index){
		if(index < 0 || index >= reelList.length)
			return null;
		else if(index >= 3){
			//ADDED- ERROR HANDLING FOR A PASSED INDEX OF 3 OR LARGER
			System.out.println("You cannot access a reel 3 or anything larger.");
			System.exit(0);
			return null;
		}
		else
			return reelList[index];
	}

	/**
	 * Method that determines if the player has won this turn.
	 * All symbols must not be null. If player has won, number of
	 * wins is incremented, winnings are computed and added to credits,
	 * and winnings are deducted from machine. If player has lost,
	 * number of losses are incremented and bet amount is added to machine.
	 * In either case, number of games played is incremented and bet
	 * amount is reset to zero.
	 * Precondition- Bet value >= 0
	 * Postcondition- Statistics for number of wins, loses and credits are updated
	 * @param one
	 * @param two
	 * @param three
	 * @return true or false (if the player has or has not won)
	 */
	public boolean isWinner(Symbols one, Symbols two, Symbols three){
		if(getBetAmount() <= 0)
			userBet = 0;
		//Declaring variable for the integer value returned from isWinCombo
		int winningCombo = 0;
		//Declaring variable to calculate a correct payout & user credit update
		int symbolPayout = 0;
		winningCombo = isWinCombo(one, two, three);
		playNumber += 1;
		//All three of the symbols matched up, so the user has won a lot this turn
		if (winningCombo == 123){
			symbolPayout = one.getPayout();
			userCredit += getBetAmount() * symbolPayout;
			totalPayout -= getBetAmount() * symbolPayout;
			winNumber += 1;
			userBet = 0;
			return true;
		}
		//This first two symbols matched up, so the user has won some this turn
		else if(winningCombo == 12){
			symbolPayout = one.getPayout();
			userCredit += getBetAmount() * (symbolPayout / 2);
			totalPayout -= getBetAmount() * (symbolPayout / 2);
			winNumber += 1;
			userBet = 0;
			return true;
		}
		//The first and the last symbols matched up, so the user has won some this turn
		else if(winningCombo == 13){
			symbolPayout = one.getPayout();
			userCredit += getBetAmount() * (symbolPayout / 2);
			totalPayout -= getBetAmount() * (symbolPayout / 2);
			winNumber += 1;
			userBet = 0;
			return true;
		}
		//The last two symbols matched up, so the user has won some this turn
		else if(winningCombo == 23){
			symbolPayout = two.getPayout();
			userCredit += getBetAmount() * (symbolPayout / 2);
			totalPayout -= getBetAmount() * (symbolPayout / 2);
			winNumber += 1;
			userBet = 0;
			return true;
		}
		//None of the symbols matched up so the user has lost this turn
		else{
			totalPayout += getBetAmount();
			lossNumber += 1;
			userBet = 0;
			return false;
		}
	}

	/**
	 * This helper method checks to see which if any of the three symbols from
	 * isWinner are a match. Depending on which ones match (either all three,
	 * only two, or none) an appropriate integer is returned so that isWinner
	 * can decide if to multiply the user's bet amount by the full matching
	 * symbol amount, by half or by none.
	 * Precondition- one, two and three are not null
	 * Postcondition- isWinner knows which reel combination is a match
	 * @param one
	 * @param two
	 * @param three
	 * @return 123,12,13,23 or 0. Specific integer value for isWinner to decide
	 * 		   how to handle the payout amounts
	 */
	private int isWinCombo(Symbols one, Symbols two, Symbols three){
		//If one of the reels is null
		if(one == null || two == null || three == null)
			return 0;
		//If all three symbols are equal
		if(one.equals(two) && one.equals(three) && two.equals(three))
			return 123;
		//If the first two symbols are equal
		else if(one.equals(two))
			return 12;
		//If the first and the third symbols are equal
		else if (one.equals(three))
			return 13;
		//If the last two symbols are equal
		else if (two.equals(three))
			return 23;
		else
			//If none of the symbols are equal
			return 0;
	}

	/**
	 * Mutator method that resets the player's bet to zero so that they
	 * may bet again for the next turn.
	 * Precondition -none
	 * Postcondition -none
	 * @param none
	 * @return none
	 */
	public void resetBet(){
		if(getBetAmount() >=0)
			userCredit += getBetAmount();
		userBet = 0;
	}

	public static void main(String[] args){
		//Create a test object to perform effective unit testing in SlotMachine
		//Also, a unit test for the SlotMachien constructor
		SlotMachine test = new SlotMachine();

		//Unit testing for increaseBet method
		System.out.println("increaseBet---");
		System.out.println(test.getBetAmount());
		test.increaseBet(1);
		System.out.println(test.getBetAmount());
		test.increaseBet(2);
		System.out.println(test.userBet);
		test.increaseBet(0);
		System.out.println(test.getBetAmount());
		test.increaseBet(-4);
		System.out.println(test.getBetAmount());
		test.increaseBet(2);
		System.out.println(test.userBet);

		//Unit testing for addCredits method
		System.out.println("addCredits---");
		System.out.println(test.userCredit);
		test.addCredits(6);
		System.out.println(test.userCredit);
		test.addCredits(-4);
		System.out.println(test.userCredit);
		System.out.println(test.getCredits());
		test.addCredits(0);
		System.out.println(test.getCredits());

		//Unit testing for getBetAmount method
		System.out.println("getBetAmount---");
		System.out.println(test.getBetAmount());
		test.userBet = 5;
		System.out.println(test.getBetAmount());
		test.userBet = -5;
		System.out.println(test.getBetAmount());

		//Unit testing for getCredits method
		System.out.println("getCredits---");
		int b = test.getCredits();
		System.out.println(b);
		test.userCredit = 7;
		b = test.getCredits();
		System.out.println(b);
		test.userCredit = -20;
		b = test.getCredits();
		System.out.println(b);

		//Unit testing for for the getHelpText method
		System.out.println("getHelpText---");
		System.out.println(test.getHelpText());

		//Unit testing for getMachineStatistics method
		System.out.println("getMachineStatistics---");
		System.out.println(test.getMachineStatistics());
		test.winNumber += 3;
		System.out.println(test.getMachineStatistics());
		test.lossNumber -= 3;
		System.out.println(test.getMachineStatistics());
		test.playNumber += 5;
		test.totalPayout += 3;
		test.winNumber += 2;
		System.out.println(test.getMachineStatistics());

		//Testing dividing by 0 handling for cash-to-play ratio
		test.totalPayout = 5;
		test.playNumber = 0;
		System.out.println(test.getMachineStatistics());

		//Unit testing for getReel method
		System.out.println("getReel---");
		System.out.println(test.getReel(3));
		test.getReel(2);
		System.out.println(test.getReel(2));
		System.out.println(test.getReel(-1));

		//Unit testing for isWinner method, including helper isWinCombo method
		System.out.println("isWinner---");
		test.winNumber = 0;
		test.lossNumber = 0;
		test.totalPayout = 0;
		test.userBet = 3;
		test.userCredit = 5;
		test.playNumber = 0;
		System.out.println(test.winNumber);
		System.out.println(test.lossNumber);
		System.out.println(test.totalPayout);
		System.out.println(test.userBet);
		System.out.println(test.userCredit);
		System.out.println(test.playNumber);
		System.out.println(test.isWinner(Symbols.SEVEN, Symbols.SEVEN, Symbols.SEVEN));
		System.out.println(test.isWinner(Symbols.ORANGE, Symbols.SEVEN, Symbols.SEVEN));
		System.out.println(test.winNumber);
		System.out.println(test.lossNumber);
		System.out.println(test.totalPayout);
		System.out.println(test.getBetAmount());
		System.out.println(test.getCredits());
		System.out.println(test.playNumber);
		System.out.println(test.isWinner(Symbols.LEMON, Symbols.SEVEN, Symbols.LEMON));
		System.out.println(test.isWinner(Symbols.ORANGE, Symbols.CHERRY, Symbols.SEVEN));
		System.out.println(test.winNumber);
		System.out.println(test.lossNumber);
		System.out.println(test.totalPayout);
		System.out.println(test.userBet);
		System.out.println(test.userCredit);
		System.out.println(test.playNumber);
		//Testing individual values for isWinCombo to see if they are a win to begin with
		//Before any values are changed
		System.out.println(test.isWinCombo(Symbols.CHERRY, Symbols.CHERRY, Symbols.CHERRY));
		System.out.println(test.isWinCombo(Symbols.CHERRY, Symbols.LEMON, Symbols.CHERRY));
		System.out.println(test.isWinCombo(Symbols.CHERRY, Symbols.LEMON, Symbols.LEMON));
		System.out.println(test.isWinCombo(Symbols.CHERRY, Symbols.LEMON, Symbols.WATERMELON));

		//Unit testing for resetBet method
		System.out.println("resetBet---");
		System.out.println(test.userBet = 0);
		test.resetBet();
		System.out.println(test.userBet);
		System.out.println(test.userBet = 6);
		test.resetBet();
		System.out.println(test.userBet);
		System.out.println(test.userBet = -2);
		test.resetBet();
		System.out.println(test.userBet);
		System.out.println(test.userCredit = 5);
		System.out.println(test.getBetAmount());
		System.out.println(test.userBet = 3);
		System.out.println(test.userCredit);
		test.resetBet();
		System.out.println(test.userCredit);
		System.out.println(test.userBet = -3);
		System.out.println(test.userCredit);
		test.resetBet();
		System.out.println(test.userBet);
		System.out.println(test.userCredit);
	}

}
