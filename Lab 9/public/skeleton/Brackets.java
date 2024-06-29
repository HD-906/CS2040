/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

import java.util.*;

public class Brackets {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int length = Integer.parseInt(scan.nextLine());
		String[] equation = scan.nextLine().split(" ");
		scan.close();
		
		Stack<Long> lastNum = new Stack<>();
		
		long totalNumber = 0L;
		int index = 0;
		while(index < length) {
			while (index < length) {
				String str = equation[index++];
				if (str.equals("(")) {
					lastNum.push(totalNumber);
					totalNumber = 1L;
					break;
				} else if (str.equals(")")) {
					totalNumber = mod(lastNum.pop() * totalNumber);
					break;
				} else {
					totalNumber = mod(totalNumber + Long.parseLong(str));
				}
			}
			while (index < length) {
				String str = equation[index++];
				if (str.equals("(")) {
					lastNum.push(totalNumber);
					totalNumber = 0L;
					break;
				} else if (str.equals(")")) {
					totalNumber = mod(lastNum.pop() + totalNumber);
					break;
				} else {
					totalNumber = mod(totalNumber * Long.parseLong(str));
				}
			}
		}
		
		System.out.println(totalNumber);
	}
	
	private static long mod(long number) {
		return number % 1_000_000_007L;
	}
}