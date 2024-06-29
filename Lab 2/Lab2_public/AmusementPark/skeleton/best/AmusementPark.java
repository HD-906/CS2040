package best;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AmusementPark {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] allRides = new int[1_000_000]; // stores all the ride IDs
		int[] startArray = new int[500_000]; // stores start index of all days
		int[] endArray = new int[500_000]; // stores end index of all days
		
		String[] currentCommand;
		
		int dayCount = 1;
		startArray[dayCount] = 0;
		int index = 0;
		
		ALL_COMMAND:
		while (true) { // read all commands first to output invalid commands
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
					
					if (index - startArray[dayCount] >= numberOfDays)
						startArray[dayCount] += numberOfDays;
						// Increases start index to skip those deleted IDs
					else
						System.out.println("Invalid command");
					
					break;
					
				case "DELETE BACK RIDE":
					numberOfDays = Integer.parseInt(currentCommand[1]);
					
					if (index - startArray[dayCount] >= numberOfDays)
						index -= numberOfDays;
						// Deceases index to overwrites those deleted IDs
					else
						System.out.println("Invalid command");
					
					break;
					
				case "CHANGE FRONT RIDE": {
					String[] tempNumberArray = currentCommand[1].split(" ");
					numberOfDays = Integer.parseInt(tempNumberArray[0]);
					iDToAdd = Integer.parseInt(tempNumberArray[1]);
					
					if (index - startArray[dayCount] >= numberOfDays) {
						startArray[dayCount] += numberOfDays - 1;
						allRides[startArray[dayCount]] = iDToAdd;
						// Increases start index to skip those deleted IDs
						// Then overwrites the first ID
					}
					else {
						System.out.println("Invalid command");
					}
				}
					break;
					
				case "CHANGE BACK RIDE": {
					String[] tempNumberArray = currentCommand[1].split(" ");
					numberOfDays = Integer.parseInt(tempNumberArray[0]);
					iDToAdd = Integer.parseInt(tempNumberArray[1]);
					
					if (index - startArray[dayCount] >= numberOfDays) {
						index -= numberOfDays-1;
						allRides[index-1] = iDToAdd;
						// Deceases index to overwrites those deleted IDs
						// Then overwrites the last ID before index
					}
					else {
						System.out.println("Invalid command");
					}
				}
					break;
					
				case "NEXT DAY":
					endArray[dayCount++] = index;
					startArray[dayCount] = index;
					// increments day count after setting end index of the day
					// sets start index for the next day
					break;
					
				case "END":
					endArray[dayCount] = index;
					// sets end index of the day
					
					break ALL_COMMAND;
					
				default:
			}
			
		}
		scan.close();
		
		for (int day=1; day<=dayCount; day++) {
			System.out.println(
					"Day " + day + ": " + Arrays.toString(
						Arrays.copyOfRange(
							allRides, startArray[day], endArray[day]
						)
					)
			);
			// print the array from start index to end index of each day
			
		}
	}
}