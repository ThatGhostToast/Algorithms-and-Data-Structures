package app;

import java.util.Scanner;

/**
 * @author Zac Almas
 */
public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the first string:");
		MyString firstString = new MyString(input.nextLine());

		System.out.println("Enter the second string:");
		MyString secondString = new MyString(input.nextLine());

		MyString copiedString = new MyString(secondString);

		System.out.println("Input starting index:");
		int startIndex = input.nextInt();
		input.nextLine();

		System.out.println("Input ending index:");
		int endIndex = input.nextInt();
		input.nextLine();

		//Testing the length method
		System.out.println("The length of the first string is: " + firstString.length());

		//Testing the toString method
		System.out.println("First " + firstString);
		System.out.println("Second " + secondString);
		System.out.println("Copied " + copiedString);

		//Testing the concat method
		System.out.println("My Concat string is: " + firstString.concatString(secondString));

		//Testing the equals method
		System.out.println("Does String 1 equal String 2? " + firstString.equals(secondString));

		//Testing the compareTo method
		int comparedValue = firstString.compareTo(secondString);
		if(comparedValue == 0){
			System.out.println(firstString + " & " + secondString + " are the same, compareTo returned a " + comparedValue);
		}else if(comparedValue == -1){
			System.out.println(firstString + " is lower in the alphabet than " + secondString + ", compareTo returned: " + comparedValue);
		}else {
			System.out.println(firstString + " is higher in the alphabet than " + secondString + ", compareTo returned: " + comparedValue);
		}

		//Testing get method
		System.out.println("The character at index of " + startIndex + " in the first string is " + firstString.get(startIndex));

		//Testing toUpper method
		System.out.println("String 1 turned into uppercase: " + firstString.toUpper());

		//Testing toLower method
		System.out.println("String 2 turned into lowercase: " + secondString.toLower());

		//Testing subString with the  starting index
		System.out.println("With the starting index of " + startIndex + ", the remaining letters in the string are: " + secondString.subString(startIndex));

		//Testing subString with a start and end index
		System.out.println("With the starting index of " + startIndex + " and an ending index of "
           	+ endIndex + ", the remaining letters in the string are: " + secondString.subString(startIndex, endIndex));

		//Testing indexOf method
		int indexOfChar = copiedString.indexOf(copiedString, 'p');
		if(indexOfChar == -1){
			System.out.println("The character 'p' was not found in: " + copiedString);
		}else{
			System.out.println("The character 'p' was first found at: " + indexOfChar + " in " + copiedString);
		}
		
		//Testing lastIndexOf method
	    int lastIndexOfChar = copiedString.lastIndexOf(copiedString, 'p');
	    if(lastIndexOfChar == -1){
	    	System.out.println("The character 'p' was not found in: " + copiedString);
	    }else{
	    	System.out.println("The character 'p' was last found at: " + lastIndexOfChar + " in " + copiedString);
	    }
	}
}