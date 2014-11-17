import java.util.ArrayList;
import java.util.Random;

public class Player {
	private Cell cell;
	private Cell level2Target = null;

	public Player(Cell cell) { //Player constructor
		this.cell = cell;
	}

	public Cell getPlayerCell() {
		return cell; //returns the player cell
	}

	public void level1Movement(Board b) { //method for controlling level 1 movement
		Random rand = new Random();
		ArrayList<Cell> neighbours = b.getNeighbours(cell); //gets its neighbours from getNeighbours
		this.cell = neighbours.get(rand.nextInt(neighbours.size())); //randomly selects its neighbour
	}

	public void level2Movement(Board b) { //method for controlling level 2 movement
		// Cell idealCell = null;
		int diffX, diffY; //the difference between the player and the desired cell
		if (level2Target == null) {
			outer: // label
			for (int x = 0; x < 12; x++) { //cycle through every cell
				for (int y = 0; y < 12; y++) {
					if (b.getCell(x, y).isDiseasedCell()) { //if the cell is diseased
						if (x <= 5 && y <= 5) { //if it is top left quadrant
							level2Target = b.getCell(11, 11); //desired location is bottom right corner
						}
						if (x > 5 && y <= 5) { //if it is top right quadrant
							level2Target = b.getCell(0, 11); //desired location is bottom left corner
						}
						if (x <= 5 && y > 5) { //if it is bottom left quadrant
							level2Target = b.getCell(11, 0); //desired location is top right corner
						}
						if (x > 5 && y > 5) { //if it is bottom right quadrant
							level2Target = b.getCell(0, 0); //desired location is top left corner
						}
						break outer; //breaks out of the label
					}
				}
			}
		}
		if (level2Target != null) { //if the ideal cell has a target
			diffX = level2Target.getX() - this.cell.getX(); //gets the difference between the ideal cell and current cell
			diffY = level2Target.getY() - this.cell.getY();
			if (diffX > 0) {
				// move down
				this.cell = b.getCell(cell.getX() + 1, cell.getY());
				return;
			} else if (diffX < 0) {
				// move up
				this.cell = b.getCell(cell.getX() - 1, cell.getY());
				return;
			}
			if (diffY > 0) {
				// move right
				this.cell = b.getCell(cell.getX(), cell.getY() + 1);
				return;
			} else if (diffY < 0) {
				// move left
				this.cell = b.getCell(cell.getX(), cell.getY() - 1);
				return;
			}
		}
	}

}
