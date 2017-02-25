package proj5;


/**
 * 			 This class is one of the two types of items that
 * 			 can be loaded into our generic plane. This class
 * 			 implements the Items interface which is used to
 * 			 grab the appropriate type of identification for
 * 			 the generic airplane as well as be able to compare
 * 			 other people by their unique government-issued ID.
 * 			 All instances of this class (as all people) have a
 * 			 name, age and a unique ID.
 * 
 * 			 Help from CMSC Help Center
 * @version  Completed 12/13/11 : Due 12/13/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project5
 * @section  01
 */
public class Person implements Items {
	
	private String name;
	private int age;
	private String ID;
	
	public Person(String name, int age, String ID) {
		this.name = name;
		this.age = age;
		this.ID = ID;
	}

	/**
	 * Accessor method for the particular person object's name
	 * Pre-condition:  none
	 * Post-condition: the name is known
	 * @param  none
	 * @return name - the person object's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Accessor method for the particular person object's age
	 * Pre-condition:  none
	 * Post-condition: the age is known
	 *
	 * @param  none
	 * @return int age - the person object's age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Accessor method for the particular person object's ID
	 * Pre-condition:  none
	 * Post-condition: the person's ID is known
	 *
	 * @param  none
	 * @return String ID - the person object's government-issued ID
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Accessor method for the particular person object's ID 
	 * Pre-condition:  none
	 * Post-condition: the person's ID is known and usable for unloading
	 *
	 * @param  none
	 * @return String getID - which in turn, returns the person's ID
	 */
	public String getIdentifier() {
		return getID();
	}
	
	/**
	 * Mutator method for creating a formatted string of a particular person object's information
	 * Pre-condition:  none
	 * Post-condition: the person object's info is known for printing later
	 *
	 * @param  none
	 * @return String printInfo - the person object's formated string information (name, age, ID)
	 */
	public String toString() {
		return String.format("%20s%9s%10s%10s%6s", (getID() + ":"), "Name =", getName(), "Age =", getAge());
	}
	
	/**
	 * Method to compare two person item's IDs and return an integer based on their alphabetical order
	 * Pre-condition:  item o isn't null or empty in the arrayList
	 * Post-condition: based on the integer result (-, 0, +), the person items can be sorted
	 *
	 * @param  Items o - other person item for comparison
	 * @return int - relating to the person objects' comparability
	 */
	public int compareTo(Items o) {
		Person other = (Person)o;
		return this.ID.compareTo(other.ID);
	}
	
	/**
	 * Main method used for unit, pre/post condition and class invariant testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Person test = new Person("Billy", 45, "This is my ID");
		Person test2 = new Person("Item 2", 5000, "22 IDDDDD");
		Person test3 = new Person("AAAAA", 900, "AAAAAA");
		System.out.println(test.toString());
		System.out.println(test2.toString());
		System.out.println(test3.toString());
		System.out.println(test.name);
		System.out.println(test.age);
		System.out.println(test.ID);
		System.out.println(test.getName());
		System.out.println(test.getAge());
		System.out.println(test.getID());
		test.name = "George";
		test.age = 60;
		test.ID = "22 This is my second ID";
		System.out.println(test.getName());
		System.out.println(test.getAge());
		System.out.println(test.getID());
		System.out.println(test.compareTo(test2));
		System.out.println(test.compareTo(test3));
		System.out.println(test2.compareTo(test3));
		System.out.println(test2.compareTo(test));
		test.ID = "A";
		System.out.println(test2.compareTo(test));
		System.out.println(test.compareTo(test2));
	}
}
