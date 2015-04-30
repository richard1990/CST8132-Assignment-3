// import statements
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class models a transaction, where the user selects a category
 * (e.g. rent, phone, etc.) and enters the transaction amount.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 */
public class Transaction implements Serializable {

	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = 7206793815873168803L;
	/** Double that holds the transaction amount. */
	protected double dAmount;
	/** String that holds the category. */
	protected String sCategory = new String();
	/** OurDate object. */
	protected OurDate date = new OurDate();

	/** 
	 * Get method that returns the transaction amount.
	 * @return the transaction amount as a double.
	 */
	public double getAmount() { return dAmount; }
	/**
	 * Get method that returns the category.
	 * @return the transaction category as a String.
	 */
	public String getCategory() { return sCategory; }
	
	/**
	 * Void method that allows user to enter a transaction.
	 * @param	category	Category object.
	 */
	public void inputTransaction(Category category) {
		Scanner input = new Scanner(System.in);
		boolean continueLoop = true;
		// create date for transaction
		this.date.inputFromKeyboard();
		// make sCategory equal to the found category in the
		// String array of categories (findCategory will
		// return the index, then find the specified category by
		// using getCategory)
		sCategory = category.getCategory(category.findCategory());
		// keep looping as long as continueLoop is true
		do {
			try {
				// ask user to enter a valid amount
				do {
					System.out.print("Enter amount: ");
					this.dAmount = input.nextDouble();
				} while (dAmount < 0 || dAmount > 1000000);
				continueLoop = false;
			} catch (InputMismatchException e) {
				System.out.print("Invalid input! Enter valid amount: ");
				input.nextLine();
			}
		} while (continueLoop);
	} // end method inputTransaction
	
	/**
	 * toString method that returns the date, category, and amount.
	 */
	public String toString() {
		return new String(date +"\t" +sCategory +"\t$" +dAmount);
	}
} // end class Transaction