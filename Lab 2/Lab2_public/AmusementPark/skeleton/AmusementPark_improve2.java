import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AmusementPark_improve2 {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		LinkedList<LinkedList<String>> scheduleAllDays = new LinkedList<LinkedList<String>>();
		String[] currentCommand, currentNumber;
		int length, num;
		
		while (true) { // one loop for a day, break when "END" is encountered
			currentCommand = scan.nextLine().split(": ");
			LinkedList<String> scheduleSingleDay = new LinkedList<String>(); // Create a new array
			scheduleSingleDay.add(currentCommand[1]);
			length = 1;
			while (true) { // one loop for a command, break when "END" or "NEXT DAY" is encountered
				currentCommand = scan.nextLine().split(": ");
				
				if (currentCommand[0].equals("NEXT DAY") || currentCommand[0].equals("END")) {
					break; // exit loop to proceed to the next day
				}
				
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
				default:
					System.out.println("Invalid command");
				}
			}
			scheduleAllDays.add(scheduleSingleDay); // add list for the day to the main list
			
			if (currentCommand[0].equals("END")) {
				break;
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
