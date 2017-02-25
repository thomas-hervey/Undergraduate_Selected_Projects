package proj4;

import java.util.Date;

/**
 * 			 This class is a sub-class of the Document class
 * 			 and has particular attributes often found common
 * 			 in reports such as a title in addition to the
 * 			 common attributes to all documents including id, 
 * 			 author, body and date. All instances of this class
 * 			 must have these attributes.
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public class Report extends Document {

	//Declaring the new variable common to all Reports: title
	String title;
	
	/**
	 * Constructs a new Report object that holds the common Report attribute plus the supered
	 * four common Document attributes
	 * Pre-condition:  id, author, body, date and title are not null or empty
	 * Post-condition: these variables are initialized
	 * @param id
	 * @param author
	 * @param body
	 * @param date
	 * @param title
	 */
	public Report(int id, String author, String body, Date date, String title) {
		super(id, author, body, date);
		this.title = title;		
	}
	
	/**
	 * Accessor method for the Report's title (used to access piv in DSS)
	 * Pre-condition:  title isn't null or empty
	 * Post-condition: the title for the particular Report is returned
	 *
	 * @param  none
	 * @return String title -the report's title
	 */
	public String getTitle() {
		if(title == "" || title == null) { return null; }
		return title;
	}
	
	/**
	 * Method for converting all of the Report's attributes to a string
	 * Pre-condition:  none
	 * Post-condition: the information for the particular Report is returned to then be printed out
	 *
	 * @param  none
	 * @return String information -the Report's information in string form
	 */
	public String toString() {
		String information = "";
		information += "Document #: " + getID() + "\n";
		information += "Date: " + getDate() + "\n";
		information += "Author: " + getAuthor() + "\n";
		information += "Title: " + getTitle() + "\n";
		information += getBody();
		return information;
	}
	
	/**
	 * main method for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args){
		Report test = new Report(10000, "Thomas", "Hello World", new Date(), "Our Title");
		System.out.println(test.getID());
		System.out.println(test.getAuthor());
		System.out.println(test.getTitle());
		System.out.println(test.getDate());
		System.out.println(test.getBody());
		System.out.println(test.toString());
		test.toString();
	}
}
