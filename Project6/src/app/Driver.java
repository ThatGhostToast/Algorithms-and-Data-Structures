package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Driver {
	public static void main(String[] args) throws IOException {
		BST bst = new BST();

		ArrayList<String> textFile = readLines("input.txt");
		System.out.println(textFile.toString());

		for (int i = 0; i <= textFile.size() - 1; i++) {
			bst.insert(textFile.get(i));
		}

		bst.inOrderTraveral(bst.root);
		Scanner scn = new Scanner(System.in);
		while (true) {
			System.out.println("Enter string, or enter -1 to quit: ");
			String input = scn.nextLine();
			if (input.equals("-1")) {
				break;
			}
			bst.search(input);
		}

		System.out.println("=-=-=-=-=-=-=-=-=-=-=\n= Removing a String =\n=-=-=-=-=-=-=-=-=-=-=");
		while (true) {
			System.out.println("Enter string to remove, or enter -1 to quit: ");
			String removedString = scn.nextLine();
			if (removedString.equals("-1")) {
				break;
			}
			boolean isRemoved = bst.delete(removedString);
			if (isRemoved) {
				bst.inOrderTraveral(bst.root);
			} else {
				System.out.println(removedString + " is not in the tree, so nothing was removed.");
				bst.inOrderTraveral(bst.root);
			}
		}
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
