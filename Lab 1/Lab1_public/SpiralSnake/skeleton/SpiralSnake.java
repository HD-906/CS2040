import java.util.Arrays;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class SpiralSnake {
	// Returns the location of the snake at a particular step for a given dimension col*row
	// Pre-cond: The snake must be in the outermost layer, to be used in the "locate" method to cover other locations
	public static int[] locateInLayer(int col, int row, int step) {
		// Snake at the top of current layer
		if (step < col) {
			int[] location = {step, 0};
			return location;
		}
		// Snake at the right of current layer
		if (step < col+row-1) {
			int[] location = {col-1, step-col+1};
			return location;
		}
		// Snake at the bottom of current layer
		if (step < 2*col+row-2) {
			int[] location = {2*col+row-step-3, row-1};
			return location;
		}
		// Snake at the left of current layer
		int[] location = {0, 2*col+2*row-step-4};
		return location;
	}
	
	// Returns the location of the snake at a particular step for a given dimension col*row
	// Pre-cond: 0 <= step < col*row
	public static int[] locate(int col, int row, int step) {
		// Number of squares at the outermost layer
		int firstLayer = 2 * (row + col - 2);
		
		// Number of layers the snake has already wrapped around
		// calculated by solving quadratic equation: step = currentLayer * (firstLayer - 4 * currentLayer + 4)
		int currentLayer = (int)(4 + firstLayer - Math.sqrt(Math.pow(4 + firstLayer, 2) - 16 * step)) / 8;
		
		// Number of steps the snake has covered after entering the current layer
		// calculated by current step minus total step in all full layers the snake has covered
		int stepInLayer = step - currentLayer * (firstLayer - 4 * currentLayer + 4);
		
		// Returns the location of the snake in that particular layer it is currently in
		int[] location = locateInLayer(col-2*currentLayer, row-2*currentLayer, stepInLayer);
		
		// Actual location including all layers the snake has covered
		location[0] += currentLayer;
		location[1] += currentLayer;
		
		return location;
	}
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		// first line contains dimension, read and convert to integers row and col
		String[] rowAndCol = scan.nextLine().split(" ");
		int row = Integer.parseInt(rowAndCol[0]);
		int col = Integer.parseInt(rowAndCol[1]);
		
		String line;
		char[][] fullMap = new char[row][col];
		// read all inputs to generate a 2d-array of the map
		for (int i=0; i<row; i++) {
			line = scan.nextLine();
			fullMap[i] = line.toCharArray();
		}
		//simulate the snake in the map generated
		int[] location = {0,0};
		boolean isFood;
		for (int i=0; i<row*col; i++) {
			// Locate the snake at step i, where i starts from 0
			location = locate(col, row, i);
			isFood = (fullMap[location[1]][location[0]] == 'X');
			if (isFood) {
				System.out.println("Apple at ("+location[0]+", "+location[1]+") eaten at step "+(i+1));
			}
		}
		scan.close();
	}
}