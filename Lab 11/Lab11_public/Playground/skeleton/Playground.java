import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class Playground {
	public static void main(String args[]) throws IOException, InterruptedException {
		
		try(BufferedReader in = 
				new BufferedReader(
						new InputStreamReader(System.in)
				)
		){
			String[] line = in.readLine().split(" ");
			int playgroundCount = Integer.parseInt(line[0]);
			int tunnelCount = Integer.parseInt(line[1]);
			int friendCount = Integer.parseInt(line[2]);

			StatusMap<StatusMap.Edge, StatusMap.Node> map = new StatusMap<>(in.readLine().split(" "));
			Integer[] friends = new Integer[friendCount];
			ArrayList<Integer> friendsReached = new ArrayList<>();
			//
			while (tunnelCount-- > 0) {
				line = in.readLine().split(" ");
				map.addTunnel(
						Integer.parseInt(line[0]), 
						Integer.parseInt(line[1]), 
						Integer.parseInt(line[2])
				);
			}
			
			while (friendCount-- > 0)
				friends[friendCount] = Integer.valueOf(in.readLine());
			
			map.setPaths();
			
			for (int friend: friends) {
				if (map.passThroughMTunnel(friend))
					friendsReached.add(friend);
			}
			
			friendsReached.sort(null);
			
			String formattedString = friendsReached.toString()
				    .replace(",", "")  //remove the commas
				    .replace("[", "")  //remove the right bracket
				    .replace("]", "")  //remove the left bracket
				    .trim();           //remove trailing spaces from partially initialized arrays
			
			System.out.println(formattedString);
		}
	}
	
	
	static class StatusMap<Edge, Node> {
		private int startingID;
		private int MTunnelID1;
		private int MTunnelID2;
		private Map<Integer, Node> nodeMap;
		private Map<Integer, Edge> edgeMap;
		private AVLTree<Node> distanceToStartList;
		private AVLTree<Node> distanceToMTunnelList;
		private int startingToMTunnelOut;
		
		
		static class Edge {
			private Node from;
			private Node to;
			public final Integer DIST;
			
			Edge(Node from, Node to, int distance){
				this.from = from;
				this.to = to;
				DIST = distance;
			}
			
			public boolean sameEdge(Edge other) {
				if (this == other || from == other.to && to == other.from)
					return true;
				return false;
			}
		}
		
		
		static class Node implements Comparable<Node> {
			public final int ID;
			private LinkedList<Edge> adjacent;
			private int shortestToStart;
			private int shortestToMTunnelOut;
			private int dummyForCompare;
			
			Node(int id) {
				ID = id;
				adjacent = new LinkedList<>();
				shortestToStart = Integer.MAX_VALUE;
				shortestToMTunnelOut = Integer.MAX_VALUE;
				dummyForCompare = Integer.MAX_VALUE;
			}

			@Override
			public int compareTo(Node other) {
				if (Integer.compare(dummyForCompare, other.dummyForCompare) != 0)
					return Integer.compare(dummyForCompare, other.dummyForCompare);
				return Integer.compare(ID, other.ID);
			}
		}
		
		
		StatusMap(String[] line) {
			startingID = Integer.parseInt(line[0]);
			MTunnelID1 = Integer.parseInt(line[1]);
			MTunnelID2 = Integer.parseInt(line[2]);
			nodeMap = new HashMap<>();
			edgeMap = new HashMap<>();
			distanceToStartList = new AVLTree<>();
			distanceToMTunnelList = new AVLTree<>();
			startingToMTunnelOut = Integer.MAX_VALUE;
		}

		public void addTunnel(int id1, int id2, int length) {
			if (!nodeMap.containsKey(id1)) {
				Node node = new Node(id1);
				nodeMap.put(id1, node);
				distanceToStartList.put(node);
				distanceToMTunnelList.put(node);
			}
			
			if (!nodeMap.containsKey(id2)) {
				Node node = new Node(id2);
				nodeMap.put(id2, node);
				distanceToStartList.put(node);
				distanceToMTunnelList.put(node);
			}
			
			if (id2 != startingID) {
				Node node = nodeMap.get(id1);
				Edge edge = new Edge(node, nodeMap.get(id2), length);
				node.adjacent.add(edge);
				edgeMap.put(id1*10000 + id2, edge);
			}
			
			if (id1 != startingID) {
				Node node = nodeMap.get(id2);
				Edge edge = new Edge(node, nodeMap.get(id1), length);
				node.adjacent.add(edge);
				edgeMap.put(id2*10000 + id1, edge);
			}
		}
		
		public boolean passThroughMTunnel(int idFriend) {
			Node friend = nodeMap.get(idFriend);
			if (friend == null)
				return false;
			//
			//System.out.println();
			//System.out.println("friend " + idFriend + ":");
			//System.out.println("start to tunnel = " + startingToMTunnelOut);
			//System.out.println("friend to tunnel = " + friend.shortestToMTunnelOut);
			//System.out.println("start to friend = " + friend.shortestToStart);
			//
			return startingToMTunnelOut + friend.shortestToMTunnelOut == friend.shortestToStart;
		}
		
		public void setPaths() {
			Node startingNode = nodeMap.get(startingID);
			
			distanceToStartList.delete(startingNode);
			startingNode.shortestToStart = 0;
			Set<Node> settled = new HashSet<>();
			settled.add(startingNode);
			setPathsStart(startingNode, settled);
			
			Node MTunnel1 = nodeMap.get(MTunnelID1);
			Node MTunnel2 = nodeMap.get(MTunnelID2);
			Node MTunnel = MTunnel1.shortestToStart > MTunnel2.shortestToStart? MTunnel1: MTunnel2;
			startingToMTunnelOut = MTunnel.shortestToStart;
			
			distanceToMTunnelList.delete(MTunnel);
			MTunnel.shortestToMTunnelOut = 0;
			settled = new HashSet<>();
			settled.add(MTunnel);
			setPathsMtunnel(MTunnel, settled);
		}
		
		private void setPathsStart(Node last, Set<Node> settled) {
			while (!distanceToStartList.isEmpty()) {
				for (Edge edge: last.adjacent) {
					if (settled.contains(edge.to))
						continue;
					if (edge.to.dummyForCompare - edge.DIST > last.shortestToStart) {
						distanceToStartList.delete(edge.to);
						edge.to.dummyForCompare = last.shortestToStart + edge.DIST;
						distanceToStartList.put(edge.to);
						//
						//System.out.println("start to " + edge.to.ID + " changed to " + edge.to.dummyForCompare);
						//
					}
				}

				//
				//System.out.println("id " + node.ID + " polled with comp " + node.dummyForCompare);
				//
				Node node = distanceToStartList.poll();
				//
				//System.out.println("id " + node.ID + " polled with comp " + node.dummyForCompare);
				//
				node.shortestToStart = node.dummyForCompare;
				node.dummyForCompare = Integer.MAX_VALUE;
				settled.add(node);
				last = node;
			}
		}
		
		private void setPathsMtunnel(Node last, Set<Node> settled) {
			while (!distanceToMTunnelList.isEmpty()) {
				for (Edge edge: last.adjacent) {
					if (settled.contains(edge.to))
						continue;
					if (edge.to.dummyForCompare - edge.DIST > last.shortestToMTunnelOut) {
						distanceToMTunnelList.delete(edge.to);
						edge.to.dummyForCompare = last.shortestToMTunnelOut + edge.DIST;
						distanceToMTunnelList.put(edge.to);
						//
						//System.out.println("Mtunnel to " + edge.to.ID + " changed to " + edge.to.dummyForCompare);
						//
					}
				}
				
				Node node = distanceToMTunnelList.poll();
				//
				//System.out.println("id " + node.ID + " polled with comp " + node.dummyForCompare);
				//
				node.shortestToMTunnelOut = node.dummyForCompare;
				//node.dummyForCompare = Integer.MAX_VALUE;
				settled.add(node);
				last = node;
			}
		}
	}
	
	static class AVLTree<Key extends Comparable<Key>> {
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
	
		public boolean isEmpty() {
			return size(root) == 0;
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
	
		public Key poll() {
			if (size() == 0)
				return null;
			Node x = poll(root);
			root = delete(root, x.key);
			return x.key;
		}
	
		private Node poll(Node x) {
			if (x.left == null)
				return x;
			return poll(x.left);
		}
	
		private Node min(Node x) {
			if (x.left == null)
				return x;
			return min(x.left);
		}
	}
}
