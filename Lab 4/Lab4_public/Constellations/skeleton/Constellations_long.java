import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Constellations_long {

	// Takes in n and k and returns nCk, which is n choose k
	public static long choose(int n, int k) {
		// System.out.println("choosing " + "(" + n + ", " + k + ")");

		if (k < 0 || k > n)
			return 0;
		if (k == 0 || k == n)
			return 1;
		
		if (k > n-k)
			k = n-k;
		return choose(n, k-1) * (n-k+1) / k;
		
		/* derivation:
		 *   n choose k
		 * = n! / (k!*(n-k)!)
		 * = n! / (k*(k-1)! * (n-k+1)!/(n-k+1))
		 * = n! / ((k-1)!*(n-(k-1))!) * (n-k+1)/k
		 * */
	}
	
	public static long calculate(int n, int a, int b) {
		/* delete */ if (a+5<b) b=a+5;
		if (n<a) {
			// System.out.println("calculating " + "(" + n + ", " + a + ", " + b + ")");
			return 1;
		}
		else {
			// System.out.println("calculating " + "(" + n + ", " + a + ", " + b + ")");
			long comb = 0;
			for (int i=a; i<=b; i++) {
				comb += choose(n, i) * calculate(n-i, a, b);
			}
			return comb;
		}
	}
	
	public static void main(String args[]) {
		// long startTime = System.currentTimeMillis(); // check time
		Scanner scan = new Scanner(System.in);

		String[] currentCommand = scan.nextLine().split(" ");
		int n = Integer.parseInt(currentCommand[0]);
		int a = Integer.parseInt(currentCommand[1]);
		int b = Integer.parseInt(currentCommand[2]);
		
		System.out.println(calculate(n, a, b));
		
		//check time
		// long stopTime = System.currentTimeMillis();
		// long elapsedTime = stopTime - startTime;
		// System.out.println("time taken: " + elapsedTime + " ms");
		
		scan.close();
	}
}

