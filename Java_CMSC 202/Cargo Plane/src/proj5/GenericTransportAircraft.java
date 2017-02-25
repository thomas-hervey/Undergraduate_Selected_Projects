package proj5;

import java.util.ArrayList;

/**
 * 			 This class simulates a generic aircraft that can have
 * 			 either persons (commuters) or cargo loaded into it.
 * 			 This class also houses and controls all of the major
 * 			 controls and abilities of the aircraft including things
 * 			 like printing the plane's status, landing, loading,
 * 			 unloading, climbing descending, etc. Our aircraft has
 * 			 it's attributes constructed through command line
 * 			 arguments including origin city, minimum flying altitude,
 * 			 maximum flying altitude, maximum storage amount. In
 * 			 addition, an array list is used to simulate loading and
 * 			 storing all of the plane's cargo that gets transported
 * 			 and unloaded. All instances of the class have these
 * 			 attributes and are able to perform any of the plane's
 * 			 usual actions (as specified in the methods, with exceptions).
 * 
 * 			 Help from CMSC Help Center
 * @version  Completed 12/13/11 : Due 12/13/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project5
 * @section  01
 * 
 * @param <Type>
 */
public class GenericTransportAircraft<Type extends Items> {
	
	//Declaring instance variables and arrayList that will be initialized by the constructor from command args
	ArrayList<Type> planeObjects;
	private boolean airborne;
	private String originCity;
	private String destinationCity;
	private int altitude;
	private int maxAltitude;
	private int minAltitude;
	private int maxStorage;
	
	
	/**
	 * Constructor method that initializes our arrayList and transport
	 * Pre-condition:  passed parameters are not null or empty
	 * Post-condition: our arrayList and genericTransportAircraft objects have been initialized. Our plane
	 * 			       now has all of the information set as the original command arguments
	 *
	 * @param  String originCity
	 * @param  int minAlt
	 * @param  int maxAlt
	 * @param  int maxStor
	 * @return int none
	 */
	public GenericTransportAircraft(String originCity, int minAlt, int maxAlt, int maxStor) {
		planeObjects = new ArrayList<Type>();
		airborne = false;
		this.originCity = originCity;
		destinationCity = "";
		this.altitude = 0;
		this.minAltitude = minAlt;
		this.maxAltitude = maxAlt;
		this.maxStorage = maxStor;
	}
	
	/**
	 * Mutator method that prints the transport's current status and item load to the log file
	 * Pre-condition:  fileWriter can write to our log file
	 * Post-condition: the transport status is written to the log file
	 *
	 * @param  fileWriter - our PrintWriter object that writes to our log file
	 * @return String info - plane status info
	 */
	public String print() {
		String info = "";
		info += ("PRINT\n");
		info += ("TransportStatus\n");
		info += ("---------------\n");
		info += ("\tMinimum flying altitude: " + minAltitude + "\n"); //Add minimum flying altitude information
		info += ("\tMaximum flying altitude: " + maxAltitude + "\n"); //Add maximum flying altitude information
		info += ("\tMaximum number of items: " + maxStorage + "\n"); //Add maximum storage amount information
		if(airborne == false){ //If the plane is on the ground, state it's local city
			info += ("\tOn the ground in " + originCity + "\n");
		}
		// otherwise, if the plane is in the air, state its starting and destination city
		else {
			info += ("\tFlying from " + originCity + " to " + destinationCity + "\n");
			info += ("\tCurrent altitude: " + altitude + "\n"); //Add current altitude if in the air
		}
		// for each item in our plane, add its information
		for(Type item : planeObjects) {
			info += "\t" +item + "\n";
		}
		return info;
	}
	
	/**
	 * Mutator method that simulates the transport taking off from being on the ground in its origin 
	 * city to its destination city
	 * Pre-condition:  the transport is not already airborne, it has a valid destination city
	 * Post-condition: the transport has taken off and is airborne at its minimum flying 
	 *                 altitude headed to its destination city
	 *
	 * @param  destination - the destination city that our transport is flying to
	 * @return String message - takeOff message
	 */
	public String takeOff(String destination) {
		try {
			if(airborne == true) {
				throw new IllegalLocationException("Error: Cannot take off when the plane is already airborne\n");
			}
			else if(destination == "" || destination == null) {
				if(destination == "") {
					throw new IllegalDestinationException("Error: Cannot take off with an empty destination\n");
				}
				else if(destination == null) {
					throw new IllegalDestinationException("Error: Cannot take off with a null destination\n");
				}
			}
		}
		catch(IllegalLocationException e) {
			String message = e.getMessage();
			return message;
		}
		catch(IllegalDestinationException e) {
			String message = e.getMessage();
			return message;
		}
		airborne = true;
		altitude = minAltitude;
		destinationCity = destination;
		return "TAKEOFF " + destination + "\n";
	}
	
