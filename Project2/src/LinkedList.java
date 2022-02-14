/*
 * Zac Almas
 * 1/30/22
 * This is my own code.
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class LinkedList<T> {
	//Head of the list
	private Node head;
	//Back of the list
	private Node tail;
	//Size of the list
	private int listSize;

	//Default constructor
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.listSize = 0;
	}

	//Parameterized constructor
	public LinkedList(Node head, Node tail) {
		this.head = head;
	    this.tail = tail;
	}

	//Copy constructor
	public LinkedList(LinkedList singlyLinkedList) {
	    head = singlyLinkedList.head;
	    tail = singlyLinkedList.tail;
	}

	//Method to access the first element in the list
	public T front() {
		//If there is an element in the head of the list then the method
		//will return the data in the head.
	    if (head != null) {
	      return (T) this.head.getData();
	    }
	    return null;
	}

	//Method to access the last element in the list
	public T back() {
		//If there is an element in the tail of the list then the method
		//will return the data in the head.
	    if (tail != null) {
	      return (T) this.tail.getData();
	    }
	    return null;
	}

	//Method to insert data into the list
	public void insert(T data) {
		//Creating a new node to hold our data.
	    Node node = new Node(data);
	    //If the head is empty that means the list is empty and the first element
	    //should be stored here
	    if (head == null) {
	      head = node;
	      //Adding one to the list size
	      listSize++;
	      return;
	    }
	    
	    //If the tail is empty then the new data will be stored there.
	    if (tail == null) {
	      tail = node;
	      //Putting proper data into the next and before variables.
	      head.next = node;
	      tail.before = head;
	      //Adding one to the list size
	      listSize++;
	      return;
	    }
	    
	    //Puts the new data into the space after the tail to increase the size of the list
	    tail.next = node;
	    //Making sure the data is correct in the before variable.
	    node.before = tail;
	    //Setting the tail to the new data since it took the tail spot in the list
	    tail = node;
	    //Adding one to the list size
	    listSize++;
	}

	//Method to remove the value at the front of the list 
	public void pop_front() {
	    this.head = this.head.next;
	    listSize--;
	}

	//Method to remove the value at the back of the list 
	public void pop_back() {
	    this.tail = this.tail.before;
	    listSize--;
	}

	//Method that returns if the list is empty or not
	public boolean empty() {
		//If nothing is in the head then the list is empty
	    if (head == null) {
	      return true;
	    }
	    return false;
	}

	//Method that returns the size of the list
	public int size() {
	    return listSize;
	}

	//Method used to reverse the order of the list
	public void reverse() {
	    Node thisNode = tail;
	    if (tail == null) {
	      thisNode = head;
	    }
	    
	    while (thisNode != null) {
	      System.out.println(thisNode);
	      thisNode = thisNode.before;
	    }
	}

	//Method used to merge a list with another list
	public void merge(LinkedList secondList) {
		//If else statement that just puts the second list behind the first
	    if (this.tail == null) {
	      this.tail = secondList.head;
	    } else {
	      this.tail.next = secondList.head;
	    }
	    this.tail = secondList.tail;
	}
	
	//==-==-==-==
	//Node class
	//==-==-==-==
	@SuppressWarnings("hiding")
	public class Node<T> {
		public T data;
		public Node next;
		public Node before;
		
		public Node(T data)
		{
			this.data = data;
		}
		
		public T getData() {
			return data;
		}

		public void setData(T data) {
		    this.data = data;
		}

		public Node getNext() {
		    return next;
		}

		public void setNext(Node next) {
		    this.next = next;
		}

		@Override
		public String toString() {
			return "Node data: " + data;
		}
		
	}
}
