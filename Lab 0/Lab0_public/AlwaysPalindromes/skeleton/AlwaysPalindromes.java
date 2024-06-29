import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AlwaysPalindromes {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		String word = scan.nextLine();
		
		if (isPalindrome(word))
			System.out.println(word);
		else {
			char[] wordChar = word.toCharArray();
			char[] palindromeWordChar = new char[word.length()*2]; 
			
			for (int i=0; i<wordChar.length; i++) {
				palindromeWordChar[i] = wordChar[i];
				palindromeWordChar[palindromeWordChar.length-1-i] = wordChar[i];
			}
			
			System.out.println(new String(palindromeWordChar));
		}
    }

	public static boolean isPalindrome(String word) {
		for (int i=0; i<word.length()/2; i++) {
			if (word.charAt(i) != word.charAt(word.length()-1-i))
				return false;
		}
		return true;
	}
}
