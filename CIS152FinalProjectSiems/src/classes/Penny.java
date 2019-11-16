package classes;

public class Penny extends Coin{
	
	private boolean steel;

	public Penny(int year, char mint, String design, boolean steel) {
		super(year, mint, design);
		this.steel = steel;
	}
	
	public boolean isSteel() {
		return steel;
	}

	public void setSteel(boolean steel) {
		this.steel = steel;
	}

}
