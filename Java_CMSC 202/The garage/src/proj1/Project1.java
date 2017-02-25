package proj1;
import java.util.Scanner;

/**
 * 				 Project1 class is used to get us students used
 * 				 to object oriented programming, class defining 
 * 				 user input and encapsulation/information hiding
 *               by creating garage objects for 4.0gpa CMSC
 *               majors. These garages will perform tasks
 *               to store and remove cars as well as document
 *               issues such as accidents.
 *
 * @version      Mostly completed 9/27/11 : Due 10/5/11
 * @author       Thomas Hervey <h46@umbc.edu>
 * @project		 CMSC 202 -Fall 2011 - Project 1
 * @section      01
 */
public class Project1 {
	//Creating a new Scanner object to be used to get user's input
	private static Scanner input = new Scanner(System.in);

	/**
	 * This method is the skeleton of our program and controls
	 * the flow of the user interaction. Here our three garage
	 * objects are used and depending on the user's menu choices
	 * they are manipulated by calling different mutator methods.
	 * Those traits which are stored in the objects can be used
	 * to determine the ability of the user's next move and the
	 * following consequences.
	 *
	 * @param args
	 * @return none
	 */
	public static void main(String[] args) {
		// Creating our three garages as objects of the Garage class
		Garage garage1 = new Garage();
		Garage garage2 = new Garage();
		Garage garage3 = new Garage();

		/* First time getting the user's menu option to have a
		*  value to get into the while loop. */
		String userChoice = getOption();
		/* As long as the user's option wasn't to exit, check which
		*  option they did chose, do appropriate tasks, then ask
		*  for another menu choice */
		while (!userChoice.equals("E")) {
			// Choice to open the door
			if (userChoice.equals("O")) {
				String garageChoice = getGarageNumber("open");
				if (garageChoice.equals("1")) {
					String openDoor = garage1.openDoor(garageChoice);
					System.out.println(openDoor);
				}
				else if (garageChoice.equals("2")) {
					String openDoor = garage2.openDoor(garageChoice);
					System.out.println(openDoor);
				}
				else if (garageChoice.equals("3")) {
					String openDoor = garage3.openDoor(garageChoice);
					System.out.println(openDoor);
				}
			}
			// Choice to close the door
			else if (userChoice.equals("C")) {
				String garageChoice = getGarageNumber("close");
				if (garageChoice.equals("1")) {
					String close = garage1.closeDoor(garageChoice);
					System.out.println(close);
				}
				else if (garageChoice.equals("2")) {
					String close = garage2.closeDoor(garageChoice);
					System.out.println(close);
				}
				else if (garageChoice.equals("3")) {
					String close = garage3.closeDoor(garageChoice);
					System.out.println(close);
				}
			}
			// Choice to pull the car in
			else if (userChoice.equals("P")) {
				String garageChoice = getGarageNumber("pull into");
				if (garageChoice.equals("1")) {
					String pullIn = garage1.pullCarIn(garageChoice);
					System.out.println(pullIn);

				}
				else if (garageChoice.equals("2")) {
					String pullIn = garage2.pullCarIn(garageChoice);
					System.out.println(pullIn);
				}
				else if (garageChoice.equals("3")) {
					String pullIn = garage3.pullCarIn(garageChoice);
					System.out.println(pullIn);
				}
			}
			// Choice to back a car out
			else if (userChoice.equals("B")) {
				String garageChoice = getGarageNumber("back out of");
				if (garageChoice.equals("1")) {
					String backOut = garage1.backCarOut(garageChoice);
					System.out.println(backOut);
				}
				else if (garageChoice.equals("2")) {
					String backOut = garage2.backCarOut(garageChoice);
					System.out.println(backOut);
				}
				else if (garageChoice.equals("3")) {
					String backOut = garage3.backCarOut(garageChoice);
					System.out.println(backOut);
				}
			}
			// Choice to find the first empty garage space
			else if (userChoice.equals("F")) {
				if (garage1.findEmptySpace() == true) {
					System.out.println("Garage 1 is empty and working.");
				}
				else if (garage2.findEmptySpace() == true) {
					System.out.println("Garage 2 is empty and working.");
				}
				else if (garage3.findEmptySpace() == true) {
					System.out.println("Garage 3 is empty and working.");
				}
				else
					System.out.println("Sorry, all of the garages are full "
							+ "and/or broken.\nPlease find another place to park.");

			}
			// Choice to display a given garage's state
			else if (userChoice.equals("D")) {
				String garageChoice = getGarageNumber("know about");
				if (garageChoice.equals("1")) {
					String spaceState = garage1.displaySpaceState(garageChoice);
					System.out.println(spaceState);
				}
				else if (garageChoice.equals("2")) {
					String spaceState = garage2.displaySpaceState(garageChoice);
					System.out.println(spaceState);
				}
				else if (garageChoice.equals("3")) {
					String spaceState = garage3.displaySpaceState(garageChoice);
					System.out.println(spaceState);
				}
			}
			/* If the user enters in an unacceptable option, no tasks will be
			*  performed, a new option will be gotten followed by another
			*  while loop iteration */
			else{
				System.out.println("----------");
				System.out.println("Sorry, that is an invalid option");
			}
			// get user's menu option input for next while loop iteration
			userChoice = getOption();
		}
		// Menu Choice E was selected
		System.out.println("You are now exiting the program.\n"
				+ "Here are the final garage states:");
		String spaceState1 = garage1.displaySpaceState("1");
		System.out.println(spaceState1);
		String spaceState2 = garage2.displaySpaceState("2");
		System.out.println(spaceState2);
		String spaceState3 = garage3.displaySpaceState("3");
		System.out.println(spaceState3);
		input.close();
		System.exit(0);
	}

