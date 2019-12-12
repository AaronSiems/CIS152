package helpers;

import classes.Coin;
import classes.Dime;
import classes.Nickel;
import classes.Penny;
import classes.Quarter;

/**
 * @author Aaron Siems
 *
 */
public class NewCoinHelper {

	public NewCoinHelper() {
		super();
	}
	
	
	
	/**
	 * @param type
	 * @param year
	 * @param mint
	 * @param design
	 * @param b1
	 * @param b2 - Only for pennies, pass false for the other 3
	 * @param error
	 * @return The specific type of coin based on inputs
	 */
	public Coin newCoin(String type, int year, char mint, String design, boolean b1, boolean b2, String error) {
		if(type.toLowerCase().equals("quarter")) {
			if (error == "") {
				return new Quarter(year, mint, design, b1, error);
			} else {
				return new Quarter(year, mint, design, b1, error);
			}
		} else if(type.toLowerCase().equals("dime")) {
			if (error == "") {
				return new Dime(year, mint, design, b1, error);
			} else {
				return new Dime(year, mint, design, b1, error);
			}
		} else if(type.toLowerCase().equals("nickel")) {
			if (error == "") {
				return new Nickel(year, mint, design, b1, error);
			} else {
				return new Nickel(year, mint, design, b1, error);
			}
		} else if(type.toLowerCase().equals("penny")) {
			if (error == "") {
				return new Penny(year, mint, design, b1, b2, error);
			} else {
				return new Penny(year, mint, design, b1, b2, error);
			}
		} else {
			return new Quarter(9999, 'S', null, true);
		}
		
	}
	
	//Validators - design var left in args in case I add validation for that too
	
	/**
	 * @param year
	 * @param mint
	 * @param design
	 * @param silver
	 * @return Empty string for valid, error string if that type of coin didn't exist
	 */
	public String validateQuarter(int year, char mint, String design, boolean silver) {
		String valid = "";
		
		if(year > 1964 && silver) { //Quarters after 1964 not silver
			if (year < 1992) { //All quarters till 92 are not silver
				valid = "This quarter cannot be silver unless it's an error.";
			} else if (mint != 'S') { //S mint marks could be silver
				valid = "This quarter cannot be silver unless it's an error.";
			}
		} else if (year < 1965 && !silver) {
			valid = "This quarter should be silver unless it is an error.";
		}
		
		return valid;
	}
	
	/**
	 * @param year
	 * @param mint
	 * @param design
	 * @param silver
	 * @return Empty string for valid, error string if that type of coin didn't exist
	 */
	public String validateDime(int year, char mint, String design, boolean silver) {
		String valid = "";
		
		if(year > 1964 && silver) { //Dimes after 1964 not silver
			if (year < 1992) { //All dimes till 92 are not silver
				valid = "This dime cannot be silver unless it's an error.";
			} else if (mint != 'S') { //S mint marks could be silver
				valid = "This dime cannot be silver unless it's an error.";
			}
		} else if (year < 1965 && !silver) {
			valid = "This dime should be silver unless it is an error.";
		}
		
		return valid;
	}
	
	/**
	 * @param year
	 * @param mint
	 * @param design
	 * @param silver
	 * @return Empty string for valid, error string if that type of coin didn't exist
	 */
	public String validateNickel(int year, char mint, String design, boolean silver) {
		String valid = "";
		
		if(year <= 1945 && silver) { //Nickels after 1945 are not silver
			if (year < 1942) { //Nickels before 1942 are not silver
				valid = "This nickel cannot be silver unless it's an error.";
			} else if (mint == ' ') { //No mint mark 1942-1945 is not silver
				valid = "This nickel cannot be silver unless it's an error.";
			}
		}
		
		return valid;
	}
	
	/**
	 * @param year
	 * @param mint
	 * @param design
	 * @param steel
	 * @param copper
	 * @return Empty string for valid, error string if that type of coin didn't exist
	 */
	public String validatePenny(int year, char mint, String design, boolean steel, boolean copper) {
		String valid = "";
		
		if(year == 1943 && !steel) { //All* 1943 pennies are steel
			valid = "This penny should be steel unless it's an error. \n";
		} else if (year != 1943 && steel) {
			valid = "This penny cannot be steel unless it's an error. \n";
		}
		
		if(year > 1982 && copper) { //Pennies after 1982 are not copper
			valid += "This penny cannot be copper unless it's an error.";
		} else if (year == 1943 && copper) { //1943 pennies are steel
			valid += "This penny should be steel instead of copper unless it's an error."; 
		}
		
		return valid;
	}

	
}
