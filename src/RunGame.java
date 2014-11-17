import java.io.*;

public class RunGame {

	public static void main(String[] args) {
		int regions = 2; 
		int xStart = 0;
		int yStart = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //buffered reader allows me to read input
		int whatToDoInt = 0;
		int level = 1;
		int score = 0;

		System.out.println("Welcome to Spread and Die!");
		System.out.println("--------------------------");
		System.out.println("Please select what you wish to do");
		System.out.println("1. Play Game");
		System.out.println("2. Exit");
		try {
			whatToDoInt = Integer.parseInt(br.readLine()); // get input from the user
		} catch (IOException e) {
		}

		switch (whatToDoInt) {
		case 1: //case statement, 1 starts the program and 2 exits it
			System.out
					.println("Please specify how many regions you want (Between 2 and 4): ");
			while (true) {
				try {
					regions = Integer.parseInt(br.readLine()); //takes the number of regions
				} catch (IOException e) {
				}
				if (regions <= 4 && regions >= 2) { //makes sure that there are between 2 and 4 regions inputted
					do {
						System.out
								.println("\nPlease enter the x coordinate for the disease (1-12): ");
						try {
							xStart = Integer.parseInt(br.readLine()) - 1; //takes x coordinate of disease start point
						} catch (IOException e) {
						}
					} while (xStart < 0 || xStart > 11);
					do {
						System.out
								.println("\nPlease enter the y coordinate for the disease (1-12): ");
						try {
							yStart = Integer.parseInt(br.readLine()) - 1; //takes y coordinate of disease start point
						} catch (IOException e) {
						}
					} while (yStart < 0 || yStart > 11);
					Board board = new Board(regions, xStart, yStart);
					do {
						board.printBoard(); //calls printboard method from board class
						board.getPlayer().level1Movement(board); //calls the level 1 movement algorithm in player class
						board.spreadDisease();
						if (board.getPlayer().getPlayerCell().isDiseasedCell()) { //if the cell the player is on
							System.out.println("You died!");                      //is diseased then the player dies and the game ends
							System.out.println("Your final score was: " + score);
							break;
						}
						score++; //increments score each round the player survives, keeping tally
						System.out.println("\nYour current score: " + score);
						System.out.println("Press enter to continue");
						try {
							br.readLine();
						} catch (IOException e) {
						}

					} while (score < 20); //repeats code until you get a score of 20
					if (score == 20) {
						System.out
						.println("You have passed level 1, with a score of: "
								+ score);
						level++; //increments to level 2
					//}
					}
					break;
				}
				System.out.println("Please enter it again"); //asking you to enter region num
			}                                                //again if it was too small or large
			if (level == 2) {
				System.out.println("\nLevel 2 commencing:\n");
				System.out
						.println("Please specify how many regions you want (Between 2 and 4): ");
				while (true) {
					try {
						regions = Integer.parseInt(br.readLine());
					} catch (IOException e) {
					}
					if (regions <= 4 && regions >= 2) {
						do {
							System.out
									.println("\nPlease enter the x coordinate for the disease (1-12): ");
							try {
								xStart = Integer.parseInt(br.readLine()) - 1;
							} catch (IOException e) {
							}
						} while (xStart < 0 || xStart > 11);
						do {
							System.out
									.println("\nPlease enter the y coordinate for the disease (1-12): ");
							try {
								yStart = Integer.parseInt(br.readLine()) - 1;
							} catch (IOException e) {
							}
						} while (yStart < 0 || yStart > 11);
						Board board = new Board(regions, xStart, yStart);
						do {
							board.printBoard();
							board.getPlayer().level2Movement(board); //calls the level 2 movement algorithm in player class
							board.spreadDisease();
							if (board.getPlayer().getPlayerCell()
									.isDiseasedCell()) {
								System.out.println("You died!");
								break;
							}
							score++;
							System.out.println("Your current score: " + score);
							System.out.println("Press enter to continue");
							try {
								br.readLine();
							} catch (IOException e) {
							}

						} while (score < 40);
						System.out
								.println("You have passed level 2, with a score of: "
										+ score);
						level++;
						break;
					}
					System.out.println("Please enter it again");
				}
			}
			break;
		case 2:
			System.out.println("Goodbye!");
			break;
		}
		
	}
}