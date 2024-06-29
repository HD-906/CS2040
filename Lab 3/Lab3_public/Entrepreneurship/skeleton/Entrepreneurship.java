import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Entrepreneurship {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		String[] currentCommand = scan.nextLine().split(" "); // read the first line and split for detail
		int commandLen = Integer.parseInt(currentCommand[0]); // number of commands on the order sheet
		int numAvailable = Integer.parseInt(currentCommand[1]); // maximum number of pizza that can be sold
		int number;
		
		Batch[] batchList = new Batch[commandLen];
		
		for (int i=0; i<commandLen; i++) {
			Batch nextBatch = new Batch(scan);
			number = nextBatch.getNum();
			if (number != -1) { // it's a CANCEL command
				i -= number+1; 
				// reduce i to overwrite on the cancelled command
				
				commandLen -= number+1; 
				// commandLen indicates number of uncanceled command so far
				// decreases with i so that the for loop can still terminate at the end of all commands
				// after the for loop, commandLen will become the number of uncanceled batches
			}
			else { // it's an ADD command
				batchList[i] = nextBatch;
			}
		}
		
		scan.close();
		
		long totalPrice = 0; // saves price in units of 0.1
		for (int i=0; i<commandLen; i++) { // process each of the uncanceled batches
			numAvailable = batchList[i].calculateBatch(numAvailable); // updates number of pizza available after the batch
			totalPrice += batchList[i].getPrice();
			if (numAvailable == 0) {
				break; // stop processing the remaining batches since there are no more pizza available
			}
		}
		System.out.println(totalPrice/10 + "." + totalPrice%10); 
		// convert and display totalPrice from units of 0.1 back to units of 1
	}
}

class Batch {
	private int number = -1; // used to differentiate if it is an ADD or CANCEL command
	private long batchPrice = 0;
	private String detail, mainCommand; // Stores the 2 lines of the batch to be processed later

	// constructor
	public Batch(Scanner scan) {
		String line = scan.nextLine();
		if (line.charAt(0) == 'A') { // ADD command: number = -1 as initialized
			mainCommand = line;
			line = scan.nextLine(); // read another line for the details of the batch
			detail = line;
		}
		else {
			number = Integer.parseInt(line.split(" ")[1]); // CANCEL command: number indicates number of batches to cancel
		}
	}
	
	// Take in the number of pizza available before the batch
	// Returns the number of pizza available after the batch
	// Also stores the total price of the batch in batchPrice, excluding orders that are skipped due to insufficient pizza
	// Price is stored in units of 0.1
	public int calculateBatch(int numAvailable) {
		int commandLen = mainCommand.length();
		number = Integer.parseInt(mainCommand.substring(4, commandLen-2)); // number of order in batch
		boolean fromLeft = (mainCommand.charAt(commandLen-1) == 'L'); // whether batch starts from left
		
		String[] orderGrp = detail.split(" ");
		
		int quantity, price;
		
		if (fromLeft) {
			for (int i=0; i<number; i++) { // batch starts from left
				quantity = Integer.parseInt(orderGrp[2*i]);
				if (numAvailable >= quantity) {
					try { // average pizza price is an integer
						price = 10 * Integer.parseInt(orderGrp[2*i+1]); 
					}
					catch (NumberFormatException e) { // average pizza price is not an integer, i.e. has 1 decimal place
						price = Integer.parseInt(orderGrp[2*i+1].replace(".", ""));
					}
					batchPrice += (long) quantity * price;
					numAvailable -= quantity;
					if (numAvailable == 0) {
						break; // stops checking as there is no more pizza available
					}
				}
			}
		}
		else {
			for (int i=number-1; i>=0; i--) { // batch starts from right
				quantity = Integer.parseInt(orderGrp[2*i]);
				if (numAvailable >= quantity) {
					try {
						price = 10 * Integer.parseInt(orderGrp[2*i+1]);
					}
					catch (NumberFormatException e) {
						price = Integer.parseInt(orderGrp[2*i+1].replace(".", ""));
					}
					batchPrice += (long) quantity * price;
					numAvailable -= quantity;
					if (numAvailable == 0) {
						break; // stops checking as there is no more pizza available
					}
				}
			}
		}
		return numAvailable;
	}

	public int getNum() {
		return number;
	}

	public long getPrice() {
		return batchPrice;
	}
}