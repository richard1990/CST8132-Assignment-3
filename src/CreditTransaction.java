/**
 * This class inherits from the Transaction class and acts as a
 * credit transaction, where the specified amount entered by the
 * user will be added to their bank balance.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 */
public class CreditTransaction extends Transaction {
	
	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = 5536948508290734898L;

	/**
	 * Overridden get method that returns the transaction amount, which
	 * will add to the user's bank balance.
	 * @return the transaction amount as a double.
	 */
	@Override
	public double getAmount() { return dAmount; }
	
	/**
	 * Overridden toString method that returns the toString method of the
	 * super class and specifies that the transaction was a credit.
	 */
	@Override
	public String toString() {
		return new String("CREDIT\t" +super.toString());
	}
} // end class CreditTransaction