	/**
	 * This method displays the menu with options to the user and gets
	 * their input. This value will be returned and used in a while loop
	 * in main to act accordingly for the chosen menu choice.
	 *
	 * @param  none
	 * @return menuOption - a string up until the user hits [enter]
	 */
	private static String getOption(){
		String O = "O - Open door";
		String C = "C - Close door";
		String P = "P - Pull car in";
		String B = "B - Back car out";
		String F = "F - Find an empty space";
		String D = "D - Display space state";
		String E = "E - Exit program";
		System.out.println("------------------------------------------");
		System.out.println("Please enter in the corresponding letter\n"
				+ "to one of the following menu options: ");
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",O,C,P,B,F,D,E);
		String menuOption = input.next();
		menuOption = menuOption.toUpperCase();
		menuOption = menuOption.trim();
		return menuOption;
	}

	/**
	 * This method displays to the user the three garage choices of which
	 * to modify. There is error checking to make sure the user chooses 1,
	 * 2, or 3. The string action should not be empty or null.
	 *
	 * @param action - a main menu choice string (shouldn't be empty)
	 * @return garageChoice - an error checked acceptable garage number
	 */
	private static String getGarageNumber(String action){
		System.out.println("Which garage do you want to " + action + " ?");
		System.out.println("Please enter either 1,2 or 3: ");

		// Gets the user's garage input
		String garageChoice = input.next();
		garageChoice = garageChoice.toUpperCase();
		garageChoice = garageChoice.trim();

		// Error checking to make sure that the user enters in an acceptable garage number
		while(!garageChoice.equals("1") && !garageChoice.equals("2") && !garageChoice.equals("3")){
			System.out.println("Sorry, that is an invalid option.");
			System.out.println("Which garage do you want to " + action + " ?");
			System.out.println("Please enter either 1,2 or 3: ");
			garageChoice = input.next();
			garageChoice = garageChoice.toUpperCase();
			garageChoice = garageChoice.trim();
		}
		return garageChoice;
	}
}
