import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Friends {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		String[] firstLine = scan.nextLine().split(" ");
		int numOfCafe = Integer.valueOf(firstLine[0]);
		long maxTime = Long.valueOf(firstLine[1]);
		Cafe[] cafeList = new Cafe[numOfCafe];

		// create cafes
		for (int i=0; i<numOfCafe; i++) {
			String[] cafeInfo = scan.nextLine().split(" ");
			Cafe newCafe = new Cafe(cafeInfo[0], Integer.valueOf(cafeInfo[1]));
			
			for (int j=0; j<newCafe.getPeople(); j++) {
				newCafe.editTime(j, scan.nextLine().split(" "));
			}
			newCafe.sortTimes();
			newCafe.findMaxFriend(maxTime);
			cafeList[i] = newCafe;
		}
		
		// Find maximum
		Cafe[] maxFriendsCafeList = new Cafe[numOfCafe];
		int cafeCount = 0;
		int maxFriend = 0;
		for (int i=0; i<numOfCafe; i++) {
			if (cafeList[i].getFriends() > maxFriend) {
				maxFriend = cafeList[i].getFriends();
				maxFriendsCafeList[0] = cafeList[i];
				cafeCount = 1;
			}
			else if (cafeList[i].getFriends() == maxFriend) {
				maxFriendsCafeList[cafeCount] = cafeList[i];
				cafeCount++;
			}
		}
		
		String nameList[] = new String[cafeCount];
		for (int i=0; i<cafeCount; i++) {
			nameList[i] = maxFriendsCafeList[i].getName();
		}
		Arrays.sort(nameList);
		
		System.out.println(maxFriend);
		for (int i=0; i<cafeCount; i++) {
			System.out.println(nameList[i]);
		}
	}
}

class Cafe {
	private String name;
	private int totalPeople;
	private long entryTimeList[];
	private long exitTimeList[];
	private int friends;
	private boolean sorted;
	
	// constructor
	Cafe(String name, int totalPeople) {
		this.name = name;
		this.totalPeople = totalPeople;
		this.entryTimeList = new long[totalPeople];
		this.exitTimeList = new long[totalPeople];
		this.friends = totalPeople;
		this.sorted = true;
	}
	
	public void findMaxFriend(long maxTime) {
		/*/
		if (maxTime == 0) {
			findMaxFriendZero();
			return;
		}
		/*/
		int entryPointer = totalPeople-1;
		long duration = entryTimeList[entryPointer] - exitTimeList[0];
		while (duration > maxTime) {
			entryPointer--;
			duration = entryTimeList[entryPointer] - exitTimeList[0];
			friends--;
		}
		if (friends >= totalPeople - 1) {
			return;
		}
		entryPointer += 2;
		int exitPointer = 1;
		while (entryPointer < totalPeople) {
			duration = entryTimeList[entryPointer] - exitTimeList[exitPointer];
			while (duration <= maxTime) {
				friends++;
				entryPointer++;
				if (entryPointer >= totalPeople) {
					return;
				}
				duration = entryTimeList[entryPointer] - exitTimeList[exitPointer];
			}
			entryPointer++;
			exitPointer++;
		}
		return;
	}
	/*/
	private void findMaxFriendZero() {
		int entryPointer = 1;
		int exitPointer = 0;
		int friendsCurrent = 1;
		friends = 1;
		while (entryPointer < totalPeople) {
			while (entryTimeList[entryPointer] <= exitTimeList[exitPointer]) {
				friendsCurrent++;
				entryPointer++;
				if (entryPointer >= totalPeople)
					break;
			}
			exitPointer++;
			
			if (friendsCurrent > friends) {
				friends = friendsCurrent;
			}
			
			if (entryPointer >= totalPeople)
				break;
			
			while (entryTimeList[entryPointer] > exitTimeList[exitPointer]) {
				friendsCurrent--;
				exitPointer++;
			}
			entryPointer++;
		}
		return;
	}
	/*/
	public void sortTimes() {
		if (!sorted) {
			Arrays.sort(entryTimeList);
			Arrays.sort(exitTimeList);
			sorted = true;
		}
	}

	public String getName() {
		return name;
	}
	
	public int getPeople() {
		return totalPeople;
	}
	public int getFriends() {
		return friends;
	}
	
	public void editTime(int index, String[] timeInterval) {
		entryTimeList[index] = Long.valueOf(timeInterval[0]);
		exitTimeList[index] = Long.valueOf(timeInterval[1]);
		sorted = false;
	}
}