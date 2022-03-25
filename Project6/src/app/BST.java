package app;

public class BST {
	//Root node
	Node root;

	//Method to insert a new node
	public void insert(String data) {
		//Creates a new node to insert
		Node newNode = new Node(data);

		//If the root of the tree is null then the new node becomes the root
		if (root == null) {
			root = newNode;
		} else {
			Node thisNode = root;
			Node parent;
			//Loops over comparing the new node with the nodes in the BST to accurately place it
			while (true) {
				parent = thisNode;
				//Comparing the name of the node first to see which direction to place the node
				if (data.compareTo(thisNode.name) < 0) {
					//Changing the current node to the left node
					thisNode = thisNode.leftNode;
					//If the left node is null then the left node is set to the new node
					if (thisNode == null) {
						parent.leftNode = newNode;
						return;
					}
				} else if (data.compareTo(thisNode.name) > 0) {
					//Changing the current node to the right node
					thisNode = thisNode.rightNode;
					//If the right node is null then the right node is set to the new node
					if (thisNode == null) {
						parent.rightNode = newNode;
						return;
					}
				} else {
					return;
				}
			}
		}
	}

	//Recursive method used to visit each node
	public void inOrderTraveral(Node thisNode) {
		if (thisNode != null) {
			inOrderTraveral(thisNode.leftNode);
			System.out.println(thisNode);
			inOrderTraveral(thisNode.rightNode);
		}
	}

	//Method used to delete a node
	public boolean delete(String data) {
		Node thisNode = root;
		Node parent = root;

		//Boolean used to check if the node is a left node
		boolean isLeftNode = true;

		//While loop used to find the data inside the BST
		while (!thisNode.name.equals(data)) {
			//Sets the new parent node
			parent = thisNode;
			//Checks to see which direction the node is in the tree
			if (data.compareTo(thisNode.name) < 0) {
				//If the data is a left node
				isLeftNode = true;
				thisNode = thisNode.leftNode;
			} else {
				//If the data is a right node
				isLeftNode = false;
				thisNode = thisNode.rightNode;
			}
			//If the node is null then nothing is deleted
			if (thisNode == null)
				return false;
		}
		
		//==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==
		//If else tower used for deleting the specific node and replacing nodes to keep 
		//the positioning accurate
		//==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==
		//If the left and right node are null
		if (thisNode.leftNode == null && thisNode.rightNode == null) {
			//If this node is equal to the root then it removes the root
			if (thisNode == root) {
				root = null;
			} else if (isLeftNode) {
				//If the node is the left node then we set the left node to null
				parent.leftNode = null;
			} else {
				//If the node is the right node then we set the right node to null
				parent.rightNode = null;
			}
		//If just the right node is null
		} else if (thisNode.rightNode == null) {
			if (thisNode == root)
				//If the node is the root, then the root is replaced by the left node
				root = thisNode.leftNode;
			else if (isLeftNode)
				//If the node is a left node, replace with the node to its left 
				parent.leftNode = thisNode.leftNode;
			else
				//If the node is a right node, replace with the node to its left
				parent.rightNode = thisNode.leftNode;
		//If the left node is null
		} else if (thisNode.leftNode == null) {
			//if the node is the root, then the root is replaced with the node to the right
			if (thisNode == root)
				root = thisNode.rightNode;
			//If the node is a left node, the node is replaced with the node to the right
			else if (isLeftNode)
				parent.leftNode = thisNode.rightNode;
			//If the node is a right node, the node is replaced with the node to the right
			else
				parent.rightNode = thisNode.leftNode;
		//If neither of the child nodes are null
		} else {
			//Node replacement
			Node replacement = replace(thisNode);
			
			//If the node is the root then it's replaced with it's new replacement 
			if (thisNode == root)
				root = replacement;
			else if (isLeftNode)
				//if the node is a left node, replace it with it's new replacement
				parent.leftNode = replacement;
			else
				//if the node is a right node, replace it with it's new replacement
				parent.rightNode = replacement;
			//replacing the replacements left node with this nodes left node
			replacement.leftNode = thisNode.leftNode;
		}
		return true;
	}

	//Method used to replace a node
	private Node replace(Node nodeToReplace) {
		//Creating the parent and the replacement
		Node replacementParent = nodeToReplace;
		Node replacement = nodeToReplace;

		//Setting this node to the nodes right node
		Node thisNode = nodeToReplace.rightNode;

		//While the node is not null, it loops over the nodes to find a replacement
		while (thisNode != null) {
			//The parent is replaced by the replacement
			replacementParent = replacement;
			//The replacement is replaced by the current node
			replacement = thisNode;
			//And the current node is replaced by the node to the left
			thisNode = thisNode.leftNode;
		}

		//If the replacement is not the right node of the node we're going to replace
		if (replacement != nodeToReplace.rightNode) {
			//The replacement parent's left node is replaced by the replacement's right node
			replacementParent.leftNode = replacement.rightNode;
			//the replacement's right node is replaced with the node we're replacing's right node
			replacement.rightNode = nodeToReplace.rightNode;
		}
		//Returns the replacement node
		return replacement;
	}

	//Method used to search the tree for a node
	public void search(String data) {
		//Creates a node to hold the current node
		Node thisNode = root;
		//Counter to know how many elements were inspected
		int counter = 0;

		//While this nodes name does not equal the data entered
		while (!thisNode.name.equals(data)) {
			//Finding the direction of the node we're looking for by comparing the values of the data
			if (data.compareTo(thisNode.name) < 0) {
				//Moving the search left
				counter++;
				thisNode = thisNode.leftNode;
				//If we search and find nothing we display that to the user with the counter
				if (thisNode == null) {
					System.out.println("Inspected " + counter + " elements\n'" + data + "' not in tree");
					return;
				}
			} else if (data.compareTo(thisNode.name) > 0) {
				//Moving the search right
				counter++;
				thisNode = thisNode.rightNode;
				//If we search and find nothing we display that to the user with the counter
				if (thisNode == null) {
					System.out.println("Inspected " + counter + " elements\n'" + data + "' not in tree");
					return;
				}
			}
		}
		
		//If the node is found we display that to the user with the amount of elements inspected
		System.out.println("Inspected " + counter + " elements\n'" + data + "' located");
	}

}

//Node class
class Node {
	String name;
	Node leftNode;
	Node rightNode;

	Node(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}