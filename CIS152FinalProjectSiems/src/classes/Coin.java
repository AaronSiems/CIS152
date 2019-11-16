package classes;

public class Coin {

	private char mint;
	private int year;
	private String design;
	

	public Coin(int year, char mint, String design) {
		super();
		this.mint = mint;
		this.year = year;
		this.design = design;
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
	
	
	
}
