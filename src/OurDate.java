// import statements
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class models a date. Most of the code was provided by
 * my professor, while I was responsible for the user input.
 * @author		Prof. Linda Crane, Richard Barney
 * @version		1.0.1 April 2013
 */
public class OurDate {
	
	/** integer to hold the day */
	private int nDay;
	/** integer to hold the month */
	private int nMonth;
	/** integer to hold the year */
	private int nYear;
	
	/**
	 * Default constructor.
	 */
	public OurDate() { 
		nDay = 1;
		nMonth = 1;
		nYear = 2013;
	}
	/**
	 * Parameterized constructor.
	 * @param	nDay	an integer holding the day.
	 * @param	nMonth	an integer holding the month.
	 * @param	nYear	an integer holding the year.
	 */
	public OurDate(int nDay, int nMonth, int nYear) {
		setDay(nDay);
		setMonth(nMonth);
		setYear(nYear);
	}
	/**
	 * Parameterized constructor.
	 * @param	date	OurDate object.
	 */
	public OurDate(OurDate date) {
		this.nDay = date.nDay;
		this.nMonth = date.nMonth;
		this.nYear = date.nYear;
	}
	
	/**
	 * Get method that returns the day.
	 * @return the day as an integer.
	 */
	public int getDay() { return nDay; }
	/**
	 * Get method that returns the month.
	 * @return the month as an integer.
	 */
	public int getMonth() { return nMonth; }
	/**
	 * Get method that returns the year.
	 * @return the year as an integer.
	 */
	public int getYear() { return nYear; }

	/**
	 * Set method that sets the day.
	 * @param	nDay	the day as an integer.
	 */
	public void setDay(int nDay) {
		if (nDay <= 0 || nDay > 31) 
			this.nDay = 1;
		else if (this.nMonth == 2 && nDay > 29) 
			this.nDay = 1;
		else if (this.nDay > 30 && (this.nMonth == 9 ||this.nMonth == 4 ||this.nMonth == 6 ||this.nMonth == 11) )
			this.nDay = 1;
		else this.nDay = nDay;
	}
	/**
	 * Set method that sets the month.
	 * @param	nMonth	the month as an integer.
	 */
	public void setMonth(int nMonth) {
		if (nMonth < 0 || nMonth > 12 )
			this.nMonth = 1;
		else this.nMonth = nMonth;
	}
	/**
	 * Set method that sets the year.
	 * @param	nYear	the year as an integer.
	 */
	public void setYear(int nYear) {
		if (nYear < 0)
			this.nYear = 2010;
		else this.nYear = nYear;
	}
	
	/**
	 * Boolean method to check if year, month, and day values
	 * have same value as OurDate object.
	 * @param	date	OurDate object.
	 * @return true if values match, false if they don't.
	 */
	public boolean isEqual(OurDate date){
		if (this.nYear == date.nYear && this.nMonth == date.nMonth && this.nDay == date.nDay)
			return true;
		else return false;
	}
	
	/**
	 * toString method that returns the year,month, and day.
	 */
	public String toString() {
		return new String(nYear + "/" + nMonth + "/" + nDay);
	}
	
	/**
	 * Method that subtracts the parameter from current date Object 
	 * and returns number of days difference.
	 * @param	date	OurDate object
	 * @return number of days difference
	 */
	public int subtract(OurDate date) {
		int [] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};  // note first entry of 0 is so that January (month 1) can be used as first array index 
		int thisJulianDate = (this.nYear-1)*365;
		for (int i = 1; i < this.nMonth; i++)
			thisJulianDate += daysInMonth[i];
		thisJulianDate += this.nDay;
		
		int paramJulianDate = (date.nYear-1)*365;
		for (int i = 1; i < date.nMonth; i++)
			paramJulianDate += daysInMonth[i];
		paramJulianDate += date.nDay;
		
		return paramJulianDate - thisJulianDate;
	} // end method subtract
	
	/**
	 * Void method that prompts user for date values
	 * from keyboard.
	 */
	public void inputFromKeyboard() {
		Scanner input = new Scanner(System.in);
		boolean continueLoop = true;
		
		// prompt for month
		do {
			try {
				do {
					System.out.print("Enter month (1-12): ");
					this.nMonth = input.nextInt();
				} while (nMonth < 1 || nMonth > 12);
				continueLoop = false;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid month.");
				input.nextLine();
			}
		} while (continueLoop);

		// set continueLoop to true again and prompt for day
		continueLoop = true;
		do {
			try {
				do {
					System.out.print("Enter day (1-31): ");
					this.nDay = input.nextInt();
				} while (nDay < 1 || nDay > 31
						 || (this.nMonth == 2 && nDay > 29)
						 || (nDay > 30 && (this.nMonth == 9
						 || this.nMonth == 4
						 || this.nMonth == 6
						 || this.nMonth == 11)));
				continueLoop = false;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid day.");
				input.nextLine();
			}
		} while (continueLoop);
		
		// set continueLoop to true again and prompt for year
		continueLoop = true;
		do {
			try {
				do {
					System.out.print("Enter year: ");
					nYear = input.nextInt();
				} while (nYear < 2013 || nYear > 2020);
				continueLoop = false;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input! Please enter a valid year.");
				input.nextLine();
			}
		} while (continueLoop);
	} // end method inputFromKeyboard

	/**
	 * Void method that adds one day to the date.
	 */
	public void addOne() {
		this.nDay++;
		if (this.nDay > 31 || (this.nMonth == 2 && this.nDay > 29)
			|| (this.nDay > 30 && (this.nMonth == 9
			|| this.nMonth == 4 || this.nMonth == 6
			|| this.nMonth == 11) ) ) { 
			
			this.nDay = 1;
			this.nMonth ++;
			if (this.nMonth > 12) {
				this.nMonth = 1;
				this.nYear++;
			}
		}
	} // end method addOne
} // end class OurDate