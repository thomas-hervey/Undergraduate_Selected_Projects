package proj5;


/**
 * 			 This class is one of the two types of items that
 * 			 can be loaded into our generic plane. This class
 * 			 implements the Items interface which is used to
 * 			 grab the appropriate type of identification for
 * 			 the generic airplane as well as be able to compare
 * 			 other cargo by their unique label. All instances 
 * 			 of this class (as all cargo) have a label, weight,
 * 			 length, width and height.
 * 
 * 			 Help from CMSC Help Center
 * @version  Completed 12/13/11 : Due 12/13/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project5
 * @section  01
 */
public class Cargo implements Items {
	
	// cargo label and size descriptions
	private String label;
	private int weight;
	private int length;
	private int width;
	private int height;
	
	public Cargo(String label, int weight, int length, int width, int height) {
		this.label = label;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Accessor method for the particular cargo object's label
	 * Pre-condition:  none
	 * Post-condition: the label is known and used for sorting
	 *
	 * @param  none
	 * @return String label - the cargo object's label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Accessor method for the particular cargo object's ID 
	 * Pre-condition:  none
	 * Post-condition: the cargo's label is known and usable for unloading
	 *
	 * @param  none
	 * @return String getLabel - which in turn, returns the cargo's label
	 */
	public String getIdentifier() {
		return getLabel();
	}
	
	/**
	 * Accessor method for the particular cargo object's weight
	 * Pre-condition:  none
	 * Post-condition: the weight is known
	 *
	 * @param  none
	 * @return int weight - the cargo object's weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Accessor method for the particular cargo object's length
	 * Pre-condition:  none
	 * Post-condition: the length is known
	 *
	 * @param  none 
	 * @return int length - the cargo object's length dimension
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Accessor method for the particular cargo object's width
	 * Pre-condition:  none
	 * Post-condition: the width is known
	 *
	 * @param  none
	 * @return int width - the cargo object's width dimension
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Accessor method for the particular cargo object's height
	 * Pre-condition:  none
	 * Post-condition: the height is known
	 *
	 * @param  none
	 * @return int height - the cargo object's height dimension
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Mutator method for creating a formatted string of a particular cargo object's information
	 * Pre-condition:  none
	 * Post-condition: the cargo object's info is known for printing later
	 *
	 * @param  none
	 * @return String StringprintInfo - the cargo object's formated string information 
	 *                           (label, weight, length, width, height)
	 */
	public String toString() {
		return String.format
		("%20s%15s%6s%13s%3s%3s%3s%3s%3s", (getLabel() + ":"), "Weight =", getWeight(), 
			"Dimensions =", getLength(), "x", getWidth(), "x", getHeight());
	}
	
	/**
	 * Method to compare two cargo item's labels and return an integer based on their alphabetical order
	 * Pre-condition:  item o isn't null or empty in the arrayList
	 * Post-condition: based on the integer result (-, 0, +), the cargo items can be sorted
	 *
	 * @param  Items o - other cargo item for comparison
	 * @return int - relating to the cargo objects' comparability
	 */
	public int compareTo(Items o) {
		Cargo other = (Cargo)o;
		return this.label.compareTo(other.label);
	}
	
	/**
	 * Main method used for unit, pre/post condition and class invariant testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Cargo test = new Cargo("Item 1", 200, 5, 10, 10);
		Cargo test2 = new Cargo("Item 2", 300, 6, 300, 10);
		Cargo test3 = new Cargo("AAAAA", 3300, 6, 30, 10);
		System.out.println(test.toString());
		System.out.println(test2.toString());
		System.out.println(test3.toString());
		System.out.println();
		System.out.println(test.label);
		System.out.println(test.weight);
		System.out.println(test.length);
		System.out.println(test.width);
		System.out.println(test.height);
		System.out.println(test.getLabel());
		System.out.println(test.getWeight());
		System.out.println(test.getLength());
		System.out.println(test.getWidth());
		System.out.println(test.getHeight());
		test.label = "Item 2 now";
		test.weight = 400;
		System.out.println(test.getWeight());
		System.out.println(test.getLabel());
		test.height = 55;
		test.length = 5;
		test.width = 77;
		System.out.println(test.getHeight());
		System.out.println(test.getLength());
		System.out.println(test.getWidth());
		System.out.println(test.compareTo(test2));
		System.out.println(test.compareTo(test3));
		System.out.println(test2.compareTo(test3));
		System.out.println(test2.compareTo(test));
		test.label = "A";
		System.out.println(test2.compareTo(test));
	}
}