	/**
	 * Mutator method that simulates the transport landing from being airborne from its origin city
	 * Pre-condition:  the transport is already airborne
	 * Post-condition: the transport has landed in its destination city, making its altitude 0, not 
	 * 				   airborne, and new origin city
	 *
	 * @param  none
	 * @return String message - land message
	 */
	public String land() {
		try{
			if(airborne == false) {
				throw new IllegalLocationException("This transport is already on the ground. It cannot land again\n");
			}
			else {
				airborne = false;
				altitude = 0;
				originCity = destinationCity;
				return "LAND\n";
			}
		}
		catch (IllegalLocationException e) {
			String message = e.getMessage();
			return message;
		}
	}
	
	/**
	 * Mutator method to simulate an airborne transport plane climbing to a new altitude
	 * Pre-condition:  the transport is already airborne and isn't climbing to more than the maximum altitude
	 * Post-condition: the transport climbs the distance to the new altitude (if over max, climbs to the max)
	 *
	 * @param  int altitudeChange - the distance that the file wants the transport to climb in feet
	 * @return String message - climb message
	 */
	public String climb(int altitudeChange) {
		try{
			if(airborne == false)
				throw new IllegalClimbException("Error: Cannot climb. The aircraft isn't airborne");
			if((altitude + altitudeChange) <= maxAltitude && altitudeChange >= 0) {
				altitude += altitudeChange;
				return "CLIMB to a new altitude of " + altitude + "\n";
			}
			else if((altitude + altitudeChange) > maxAltitude) {
				altitude = maxAltitude;
				throw new IllegalClimbException("Error: Cannot climb. Will exceed maximum allowable altitude of " + 
					maxAltitude + "\n");
			}
			else if(altitudeChange < 0)
				throw new IllegalClimbException("Error: Cannot climb by a negative number\n");
			else
				return "";
		}
		catch(IllegalClimbException e) {
			String message = e.getMessage();
			return message;
		}
	}
	
	/**
	 * Mutator method to simulate an airborne transport plane descending to a new altitude
	 * Pre-condition:  the transport is already airborne and isn't descending to more than the minimum altitude
	 * Post-condition: the transport descends the distance to the new altitude (if under minimum, descends to the minimum)
	 *
	 * @param  int altitudeChange - the distance that the file wants the transport to descend in feet
	 * @return String message - descent message
	 */
	public String descend(int altitudeChange) {
		try{
			if(airborne == false)
				throw new IllegalDescendException("Error: Cannot climb. The aircraft isn't airborne");
			if((altitude - altitudeChange) >= minAltitude && altitudeChange >= 0) {
				altitude -= altitudeChange;
				return "DESCEND to a new altitude of " + altitude + "\n";
			}
			else if((altitude - altitudeChange) < minAltitude) {
				altitude = minAltitude;
				throw new IllegalDescendException("Error: Cannot descend. Will go below minimum altitude of " + minAltitude + "\n");
			}
			else if(altitudeChange < 0) {
				throw new IllegalDescendException("Error: Cannot descend by a negative number\n");
			}
			else { return ""; }
		}
		catch(IllegalDescendException e) {
			String message = e.getMessage();
			return message;
		}
	}
	
	/**
	 * Mutator method that loads our item into our plane (planeOjbects arrayList). The item's type, which 
	 * should be the same type as all of the other loaded items and the plane is then sorted alphabetically.
	 * Then this loaded information is returned to be printed to our log file.
	 * Pre-Condition:  our item isn't empty, null, and isn't the same exact item or a different item type
	 * Post-Condition: our item has been sorted alphabetically, loaded into our plane (added to the arrayList)
	 * 				   and the string information returned
	 *
	 * @param  imte - our item to be loaded into the plane
	 * @return LOAD + item.toString() - formatted load information for the newly added item
	 */
	public String load(Type item) {
		try {
			// if the arrayList is as large as our max number of items, we cannot store anymore
			if(planeObjects.size() >= maxStorage) {
				throw new IllegalStorageAmountException("Error: Cannot load. Will exceed maximum storage amount of " + 
					maxStorage + "\n");
			}
			// if the plane is already in the air, it cannot load
			if(airborne == true) {
				throw new IllegalLocationException("Error: Cannot load while the plane is airborne\n");
			}
			// if the arrayList can handle more items, compare that item, sort it (by either ID or label), store
			else if(planeObjects.size() > 0) {
				int length = planeObjects.size();
				for(int i = 0; i < length; i ++) {
					if(item.compareTo(planeObjects.get(i)) < 0) {
						planeObjects.add(i, item);
						return "LOAD " + item.toString() + "\n";
					}
					// try to add something to the end, it sequentially should come at the end of the list
					else if(i == length - 1) {
						planeObjects.add(item);
						return "LOAD " + item.toString() + "\n";
					}
				}
				return ("Error: Cannot load because there is some error with the item being an unacceptable type");
			}
			// if the arrayList planeObjects is empty, then we can just add the item
			else {
				planeObjects.add(item);
				return "LOAD " + item.toString() + "\n";
			}
		}
		catch (IllegalStorageAmountException e) {
			String message = e.getMessage();
			return message;
		}
		catch (IllegalLocationException e) {
			String message = e.getMessage();
			return message;
		}
	}
	
