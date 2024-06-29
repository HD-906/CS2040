import java.math.BigInteger;
import java.util.*;

/**
 * Name:
 * Matric. No:
 */

public class Candyland {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		int N = Integer.valueOf(scan.nextLine());
		
		BigInteger c = BigInteger.TWO.pow(N-1);
		
		System.out.println(c);
		
		scan.close();
	}
}