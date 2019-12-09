package classes;

public class Penny extends Coin{
	
	private boolean steel;
	private boolean copper;

	public Penny(int year, char mint, String design, boolean steel, boolean copper) {
		super(year, mint, design);
		this.steel = steel;
		this.copper = copper;
	}
	
	public Penny(int year, char mint, String design, boolean steel, boolean copper, String error) {
		super(year, mint, design, error);
		this.steel = steel;
		this.copper = copper;
	}

	
	public boolean isSteel() {
		return steel;
	}

	public void setSteel(boolean steel) {
		this.steel = steel;
	}

	public boolean isCopper() {
		return copper;
	}

	public void setCopper(boolean copper) {
		this.copper = copper;
	}
	
	public String toString() {
		if(error.equals("") || error.equals(" ")) {
			return year + " " + mint + " " + design + " Penny";
		} else {
			return year + " " + mint + " " + design + " Penny ("  + error + ")";
		}
		
	}

	@Override
	public boolean isSilver() {
		// TODO Auto-generated method stub
		return false;
	}

}
