/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Deliveries {
    public static void main(String args[]) throws IOException {
        System.out.println(maxMoney());
    }
    
    // returns the max amount of money for output
    public static long maxMoney() throws IOException {
        PriorityQueue<Request> requestQueue = new PriorityQueue<>();
        
        BufferedReader in =
            new BufferedReader(
                new InputStreamReader(System.in)
            );
        int numOfLines = Integer.valueOf(in.readLine());
        int maxTime = 0; // Stores the latest (largest) deadline of all queries
        
        while (numOfLines-- > 0) {
            String[] line = in.readLine().split(" ");
            int time = Integer.valueOf(line[0]);
            int money = Integer.valueOf(line[1]);
            
            maxTime = Integer.max(time, maxTime);
            requestQueue.add(new Request(time, money));
            // Stores requests in a PriorityQueue structure
            // starting from the most paid request
        }
        in.close();

        long totalMoney = 0L;
        boolean[] schedule = new boolean[maxTime];
        // Creates a schedule for all queries to be accepted, where true means occupied
        int earliestAvailable = 0; // The earliest time that is not occupied
        while (!requestQueue.isEmpty()) {
            Request request = requestQueue.poll();
            // fill the schedule starting from the most paid request
            int time = request.getTime();
            if (time - 1 >= earliestAvailable) { // check if deadline falls after earliest available time
            	while (schedule[--time]);
            	// iterate backward starting from the deadline of the request
            	// until an available time slot (which is a false) is found
                schedule[time] = true;
                totalMoney += request.getMoney();
            
	            if (time == earliestAvailable) {
	                // The time slot found is the earliest available
	                // Find the next earliest available time
	                time = request.getTime() - 1;
	                while (++time < maxTime) {
	                    if (!schedule[time]) { // next earliest available time (which is a false) found
	                        earliestAvailable = time;
	                        break;
	                    }
	                }
	                if (time == maxTime) // No available time found, schedule is full
	                    break; // Stop checking for remaining less paid requests
	            }
            }
        }
        return totalMoney;
    }
}

class Request implements Comparable<Request> {
    private int time;
    private int money;

    // Constructor
    Request(int time, int money) {
        this.time = time;
        this.money = money;
    }

    public int getTime() {
        return time;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Request other) { // sort in decreasing order by default
        return Integer.compare(other.money, money); // sort by the attribute money
    }
}
