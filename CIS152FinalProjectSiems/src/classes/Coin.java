package classes;

/**
 * @author Aaron Siems
 *
 */
public abstract class Coin{

	protected char mint;
	protected int year;
	protected String design;
	protected String error;
	

	/**
	 * Creates a coin without an error
	 * @param year
	 * @param mint
	 * @param design
	 */
	public Coin(int year, char mint, String design) {
		super();
		this.mint = mint;
		this.year = year;
		this.design = design;
	}
	
	/**
	 * Creates a coin with an error
	 * @param year
	 * @param mint
	 * @param design
	 * @param error
	 */
	public Coin(int year, char mint, String design, String error) {
		super();
		this.mint = mint;
		this.year = year;
		this.design = design;
		this.error = error;
	}

	
	public char getMint() {
		return mint;
	}
	public void setMint(char mint) {
		this.mint = mint;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public abstract boolean isSilver();
	
	
	
}
