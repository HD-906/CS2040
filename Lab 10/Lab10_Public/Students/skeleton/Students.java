/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

import java.util.*;
import java.io.*;

public class Students {
	public static void main(String args[]) throws IOException {
		
		try(BufferedReader in = 
				new BufferedReader(
						new InputStreamReader(System.in)
				)
		){
			String[] lines = in.readLine().split(" ");
			int peopleCount = Integer.parseInt(lines[0]);
			int linksCount = Integer.parseInt(lines[1]);
			
			StatusMap<String> status = new StatusMap<>();
			
			while (peopleCount-- > 0) {
				String[] myStatus = in.readLine().split(" ");
				
				if (myStatus[1].equals("s"))
					status.addStudent(myStatus[0]);
				
				else if (myStatus[1].equals("n"))
					status.addNotStudent(myStatus[0]);
			}
			
			CurrentState currentState = CurrentState.WIN_IMPOSSIBLE;
			
			while (linksCount-- > 0) {
				String[] link = in.readLine().split(" -> ");
				
				if (status.isNotStudent(link[0]) || // n -> X OR
						status.isStudent(link[1])) // X -> s
					// links from 'n' or to 's' are ignored
					// as they are unrelated to the win condition
					continue;
				
				// A win is only possible if at least one link
				// that is not of the 2 states listed above has appeared
				currentState = CurrentState.UNCLEAR;
				
				if (status.isStudent(link[0])) { // s -> *
					
					if (status.isNotStudent(link[1])) { // s -> n
						// a link "s -> n" found
						currentState = CurrentState.WIN_FOUND;
						break;
					} else if (status.addAllChildren(link[1])) { // s -> ?
						/* if '?' is actually 'n', a win is found.
						 * since it is not guaranteed a win, '?' will
						 * be hypothesized to 's' for successive condition checks
						 * all '?' linked to it is also changed to 's'
						 * due to the same reason
						 * method returns true and break the loop with
						 * a win found only if a 'n' is found in the process
						 * meaning at least one "s -> n" is guaranteed somewhere
						 * otherwise it returns false after changing all
						 * successive '?'s to 's's and the loop continues
						 */
						currentState = CurrentState.WIN_FOUND;
						break;
					}
					
				} else { // ? -> *
						
					status.addUnknownLink(link[0], link[1]);
					
				}
			}
			
			switch (currentState) {
			
			case WIN_IMPOSSIBLE:
				System.out.println("EVERYONE LOSES");
				break;

			case UNCLEAR:
				System.out.println("OUTCOME UNCLEAR");
				break;

			case WIN_FOUND:
				System.out.println("VICTORY");
				break;
				
			}
		}
	}
	
	static enum CurrentState {
		WIN_IMPOSSIBLE,
		UNCLEAR,
		WIN_FOUND
	}
	
	/* Stores nodes with status 's' and 'n' into 2 separate HashSets
	 * Stores any '? -> ?' or '? -> n' into unknownNode as key and value
	 * multiple values to the same key are stored as a LinkedList
	 */
	static class StatusMap<E> {
		private Set<E> studentSet;
		private Set<E> notStudentSet;
		private Map<E, LinkedList<E>> unknownNode;
		
		StatusMap() {
			studentSet = new HashSet<>();
			notStudentSet = new HashSet<>();
			unknownNode = new HashMap<>();
		}
		
		/* the method adds node e in unknownNode to studentSet
		 * also adds all nodes whom e is pointing to to studentSet 
		 * i.e. changes all successive nodes from status '?' to status 's'
		 * nodes are added by depth first, 
		 * returns true and stop adding once it found a node
		 * that's in notStudent (under status 'n')
		 * else returns false
		 */
		public boolean addAllChildren(E e) {
			if (notStudentSet.contains(e)) // node with status 'n' found
				return true;
			
			if (!studentSet.contains(e)) {
				studentSet.add(e); 
				// mark all nodes to status 's'
				if (unknownNode.containsKey(e)) { 
					// node is pointing to other nodes
					for (E eChild: unknownNode.get(e)) {
						if (addAllChildren(eChild)) 
							// depth first search by recurrence
							return true;
					}
				}
			}
			
			return false;
		}
		
		public boolean isStudent(E e) {
			return studentSet.contains(e);
		}
		
		public void addStudent(E e) {
			studentSet.add(e);
		}
		
		public boolean isNotStudent(E e) {
			return notStudentSet.contains(e);
		}
		
		public void addNotStudent(E e) {
			notStudentSet.add(e);
		}
		
		public void addUnknownLink(E e, E eChild) {
			if (!unknownNode.containsKey(e))
				unknownNode.put(e, new LinkedList<>());
			
			unknownNode.get(e).add(eChild);
		}
	}
}
