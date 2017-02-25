package proj4;

import java.util.Date;

/**
 *  		 This class is a sub-class of the Document class
 * 			 and has particular attributes often found common
 * 			 in memos such as a subject, recipient and 
 * 			 distribution list in addition to the common 
 * 			 attributes to all documents including id, author, 
 * 			 body and date. All instances of this class must
 * 			 have these attributes.
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public class Memo extends Document {
	
	//Declaring three new variables common to all Memos: subject, recipient, distributionList
	private String subject;
	private String recipient;
	private String distributionList;
	
	/**
	 * Constructs a new Memo object that holds three common Memo attributes plus the supered four
	 * common Document attributes
	 * Pre-condition:  id, author, body, date, subject, recipient and distributionList are not empty or null
	 * Post-condition: these variables are initialized
	 * @param id
	 * @param author
	 * @param body
	 * @param date
	 * @param subject
	 * @param recipient
	 * @param distributionList
	 */
	public Memo(int id, String author, String body, Date date, String subject, 
		String recipient, String distributionList) {
		super(id, author, body, date);
		this.subject = subject;
		this.recipient = recipient;
		this.distributionList = distributionList;
	}
	
	/**
	 * Accessor method for the Memo's subject (used to access piv in DSS)
	 * Pre-condition:  subject isn't null or empty
	 * Post-condition: the subject of the particular Memo is returned
	 *
	 * @param  none
	 * @return String subject -the Memo's subject
	 */
	public String getSubject() {
		if(subject == "" || subject == null) { return null; }
		return subject;
	}
	
	/**
	 * Accessor method for the Memo's recipient (used to access piv in DSS)
	 * Pre-condition:  recipient isn't null or empty
	 * Post-condition: the recipient of the particular Memo is returned
	 *
	 * @param  none
	 * @return String recipient -the Memo's recipient
	 */
	public String getRecipient(){
		if(recipient == "" || recipient == null) { return null; }
		return recipient;
	}
	
	/**
	 * Accessor method for the Memo's distribution list (used to access piv in DSS)
	 * Pre-condition:  distributionList isn't null or empty
	 * Post-condition: the distributionList of the particular Memo is returned
	 *
	 * @param  none
	 * @return String distributionList -the Memo's distribution list
	 */
	public String getDistributionList(){
		if(distributionList == "" || distributionList == null) { return null; }
		return distributionList;
	}
	
	/**
	 * Method for converting all of the Memo's attributes to a string
	 * Pre-condition:  none
	 * Post-condition: the information for the particular Memo will be returned then to be printed out
	 *
	 * @param  none
	 * @return none
	 */
	public String toString() {
		String information = "";
		information += "Document #: " + getID() + "\n";
		information += "Date: " + getDate() + "\n";
		information += "Author: " + getAuthor() + "\n";
		information += "Subject: " + getSubject() + "\n";
		information += "Recipient: " + getRecipient() + "\n";
		information += "Distribution list: " + getDistributionList() + "\n";
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
		Memo test = new Memo(10011, "John", "Hello World", new Date(), "test subject1", "test recipient1", "da list");
		System.out.println(test.getID());
		System.out.println(test.getAuthor());
		System.out.println(test.getDate());
		System.out.println(test.getBody());
		System.out.println(test.getSubject());
		System.out.println(test.getRecipient());
		System.out.println(test.getDistributionList());
		System.out.println(test.toString());
		test.subject = "";
		System.out.println(test.getSubject());
		test.recipient = "asdasd";
		System.out.println(test.getRecipient());
		test.distributionList = "asdasd asd asd asd ";
		System.out.println(test.getDistributionList());
		test.toString();	
	}
}
