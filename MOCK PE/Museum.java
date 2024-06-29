import java.util.*;

public class Museum {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.valueOf(scan.nextLine());
		String[] arr = scan.nextLine().split(" ");
		Map<String, Integer> arrMap = new HashMap<>();
		int lowP=0, maxChain=1;
		for (int i=0; i<n; i++) {
			iD = arr[i];
			if (arrMap.contains(iD)) {
				int iDup = arrMap.get(iD);
				maxChain = (i-lowP>maxChain)? i-lowP: maxChain;
				for (int iDel=lowP; iDel<iDup; iDel++)
					arrMap.remove(arr[iDel]);
				lowP = iDup + 1;
			}
			arrMap.put(iD, i);
		}
		maxChain = (n-lowP>maxChain)? n-lowP: maxChain;
		System.out.println(maxChain);
}