	/**
	 * Mutator method that unloads our item from the plane (planeObjects arrayList). Before it can unload,
	 * there must be a check to make sure that the plane is on the ground and that the item is in out plane.
	 * Pre-condition:  our identifier isn't empty, null and is of the correct type as our plane's type
	 * 				   Also, our plane cannot be airborne and the item has to be in our plane
	 * Post-condition: The item with the passed identification is unloaded, removed from our plane 
	 * 				   (removed from our arrayList) and the string information returned
	 *			   
	 * @param  String identifier - either the label or ID (depending on cargo vs person) to be retrieved for unloading
	 * @return "UNLOAD" + planeObjects.get(i).toString() - formatted unload information for the removed item
	 */
	public String unload(String identifier) {
		boolean removed = false; //Variable to check to see if anything has been removed
		try {
			// if the plane is airborne, it cannot unload
			if(airborne == true) {
				throw new IllegalLocationException("Error: Cannot unload while the plane is in the air\n");
			}
			else {
				for(int i = 0; i < planeObjects.size(); i++) {
					if(planeObjects.get(i).getIdentifier().equals(identifier)) {
						removed = true;
						String unloadable = planeObjects.get(i).toString();
						planeObjects.remove(planeObjects.get(i));
						return "UNLOAD " + unloadable + "\n";
					}
				}
				// if removed is false, then the item isn't in the plane and cannot be unloaded
				if(removed == false) {
					throw new NoItemFoundException("Error: Cannot unload " + 
						identifier + " item because is isn't on the transport\n");
				}
			}
			return ("Error: ");
		}
		catch(IllegalLocationException e) {
			String message = e.getMessage();
			return message;
		}
		catch(NoItemFoundException e) {
			String message = e.getMessage();
			return message;
		}
	}
	
	/**
	 * Main method used for unit, pre/post condition and class invariant testing
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		GenericTransportAircraft test = new GenericTransportAircraft("Boston", 2000, 5000, 5);
		System.out.println(test.print());
		System.out.println(test.takeOff("New York"));
		System.out.println(test.takeOff("Philly"));
		System.out.println(test.land());
		System.out.println(test.land());
		System.out.println(test.climb(300));
		System.out.println(test.altitude);
		System.out.println(test.climb(300000));
		System.out.println(test.climb(-3));
		System.out.println(test.altitude);
		System.out.println(test.descend(300));
		System.out.println(test.altitude);
		System.out.println(test.descend(300000));
		System.out.println(test.descend(-3));
		System.out.println(test.altitude);
		test.airborne = true;
		
		//Testing loading a person
		System.out.println(test.load(new Person("Jerry", 54, "WW121")));
		test.airborne = false;
		System.out.println(test.load(new Person("Jerry", 54, "WW121")));
		System.out.println(test.load(new Person("Zilly", 666, "AAWW121")));
		test.maxAltitude = 7000;
		System.out.println(test.print());
		
		//System.out.println(planeObjects);
		test.maxStorage = 2;
		System.out.println(test.load(new Person("Hilly", 667, "QQWW121")));
		System.out.println(test.unload("WW121"));
		System.out.println(test.print());
		System.out.println(test.unload("SHOULDNT BE IN HERE"));
		System.out.println(test.print());
		test.airborne = true;
		System.out.println(test.unload("QQWW121"));
		System.out.println("************************");
		
		//Testing loading cargo
		GenericTransportAircraft test2 = new GenericTransportAircraft("Dallas", 1000, 7000, 10);
		System.out.println(test2.load(new Cargo("Label1", 30, 10, 10, 10)));
		test2.airborne = true;
		System.out.println(test2.load(new Cargo("Label2", 40, 100, 20, 10)));
		test2.airborne = false;
		System.out.println(test2.load(new Cargo("Label2", 40, 100, 20, 10)));
		System.out.println(test2.load(new Cargo("AALabel3", 40, 100, 20, 444)));
		System.out.println(test2.print());
		System.out.println(test2.unload("Label1"));
		System.out.println(test.unload("SHOULDNT BE IN HERE"));
		test2.maxStorage = 2;
		System.out.println(test2.load(new Cargo("RRLabel3", 50, 100, 20, 444)));
	}
}
