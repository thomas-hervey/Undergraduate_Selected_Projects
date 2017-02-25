package proj5;

public interface Items extends Comparable<Items> {
	
	/**
	 * Accessor method stub implemented to get either a person's ID or a cargo's label
	 * Pre-condition:  none
	 * Post-condition: the piv is known
	 * @param  none
	 * @return ID or label - the piv returned from either Person's getID() or Cargo's getLabel()
	 */
	public String getIdentifier();
}
