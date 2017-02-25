package proj4;

import java.util.Date;
/**
 * 			 This class is a base class for the derived
 * 			 sub-classes which holds attributes common
 * 			 to all documents including id, author, body
 * 			 and date. All instances of this class must
 * 			 have these attributes.		 
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public abstract class Document {
	
	// Variables common to all Documents: id, author, body, date
	private int id;
	private String author;
	private String body;
	private Date date;
	
	/**
	 * Constructor - new Document object that holds the four common Document attributes
	 * Pre-condition:  id, author, body and date are not empty or null
	 * Post-condition: these variables are initialized
	 * @param  author
	 * @param  id
	 * @param  body
	 * @param  date
	 * @return none
	 */
	public Document(int id, String author, String body, Date date) {
		this.id = id;
		this.author = author;
		this.body = body;
		this.date = date;
	}
	
	/**
	 * Accessor method for the Document's author (used to access piv in DSS)
	 * Pre-condition:  author isn't null
	 * Post-condition: the author of the particular document is returned
	 *
	 * @param  none
	 * @return String author - Document's author
	 */
	public String getAuthor() {
		if(author == "" || author == null) { return null; }
		return author;
	}
	
	/**
	 * Accessor method for the Document's ID (used to access piv in DSS)
	 * Pre-condition:  none
	 * Post-condition: the id for the particular document is returned
	 *
	 * @param  none
	 * @return int id - Document's id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Accessor method for the Document's body (used to access piv in DSS)
	 * Pre-condition:  body isn't empty or null
	 * Post-condition: the body of the particular document is returned
	 * @param  none
	 * @return String body - Document's body
	 */
	public String getBody() {
		if(body == "" || body == null) { return null; }
		return body;
	}
	
	/**
	 * Accessor method for the Document's date (used to access piv in DSS)
	 * Pre-condition:  the date makes logical sense in time
	 * Post-condition: the date of the particular document is returned
	 *
	 * @param  none
	 * @return Date date - Document's date
	 */
	public Date getDate() {
		return date;
	}
	
	public String getSubject;  //Abstract method stub for getting a specific type of Document's subject
	
	public String getRecipient; //Abstract method stub for getting a specific type of Document's recipient
	
	public String getRecipientEMail; //Abstract method stub for getting a specific type of Documents's recipient email
	
	public String getDistributionList; //Abstract method stub for getting a specific type of Documents's distribution list
	
	public String getTitle; //Abstract method stub for getting a specific type of Document's title
	
	/**
	 * Method for converting all of the Document's attributes to a string
	 * Pre-condition:  none
	 * Post-condition: the information for the particular document will be returned to then be printed out
	 *
	 * @param  none
	 * @return String information - the Document's information in string form
	 */
	public String toString() {
		String information = "";
		information += "Document #: " + getID() + "\n";
		information += "Date: " + getDate() + "\n";
		information += "Author: " + getAuthor() + "\n";
		information += getBody();
		return information;
	}
	
	/**
	 * main method for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Document test = new EmailMessage(10033, "Mary", "body test", new Date(), "subject through document", 
				"recipient through document", "recipient email through document");
		System.out.println(test.id);
		System.out.println(test.author);
		System.out.println(test.body);
		System.out.println(test.date);
		System.out.println(test.getID());
		System.out.println(test.getAuthor());
		System.out.println(test.getBody());
		System.out.println(test.getDate());
		test.author = "";
		System.out.println(test.getAuthor());
		test.body = "";
		System.out.println(test.getBody());
		System.out.println(test.toString());
		test.toString();

		
	}
}
