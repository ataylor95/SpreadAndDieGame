/**
 * Board Class responsible for initialising and printing the board
 *   
 */

import java.util.ArrayList;
import java.util.Random;

public class Board {

	private Cell[][] cells;
	private Player player;

	public Board(int regions, int xStart, int yStart) {
		this.cells = new Cell[12][12];
		for (int x = 0; x < 12; x++) { // cycle through all x's
			for (int y = 0; y < 12; y++) { // cycle through all the y's
				cells[x][y] = new Cell(regions, x, y); // assign a new instance
														// of Cell to each grid
														// position
			}
		}
		Random rand = new Random();
		this.player = new Player(cells[rand.nextInt(12)][rand.nextInt(12)]); //assigns the Player cell randomly
		cells[xStart][yStart].setDiseased(); //Set the cell indicated by user as both infected and diseased
		cells[xStart][yStart].setInfected();
	}

	public void printBoard() {
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				if (this.player.getPlayerCell() == cells[x][y]) {
					System.out.print("P"); //if the cell is equal to the player cell then it prints a "P"
				} else if (cells[x][y].isDiseasedCell()) {
					System.out.print("D"); //if the cell is diseased then it prints a "D"
				} else if (cells[x][y].isInfectedCell()) {
					System.out.print("I"); //if the cell is infected then it prints a "I"
				} else
					System.out.print(cells[x][y].getRegion().getString()); //printing the regions
			}
			System.out.println();
		}
	}

	public Cell getCell(int x, int y) {
		try {
			return cells[x][y]; //returns the cell at specified x and y
		} catch (ArrayIndexOutOfBoundsException e) { //stop it from crashing
			return null; //in case of an ArrayIndexOutOfBoundsException, return null
		}
	}

	public Cell getRandomCell() {
		Random rand = new Random();
		return cells[rand.nextInt(12)][rand.nextInt(12)]; //randomly gets a cell by
	}													  //randomising the array indexes

	public ArrayList<Cell> getNeighbours(Cell cell) { //gets the neighboruing cells
		int x = cell.getX(); //gets x value of the cell
		int y = cell.getY(); //gets y value of the cell
		ArrayList<Cell> list = new ArrayList<Cell>(); //creates a list of the neighbouring
		if (x > 0) {								  //cells
			list.add(cells[x - 1][y]); //gets cell below and adds to list
		}
		if (x < 11) {
			list.add(cells[x + 1][y]); //gets cell above and adds to list
		}
		if (y > 0) {
			list.add(cells[x][y - 1]); //gets cell to the left and adds to list
		}
		if (y < 11) {
			list.add(cells[x][y + 1]); //gets cell to the right and adds to list
		}
		return list; //returns the list
	}

	public Player getPlayer() {
		return this.player; //returns player
	}

	public void spreadDisease() {
		ArrayList<Cell> list = new ArrayList<Cell>(); //list of diseased cells
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 12; y++) {
				if (!cells[x][y].isInfectedCell()) { //if it is not infected, ignore it
					continue;
				}
				if (!cells[x][y].isDiseasedCell()) { //if it is not diseased but
					cells[x][y].setDiseased();       //is infected, set to diseased
					continue;
				}
				if (cells[x][y].isDiseasedCell()) { //if it is diseased, add to the list
					list.add(cells[x][y]);
				}
			}
		}
		for (Cell cell : list) { //for all the cells in the list do:
			for (Cell neighbour : getNeighbours(cell)) { //gets its neighbours
				if (!neighbour.isInfectedCell()) { //if its neighbour is not infected
					if (neighbour.getRegion() == cell.getRegion()) { //if same region, set diseased
						neighbour.setDiseased();
					} else {
						neighbour.setInfected(); //else set it it to infected
					}
				}
			}
		}
	}
}