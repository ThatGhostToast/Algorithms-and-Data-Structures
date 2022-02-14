import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * provided file for DLinkedList Assignment 
 *
 * @author Zac Almas
 */
public class DLinkedList<T extends Comparable<T>> {

    @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws FileNotFoundException {

        DLinkedList<String> lst1 = new DLinkedList<>();
        DLinkedList<String> lst2 = new DLinkedList<>();        

        Scanner fin = new Scanner(new File("text1.in"));
        String str;

        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str); 
            lst1.insertOrderUnique(str);           
        }
        fin.close();

        fin = new Scanner(new File("text2.in"));
        while (fin.hasNext()) {
            str = fin.next();
            str = cleanUp(str);
            lst2.insertOrderUnique(str);           
        }

        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        
        System.out.println("\nRemoving  'goodbye' from list 2:");
        lst2.remove("goodbye");
        System.out.println("List 2:  " + lst2);
        
        
        DLinkedList combined = lst1.merge(lst2);
        
        System.out.println("\nAFTER MERGE");
        System.out.println("List 1:  " + lst1);
        System.out.println("List 2:  " + lst2);
        System.out.println("\n" + combined);
    }

    /**
     * ASSIGNED
     * @param str
     * @return str in all lower case with LEADING and TRAILING non-alpha
     * chars removed
     */
    public static String cleanUp(String str) {
    	//Using replaceAll to remove special characters
    	return str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    }

    //inner DNode class:  PROVIDED
    private class DNode {
        private DNode next, prev;
        private T data;

        private DNode(T val) {
            this.data = val;
            next = prev = this;
        }
    }

    //DLinkedList fields:  PROVIDED
    private DNode header;

    //create an empty list:  PROVIDED
    public DLinkedList() {
        header = new DNode(null);
    }

    /**
     * PROVIDED add
     *
     * @param item return ref to newly inserted node
     */
    public DNode add(T item) {
        //make a new node
        DNode newNode = new DNode(item);
        //update newNode
        newNode.prev = header;
        newNode.next = header.next;
        //update surrounding nodes
        header.next.prev = newNode;
        header.next = newNode;
        return newNode;
    }

    //PROVIDED
    public String toString() {
        String str = "[";
        DNode curr = header.next;
        while (curr != header) {
            str += curr.data + " ";
            curr = curr.next;
        }
        str = str.substring(0, str.length() - 1);
        return str + "]";
    }

    /**
     * ASSIGNED
     * remove val from the list
     *
     * @param val
     * @return true if successful, false otherwise
     */
    public boolean remove(T val) {
    	// Setting the current node
    	DNode current = header.next;
    	// While loop that finds the specific word in the list
        while (current != header && current.data.compareTo(val) != 0) {
          current = current.next;
        }
        if(current == header){
          return false;
        }
        //Once the word is found these statements get it removed from the list
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = current;
        current.prev = current;
        return true;
    }

    /**
     * PROVIDED
     *
     * @param item
     */
    public void insertOrder(T item) {
    	add(item);  	
    	//Sort the list after inserting the one element
    	DNode current = null, index = null;  
        T tempItem;   
        //Check whether list is empty  
        if(header.next == null) {  
            return;  
        }  
        else {  
            //Current will point to head  
            for(current = header.next; current.next != null && current.data!= null; current = current.next) { 
                //Index will point to node next to current  
                for(index = current.next; index != null && index.data!= null; index = index.next) {  
                    //If current's data is greater than index's data, swap the data of current and index
                    if(current.data.compareTo(index.data) > 0) {  
                        tempItem = current.data;  
                        current.data = index.data;  
                        index.data = tempItem;  
                    }  
                	
                }  
            }  
        }
    }

    /**
     * ASSIGNED
     *
     * @param item
     */
    public boolean insertOrderUnique(T item) {
    	//Getting the current node
    	DNode current = header.next;
    	//Finds where the item would fit in the list alphabetically
        while (current != header && current.data.compareTo(item) < 0) {
          current = current.next;
        }
        if(current != header && current.data.compareTo(item) == 0){
          return false;
        }
        //Makes a new node out of the item
        DNode newNode = new DNode(item);
        //Statements used to add the item into the list
        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
        return true;
    }

    /**
     * ASSIGNED
     * PRE:  this and rhs are sorted lists
     * @param rhs
     * @return list that contains this and rhs merged into a sorted list
     * POST:  returned list will not contain duplicates
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public DLinkedList merge(DLinkedList rhs) {
    	//DLinked List object that holds the merged lists
    	DLinkedList result = new DLinkedList();
    	//Getting the current node
        DNode current = this.header.prev;
        //While loop that adds the data from the current node
        while (current != this.header){
          result.add(current.data);
          current = current.prev;
        }
        //Changing the current node 
        current = rhs.header.next;
        
        //While loop to add data from the second node
        while (current != rhs.header){
          result.insertOrderUnique(current.data);
          current = current.next;
        }
        return result;
    }

}