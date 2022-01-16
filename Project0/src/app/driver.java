package app;

import java.io.*;
import java.util.*;

/**
 * @author Zac Almas
 */
public class driver {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("test.txt"); // Getting the text file
		Scanner scn = new Scanner(file); // Reading in the text file
		Scanner userIn = new Scanner(System.in); // Scanner for taking in user input
		String[] wordArray = new String[50]; // Array used to store the words from the text file
		int increment = 0; // Counter tool
		
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		// Adding the words to the array
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		while (scn.hasNextLine())
		{
			//If there's no more space in the array the loop will break
			if (increment == wordArray.length)
				break;
			
			//using nextLine to add the contents of the file into the array
			wordArray[increment] = scn.nextLine();
			increment++;
		}		
		
		//Displaying the unsorted array.
		print(wordArray);
		
		System.out.println();
		
		//Sorting the array
		wordArray = sort(wordArray);
		
		//Printing the array
		print(wordArray);
		
		//Space out the terminal a bit
		for(int x = 0; x < 3; x++)
			System.out.println();
		
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		//Taking in user input, and using it to find a word
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		String input = "";
		while (!input.equals("0"))
		{
			input = "";
			
			//Prompt the user
			System.out.println("What word do you want to find? 0 to stop.");
			input = userIn.nextLine();	
			
			if (!input.equals("0"))
				search(wordArray, input);
				
		}	
		
		//Closing the scanners
		scn.close();
		userIn.close();
	}
	
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	//Method used to search the array for the key entered
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static void search(String[] wordArray, String input)
	{
		//Variables used to help us search
		int bottomHalf = 0;
		int topHalf = wordArray.length - 1;
		boolean itemFound = false;
		
		//While loop used to search the array
		while (bottomHalf <= topHalf)
		{
			int mid = bottomHalf + (topHalf - bottomHalf) / 2;
			//If the word is found it is displayed to the user and the loop breaks
			if (wordArray[mid].equals(input))
			{
				System.out.println(input + " is in the text.");
				itemFound = true;
				break;
			}
			if (wordArray[mid].compareTo(input) < 0)
			{
				bottomHalf = mid + 1;
			} else {
				topHalf = mid - 1;
			}
		}
		if (!itemFound)
		{
			//If the word isn't in the array then this will be displayed
			System.out.println(input + " is not in the text.");
		}
	}
	
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method with a for loop to display the sorted array
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static void print(String[] words)
	{
		//Looping over the length of the array and printing the element
		for (int x = 0; x < words.length; x++)
		{
			System.out.println(words[x]);
		}
	}
	
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	//Method used to sort the array
	//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static String[] sort(String[] wordArray)
	{
		String temp; // Variable used to temporarily hold a word from the array
		for (int x = 0; x < wordArray.length; x++)
		{
			for (int y = x + 1; y < wordArray.length; y++)
			{
				if (wordArray[y].compareTo(wordArray[x]) < 0) {
					temp = wordArray[x];
					wordArray[x] = wordArray[y];
					wordArray[y] = temp;
				}
			}
		}
		//Returns the sorted array
		return wordArray;
	}
}
