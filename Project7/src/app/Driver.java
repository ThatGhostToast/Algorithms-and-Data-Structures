package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Driver {
	public static void main(String[] args) throws IOException {
		ArrayList<String> textFile = readLines("input.txt");
		Hashy hashy = new Hashy();

		//For loop that adds the content from the file into the linked list 
		for (int x = 0; x < textFile.size(); x++) {
			Random rng = new Random();
			hashy.put(textFile.get(x), rng.nextInt(1000));
		}
		
		//Scanner for taking in user input
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter -1 to quit \nEnter -2 to see Hash Table \nEnter word to search for: ");
			String input = scanner.nextLine();
			if (input.equals("-1")) {
				break;
			}
			if (input.equals("-2")) {
				hashy.displayHashTable();
				
			}
			if (hashy.findObject(input) == null && !input.equals("-2")) {
				System.out.println("No items found.  Result returned with 0 elements searched");
			}
		} while (true);

	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method used to assist testing methods by getting the file input
	public static ArrayList<String> readLines(String fileName) {
		try {
			// Getting the file into the program
			File file = new File(fileName);
			Scanner scn = new Scanner(file);
			// Putting the contents of the file into a list
			ArrayList<String> fileContent = new ArrayList<String>();
			while (scn.hasNextLine()) {
				fileContent.add(scn.nextLine());
			}

			// Returning the List
			return fileContent;
		} catch (FileNotFoundException e) {
			// If the file isn't found this will throw
			System.out.println("ERROR: FILE NOT FOUND");
			e.printStackTrace();
			return null;
		}
	}
}
