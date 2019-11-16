package classes;

public class Dime extends Coin{

	private boolean silver;
	
	public Dime(int year, char mint, String design, boolean silver) {
		super(year, mint, design);
		this.silver = silver;
	}

	public boolean isSilver() {
		return silver;
	}

	public void setSilver(boolean silver) {
		this.silver = silver;
	}
	
	
}
