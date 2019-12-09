package classes;

public class Quarter extends Coin{
	
	private boolean silver;

	public Quarter(int year, char mint, String design, boolean silver) {
		super(year, mint, design);
		this.silver = silver;
	}

	public Quarter(int year, char mint, String design, boolean silver, String error) {
		super(year, mint, design, error);
		this.silver = silver;
	}

	public boolean isSilver() {
		return silver;
	}

	public void setSilver(boolean silver) {
		this.silver = silver;
	}
	
	public String toString() {
		if(error.equals("") || error.equals(" ")) {
			return year + " " + mint + " " + design + " Quarter";
		} else {
			return year + " " + mint + " " + design + " Quarter ("  + error + ")";
		}
	}

}
