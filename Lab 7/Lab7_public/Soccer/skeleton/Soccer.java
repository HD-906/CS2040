import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Soccer {
	public static void main(String args[]) throws IOException {
		
		class Team implements Comparable<Team>{
			private long score;
			private final int order;
			
			Team(long score, int order) {
				this.score = score;
				this.order = order;
			}

			@Override
			public int compareTo(Team otherTeam) {
				int comparedScore = Long.compare(score, otherTeam.score);
				if (comparedScore != 0)
					return comparedScore;
				return Integer.compare(otherTeam.order, order);
			}
		}
		
		PriorityQueue<Team> tasks = new PriorityQueue<>(Collections.reverseOrder());
		tasks.add(new Team(100L, 1));
		tasks.add(new Team(200L, 2));
		tasks.add(new Team(100L, 3));
		
		System.out.println(tasks.peek().score+", "+tasks.poll().order);
		System.out.println(tasks.peek().score+", "+tasks.poll().order);
		System.out.println(tasks.peek().score+", "+tasks.poll().order);
		
		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();
		set1.add(1); set1.add(2); set1.add(3);
		set2.addAll(set1); set2.add(10); set2.add(11);
		set1.add(10); set1.add(20); set1.add(21);
		System.out.println("size1: " + set1.size());
		System.out.println("size2: " + set2.size());
		;
		
		Map<Integer, Set<Integer>> map = new HashMap<>();
		map.put(1, set1);
		System.out.println("size1map: " + map.get(1).size());
		map.get(1).retainAll(set2);
		System.out.println("size1map: " + map.get(1).size());
		System.out.println(map.get(1).size());
		int x = Integer.max(1,2);
		/****************************** end of class definition ******************************/

		AVLTree<Team> leaderboard = new AVLTree<>();
		Map<String, Team> nameMap = new HashMap<>();
		
		BufferedReader in = 
			new BufferedReader(
				new InputStreamReader(System.in)
			);
		
		int numberOfLines = Integer.valueOf(in.readLine());
		int order = 0;
		
		try(PrintWriter out = 
			new PrintWriter(
				new BufferedWriter(
					new OutputStreamWriter(System.out)
				)
			)
		) {
			
			while (numberOfLines-- > 0) {
				String[] line = in.readLine().split(" ");
				String teamName = line[1];
				Team team;
				switch (line[0].charAt(0)) {
				
				case 'A': // ADD
					long teamScore = Long.valueOf(line[2]);
					team = new Team(teamScore, order++);
					nameMap.put(teamName, team);
					leaderboard.put(team);
					
					continue;
					
				case 'M': // MATCH
					long scoreChange = Long.valueOf(line[3]) - Long.valueOf(line[4]);
					
					team = nameMap.get(teamName);
					leaderboard.delete(team);
					team.score += scoreChange;
					if (team.score > 0)
						leaderboard.put(team);
					else
						nameMap.remove(teamName);
					
					teamName = line[2];
					
					team = nameMap.get(teamName);
					leaderboard.delete(team);
					team.score -= scoreChange;
					if (team.score > 0)
						leaderboard.put(team);
					else
						nameMap.remove(teamName);
					
					continue;
					
				case 'Q': // QUERY
					team = nameMap.get(teamName);
					if (team == null) {
						out.println("Team " + teamName + " is ELIMINATED");
						continue;
					}
					
					out.println("Team " + 
						teamName + ": " + 
						team.score + " points, rank " + 
						leaderboard.rank(team)
					);
					
					continue;
				default:
				}
			}
			
		} finally {
			in.close();
		}
	}
}

class AVLTree<Key extends Comparable<Key>> {
	private Node root;

	private class Node {
		private final Key key;
		private int height;
		private int size;
		private Node left;
		private Node right;

		public Node(Key key, int height, int size) {
			this.key = key;
			this.size = size;
			this.height = height;
		}
	}
	
	public int rank(Key key) {
		return rank(root, key);
	}
	
	public int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key) + 1 + size(x.right);
		else if (cmp > 0)
			return rank(x.right, key);
		else
			return 1 + size(x.right);
		}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	private int height(Node x) {
		if (x == null)
			return -1;
		return x.height;
	}

	public boolean contains(Key key) {
		return contains(root, key);
	}

	public boolean contains(Node x, Key key) {
		if (x == null)
			return false;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return contains(x.left, key);
		else if (cmp > 0)
			return contains(x.right, key);
		else
			return true;
	}

	public void put(Key key) {
		root = put(root, key);
	}

	private Node put(Node x, Key key) {
		if (x == null)
			return new Node(key, 0, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key);
		} else if (cmp > 0) {
			x.right = put(x.right, key);
		} else {
			return x;
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	private Node balance(Node x) {
		if (balanceFactor(x) < -1) {
			if (balanceFactor(x.right) > 0) {
				x.right = rotateRight(x.right);
			}
			x = rotateLeft(x);
		} else if (balanceFactor(x) > 1) {
			if (balanceFactor(x.left) < 0) {
				x.left = rotateLeft(x.left);
			}
			x = rotateRight(x);
		}
		return x;
	}

	private int balanceFactor(Node x) {
		return height(x.left) - height(x.right);
	}

	private Node rotateRight(Node x) {
		Node y = x.left;
		x.left = y.right;
		y.right = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	private Node rotateLeft(Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		y.size = x.size;
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	public void delete(Key key) {
		if (!contains(key))
			return;
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = delete(x.left, key);
		} else if (cmp > 0) {
			x.right = delete(x.right, key);
		} else {
			if (x.left == null) {
				return x.right;
			} else if (x.right == null) {
				return x.left;
			} else {
				Node y = x;
				x = min(y.right);
				x.right = deleteMin(y.right);
				x.left = y.left;
			}
		}
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.left) + size(x.right);
		x.height = 1 + Math.max(height(x.left), height(x.right));
		return balance(x);
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}
}