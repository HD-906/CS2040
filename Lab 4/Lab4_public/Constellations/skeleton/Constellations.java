import java.math.BigInteger;
import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Constellations {

	// Takes in n and k and returns nCk, which is n choose k
	
	private static long choose(int n, int k) {
		// terminal condition
		if (k < 0 || k > n)
			return 0;
		if (k == 0 || k == n)
			return 1;
		
		if (k > n-k)
			k = n-k; // to reduce the number of recursions since nCk = nC(n-k)
		
		return choose(n, k-1) * (n-k+1) / k;
		
		/* derivation:
		 *   nCk
		 * = n! / (k!*(n-k)!)
		 * = n! / (k*(k-1)! * (n-k+1)!/(n-k+1))
		 * = n! / ((k-1)!*(n-(k-1))!) * (n-k+1)/k
		 * = nC(k-1) * (n-k+1)/k
		 * */
	}
	
	// Takes in n, a and b and returns the total number of configurations that
	// can be made to a star map with n stars, constellations of size a to b
	
	private static BigInteger numOfConfig(int n, int a, int b) {
		
		// terminal condition:
		
		if (n<a) {
			return BigInteger.ONE;
		}
		
		/* When total number of stars is less than the minimum constellation size, 
		 * only the empty configuration is possible
		 * */
		
		BigInteger partialCombi;
		BigInteger totalCombi = BigInteger.ZERO;
		for (int i=a; i<=b; i++) {
			partialCombi = numOfConfig(n-i, a, b).multiply(BigInteger.valueOf(choose(n, i)));
			totalCombi = totalCombi.add(partialCombi);
		}
		
		/* First make a constellation of size i=a,
		 * then the total number of configurations of the remaining stars is
		 * numOfConfig(n-i, a, b)
		 * There are nCi ways of making the first constellation, yielding a total of
		 * numOfConfig(n-i, a, b) * nCi configs where the first constellation has the size of a
		 * 
		 * Then sum up the values for all possible integer i in the range a<=i<=b
		 * 
		 * In the case where there is no way to make the first constellation after i increases, 
		 * meaning a<=n<i<=b, 
		 * nCi will result in 0, thus these cases will not contribute to the total sum
		 * */
		
		return totalCombi;
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String[] currentCommand = scan.nextLine().split(" ");
		
		int n = Integer.parseInt(currentCommand[0]);
		int a = Integer.parseInt(currentCommand[1]);
		int b = Integer.parseInt(currentCommand[2]);
		
		BigInteger c = numOfConfig(n, a, b); // Get the total number of configs
		c = c.mod(BigInteger.valueOf(1_000_000_007L)); // modulo 10^9 + 7
		
		System.out.println(c);
		
		scan.close();
		
	}
}