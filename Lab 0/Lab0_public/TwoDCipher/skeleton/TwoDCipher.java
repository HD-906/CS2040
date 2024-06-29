import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class TwoDCipher {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int rows = scan.nextInt(), cols = scan.nextInt();
		String str = scan.nextLine();
		
		char[][] wordArray = ConvertArray(scan.nextLine(), rows, cols);
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				wordArray[i][j] = Cipher(wordArray[i][j], (i+1)*(j+1));
			}
			System.out.println(new String(wordArray[i]));
		}
		
		// System.out.println(Decrypt(wordArray)); // Decrypt back
    }
	
	// decrypting function
	public static String Decrypt(char[][] wordArray) {
		int rows = wordArray.length, cols = wordArray[0].length;
		char[] message = new char[rows * cols];

		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				message[j + i*cols] = DecryptChar(wordArray[i][j], (i+1)*(j+1));
			}
			System.out.print("\n");
		}
		
		return new String(message);
	}

	//assuming word.length == rows * cols
	private static char[][] ConvertArray(String word, int rows, int cols) {
		char[][] wordArray2D = new char[rows][cols];
		char[] wordArray1D = word.toCharArray();
		for (int i=0; i<rows; i++)
			System.arraycopy(wordArray1D, i*cols, wordArray2D[i], 0, cols);
		
		return wordArray2D;
	}
	
	private static char Cipher(char c, int offset) {
		c = (char) ('a' + (c - 'a' + offset)%26);
		return c;
	}
	
	private static char DecryptChar(char c, int offset) {
		c = (char) ('a' + (c - 'a' - offset + 26)%26);
		return c;
	}
}
