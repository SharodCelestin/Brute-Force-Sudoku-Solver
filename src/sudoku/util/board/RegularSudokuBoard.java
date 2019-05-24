/**
 * This material is based upon work supported by 
 * the National Science Foundation under Grant No. 1140753.
 */

package sudoku.util.board;

import java.util.Stack;

/**
 * A sudoku board whose regions are boxes each with identical width and height
 */
public class RegularSudokuBoard implements SudokuBoard {
	private final int boardSize;
	private final int[] boardCells;
	private int boxWidth;
	private int boxHeight;
	private Stack<Move> history;
	private int numbers;
	public int[][] boxCount;
	public int[][] colCount;
	public int[][] rowCount;

	/**
	 * @param width
	 *            - the width of a region
	 * @param height
	 *            - the height of a region
	 */
	public RegularSudokuBoard(int width, int height) {
		boardSize = width * height;
		boardCells = new int[boardSize * boardSize];
		boxCount = new int[boardSize][boardSize];
		colCount = new int[boardSize][boardSize];
		rowCount = new int[boardSize][boardSize];
		this.boxHeight = height;
		this.boxWidth = width;
				
		boxWidth = width;
		history = new Stack<Move>();
		numbers = 0;

	}

	public void move(int num, int cell) {
		if (numbers == 0) {
			for (int i = 0; i < boardCells.length; i++) {
				if (boardCells[i] != 0) {
					boxCount[this.getBox(i)][boardCells[i] - 1]++;
					rowCount[this.getRow(i)][boardCells[i] - 1]++;
					colCount[this.getCol(i)][boardCells[i] - 1]++;
				}
			}
			numbers++;
		}
		if (this.isLegal(num, cell)) {
			this.setCell(num, cell);
			rowCount[this.getRow(cell)][num - 1]++;
			colCount[this.getCol(cell)][num - 1]++;
			boxCount[this.getBox(cell)][num - 1]++;
			Move m = new Move(num, cell);
			history.add(m);
		}
	}

	public void move(Move move) {

	}

	public void unmove() {
		Move move = history.pop();
		int cell = move.getCell();
		this.resetCell(cell);
	}

	public void setCell(int num, int cell) {
		boardCells[cell] = num;
	}

	public void resetCell(int cell) {
		int number = boardCells[cell];
		boardCells[cell] = 0;
		rowCount[this.getRow(cell)][number - 1]--;
		colCount[this.getCol(cell)][number - 1]--;
		boxCount[this.getBox(cell)][number - 1]--;
	}

	public boolean isLegal(int num, int cell) {
		if ((rowCount[this.getRow(cell)][num - 1] == 0) && (colCount[this.getCol(cell)][num - 1] == 0)
				&& (boxCount[this.getBox(cell)][num - 1] == 0)) {
			return true;
		}

		return false;
	}

	public boolean isLegal(Move move) {
		return false;
	}

	public Stack<Move> history() {
		return history;
	}

	public int size() {
		return boardSize;
	}

	public boolean isSolved() {
		for (int i = 0; i < boardSize; i++) {
			if (!isGoodRow(i) || !isGoodCol(i) || !isGoodBox(i)) {
				return false;
			}
		}
		return true;

	}

	private boolean isGoodRow(int row) {
		for (int i = 0; i < boardSize; i++) {
			if (rowCount[row][i] != 1) {
				return false;
			}
		}
		return true;

	}

	private boolean isGoodCol(int col)

	{

		for (int i = 0; i < boardSize; i++)

		{

			if (colCount[col][i] != 1) {

				return false;

			}
		}
		return true;

	}

	private boolean isGoodBox(int box) {
		for (int i = 0; i < boardSize; i++) {
			if (boxCount[box][i] != 1) {
				return false;
			}
		}

		return true;

	}

	public int valueAt(int cell) {
		return boardCells[cell];
	}

	public int getRow(int cell) {
		return cell / boardSize;

	}

	public int getCol(int cell) {
		return cell % boardSize;

	}

	public int getBox(int cell) {
		int boxcolumn = this.getCol(cell) / boxWidth;
		int boxrow = this.getRow(cell) / boxHeight;
		int box = boxrow * boxHeight + boxcolumn;
		return box;
	}

	public boolean isZero(int cell) {
		if (boardCells[cell] == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		builder.append("Board size: " + boardSize + " X " + boardSize + "\n\n");
		for (int i = 0; i < Math.pow(boardSize, 2); i++) {
			builder.append(boardCells[i] == 0 ? "_" : Integer.toString(boardCells[i], boardSize + 1));
			// builder.append(boardCells[i] == 0 ? String.format("%3s", "_") :
			// String.format("%3s", ""+boardCells[i]));
			builder.append(" ");
			if (i % boardSize == boardSize - 1)
				builder.append("\n");
		}
		return builder.toString();
	}

}