import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AmusementPark_test {
	public static void main(String args[]) throws FileNotFoundException {
		boolean PC = true;
		final String DEVICE = PC? "Administrator": "HD";
		final String ONEDRIVE = "C:\\Users\\" + DEVICE + "\\OneDrive\\";
		final String PREFIXLAB = "Lab 2\\Lab2_public\\AmusementPark\\"; // Change every lab
		final String PREFIX = ONEDRIVE + "Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\" + PREFIXLAB;
		final String EXTENSION_IN = "txt";
		final String EXTENSION_OUT = "txt";
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
		int testFrom = 8;
		int testTo = 8;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //

			int[] allRides = new int[1_000_000];
			Map<Integer, Day> dayMap = new HashMap<>();
			
			String[] currentCommand;
			
			int dayCount = 1;
			dayMap.put(dayCount, new Day(0));
			int index = 0;
			
			ALL_COMMAND:
			while (true) { // read all command first
				Day day = dayMap.get(dayCount);
				currentCommand = scan.nextLine().split(": ");
				int numberOfDays, iDToAdd;
				
				switch (currentCommand[0]) {
					case "START RIDE": // fall through
					case "NEXT RIDE":
						iDToAdd = Integer.parseInt(currentCommand[1]);
						allRides[index] = iDToAdd;
						index++;
						
						continue;
						
					case "DELETE FRONT RIDE":
						numberOfDays = Integer.parseInt(currentCommand[1]);
						
						if (day.getTotal(index) >= numberOfDays)
							day.addStartIndex(numberOfDays);
						//else
							//System.out.println("Invalid command");
						
						break;
						
					case "DELETE BACK RIDE":
						numberOfDays = Integer.parseInt(currentCommand[1]);
						
						if (day.getTotal(index) >= numberOfDays)
							index -= numberOfDays;
						//else
							//System.out.println("Invalid command");
						
						break;
						
					case "CHANGE FRONT RIDE": {
						String[] tempNumberArray = currentCommand[1].split(" ");
						numberOfDays = Integer.parseInt(tempNumberArray[0]);
						iDToAdd = Integer.parseInt(tempNumberArray[1]);
						
						if (day.getTotal(index) >= numberOfDays) {
							day.addStartIndex(numberOfDays-1);
							allRides[day.getStartIndex()] = iDToAdd;
						}
						else {
							//System.out.println("Invalid command");
						}
					}
						break;
						
					case "CHANGE BACK RIDE": {
						String[] tempNumberArray = currentCommand[1].split(" ");
						numberOfDays = Integer.parseInt(tempNumberArray[0]);
						iDToAdd = Integer.parseInt(tempNumberArray[1]);
						
						if (day.getTotal(index) >= numberOfDays) {
							index -= numberOfDays-1;
							allRides[index-1] = iDToAdd;
						}
						else {
							//System.out.println("Invalid command");
						}
					}
						break;
						
					case "NEXT DAY":
						day.setEndIndex(index);
						dayMap.put(++dayCount, new Day(index));
						
						break;
						
					case "END":
						day.setEndIndex(index);
						
						break ALL_COMMAND;
						
					default:
				}
				
			}
			scan.close();
			
			for (int i=1; i<=dayCount; i++) {
				Day day = dayMap.get(i);
				int[] singleDayRides = Arrays.copyOfRange(allRides, day.getStartIndex(), day.getEndIndex());
			}
			
			// ********************************** COPY PASTE ABOVE ***************************************** //
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("\nTimetaken for test " + i_test + ": \n" + elapsedTime + " ms\n");
			scan.close();
		}
	}
}
