// import statements
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
/**
 * This class is the main launcher for the program as a whole. A menu is 
 * presented to the user where they can add a category, create a transaction,
 * display all transactions, etc. The user can also save all the data they
 * entered through serialization so that when the program runs again, all
 * data is restored.
 * @author		Richard Barney
 * @version		1.0.0 April 2013
 * @since		1.7
 */
public class Assign3 {
	/**
	 * main method as required by JVM.
	 * @param  args   standard command line parameters as a String array.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Category category = new Category();
		Bank bank = new Bank(1);
		String sOption = "n"; // give the String a null value
		try { // create files
			File bankFile = new File("Bank.ser");
			File categoryFile = new File("Category.ser");
			ObjectInputStream objInput;
			// if Bank.ser exists, move the Bank data to the file,
			// else create Bank.ser
			if (bankFile.exists()) {
				objInput = new ObjectInputStream(new FileInputStream("Bank.ser"));
				bank = (Bank) objInput.readObject();
				objInput.close();
			}
			else {
				bankFile.createNewFile();
			}
			// if Category.ser exists, move the Category data to the file,
			// else create Category.ser 
			if (categoryFile.exists()) {
				objInput = new ObjectInputStream(new FileInputStream("Category.ser"));
				category = (Category) objInput.readObject();
				objInput.close();
			}
			else {
				categoryFile.createNewFile();
			}
		} catch(IOException | ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// display welcome message
		System.out.println("Welcome!");
		// loop thru menu as long as "x" is not entered
		while (sOption.length() > 1 || sOption.toLowerCase().charAt(0) != 'x') {
			System.out.println("Enter"
								+"\n\"a\" to add a new category"
								+"\n\"d\" to display all categories"
								+"\n\"t\" to add a transaction"
								+"\n\"b\" to display your balance"
								+"\n\"s\" to show transactions for a category"
								+"\n\"f\" to display all transactions to date"
								+"\n\"g\" to save"
								+"\n\"x\" to exit");
			sOption = input.nextLine();
			switch(sOption.toLowerCase()) {
				case "a":
					category.addCategory();
					System.out.println("");
					break;
				case "d":
					category.displayCategories();
					System.out.println("");
					break;
				case "t":
					bank.addTransaction(category);
					System.out.println("");
					break;
				case "b":
					System.out.print(bank.toString());
					System.out.println("");
					break;
				case "s":
					bank.displayCategory(category);
					System.out.println("");
					break;
				case "f":
					bank.displayTransactions();
					System.out.println("");
					break;
				case "g":
					// entering "g" saves the data, so the lines below do the work necessary to save
					// the Bank and Category data as SER files
					try {
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Bank.ser"));
						out.writeObject(bank);
						out.close();
						System.out.println("Bank file saved.");
					} catch (Exception e) {
						System.out.print("Error writing to file Bank.ser! ");
						e.printStackTrace();
					}
					try {
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Category.ser"));
						out.writeObject(category);
						out.close();
						System.out.println("Category file saved.");
					} catch (Exception e) {
						System.out.println("Error writing to file Category.ser!\n");
						e.printStackTrace();
					}
			} // end switch statement
		} // end big while loop
		if (sOption.toLowerCase().charAt(0) == 'x' && sOption.length() == 1) { // entering "x" ends program
			System.out.print("Goodbye.");
		}
	} // end method main
} // end class Assign3