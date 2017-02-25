package proj4;

import java.util.*;


/**
 * 			 This class is a storage system class
 * 			 that contains objects from the Document
 * 			 sub-classes in active and archived array
 * 			 lists. These lists will change and be
 * 			 reported upon depending on the user's
 * 			 request from the Project4 class. All
 * 			 instances of this class must be altered
 * 			 only by methods within this class and
 * 			 can only be altered by a user's input. 
 * 
 * @version  Completed 11/26/11 : Due 11/28/11
 * @author   Thomas Hervey <h46@umbc.edu>
 * @project  CMSC202 -Fall 11- Project4
 * @section  01
 */
public class DocumentStorageSystem {
	
	//Declaring an ID variable that will be assigned sequentially to each newly created document
	private int id;
	//Declaring two array lists that will be used as our storage systems: activeDocuments, archivedDocuments
	private ArrayList<Document> activeDocuments;
	private ArrayList<Document> archivedDocuments;
	
	/**
	 * Initializes our id and two array lists for our DocumentStorageSystem fileSystem
	 * Pre-condition:  none
	 * Post-condition: our id and two array lists (activeDocuments, archivedDocuments) are initialized
	 * @param  none
	 * @return none
	 */
	public DocumentStorageSystem() {
		id = 10000;
		activeDocuments = new ArrayList<Document>();
		archivedDocuments = new ArrayList<Document>();
	}
	
	/**
	 * Accessor method used to get and eventually set the next sequential id for our newly created document
	 * Pre-condition:  id initially starts out at 10000
	 * Post-condition: the next newly created document will have the next id, 1 more than the last created document
	 * @param  none
	 * @return int id - the sequential assigned id of the next new document
	 */
	public int getNextID() {
		id++;
		return id;
	}
	
	/**
	 * Mutator method for storing a newly created document into our active array list
	 * Pre-condition:  ourDocument isn't null or empty
	 * Post-condition: ourDocument is stored into the activeDocuments, which is then sorted by id
	 * @param  Document ourDocument = document to store in activeDocuments
	 * @return none
	 */
	public void storeDocument(Document ourDocument) {
		activeDocuments.add(ourDocument);
	}
	
	/**
	 * Accessor for displaying one particular active array list document based on the searched id
	 * Pre-condition:  id isn't null or empty, activeDocuments isn't empty or null
	 * Post-condition: the document with that id has its information printed out
	 * @param  int id -id of particular document
	 * @return none
	 */
	public void displayDocument(int id) {
		for(int i = 0; i < activeDocuments.size(); i++) //For each Document in our active documents
			if(activeDocuments.get(i).getID() == id) { //If the document has our desired id
				toString(activeDocuments.get(i)); } //Print out that document
	}
	
	/**
	 * Accessor method for printing out all of our active array list documents
	 * Pre-condition:  activeDocuments isn't empty or null
	 * Post-condition: the activeDocuments array list has all of its document's information printed out 
	 * @param  none
	 * @return none
	 */
	public void displayActiveDocuments() {
		for(int i = 0; i < activeDocuments.size(); i++) { //For each Document in our active documents
			toString(activeDocuments.get(i)); } //Print out that document
	}
	
	/**
	 * Accessor method for printing out all of our archived array list documents
	 * Pre-condition:  archivedDocuments isn't empty or null
	 * Post-condition: the archivedDocuments array list has all of its document's information printed out
	 * @param  none
	 * @return none
	 */
	public void displayArchivedDocuments() {
		for(int j = 0; j < archivedDocuments.size(); j++) { //For each document in our archived documents
			toString(archivedDocuments.get(j)); } //Print out that document
	}
	
	/**
	 * Accessor method for printing out all active documents in our array list that contain a desired
	 * Pre-condition:  phrase isn't null or empty, our activeDocuments array list isn't null or empty
	 * Post-condition: if there are any documents with that id, their ids will be returned and printed out
	 * specific search phrase
	 * @param  none
	 * @return none
	 */
	public void findADocument(String phrase) {
		String wantedDocuments = "";
		for(int i = 0; i < activeDocuments.size(); i++) { //For each document in our active documents
			Document checkedDocument = activeDocuments.get(i); //checkedDocument is that document's ID
			if(checkedDocument.getBody().contains(phrase)) //If that document's body contains our phrase
				wantedDocuments += checkedDocument.getID() + " "; //Add that document's ID to our list
		}
		System.out.println(wantedDocuments); //Print out this list of document IDs
	}
	
