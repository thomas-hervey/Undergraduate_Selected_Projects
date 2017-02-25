/** Enumeration: Symbols
 * @version: 10/10/11
 * @author: Ryan Bergeron
 * This class contains the values for the enumerated type Symbols. Each symbol
 * has a payout value and image associated with it.
 * Invariant: All symbols must have a positive, even payout value
 */
package proj2;

import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * The Symbols enum encapsulate each of the Symbols that appear in the Lucky Sevens
 * Slot Machine.  Using this enum assures that all code refers to the Symbols in the
 * same matter.  i.e. <code>Symbols.Seven</code>, <code>Symbols.Plum</code>, ..., with
 * now possibility of conflicting representations.
 * 
 * @author Ryan Bergeron
 *
 */
public enum Symbols {
	/**
	 * The Seven
	 */
	SEVEN(12,"images/seven.jpg"),
	/**
	 * The Watermelon
	 */
	WATERMELON(10,"images/watermelon.jpg"),
	/**
	 * The Orange
	 */
	ORANGE(8,"images/orange.jpg"),
	/**
	 * The Plum
	 */
	PLUM(6,"images/plum.jpg"),
	/**
	 * The Lemon
	 */
	LEMON(4,"images/lemon.jpg"),
	/**
	 * The Cherry
	 */
	CHERRY(2,"images/cherry.jpg");
	
	private int payout;				// Symbol payout value
	private BufferedImage image;	// Symbol image
	private String icon;			// Symbol file name

	/** Constructor - Payout must be positive and even, file name must not be null
	 * @param payout - Symbol payout amount
	 * @param icon - Symbol image file name
	 */
	Symbols(int payout, String icon){
		this.payout = payout;
		this.icon = icon;
		loadImage(icon);
	}
	
	/** Copy Constructor - Symbol must not be null
	 * @param s - A single symbol
	 */
	Symbols(Symbols s){
		payout = s.payout;
		icon = s.icon;
		loadImage(icon);
	}

	/** Accessor for symbol payout amount. No preconditions.
     * 
	 * @return - Symbol's payout amount
	 */
	public int getPayout(){
		return payout;
	}
	
	/** Accessor for symbol image. No preconditions.
	 * 
	 * @return - Returns symbol image
	 */
	public BufferedImage getImage(){
		return image;
	}
	
	/** Compares two symbols for equality. Symbol for comparison must not be null.
	 * @param s A single symbol
	 * @return true if symbols are equal, else false
	 */
	public boolean equals(Symbols s){
		if(icon.equals(s.icon)){
			return true;
		}
		return false;
	}
		
	/** Loads a symbol's image.
	 * @param icon - Symbol image file name
	 * @precondition - Image file name must not be null.
	 */
	private void loadImage(String icon){
		image = null;
		try{
			image = ImageIO.read(getClass().getResource(icon));
		}catch(IOException e){
			System.out.println("Error:  Could not load image: "+ icon);
		}
	}
	
	/** toString(). No precondition.
	 * 
	 * @return - String containing icon file name
	 */
	public String toString(){
		return icon;
	}
}
