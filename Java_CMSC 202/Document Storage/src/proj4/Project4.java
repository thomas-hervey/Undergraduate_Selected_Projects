/**
 * 
 */
package proj4;

import java.util.Scanner;
import java.util.Date;

/**
 * 			 This class is the running class that
 * 			 controls the flow and the hierarchy of
 * 			 our program. Here the user interacts 
 * 			 with a menu and can create, remove,
 *			 or view documents that are in our
 *			 document storage system in our
 *			 DocumentStorageSystem class. All instances
 *			 of this class (calls within main) will
 *			 not call anything except for helper methods
 *			 and methods within our document storage system.
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public class Project4 {
	
	private static Scanner input = new Scanner(System.in); //Creating a scanner object
	private static int menuChoice; //Creating an integer to be assigned to the nextLine in getMenuChoice
	private static int idTest; //Creating an integer to be assigned to the nextLine in getDocumentID
	
	//Creating a string to be passed into getNewBody, helps reuse code for getDsitributonList
	private static String textBody = "text body";
	private static DocumentStorageSystem fileSystem = new DocumentStorageSystem(); //Creating our document storage system
	
	/**
	 * Mutator method for creating a new EmailMessage. An email consists of: ID, 
	 * Author, Date, Body, Subject, Recipient, Recipient Email
	 * Pre-condition:  none
	 * Post-condition: a new email message with no empty values is created
	 *
	 * @param  none
	 * @return new EmailMessage
	 */
	private static Document create_new_email(String textBody) {
		int id = fileSystem.getNextID();
		String author = getNewAuthor();
		String subject = getNewSubject();
		String recipient = getNewRecipient();
		String recipientEMail = getNewRecipientEMail();
		String body = getNewBody(textBody);
		System.out.println("E-mail #" + id + " entered into active documents list.");
		Date currentDateTime = new Date();
		return new EmailMessage(id, author, body, currentDateTime, subject, recipient, recipientEMail);
	}
	
	/**
	 * Mutator method for creating a new Memo. A memo consists of: ID, 
	 * Author, Date, Body, Subject, Recipient, Distribution List
	 * Pre-condition:  none
	 * Post-condition: a new memo with no empty values is created
	 *
	 * @param  none
	 * @return new Memo
	 */
	private static Document create_new_memo(String textBody) {
		int id = fileSystem.getNextID();
		String author = getNewAuthor();
		String subject = getNewSubject();
		String recipient = getNewRecipient();
		String distributionList = getNewDistributionList();
		String body = getNewBody(textBody);
		System.out.println("Memo #" + id + " entered into active documents list.");
		Date currentDateTime = new Date();
		return new Memo(id, author, body, currentDateTime, subject, recipient, distributionList);
	}
	
	/**
	 * Mutator method for creating a new Report. A report consists of: ID, Author, Date, Body, Title
	 * Pre-condition:  none
	 * Post-condition: a new report with no empty values is created
	 *
	 * @param  none
	 * @return new Report
	 */
	private static Document create_new_report(String textBody) {
		int id = fileSystem.getNextID();
		String author = getNewAuthor();
		String title = getNewTitle();
		String body = getNewBody(textBody);
		Date currentDateTime = new Date();
		System.out.println("Report #" + id + " entered into active documents list.");
		return new Report(id, author, body, currentDateTime, title);
	}
	
	/**
	 * Mutator method for creating the user's desired author
	 * Pre-condition:  none
	 * Post-condition: a new author for a document is created
	 *
	 * @param  none
	 * @return String author -our new document's author
	 */
	private static String getNewAuthor() {
		System.out.println("Enter author:");
		String author = input.nextLine();
		return author;
	}
	
	/**
	 * Mutator method for creating the user's desired body
	 * Pre-condition:  none
	 * Post-condition: a new body, including blank lines, is created for the document
	 *
	 * @param  none
	 * @return String body -our new document's body
	 */
	private static String getNewBody(String param) {
		String body = "";
		System.out.println("Enter " + param + " (Type END on separate line to stop):");
		String currentLine = input.nextLine() + "\n";
		String temp = currentLine;
		temp = temp.trim().toUpperCase();
		while(!temp.equals("END")) {
			if(currentLine.equals("")) { body += "\n"; }
			else{ body += currentLine; }
			currentLine = input.nextLine() + "\n";
			temp = currentLine;
			temp = temp.trim().toUpperCase();
		}
		return body;
	}
	
	/**
	 * Mutator method for creating the user's desired EmailMessage or Memo subject
	 * Pre-condition:  none
	 * Post-condition: a new subject for an email or memo is created
	 *
	 * @param  none
	 * @return String subject - our new EmailMessage or Memo's subject
	 */
	private static String getNewSubject() {
		System.out.println("Enter subject:");
		String subject = input.nextLine();
		return subject;
	}
	
	/**
	 * Mutator method for creating the user's desired EmailMessage or Memo recipient name
	 * Pre-condition:  none
	 * Post-condition: a new recipient for an email or memo is created
	 *
	 * @param  none
	 * @return String recipient -our new EmailMessage or Memo's recipient
	 */
	private static String getNewRecipient() {
		System.out.println("Enter recipient:");
		String recipient = input.nextLine();
		return recipient;
	}
	
	/**
	 * Mutator method for creating the user's desired recipient email
	 * Pre-condition:  none
	 * Post-condition: a new recipient email for an email is created
	 *
	 * @param  none
	 * @return String recipientEMail - our new EmailMessage recipient email
	 */
	private static String getNewRecipientEMail() {
		System.out.println("Enter recipient's e-mail address:");
		String recipientEMail = input.nextLine();
		return recipientEMail;
	}
	
	/**
	 * Mutator method for creating the user's desired distribution list
	 * Pre-condition:  none
	 * Post-condition: a new distribution list for a memo is created
	 *
	 * @param  none
	 * @return String distributionList -our new Memo distribution list
	 */
	private static String getNewDistributionList() {
		return getNewBody("distribution list");
	}
	
	/**
	 * Mutator method for creating the user's desired title
	 * Pre-condition:  none
	 * Post-condition: a title for a report is created
	 *
	 * @param  none
	 * @return String title -our new Report title
	 */
	private static String getNewTitle() {
		System.out.println("Enter title:");
		String title = input.nextLine();
		return title;
	}
	
	/**
	 * Accessor method for retrieving the user's desired search ID
	 * Pre-condition:  none
	 * Post-condition: if the user enters in an integer, it is returned and used for searching 
	 * 				   otherwise nothing is returned, the program doesn't crash and that iteration 
	 * 				   of main's loop gets skipped
	 * @param  none
	 * @return documentID - the search for document ID
	 */
	private static int getDocumentID(int idTest) {
		System.out.println("Enter the document ID: ");
		//After reading the run time error, I found out how to handle strings that need to be integers
		t
		//Try and see if the input is an integer
		ry{ 
			idTest = Integer.parseInt(input.nextLine());
		}
		catch(NumberFormatException nfe){ //Catch these exceptions
			
		}
		int documentID = new Integer(idTest); //Convert the input to an integer and return
		return documentID;
	}
	
	/**
	 * Accessor method for retrieving the user's desired search phrase
	 * Pre-condition:  none
	 * Post-condition: a search phrase is created to be used to search
	 * @param  none
	 * @return input.nextLine() -search phrase
	 */
	private static String locateDocument() {
		System.out.println("Get search phrase: ");
		String phrase = input.nextLine();
		return phrase;
	}
	
	/**
	 * Accessor method for getting the user's menu choice
	 * Pre-condition:  none
	 * Post-condition: if the user enters in an integer, it is returned and used for searching 
	 * 				   otherwise nothing is returned, the program doesn't crash and that iteration 
	 * 				   of main's loop gets skipped
	 * @param  none
	 * @return menuChoice - number menu choice
	 */
	public static int getMenuChoice(int menuChoice) {
		displayMenu();
		System.out.println("Enter your choice: ");
		//After reading the run time error, I found out how to handle strings that need to be integers
		
		//Try and see if the input is an integer
		try{ menuChoice = Integer.parseInt(input.nextLine()); }
		catch(NumberFormatException nfe){
			/* TODO: catch exception */
		}
		menuChoice = new Integer(menuChoice); //Convert the input to an integer and return
		return menuChoice;
	}
	
	/**
	 * Method that displays the main menu options for our Document Storage System to the user
	 * Pre-condition:  none
	 * Post-condition: the menu will be printed for the user to make a decision
	 *
	 * @param  none
	 * @return none
	 */
	private static void displayMenu(){
		String menu = "";
		menu += "\n     Document Storage System Menu";
		menu += "\n 1 - Create and store an e-mail";
		menu += "\n 2 - Create and store a memo";
		menu += "\n 3 - Create and store a report";
		menu += "\n 4 - Display a document";
		menu += "\n 5 - List all active documents";
		menu += "\n 6 - List all archived documents";
		menu += "\n 7 - Locate documents containing a specific word or phrase";
		menu += "\n 8 - Archive a document";
		menu += "\n 9 - Retrieve a document from the archive";
		menu += "\n10 - Clear the archive";
		menu += "\n99 - QUIT";
		System.out.println(menu);
	}

	/**
	 * Main method called when running the program and supporting the programs skeletal structure.
	 * This method represents the outcomes of the user's menu choice and controls the program flow.
	 * Pre-condition:  none
	 * Post-condition: none
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		int userChoice = getMenuChoice(menuChoice); //Get the first user menu choice
		while(userChoice != 99){ //As long as the user doesn't select 99 to QUIT
			//Create a new email and store it in fileSystem's activeDocuments
			if(userChoice == 1){
				fileSystem.storeDocument(create_new_email(textBody));
			}
			//Create a new memo and store it in fileSystem's activeDocuments
			else if(userChoice == 2){
				fileSystem.storeDocument(create_new_memo(textBody));
			}
			//Create a new report and store it in fileSystem's activeDocuments
			else if(userChoice == 3){
				fileSystem.storeDocument(create_new_report(textBody));
			}
			//Get a desired document ID and display the contents (except body) of that fileSystem's activeDocuments document
			else if(userChoice == 4){
				fileSystem.displayDocument(getDocumentID(idTest));	
			}
			//Display the contents (except body) of all of fileSystem's activeDocuments
			else if(userChoice == 5){
				fileSystem.displayActiveDocuments();
			}
			//Display the contents (except body) of all of fileSystem's archivedDocuments
			else if(userChoice == 6){
				fileSystem.displayArchivedDocuments();
			}
			//Try and locate any document by ID if any contain an entered phrase
			else if(userChoice == 7){
				fileSystem.findADocument(locateDocument());
			}
			//Archive a document from the active documents given an ID
			else if(userChoice == 8){
				fileSystem.archiveADocument(getDocumentID(idTest));
			}
			//Retrieve an archived document given an ID
			else if(userChoice == 9){
				fileSystem.retrieveADocument(getDocumentID(idTest));
			}
			//Remove all files from the archived documents
			else if(userChoice == 10){
				fileSystem.clearArchive();
			}
		userChoice = getMenuChoice(menuChoice); //After each iteration & result, re-prompt for a menu choice
		}
		System.out.println("Exiting Document Storage System");
		System.exit(0); //If the user selects option 99, they will exit the loop and the program
	}
}
