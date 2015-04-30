// import statements
import java.util.Scanner;
import java.io.Serializable;
/**
 * This class models a bank account. The user will be able to make up to
 * 1000 transactions where they specify the transaction category and the
 * transaction amount that will be added or deducted from their bank
 * balance.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 */
public class Bank implements Serializable {
	
	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = -2988419960113692987L;
	/** Integer that holds the max number of transactions (1000). */
	final private int MAXTRANSACTIONS = 1000;
	/** Double that holds the user's bank balance. */
	private double dBalance;
	/** Array of Transaction objects to hold the transactions. */
	private Transaction[] transactions = new Transaction[MAXTRANSACTIONS];
	/** Integer to hold the number of transactions. */
	private int nNumberOfTransactions;
	
	/**
	 * Default constructor.
	 */
	public Bank() { }
	/**
	 * Initial constructor, user will have $100 in their bank to start.
	 */
	public Bank(double dBalance) {
		this.dBalance = 100;
	}
	
	/**
	 * Get method that returns the user's bank balance.
	 * @return the user's bank balance as a double.
	 */
	public double getBalance() { return dBalance; }

	/**
	 * Void method that displays all transactions for a specific category.
	 * @param	categories	Category object.
	 */
	public void displayCategory(Category category) {
		double dAmount = 0; // double used to hold the total amount specific
							// to that category
		String sCategory;
		// if no transactions exist, return
		if (nNumberOfTransactions == 0) {
			System.out.println("You must first create a transaction to do this option!");
			return;
		}
		// make sCategory equal to the found category in the
		// String array of categories (findCategory will
		// return the index, then find the specified category by
		// using getCategory)
		sCategory = category.getCategory(category.findCategory());
		// loop through the number of transactions, and if that specific
		// transaction's category equals the category specified by the user, 
		// display the transaction to the user and increment the total 
		// transaction amount for that specific category
		for (int i = 0; i < nNumberOfTransactions; i++) {
			if (transactions[i].getCategory().equals(sCategory)) {
				System.out.println(transactions[i].toString());
				dAmount += transactions[i].getAmount();
			}
		}
		System.out.println("CATEGORY BALANCE:    $" +dAmount);
	} // end method displayCategory
	
	/**
	 * Void method that displays all transactions.
	 */
	public void displayTransactions() {
		// if no transactions exist, return
		if (nNumberOfTransactions == 0) {
			System.out.println("No transactions to display.");
			return;
		}
		System.out.println("TRANSACTION RECORD:");
		// loop through the array of transactions and print each
		// element on a new line
		for (int i = 0; i < nNumberOfTransactions; i++) {
			System.out.println(transactions[i].toString());
		}
		System.out.println("BALANCE:    $" +getBalance()); // display the bank balance
	} // end method displayTransactions

	/**
	 * Void method that allows the user to add a new transaction.
	 * @param	categories	Category object.
	 */
	public void addTransaction(Category category) {
		Scanner input = new Scanner(System.in);
		String sSelection;
		// if the max number of transactions has occurred, return
		if (nNumberOfTransactions >= MAXTRANSACTIONS) {
			System.out.println("You have reached the transaction limit! Transaction not added!");
			return;
		}
		// if no categories exist, return
		if (category.getNumCategories() == 0) {
			System.out.println("Please create a category before creating a transaction.");
			return;
		}
		do {
			// ask user to enter c for credit transaction or d for debit transaction
			System.out.println("Enter c for credit or d for debit: ");
			sSelection = input.nextLine();
		} while (sSelection.length() > 1 || (sSelection.charAt(0) != 'c' && sSelection.charAt(0) != 'd'));
		if (sSelection.charAt(0) == 'c') { // c is new credit transaction
			transactions[nNumberOfTransactions] = new CreditTransaction();
		}
		if (sSelection.charAt(0) == 'd') { // d is new debit transaction
			transactions[nNumberOfTransactions] = new DebitTransaction();
		}
		// have the user input the transaction passing the category selected as
		// a parameter to keep track of transactions related to categories, add 
		// the amount entered for the transaction to the bank balance, and 
		// increment the number of transactions
		transactions[nNumberOfTransactions].inputTransaction(category);
		dBalance += transactions[nNumberOfTransactions].getAmount();
		nNumberOfTransactions++;
	} // end method addTransaction
	
	/**
	 * toString method that returns the user's bank balance.
	 */
	public String toString() {
		return new String("Your current bank balance is: $" +dBalance +"\n");
	}
}
//end class Bank