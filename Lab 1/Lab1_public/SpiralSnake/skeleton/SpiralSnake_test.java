/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class SpiralSnake_test {
	public static int[] locateInLayer(int col, int row, int step) {
		if (step < col) {
			int[] location = {step, 0};
			return location;
		}
		if (step < col+row-1) {
			int[] location = {col-1, step-col+1};
			return location;
		}
		if (step < 2*col+row-2) {
			int[] location = {2*col+row-step-3, row-1};
			return location;
		}
		int[] location = {0, 2*col+2*row-step-4};
		return location;
	}
	
	public static int[] locate(int col, int row, int step) {
		int firstLayer = 2 * (row + col - 2);
		int currentLayer = (int)(4 + firstLayer - Math.sqrt(Math.pow(4 + firstLayer, 2) - 16 * step)) / 8;
		int stepInLayer = step - currentLayer * (firstLayer - 4 * currentLayer + 4);
		int[] location = locateInLayer(col-2*currentLayer, row-2*currentLayer, stepInLayer);
		location[0] += currentLayer;
		location[1] += currentLayer;
		return location;
	}
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 1\\Lab1_public\\SpiralSnake\\";
		File[] file_in = {
				new File(PREFIX + "input\\1.in"),
				new File(PREFIX + "input\\2.in"),
				new File(PREFIX + "input\\3.in"),
				new File(PREFIX + "input\\4.in"),
				new File(PREFIX + "input\\5.in"),
				new File(PREFIX + "input\\6.in"),
				new File(PREFIX + "input\\7.in"),
				new File(PREFIX + "input\\8.in")
				};
		File[] file_out = {
				new File(PREFIX + "output\\1.out"),
				new File(PREFIX + "output\\2.out"),
				new File(PREFIX + "output\\3.out"),
				new File(PREFIX + "output\\4.out"),
				new File(PREFIX + "output\\5.out"),
				new File(PREFIX + "output\\6.out"),
				new File(PREFIX + "output\\7.out"),
				new File(PREFIX + "output\\8.out")
				};
		Scanner scan;
		String line;
		String[] rowAndCol;
		char[][] fullMap;
		int[] location = {0,0};
		boolean isFood;
		for (int i=7; i<file_in.length; i++) {
			scan = new Scanner(file_in[i]);
			// first line contains dimension, read and convert to integers row and col
			rowAndCol = scan.nextLine().split(" ");
			int row = Integer.parseInt(rowAndCol[0]);
			int col = Integer.parseInt(rowAndCol[1]);
			System.out.println("row: "+row+", col: "+col); // test print
			
			fullMap = new char[row][col];
			
			// read all input to generate a 2d-array of the map
			for (int j=0; j<row; j++) {
				line = scan.nextLine();
				fullMap[j] = line.toCharArray();
				System.out.println(fullMap[j]); // test print
			}
			//simulate the snake in the map generated
			location[0] = 0;
			location[1] = 0;
			for (int j=0; j<row*col; j++) {
				location = locate(col, row, j);
				isFood = (fullMap[location[1]][location[0]] == 'X');
				if (isFood) {
					System.out.println("Apple at ("+location[0]+", "+location[1]+") eaten at step "+(j+1));
				}
			}
			
			//scan = new Scanner(file_out[i]);
			//testCase_out = scan.nextLine();
			System.out.println("\n"); // Separate between test cases
		}
	}
}
