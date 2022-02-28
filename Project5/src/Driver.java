import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	//Zac Almas
	//CST-201
	//2/27/22
	//This is my own code
	public static void main(String[] args) throws IOException {
		// Testing slip
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=-  Slip Output  -=\n=-=-=-=-=-=-=-=-=-=");
		// Getting the content of the file
		String[] inputArray = readLines("slipTest.txt");
		// If the file was empty or not found the program will not run this test
		if (inputArray != null) {
			// Loop over every line in the array and check for slip
			for (int x = 0; x < inputArray.length; x++) {
				if (isSlip(inputArray[x])) {
					System.out.println(inputArray[x] + ": Yes");
				} else {
					System.out.println(inputArray[x] + ": No");
				}
			}
		} else {
			System.out.println("An error occurred with the slip file.");
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=- End of Output -=\n=-=-=-=-=-=-=-=-=-=\n");

		// Testing slap
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=-  Slap Output  -=\n=-=-=-=-=-=-=-=-=-=");
		// Getting the content of the file
		inputArray = readLines("slapTest.txt");
		// If the file was empty or not found the program will not run this test
		if (inputArray != null) {
			// Loop over every line in the array and check for slap
			for (int x = 0; x < inputArray.length; x++) {
				if (isSlap(inputArray[x])) {
					System.out.println(inputArray[x] + ": Yes");
				} else {
					System.out.println(inputArray[x] + ": No");
				}
			}
		} else {
			System.out.println("An error occurred with the slap file.");
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=- End of Output -=\n=-=-=-=-=-=-=-=-=-=\n");

		// Testing slop
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=-  Slop Output  -=\n=-=-=-=-=-=-=-=-=-=");
		// Getting the content of the file
		inputArray = readLines("slopTest.txt");
		// If the file was empty or not found the program will not run this test
		if (inputArray != null) {
			// Loop over every line in the array and check for slop
			for (int x = 0; x < inputArray.length; x++) {
				if (isSlop(inputArray[x])) {
					System.out.println(inputArray[x] + ": Yes");
				} else {
					System.out.println(inputArray[x] + ": No");
				}
			}
		} else {
			System.out.println("An error occurred with the slop file.");
		}
		System.out.println("=-=-=-=-=-=-=-=-=-=\n=- End of Output -=\n=-=-=-=-=-=-=-=-=-=\n");
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Method used to assist testing methods by getting the file input
	public static String[] readLines(String fileName) {
		try {
			// Getting the file into the program
			File file = new File(fileName);
			Scanner scn = new Scanner(file);
			// Putting the contents of the file into a list
			List<String> fileContent = new ArrayList<String>();
			while (scn.hasNextLine()) {
				fileContent.add(scn.nextLine());
			}
			// Converting the list to an array
			String[] arrContent = fileContent.toArray(new String[0]);

			// Close the scanner
			scn.close();

			// Returning the array
			return arrContent;
		} catch (FileNotFoundException e) {
			// If the file isn't found this will throw
			System.out.println("ERROR: FILE NOT FOUND");
			e.printStackTrace();
			return null;
		}
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Methods used to check for slip
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static boolean isSlip(String str) {
		// Checking to make sure it's long enough to be a slip
		if (str.length() <= 2) {
			return false;
		}

		// Making sure the first letter follows the rules of a slip
		if (str.charAt(0) != 'D' && str.charAt(0) != 'E') {
			return false;
		}

		// Making sure the next letter follows the rules of a slip
		if (str.charAt(1) != 'F') {
			return false;
		}

		// Checking for a sequence of F's
		int i = 1;
		int n = str.length();
		while (i < n && str.charAt(i) == 'F') {
			i++;
		}

		// Checking to make sure there's a character after the F's
		if (i == n) {
			return false;
		}

		// Checking if it's a G or a slip
		if ((n == i + 1 && str.charAt(i) == 'G') || isSlip(str.substring(i))) {
			return true;
		} else {
			return false;
		}
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Methods used to check for slap
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static boolean isSlap(String str) {
		// Checking to make sure the string is the correct length for a slap
		if (str.length() == 1) {
			return false;
		} else if (str.length() == 2) {
			// Making sure that if the string is 2 characters long, they are the
			// qualifications of a slap
			if (str.charAt(0) == 'A' && str.charAt(1) == 'H') {
				return true;
			} else {
				return false;
			}
			// Checking to make sure the string is the correct length again
		} else if (str.length() == 3 || str.length() == 4) {
			return false;
		} else {
			// If the strings first character is not A then its not a slap
			if (str.charAt(0) != 'A') {
				return false;
			}
			// Getting the length of the string
			int n = str.length();
			// Testing to see if our string is a slap
			// AB followed by a slap followed by a C
			boolean case1 = isSlap(str.substring(2, n - 1)) && (str.charAt(n - 1) == 'C');
			// A followed by a slip followed by a C
			boolean case2 = isSlip(str.substring(1, n - 1)) && (str.charAt(n - 1) == 'C');
			if ((str.charAt(1) == 'B' && case1) || case2) {
				return true;
			} else {
				return false;
			}
		}
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	// Methods used to check for slop
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
	public static boolean isSlop(String str) {
		// Making sure the length of the string entered is long enough to be a slop
		if (str.length() < 5) {
			return false;
		} else {
			//2 character slap check
			if (isSlap(str.substring(0, 2)) && isSlip(str.substring(2))) {
				return true;
			}

			// A slap ends with H if it is two characters long and C if it's longer
			// find the last time C is in the string
			int lastCidex = str.lastIndexOf('C');
			// If there is no C then there is no slap meaning there is no slop
			if (lastCidex == -1) {
				return false;
			} else {
				// Checking if there is a slap followed by a slip based on the last time
				// a C was found in the string
				if (isSlap(str.substring(0, lastCidex + 1)) && isSlip(str.substring(lastCidex + 1))) {
					return true;
				} else {
					return false;
				}
			}

		}
	}

}
