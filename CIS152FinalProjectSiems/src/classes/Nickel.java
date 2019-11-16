package classes;

public class Nickel extends Coin{
	
	private boolean silver;

	public Nickel(int year, char mint, String design, boolean silver) {
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
