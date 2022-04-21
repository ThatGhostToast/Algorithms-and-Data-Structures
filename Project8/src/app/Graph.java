package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Graph {
	// =-=-=-=-=-=-=-=-=-=-=
	// Setting up the graph
	// =-=-=-=-=-=-=-=-=-=-=
	private final int SIZE = 15;
	public LinkedList<Node>[] graph = new LinkedList[SIZE];

	// =-=-=-=-=-=-=-=-=
	// Getting the data
	// =-=-=-=-=-=-=-=-=
	// Counties provided from the graph guide
	private String[] counties = new String[] { "Mohave", "Coconino", "Navajo", "Apache", "Greenlee", "Cochise",
			"Santa Cruz", "Pima", "Pinal", "Graham", "Gila", "Yavapai", "La Paz", "Yuma", "Maricopa" };
	// Edges provided from the graph guide
	// I did a 'control shift f' at some point and its too far back to fix ಥ_ಥ
	// Sorry for the number blob!
	private int[][] edges = new int[][] { { 0, 1, 14 }, { 0, 12, 9 }, { 0, 11, 14 }, { 1, 0, 14 }, { 1, 11, 9 },
			{ 1, 10, 17 }, { 1, 2, 9 }, { 2, 1, 9 }, { 2, 10, 13 }, { 2, 9, 20 }, { 2, 3, 5 }, { 3, 2, 5 },
			{ 3, 9, 19 }, { 3, 4, 17 }, { 4, 3, 17 }, { 4, 9, 4 }, { 4, 5, 16 }, { 5, 4, 16 }, { 5, 9, 12 },
			{ 5, 7, 9 }, { 5, 6, 8 }, { 6, 5, 8 }, { 6, 7, 6 }, { 7, 6, 6 }, { 7, 5, 9 }, { 7, 9, 12 }, { 7, 8, 7 },
			{ 7, 14, 18 }, { 7, 13, 23 }, { 8, 7, 7, }, { 8, 9, 13 }, { 8, 10, 5 }, { 8, 14, 6 }, { 9, 7, 12 },
			{ 9, 5, 12 }, { 9, 4, 4 }, { 9, 3, 19 }, { 9, 2, 20 }, { 9, 10, 7 }, { 9, 8, 13 }, { 10, 8, 5 },
			{ 10, 9, 7 }, { 10, 2, 13 }, { 10, 1, 17 }, { 10, 11, 18 }, { 10, 14, 8 }, { 11, 14, 9 }, { 11, 10, 18 },
			{ 11, 1, 9 }, { 11, 0, 14 }, { 11, 12, 15 }, { 12, 0, 9 }, { 12, 11, 15 }, { 12, 14, 15 }, { 12, 13, 11 },
			{ 13, 12, 11 }, { 13, 14, 18 }, { 13, 7, 23 }, { 14, 12, 15 }, { 14, 13, 8 }, { 14, 7, 18 }, { 14, 8, 6 },
			{ 14, 10, 8 }, { 14, 11, 9 } };

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Constructor to build the linked list
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public Graph() {
		for (int x = 0; x < SIZE; x++) {
			graph[x] = new LinkedList<>();
		}
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method for generating the graph
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public void createGraph() {
		for (int i = 0; i < edges.length; i++) {
			createNode(edges[i][0], edges[i][1], edges[i][2]);
		}
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method for adding a node into the graph
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	private void createNode(int location, int dest, int dist) {
		//Linked list for the nodes at that location
		LinkedList<Node> nodesAtLocaiton = graph[location];
		//Creating a new node
		Node node = new Node();
		//Setting the nodes values
		node.location = location;
		node.dest = dest;
		node.dist = dist;
		//Adding it to the list
		nodesAtLocaiton.add(node);
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method used for displaying the adjacency matrix
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public void display() {
		System.out.println("=-=- Adjacency Matrix -=-=");
		for (int x = 0; x < SIZE; x++) {
			System.out.println("Vertices[" + x + "] " + graph[x]);
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method used to get the distance
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public int getDistance(int start, int end) {
		// If they are the same vertices then there is no distance
		if (start == end) {
			return 0;
		}

		// Checking the vertices neighbors to see if the end node is one of it's
		// neighbors
		for (String s : getNeighbors(start)) {
			// If the vertices has a neighbor that equals where we're going
			if (s.equals(counties[end])) {
				// Looping over the vertices linked list to get the specific neighbor
				for (int x = 0; x < graph[start].size(); x++) {
					// If statement when the neighbor is found
					if (graph[start].get(x).dest == end) {
						return graph[start].get(x).dist;
					}
				}
			}
		}

		int[] neighbors = new int[graph[start].size()];
		for (int x = 0; x < neighbors.length; x++) {
			neighbors[x] = graph[start].get(x).dest;
		}

		// Checking the neighbors vertices neighbors to see if the end node is one of
		// it's
		// neighbors
		for (int nei = 0; nei < neighbors.length; nei++) {
			// Total distance between the two nodes
			int totalDistance = 0;

			for (String s : getNeighbors(neighbors[nei])) {
				// If the vertices has a neighbor that equals where we're going
				if (s.equals(counties[end])) {
					// Getting the distance of the first neighbors
					totalDistance = totalDistance + getDistance(start, neighbors[nei]);
					// Looping over the vertices linked list to get the specific neighbor
					for (int x = 0; x < graph[neighbors[nei]].size(); x++) {
						// If statement when the neighbor is found
						if (graph[neighbors[nei]].get(x).dest == end) {
							// Adding the distance to the total distance
							totalDistance = totalDistance + graph[neighbors[nei]].get(x).dist;
							// Return the total distance
							return totalDistance;
						}
					}
				}
			}
		}

		// If the quicker routes cannot be found, then it will resort to finding it via
		// the longest route
		int temp = 1;
		int finalDistance = 0;
		// Looping over the graph
		for (int x = 0; x < graph.length; x++) {
			// Getting each of the nodes out of the graph at the current vertices
			for (int y = 0; y < graph[x].size(); y++) {
				// If the graph has the end point as a neighbor, then the distance will be added
				// and the total will be returned
				if (graph[x].get(y).dest == end) {
					finalDistance += graph[x].get(y).dist;
					return finalDistance;
				}
				// Else the next vertices distance will be added
				if (graph[x].get(y).dest == temp) {
					finalDistance += graph[x].get(y).dist;
				}
			}
			temp++;
		}

		return finalDistance;
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method to get the neighbors
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public List<String> getNeighbors(int vertices) {
		// List of neighbors
		List<String> neighbors = new ArrayList<>();
		// For each node in the graph with a specific vertices getting every single
		// destination
		for (Node node : graph[vertices]) {
			// Adding the neighbors to the list
			neighbors.add(counties[node.dest]);
		}
		// Return the list of neighbors
		return neighbors;
	}

}
