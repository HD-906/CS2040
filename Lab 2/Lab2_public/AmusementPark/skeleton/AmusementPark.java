import java.util.LinkedList;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AmusementPark {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		LinkedList<LinkedList<String>> scheduleAllDays = new LinkedList<LinkedList<String>>();
		String[] currentCommand, currentNumber;
		int length, num;

		ALL_COMMAND:
		while (true) { // one loop for a day, break when "END" is encountered
			currentCommand = scan.nextLine().split(": ");
			LinkedList<String> scheduleSingleDay = new LinkedList<String>(); // Create a new array
			scheduleSingleDay.add(currentCommand[1]);
			length = 1;
			
			SINGLE_DAY:
			while (true) { // one loop for a command, break when "END" or "NEXT DAY" is encountered
				currentCommand = scan.nextLine().split(": ");
				
				currentNumber = currentCommand[1].split(" ");
				
				switch (currentCommand[0]) {
				case "NEXT RIDE":
					scheduleSingleDay.add(currentNumber[0]);
					length++;
					break;
				case "DELETE FRONT RIDE":
					num = Integer.parseInt(currentNumber[0]); // number of events to delete
					if (length >= num) {
						scheduleSingleDay.subList(0, num).clear();
						length -= num;
					}
					else {
						System.out.println("Invalid command");
					}
					break;
				case "DELETE BACK RIDE":
					num = Integer.parseInt(currentNumber[0]); // number of events to delete
					if (length >= num) {
						scheduleSingleDay.subList(length-num, length).clear();
						length -= num;
					}
					else {
						System.out.println("Invalid command");
					}
					break;
				case "CHANGE FRONT RIDE":
					num = Integer.parseInt(currentNumber[0]); // number of events to delete or edit
					if (length >= num) {
						scheduleSingleDay.subList(0, num).clear();
						scheduleSingleDay.add(0, currentNumber[1]);
						length -= num-1;
					}
					else {
						System.out.println("Invalid command");
					}
					break;
				case "CHANGE BACK RIDE":
					num = Integer.parseInt(currentNumber[0]); // number of events to delete or edit
					if (length >= num) {
						scheduleSingleDay.subList(length-num, length).clear();
						scheduleSingleDay.add(currentNumber[1]);
						length -= num-1;
					}
					else {
						System.out.println("Invalid command");
					}
					break;
				case "NEXT DAY":
					scheduleAllDays.add(scheduleSingleDay); // add list for the day to the main list
					break SINGLE_DAY;
				case "END":
					scheduleAllDays.add(scheduleSingleDay); // add list for the day to the main list
					break ALL_COMMAND;
				default:
					System.out.println("Invalid command");
				}
			}
		}
		
		// Print schedule day by day
		for(int i=0; i<scheduleAllDays.size(); i++) {
			scheduleAllDays.get(i);
			System.out.println("Day " + (i+1) + ": " + scheduleAllDays.get(i));
		}
		scan.close();
	}
}
