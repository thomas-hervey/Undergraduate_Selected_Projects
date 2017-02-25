package proj3;

/**
 * 			  Encapsulates the row and column of one cell in a 2-D coordinate system. 
 * 			  Class Invariants: The row and column are nonnegative, coordinate objects are immutable
 * @version   Completed Nov 7 : Due Nov 7
 * @author    Thomas Hervey <h46@umbc.edu>
 * @project	  CMSC202 -Fall 2011 -Project 3
 * @section   01
 */
public class Coordinate extends java.lang.Object {
	
	private int rowNumber;
	private int columnNumber;
	
	/**
	* Constructor - new Coordinate from the specified row and column
	*
    * @Param  row - the row (zero based)
    * @Param  column - the column (zero base)
    * @return none
    */
	public Coordinate(int row,int column) {
		this.rowNumber = row;
		this.columnNumber = column;
	}
		
	/**
	* Accessor for the column
	* Pre-condition: columnNumber >= 0
	* Post-condition: none
	*
	* @param  none
	* @Return int columnNumber - the column number
	*/
	public int getColumn() {	
		if(columnNumber <= 0) { return 0; }
		else { return columnNumber; }
	}
	
	/**
	* Accessor for the row
	* Pre-condition: rowNumber >= 0
	* Post-condition: none
	*
	* @param  none
	* @Return int rowNumber - the row number
	*/
	public int getRow() {
		if(rowNumber <= 0) { return 0; }
		else { return rowNumber; }
	}
	/**
	 * Main method for unit testing
	 *
	 * @param  args
	 * @return none
	 */
	public static void main(String[] args) {
		Coordinate test = new Coordinate(2, 3);
		System.out.println(test.getRow());
		System.out.println(test.getColumn());
		test.rowNumber = 3;
		System.out.println(test.rowNumber);
		System.out.println(test.getRow());
		test.columnNumber = 4;
		System.out.println(test.columnNumber);
		System.out.println(test.getColumn());
		test.columnNumber = -3;
		System.out.println(test.getColumn());
	}
}
