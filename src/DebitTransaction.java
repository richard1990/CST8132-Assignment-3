/**
 * This class inherits from the Transaction class and acts as a
 * debit transaction, where the specified amount entered by the
 * user will be deducted from their bank balance.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 */
public class DebitTransaction extends Transaction {
	
	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = -5023435420593132376L;

	/**
	 * Overridden get method that returns the transaction amount, which
	 * will subtract from the user's bank balance.
	 * @return the transaction amount as a double.
	 */
	@Override
	public double getAmount() { return (-dAmount); }
	
	/**
	 * Overridden toString method that returns the super class and
	 * specifies that the transaction was a debit.
	 */
	@Override
	public String toString() {
		return new String("DEBIT\t" +super.toString());
	}
} // end class CreditTransaction
