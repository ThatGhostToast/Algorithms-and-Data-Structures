package app;

import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class Hashy {
	public static class Node {
		@Override
		public String toString() {
			return "=-Hashed Object: " + "Content='" + data + "', Value='" + value + "', Bucket='" + bucket + "'-=";
		}

		//String being stored
		private String data;
		//Random value
		private Integer value;
		//Bucket the data is being stored in
		private Integer bucket;
	}

	private int arraySize = 10;
	private LinkedList<Node>[] ll = new LinkedList[arraySize];

	// Constructor
	public Hashy() {
		for (int x = 0; x < arraySize; x++) {
			ll[x] = null;
		}
	}

	// Method used to get the hashed object from the linked list to return it to the
	// findHash method
	private Node getObject(String key) {
		//Initialize a counter to see how many elements were searched in the bucket to find the data
		int count = 0;
		//If there is no key, then there's nothing to search
		if (key == null)
			return null;

		//Getting the bucket to search
		int bucket = key.hashCode() % arraySize;
		
		//Creating a list of nodes populated by the content of the bucket
		LinkedList<Node> objects = ll[bucket];

		//If there's nothing in the bucket then there is no content
		if (objects == null)
			return null;

		//For each object in the linked list
		for (Node obj : objects) {
			if (!obj.data.equals(key)) {
				//If the object hasn't been found yet add one to the counter
				count++;
			} else {
				//Once the object is found, print the count and the object's data
				System.out.println(count + " elements searched to find " + key);
				return obj;
			}

		}

		return null;
	}

	// Method used for finding a hashed object inside the linked list and returning
	// it's value
	public Integer findObject(String key) {
		Node obj = getObject(key.toLowerCase());

		// If the object was found or not
		if (obj == null) {
			return null;
		} else {
			return obj.value;
		}
	}

	// Method used to place objects into the table
	public void put(String data, Integer value) {
		// Getting the bucket that the data is being put into
		int bucket = Math.abs(data.hashCode() % arraySize);
		// Getting all of the objects in the linked list
		LinkedList<Node> objects = ll[bucket];
		// If the list is empty
		if (objects == null) {
			// Set items to a new linked list
			objects = new LinkedList<>();

			// Create a new node for our object to be stored in
			Node newObj = new Node();

			// Add data to the node
			newObj.data = data.toLowerCase();
			newObj.value = value;
			newObj.bucket = bucket;
			objects.add(newObj);

			// Add the objects into the bucket
			ll[bucket] = objects;
		} else {
			// For each node inside the linked list
			for (Node obj : objects) {
				// If the data equals the data of another object in the list
				if (obj.data.equals(data)) {
					// Update the value and bucket of the object
					obj.value = value;
					obj.bucket = bucket;
					return;
				}
			}

			// Create a new node for the data to be stored
			Node newObj = new Node();

			// Add data into the node
			newObj.data = data.toLowerCase();
			newObj.value = value;
			newObj.bucket = bucket;

			// Place data into the linked list
			objects.add(newObj);
		}
	}

	// Method used for displaying the all the content of the table
	public void displayHashTable() {
		// Loops over the linked list and prints the content
		for (int x = 0; x < arraySize; x++) {
			System.out.println(ll[x]);
		}
	}
}
