// import statements
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class allows the user to create a category for a transaction
 * (such as rent, phone bill, etc.). The categories are stored in an
 * array of Strings with a maximum of 100 elements.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 */
public class Category implements Serializable {
	
	/** Eclipse-generated serialVersionUID. */
	private static final long serialVersionUID = 3641683726167473721L;
	/** Integer that holds the max number of categories (100). */
	final private int MAXCATEGORIES = 100;
	/** Array of Strings that will hold the categories. */
	private String[] sCategoriesArray = new String[MAXCATEGORIES];
	/** Integer that holds the number of categories created. */
	private int nNumberOfCategories;
	
	/**
	 * Default constructor.
	 */
	public Category() { }
	
	/**
	 * Get method that returns the specified element of the String array
	 * of categories.
	 * @param	nIndex	an integer to determine the index of the String array
	 * 					of categories.
	 * @return the specified category.
	 */
	public String getCategory(int nIndex) { return sCategoriesArray[nIndex]; }
	/**
	 * Get method that returns the number of categories.
	 * @return the number of categories as an integer.
	 */
	public int getNumCategories() { return nNumberOfCategories; }
	
	/**
	 * Void method that allows the user to add a new category.
	 */
	public void addCategory() {
		Scanner input = new Scanner(System.in);
		// check if array is full and if it is, return
		if (nNumberOfCategories >= MAXCATEGORIES) {
			System.out.println("You have reached the description limit! Category not added!");
			return;
		}
		do {
			System.out.print("Enter category name: ");
			this.sCategoriesArray[nNumberOfCategories] = input.nextLine();
		} while (sCategoriesArray[nNumberOfCategories].equals("") || sCategoriesArray[nNumberOfCategories].equals(" "));
		nNumberOfCategories++;
	}
	
	/**
	 * Void method that displays all categories.
	 */
	public void displayCategories() {
		// if the number of categories is 0, return
		if (nNumberOfCategories == 0) {
			System.out.println("There are no categories to display because none have been created yet!");
			return;
		}
		System.out.println("Categories: ");
		// loop through array and display each category on a new line
		for (int i = 0; i < nNumberOfCategories; i++) {
			System.out.println(i +": " +sCategoriesArray[i]);
		}
	}
	
	/**
	 * Method that finds a category based on the index of the String array
	 * of categories.
	 * @return the index of the array of categories.
	 */
	public int findCategory() {
		Scanner input = new Scanner(System.in);
		boolean continueLoop = true;
		int nIndex = 0;
		displayCategories();
		// keep looping as long as continueLoop is true
		do {
			try {
				// ask user to enter a valid category index
				do {
					System.out.print("Enter category index: ");
					nIndex = input.nextInt();
				} while (nIndex < 0 || nIndex >= nNumberOfCategories);
				continueLoop = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid category index.");
				input.nextLine();
			}
		} while (continueLoop);
		return nIndex; // return the index
	} // end method findCategory
} // end class Category