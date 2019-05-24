/**
 * This material is based upon work supported by 
 * the National Science Foundation under Grant No. 1140753.
 */

package sudoku.util.board;

import java.io.File;
import java.util.Scanner;
import java.util.*;
/**
 * A simple parser.
 */
public class RegularSudokuParser
{
	/**
	 * Parses a sudoku file. See the sudoku puzzle directory for specifications
	 * 
	 * @param puzzleFile - the puzzle to be parsed
	 * @return - the sudoku puzzle the file represented
	 * @throws Exception - the file was not found
	 */
	public RegularSudokuBoard parse(File puzzleFile) throws Exception
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(puzzleFile);
		int cell = 0;
		int width = input.nextInt();
		System.out.println(width + " width ");
		int height = input.nextInt();
		System.out.println(height + " height ");
		RegularSudokuBoard board = new RegularSudokuBoard(width,height);
		while(input.hasNextInt()) {
		board.setCell(input.nextInt(),cell);
		cell++;
		}
		return board;
		}
}

