import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Macarons {
	public static void main(String args[]) {
	    Scanner scan = new Scanner(System.in);
	    
	    int numCount = scan.nextInt();
	    int divisor = scan.nextInt();
	    
	    Map<Integer, Long> numOfOccur = new HashMap<>();
	    
	    /* When the algorithm reads through the numbers on each macaron,
	     * it takes the sum of all the numbers on macarons it has read, s,
	     * and calculates the remainder r = s%d where d is the divisor, for every macaron. 
	     * All possible sequence can only be found either between 2 macarons
	     * (left exclusive, right inclusive) with the same value of r, 
	     * or all values before and including the macaron with r=0.
	     * 
	     * The map is to store the value of r to the number of times it has appeared 
	     * starting from the second time, except for r=0 which will be counted from
	     * its first appearance.
	     */
	    
	    Integer lastRemainder = scan.nextInt() % divisor;
	    if (lastRemainder != 0) {
	    	numOfOccur.put(lastRemainder, 0L);
	    	numOfOccur.put(0, 0L);
	    }
	    else
		    numOfOccur.put(0, 1L);
	    
    	/* Each value stores the times of occurrence of its key r starts 
    	 * from the 2nd time, so 0 is initialized when it first appear.
    	 * 
    	 * It is coded as if r=0 has already appeared once before any macaron is read
    	 * and initialized, since it needs to be counted from the first appearance
    	 */
	    
	    for (int i = 1; i < numCount; i++) {
	    	lastRemainder = (lastRemainder + scan.nextInt() % divisor) % divisor;
	    	numOfOccur.merge(lastRemainder, 0L, (a,b) -> a++);
	    	
	    	/* Each time updates variable lastRemainder to the new r
	    	 * using the modulo distributive rule: 
	    	 * (a+b)%c = ((a%c) + (b%c) % c)
	    	 * 
	    	 * Then update map numOfOccur by initializing it with 0 if it has not
	    	 * appeared yet (i.e. key not found), or increment the value mapped
	    	 * to key r by 1
	    	*/
	    }
	    long sum = numOfOccur.values().stream().map(n -> n*(n+1)/2).reduce(0L, Long::sum);
	    
	    /* Rewrite the collection of all numbers of occurrence n for each r that has appeared
	     * to the summation of all numbers up till n using the summation formula n*(n+1)/2,
	     * which is the total number of ways to choose a combination between any 2 of the n+1 
	     * macarons with that specific value of r, before summing all of them up.
	     * 
	     * long type is used for n
	     * as n can exceed 2^31 - 1 in the process of the calculation n*(n+1).
	     */
	    
	    System.out.println(sum);
	}
}
