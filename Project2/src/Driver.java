/*
 * Zac Almas
 * 1/30/22
 * This is my own code
 */

public class Driver {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList();
	    LinkedList<String> mergeList = new LinkedList();

	    //check if list is empty
	    System.out.println("Is the list empty? " + list.empty() + "\nAdding in data...");

	    //inserting new data into list
	    list.insert("Hello");
	    list.insert("Hey");
	    list.insert("Hi");

	    //create a second list for merging
	    mergeList.insert("Goodbye");
	    mergeList.insert("Bye");
	    mergeList.insert("See you later");

	    //check to see if the list empty after populating
	    System.out.println("\nIs the list empty after adding data? " + list.empty());
	    
	    //Getting the data out of the head and tail of the list
	    System.out.println("Head: " + list.front());
	    System.out.println("Tail: " + list.back());
	    
	    //check size of the list
	    System.out.println("Size of the list after adding data: " + list.size());

	    //reverse the list
	    System.out.println("\nData in the list reversed:");
	    list.reverse();


	    //remove head and replace with next
	    list.pop_front();
	    System.out.println("\nNew Head after removing the front of the list: " + list.front());
	    //check size after removing head
	    System.out.println("New size after removing the front of the list: " + list.size());
	    //remove tail and replace with previous
	    list.pop_back();
	    System.out.println("New Tail after removing the back of the list: " + list.back());
	    //check size after removing tail
	    System.out.println("New size after removing the back of the list: " + list.size());

	    //merge the list and the merge list
	    System.out.println("\nMerging two lists and displaying the new list's head and tail.");
	    list.merge(mergeList);
	    System.out.println("Head: " + list.front());
	    System.out.println("Tail: " + list.back());
	    
	}
}
