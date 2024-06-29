import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Name:
 * Matric. No:
 */

public class Friends_test {
	public static void main(String args[]) throws FileNotFoundException {
		final String ONEDRIVE = "C:\\Users\\Administrator\\OneDrive\\";
		final String PREFIX = ONEDRIVE + "Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 5\\Lab5_public\\Friends\\";
		final String EXTENSION_IN = "in";
		final String EXTENSION_OUT = "out";
		File[] file_in = {
				new File(PREFIX + "input\\1." + EXTENSION_IN),
				new File(PREFIX + "input\\2." + EXTENSION_IN),
				new File(PREFIX + "input\\3." + EXTENSION_IN),
				new File(PREFIX + "input\\4." + EXTENSION_IN),
				new File(PREFIX + "input\\5." + EXTENSION_IN),
				new File(PREFIX + "input\\6." + EXTENSION_IN),
				new File(PREFIX + "input\\7." + EXTENSION_IN),
				new File(PREFIX + "input\\8." + EXTENSION_IN)
				};
		File[] file_out = {
				new File(PREFIX + "output\\1." + EXTENSION_OUT),
				new File(PREFIX + "output\\2." + EXTENSION_OUT),
				new File(PREFIX + "output\\3." + EXTENSION_OUT),
				new File(PREFIX + "output\\4." + EXTENSION_OUT),
				new File(PREFIX + "output\\5." + EXTENSION_OUT),
				new File(PREFIX + "output\\6." + EXTENSION_OUT),
				new File(PREFIX + "output\\7." + EXTENSION_OUT),
				new File(PREFIX + "output\\8." + EXTENSION_OUT)
				};
		Scanner scan;
		int testFrom = 1;
		int testTo = 8;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //

			String[] firstLine = scan.nextLine().split(" ");
			int numOfCafe = Integer.valueOf(firstLine[0]);
			//long maxTime = Long.valueOf(firstLine[1]);
			long maxTime = 0;
			cafe[] cafeList = new cafe[numOfCafe];
			
			for (int i=0; i<numOfCafe; i++) {
				String[] cafeInfo = scan.nextLine().split(" ");
				cafe newCafe = new cafe(cafeInfo[0], Integer.valueOf(cafeInfo[1]));
				
				for (int j=0; j<newCafe.getPeople(); j++) {
					newCafe.editTime(j, scan.nextLine().split(" "));
				}
				newCafe.sortTimes();
				newCafe.findMaxFriend(maxTime);
				cafeList[i] = newCafe;
			}
			
			cafe[] maxFriendsCafeList = new cafe[numOfCafe];
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
			
			// ******** OUTPUT ********
			scan.close();
			scan = new Scanner(file_out[i_test]);
			System.out.println("\nOUTPUT: ");
			do {
				System.out.println(scan.nextLine());
			} while (scan.hasNext());
			// ********************************** COPY PASTE ABOVE ***************************************** //
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("\nTimetaken for test " + i_test + ": \n" + elapsedTime + " ms\n");
			scan.close();
		}
	}
}
