//package improve;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AmusementPark {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		Day[] dayArray = new Day[500_000];
		
		String[] line;
		
		int dayCount = 1;
		Day day = new Day();
		
		ALL_COMMAND:
		while (true) { // read all command first
			line = scan.nextLine().split(": ");
			int numberOfDays;
			String iDToAdd;
			
			switch (line[0]) {
			
				case "START RIDE": // fall through
				case "NEXT RIDE":
					iDToAdd = line[1];
					day.add(iDToAdd);
					day.incrementEndIndex();
					
				continue;
					
				case "DELETE FRONT RIDE":
					numberOfDays = Integer.parseInt(line[1]);
					
					if (day.getTotal() >= numberOfDays)
						day.addStartIndex(numberOfDays);
					else
						System.out.println("Invalid command");
					
				continue;
					
				case "DELETE BACK RIDE":
					numberOfDays = Integer.parseInt(line[1]);
					
					if (day.getTotal() >= numberOfDays)
						day.minusEndIndex(numberOfDays);
					else
						System.out.println("Invalid command");
					
				continue;
					
				case "CHANGE FRONT RIDE": {
					String[] tempNumberArray = line[1].split(" ");
					numberOfDays = Integer.parseInt(tempNumberArray[0]);
					iDToAdd = tempNumberArray[1];
					
					if (day.getTotal() >= numberOfDays) {
						day.addStartIndex(numberOfDays-1);
						day.setFirst(iDToAdd);
					}
					else {
						System.out.println("Invalid command");
					}
				}
				continue;
					
				case "CHANGE BACK RIDE": {
					String[] tempNumberArray = line[1].split(" ");
					numberOfDays = Integer.parseInt(tempNumberArray[0]);
					iDToAdd = tempNumberArray[1];
					
					if (day.getTotal() >= numberOfDays) {
						day.minusEndIndex(numberOfDays-1);
						day.add(iDToAdd, -1);
					}
					else {
						System.out.println("Invalid command");
					}
				}
				continue;
					
				case "NEXT DAY":
					dayArray[dayCount++] = day;
					day = new Day();

				continue;
					
				case "END":
					dayArray[dayCount] = day;
					
				break ALL_COMMAND;
					
				default:
			}
			
		}
		scan.close();
		
		for (int i=1; i<=dayCount; i++) {
			dayArray[i].println(i);
		}
	}
}

class Day {
	private int startIndex;
	private int endIndex;
	public ArrayList<String> rides;
	
	// constructor
	Day() {
		startIndex = 0;
		endIndex = 0;
		rides = new ArrayList<>();
	}
	
	public void println(int dayCount) {
		System.out.println("Day " + dayCount + ": " + actualRides());
	}
	
	private ArrayList<String> actualRides() {
		return new ArrayList<>(
				rides.subList(startIndex, endIndex)
				);
	}

	public void set(String idToAdd, int index) {
		if (index < rides.size())
			rides.set(index, idToAdd);
		else
			rides.add(idToAdd);
	}
	
	public void setFirst(String idToSet) {
		set(idToSet, startIndex);
	}

	public void add(String idToAdd, int index) {
		set(idToAdd, endIndex + index);
	}
	
	public void add(String idToAdd) {
		set(idToAdd, endIndex);
	}
	
	public void addStartIndex(int number) {
		startIndex += number;
	}
	
	public void minusEndIndex(int number) {
		endIndex -= number;
	}
	
	public void setEndIndex(int end) {
		endIndex = end;
	}
	
	public void incrementEndIndex() {
		endIndex++;
	}
	
	public int getTotal() {
		return endIndex - startIndex;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
}
