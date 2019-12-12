package classes;

public abstract class Coin{

	protected char mint;
	protected int year;
	protected String design;
	protected String error;
	

	public Coin(int year, char mint, String design) {
		super();
		this.mint = mint;
		this.year = year;
		this.design = design;
	}
	
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
