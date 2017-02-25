package proj4;

import java.util.Date;

/**
 *  		 This class is a sub-class of the Document class
 * 			 and has particular attributes often found common
 * 			 in email messages such as a subject, recipient 
 * 			 and recipient email in addition to the common 
 * 			 attributes to all documents including id, author, 
 * 			 body and date. All instances of this class must
 * 			 have all of these attributes.		 
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public class EmailMessage extends Document {
	
	//Declaring three new variables common to all EmailMessages: subject, recipient, recipientEMail
	private String subject;
	private String recipient;
	private String recipientEMail;
	
	/**
	 * Constructs a new EmailMessage object that holds the three common EmailMessage attributes plus the
	 * supered four common Document attributes
	 * Pre-condition:  id, author, body, date, subject, recipient and recipientEmail are not empty or null
	 * Post-condition: these variables are initialized
	 * @param  id
	 * @param  author
	 * @param  body
	 * @param  date
	 * @param  subject
	 * @param  recipient
	 * @param  recipientEMail
	 * @return none
	 */
	public EmailMessage(int id, String author, String body, Date date, 
		String subject, String recipient, String recipientEMail) {
		super(id, author, body, date);
		this.subject = subject;
		this.recipient = recipient;
		this.recipientEMail = recipientEMail;
	}
	
	
	/**
	 * Accessor method for the EmailMessage's subject (used to access piv in DSS)
	 * Pre-condition:  subject isn't empty or null
	 * Post-condition: the subject of the particular EmailMessage is returned
	 *
	 * @param  none
	 * @return String subject -the EmailMessage's subject
	 */
	public String getSubject() {
		if(subject == "" || subject == null) { return null; }
		return subject;
	}
	
	
	/**
	 * Accessor method for the EmailMessage's recipient (used to access piv in DSS)
	 * Pre-condition:  recipient isn't empty or null
	 * Post-condition: the recipient of the particular EmailMessage is returned
	 *
	 * @param  none
	 * @return String recipient - the EmailMessage's recipient
	 */
	public String getRecipient() {
		if(recipient == "" || recipient == null) { return null; }
		return recipient;
	}
	
	/**
	 * Accessor method for the EmailMessage's recipient's email (used to access piv in DSS)
	 * Pre-condition:  recipientEmail isn't empty or null
	 * Post-condition: the recipientEmail of the particular EmailMessage is returned
	 *
	 * @param  none
	 * @return String recipientEmail -the EmailMessage's recipient's email
	 */
	public String getRecipientEMail() {
		if(recipientEMail == "" || recipientEMail == null) { return null; }
		return recipientEMail;
	}
	
	/**
	 * Method for converting all of the EmailMessage's attributes to a string
	 * Pre-condition:  none
	 * Post-condition: the information for the particular EmailMessage will be returned to then be printed out
	 *
	 * @param  none
	 * @return String information -the EmailMessage's information in string form
	 */
	public String toString() {
		String information = "";
		information += "Document #: " + getID() + "\n";
		information += "Date: " + getDate() + "\n";
		information += "Author: " + getAuthor() + "\n";
		information += "Subject: " + getSubject() + "\n";
		information += "Recipient: " + getRecipient() + "\n";
		information += "Recipient's e-mail: " + getRecipientEMail() + "\n";
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
		EmailMessage test = new EmailMessage(10011, "Gary", "Hello World3", new Date(), 
				"test subject2", "test recipient2", "recipient email");
		System.out.println(test.getID());
		System.out.println(test.getAuthor());
		System.out.println(test.getDate());
		System.out.println(test.getBody());
		System.out.println(test.getSubject());
		System.out.println(test.getRecipient());
		System.out.println(test.getRecipientEMail());
		System.out.println(test.toString());
		test.recipient = "";
		System.out.println(test.getRecipient());
		test.recipientEMail = "";
		System.out.println(test.getRecipientEMail());
		test.subject = "new test subject";
		System.out.println(test.getSubject());
		test.toString();
	}
}
