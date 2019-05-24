
package sudoku.brute;

import java.util.Arrays;

import sudoku.util.Solver;
import sudoku.util.board.SudokuBoard;

/**
 * A sudoku solver which employs sudoku.brute force to solve puzzles. Puzzles
 * are solved through explicit enumeration of all possible board states. For
 * instance, it starts by plugging a 1 into each empty cell and then, with each
 * subsequent attempt, the guess is incremented as if a number in base <board
 * size>.
 */
public class BruteForceSolver implements Solver {
	int guesses = 0;
	public void solve(SudokuBoard board) {
		
		for (int i = 0; i < (board.size() * board.size()); i++) {
			if (board.valueAt(i) == 0) {
				guesses++;
			}
		}

		int[] guess = new int[guesses];
		System.out.println(guesses);
		for (int i = 0; i < guess.length; i++) {
			guess[i] = 1;
		}

		int power = (int) Math.pow(board.size(), guesses) - 1;
		System.out.println(power);
		int boardcells = board.size() * board.size();

		{
			for (int i = 0; i < power && (!board.isSolved()); i++) {
				int number = 0;
				while (!(board.history().isEmpty()) && (!board.isSolved())) {
					board.unmove();
				}

				for (int j = 0; j < boardcells - 1; j++) {
					if (board.valueAt(j) == 0) {
						board.move(guess[number], j);
						number++;
						if (board.isSolved()) {
							break;
						}
					}
				}
				increment(guess, board);
			}

			if (board.isSolved()) {
				System.out.println(board);
			} else {
				System.out.println("can't solve");
			}
		}
	}

	private void increment(int[] guess, SudokuBoard board) {
		for (int i = 0; i < guess.length; i++) {
			guess[i]++;
			if (guess[i] == board.size() + 1) {
				guess[i] = 1;
			} else {
				break;
			}
		}
	}
}
