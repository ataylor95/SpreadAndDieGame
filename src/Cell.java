import java.util.Random;

public class Cell {

	private Region region;
	private boolean infected;
	private boolean diseased;
	int x, y;

	enum Region { // Will Shelver (username: was4) helped with following
				  // enumeration code
		First("a"), Second("b"), Third("c"), Fourth("d");

		private final String string;

		private Region(String s) {
			this.string = s;
		}

		public String getString() {
			return string;
		}
	}

	public Cell(int regions, int x, int y) { //constructor for cell
		Random random = new Random();
		this.region = Region.values()[random.nextInt(regions)]; //gives cells random regions
		this.diseased = false; //by default all cells aren't infected or diseased when initialised
		this.infected = false;
		this.x = x; //sets the x
		this.y = y; //sets the y
	}

	public int getX() {
		return x; //returns the x value
	}

	public int getY() {
		return y; //returns the y value
	}

	public Region getRegion() {
		return this.region; //returns the region of the cell
	}

	public void setDiseased() {
		this.diseased = true; //sets the cell to be infected and diseased
		this.infected = true; //it is infected if it is diseased
	}

	public void setInfected() {
		this.infected = true; //sets the cell to be infected
	}

	public boolean isInfectedCell() {
		return this.infected; //returns if diseased
	}

	public boolean isDiseasedCell() {
		return this.diseased; //returns if infected
	}

	public String toString() { // For debugging, allows me to see which cell is
							   // being used and what region it is
		return x + ":" + y + ":" + this.getRegion().string;
	}
}