	/**
	 * Mutator method for archiving a document, moving it from our active to our archived array list
	 * Pre-condition:  id isn't null or empty, our activeDocument array list isn't empty or null
	 * Post-condition: the document with that id in our activeDocuments is removed and placed 
	 *                 in order by id in our archivedDocuments
	 * @param  none
	 * @return none
	 */
	public void archiveADocument(int id) {
		Document checkedDocument = null;
		for(int i = 0; i < activeDocuments.size(); i++) { //For each document in our active documents
			checkedDocument = activeDocuments.get(i); //checkedDocument is that document's ID
			if(checkedDocument.getID() == id) { //If the ID is our desired id
				archivedDocuments.add(checkedDocument); //add the document to the end of our archive
				activeDocuments.remove(checkedDocument); //remove the document from our active documents
				sort(activeDocuments);
				sort(archivedDocuments);
				System.out.println("Document #:" + checkedDocument.getID() + " is now archived.");	
			}
		}			
	}
	
	/**
	 * Mutator method for retrieving an archived document in our archived array list and placing it
	 * back into our active array list
	 * Pre-condition:  id isn't null or empty, our activeDocuments array list isn't empty
	 * Post-condition: the document with that id in our archivedDocuments is removed and placed
	 * 				   in order by id in our activeDocuments
	 * @param  none
	 * @return none
	 */
	public void retrieveADocument(int id) {
		Document checkedDocument = null;
		for(int i = 0; i < archivedDocuments.size(); i++) { //For each document in our active documents
			checkedDocument = archivedDocuments.get(i); //checkedDocument is that document's ID
			if(checkedDocument.getID() == id) { //If the ID is our desired id
				activeDocuments.add(checkedDocument); //add the document to the end of our active documents
				archivedDocuments.remove(checkedDocument); //remove the document from our archived documents
				sort(activeDocuments);
				sort(archivedDocuments);
				System.out.println("Document #:" + checkedDocument.getID() + 
						" has been placed back into the active list.");
			}
		}
	}
	
	/**
	 * Mutator method for bubble sorting our passed array list each and every time the list is modified
	 * Pre-condition:  our passed array list isn't empty
	 * Post-condition: our passed array list gets bubble sorted by document ID
	 * @param  ArrayList<Document> arrayList
	 * @return none
	 */
	public void sort(ArrayList<Document> arrayList) {
		int listSize = arrayList.size();
		Document temp;
		for(int i = 0; i < listSize; i++) {
			for(int x = 1; x < listSize - i; x ++) {
				if(arrayList.get(x - 1).getID() > arrayList.get(x).getID()) {
					temp = arrayList.get(x - 1);
					arrayList.set(x - 1, arrayList.get(x));
					arrayList.set(x, temp);
				}
			}
		}
	}
	
	/**
	 * Mutator method for clearing out everything in our archived array list
	 * Pre-condition:  none
	 * Post-condition: the archive array list becomes empty
	 * @param  none
	 * @return none
	 */
	public void clearArchive() {
		archivedDocuments.clear();
		System.out.println("Archive has been cleared");
	}
	
	/**
	 * Method for printing out our desired document in one of our arrays list's information
	 * Pre-condition:  none
	 * Post-condition: the string form of our document's information is printed
	 * @param  ourDocument
	 * @return none
	 */
	public void toString(Document ourDocument) {
		System.out.println(ourDocument);
	}
	
	/**
	 * main for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		DocumentStorageSystem test = new DocumentStorageSystem();
		System.out.println(test.id);
		test.id = 2000;
		System.out.println(test.getNextID());
		EmailMessage testMessage = new EmailMessage(10044, "GOB", "Hello WorldDD", new Date(), 
				"test subject4", "test recipient4", "recipient email4");
		test.activeDocuments.set(1, testMessage);
		test.toString();
		test.displayActiveDocuments();
		test.archiveADocument(10044);
		test.displayArchivedDocuments();
		test.clearArchive();
		test.displayArchivedDocuments();
		test.displayActiveDocuments();
		test.displayDocument(10000); //shouldn't be anything
		test.activeDocuments.set(1, testMessage);
		EmailMessage testMessage2 = new EmailMessage(10042, "GOB", "Hello WorldDD", new Date(), 
				"test subject4", "test recipient4", "recipient email4");
		test.activeDocuments.set(2, testMessage2);
		test.displayActiveDocuments();
	}
}
