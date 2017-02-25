package proj5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 			 This class acts as the control structure and the
 * 			 running class that the program is based off of.
 * 			 Here, our file reader and writer are created to
 * 			 read from the command file and write to the log
 * 			 file as well as take in the other command line
 * 			 arguments and initialize them as our generic
 * 			 airplane attributes. Once there is a generic
 * 			 airplane created from these traits, our main
 * 			 method will look at the command file and check to
 * 			 to see if there is another line in the file. So
 * 			 long as there is another line in the file, it
 * 			 will examine the line and perform the according
 * 			 plane action based on that line's text. All of
 * 			 these actions will be logged into the log file
 * 			 including errors and exceptions. All instances
 * 			 of this class will be a generic aircraft and
 * 			 will have the command line arguments as its
 * 			 attributes. 
 * 
 * 			 Help from CMSC Help Center
 * @version  Completed 12/13/11 : Due 12/13/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project5
 * @section  01
 * 
 * @param <Type>
 */
public class Project5 {
	
	private static Scanner fileReader;
	private static PrintWriter fileWriter;
	
	@SuppressWarnings("rawtypes")
	private static GenericTransportAircraft transport;
	
	private static String cargoType;
	private static String originCity;
	private static String minAltitude;
	private static String maxAltitude;
	private static String maxStorage;
	private static String commandFile;
	private static String logFile;
	
	/**
	* 
	*/
	public static void main(String[] args){
		
		try {
			if (args.length != 7) { throw new IllegalArgumentNumberException("There must be seven arguments for this program"); }

			//Declaring variables to each of our command line arguments. These arguments are common to all transport aircraft
			cargoType = args[0]; //Cargo type of our fifth argument (args[5]) file; either cargo or commuter
			originCity = args[1]; //First origin city of our transport
			minAltitude = args[2]; //Minimum flying altitude of our transport
			maxAltitude = args[3]; //Maximum flying altitude of our transport
			maxStorage = args[4]; //Maximum number of storage items within our transport
			commandFile = args[5]; //Command file where we read in all of our transport items, item info and transport commands
			logFile = args[6]; //log file where each of our commands including errors are logged
			
			//Converting our minimum altitude, maximum altitude and maximum storage amount to integers
			Integer minAlt = Integer.parseInt(minAltitude);
			Integer maxAlt = Integer.parseInt(maxAltitude);	
			Integer maxStor = Integer.parseInt(maxStorage);	

			try {
				cargoType = cargoType.toLowerCase(); //To ignore argument case, put cargoType to lower case
				// create a new aircraft of the given type
				if(cargoType.equals("cargo")) {
					transport = new GenericTransportAircraft<Cargo>(originCity, minAlt, maxAlt, maxStor);
				}
				else if(cargoType.equals("commuter")) {
					transport = new GenericTransportAircraft<Person>(originCity, minAlt, maxAlt, maxStor);
				}
				else {
					throw new IllegalAircraftTypeException("Error: Cannot create an 
						aircraft that isn't of type Commuter or Cargo");
				}
			}
			catch(IllegalAircraftTypeException e) {
				String message = e.getMessage();
				System.out.println(message);
				System.exit(0);
			}
				
			// creating a new fileInputStream from our 6th argument commandFile text
			FileInputStream x;
			try {
				x = new FileInputStream(commandFile);
				// initializing a new Scanner out of our fileInputStream commandFile
				fileReader = new Scanner(x);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			// creating a new fileOutStream for our 7th argument logFile text
			FileOutputStream y;
			try {
				y = new FileOutputStream(logFile);
				// initializing a new PrintWriter out of our fileOutputStream logFile
				fileWriter = new PrintWriter(y);
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
				System.exit(0);
			}
			
			// as long as there is another line in our commandFile and the next line isn't QUIT
			while(fileReader.hasNextLine()) {
				//Create and assign the String currentLine equal to the next file line
				String currentLine = fileReader.nextLine();
				//Check to see what is on that line by calling lineCheck and if possible do the according
				lineCheck(currentLine);
			}
			// when there are no more lines, close the fileReader, fileWriter and quit the program
			fileReader.close();
			fileWriter.close();
			System.exit(0);
		}
		catch(IllegalArgumentNumberException e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	/**
	 * Method that checks the next (now current) line of our command file
	 * and performs the according action based on that line. In here, it
	 * also grabs the next few inputs based on the action (ex. TAKEOFF will
	 * grab the next line to get a destination to pass to the generic plane's
	 * takeOff method). With each action, our fileWriter will log the action.
	 * Pre-condition:  the line isn't empty, null and is one of the
	 * 			  	   expected appropriate plane actions
	 * Post-condition: a log will be written and the appropriate action
	 * 				   performed for the according plane request
	 *
	 * @param  String c - our current line in the command file to be checked
	 * @return none
	 */
	@SuppressWarnings("unchecked")
	private static void lineCheck(String c){
		// if the next line says PRINT
		if(c.equals("PRINT")) { fileWriter.write(transport.print()); }
		// if the next line says LAND
		else if(c.equals("LAND")){ fileWriter.write(transport.land()); }
		// if the next line says TAKEOFF
		else if(c.equals("TAKEOFF")) {
			String destinationCity = fileReader.nextLine();
			fileWriter.write(transport.takeOff(destinationCity));
		}
		// if the next line says CLIMB
		else if(c.equals("CLIMB")) {
			Integer climbFeet = Integer.parseInt(fileReader.nextLine());
			fileWriter.write(transport.climb(climbFeet));
		}
		// if the next line says DESCEND
		else if(c.equals("DESCEND")) {
			Integer descendFeet = Integer.parseInt(fileReader.nextLine());
			fileWriter.write(transport.descend(descendFeet));
		}
		// if the next line says LOAD
		else if(c.equals("LOAD")) {
			try {
				// if the cargo type is Cargo, get 5 lines for a cargo's info
				if(cargoType.equals("cargo")){
					String label = fileReader.nextLine();
					//System.out.println(label);
					int weight = fileReader.nextInt();
					//System.out.println(weight);
					int length = fileReader.nextInt();
					//System.out.println(length);
					int width = fileReader.nextInt();
					//System.out.println(width);
					int height = fileReader.nextInt();
					//System.out.println(height);
					Cargo newCargo = new Cargo(label, weight, length, width, height);
					fileWriter.write(transport.load(newCargo));	
				}
				// otherwise, if the cargo type is Commuter, get 3 lines for a person's info
				else if (cargoType.equals("commuter")) {
					String name = fileReader.nextLine();
					int age = fileReader.nextInt();
					fileReader.nextLine();
					String iD = fileReader.nextLine();
					Person newPerson = new Person(name, age, iD);
					fileWriter.write(transport.load(newPerson));
				}
				// if the command file wants to load an object, but our object isn't cargo or a commuter
				else { throw new IllegalTransportTypeException("Error: Cannot load because this transport isn't a valid type"); }
			}
			catch(IllegalTransportTypeException e) {
				String message = e.getMessage();
				fileWriter.write(message);
			}
		}
		// if the next line says UNLOAD
		else if(c.equals("UNLOAD")) { 
			String identification = fileReader.nextLine();
			fileWriter.write(transport.unload(identification));
		}
		// if the next line says QUIT
		else if(c.equals("QUIT")){
			fileReader.close();
			fileWriter.close();
			System.exit(0);
		}
		// if the next line doesn't have any command that an aircraft can do (or quit), throw an error
		else{
			//throw an error
		}
	}
}
