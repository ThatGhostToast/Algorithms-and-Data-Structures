package app;

import java.util.Arrays;

/**
 * @author Zac Almas
 */
public class MyString {

	private char[] charArray; // An Array of characters provided by the user
	private int curr_length; // Length of the array

	// Default Constructor
	public MyString() {
		this.charArray = null;
		this.curr_length = 0;
	}

	//Initialized Constructor
	public MyString(String inputString) {
		this.charArray = inputString.toCharArray();
		this.curr_length = this.charArray.length;
	}

	//Copy constructor
	MyString(MyString copy) {
		charArray = copy.charArray;
    	curr_length = copy.curr_length;
	}

	//Method to return the length of the array
	public int length() {
		return charArray.length;
	}
	
	//Method that handles additional memory for the String
	private void ensureCapacity(){
		//If the array is too small this will make a bigger array
 		if(curr_length < this.charArray.length){
 			char[] biggerCharArray = new char[2 * this.charArray.length];
 			for(int i = 0; i < this.charArray.length; i++){
 				biggerCharArray[i] = this.charArray[i];
 				this.charArray = biggerCharArray;
 			}
 		}
 	}

	@Override
	public String toString() {
		return  "String Array: " + Arrays.toString(charArray) +
				", Length: " + curr_length;
	}

	//Method used to concat the two strings together
	public MyString concatString(MyString otherString) {
		ensureCapacity();
		//Using a string builder to create our string
		StringBuilder sb = new StringBuilder();
		sb.append(this.charArray);
		sb.append(otherString.charArray);
		//Putting the new string into a new object to return to the user
		MyString concatObject = new MyString(sb.toString());
		return concatObject;
	}

	//Method used to check if one string equals another
	public boolean equals(MyString otherString) {
		return Arrays.equals(this.charArray, otherString.charArray);
	}

	//Compares two strings into alphabetical order
	public int compareTo(MyString otherString) {
		//CompareTo returns a number. If the number is 0 then the two strings are the same
		int returnValue = 0;
    
		//Getting the array length to test by
		int indexLength = compareArrayLength(this.charArray.length, otherString.charArray.length);
		//Testing each element in the array
		for (int i = 0; i < indexLength; i++) {
			//If the element in the array is the same it will return 0
			if (this.charArray[i] == otherString.charArray[i]) {
				returnValue = 0;
				//If the element is lower alphabetically it will return -1
			} else if (this.charArray[i] < otherString.charArray[i]) {
				return returnValue = -1;
				//If the element is higher alphabetically it will return 1
			} else if (this.charArray[i] > otherString.charArray[i]) {
				return returnValue = 1;
			}
		}
		return returnValue;
	}

	//Method used to find the shorter array and return the shorter length
	private int compareArrayLength(int originalArray, int secondArray){
		if(originalArray > secondArray){
			return secondArray;
		}
		return originalArray;
	}

	//Method used to get a character out of the array
	public char get(int key) {
		//Using a string builder to turn the char array into a string
		StringBuilder sb = new StringBuilder();
		sb.append(this.charArray);
		String myString = sb.toString();
		//Getting the specific character out of the string
		char foundChar = myString.charAt(key);
		return foundChar;
	}

	//Method used to turn the array into uppercase 
	public MyString toUpper() {
		//Using String builder to build the string in upper case 
		StringBuilder sb = new StringBuilder();
		sb.append(this.charArray);
		this.charArray = sb.toString().toUpperCase().toCharArray();
		return this;
	}

	//Method used to turn the array into lowercase
	public MyString toLower() {
		//Using String builder to build the string in lower case 
		StringBuilder sb = new StringBuilder();
		sb.append(this.charArray);
		this.charArray = sb.toString().toLowerCase().toCharArray();
		return this;
	}

	//Method to create a substring that removes a piece of the string
 	public MyString subString(int index) {
 		//Turning the char array into the substring
 		String subString = new String(this.charArray, index, this.charArray.length - (index));
 		//Updating the variables and returning
 		this.charArray = subString.toCharArray();
 		this.curr_length = this.charArray.length;
 		return this;
 	}

 	//Method to create a substring that removes two pieces of the string
 	public MyString subString(int start, int end) {
 		//Turning the char array into the substring
 		String subString = new String(this.charArray, start, end);
 		//Updating the variables and returning
 		this.charArray = subString.toCharArray();
 		this.curr_length = this.charArray.length;
 		return this;
 	}

 	//Method used to find a character in the array and return the position of the first time it appears
 	public int indexOf(MyString stringToSearch, char characterSearched) {
 		//Using a string builder to remake our char array into a string
 		StringBuilder sb = new StringBuilder();
 	    sb.append(stringToSearch.charArray);
 	    String newString = sb.toString();
 	    //Making a variable to hold the index of where the character was found
 	    int i = 0;
 	    //Looping over every character in the array until a match is found or there isnt anything left
 	    for (int x = 0; x < stringToSearch.charArray.length; x++) {
 	    	if (newString.charAt(x) != characterSearched) {
 	    		i = -1;
 	    	} else if (newString.charAt(x) == characterSearched) {
 	    		i = x;
 	    		return i;
 	    	}

 	    }
 	    return i;
 	  }

 	  //Method used to find a character in the array and return the position of the last time it appears
 	  public int lastIndexOf(MyString stringToSearch, char characterSearched) {
 		  int i = indexOf(stringToSearch, characterSearched);
 		  //Using a string builder to remake our char array into a string
 		  StringBuilder sb = new StringBuilder();
 		  sb.append(stringToSearch.charArray);
 		  String string = sb.toString();
 		  //Loops until the end of the array saving the last time it found the character
 		  for (int x = i; x < stringToSearch.charArray.length; x++) {
 			  if (i == -1) {
 				  //If it wasn't found in indexOf then it wont be found here so it returns
 				  return i;
 			  } else if (string.charAt(x) == string.charAt(i)) {
 				 i = x;
	 	      }
 		  } 
 		  return i;
 	  }


 	//=-=-=-=-=-=-=-=-=-=
 	//Getters and Setters
 	//=-=-=-=-=-=-=-=-=-=
 	public char[] getCharArray() {
 		return charArray;
 	}

 	public void setCharArray(char[] charArray) {
 		this.charArray = charArray;
 	}

 	public int getCurr_length() {
 		return curr_length;
 	}

 	public void setCurr_length(int curr_length) {
 		this.curr_length = curr_length;
 	}

}