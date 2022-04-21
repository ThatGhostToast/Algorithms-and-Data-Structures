package app;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Graph graph = new Graph();
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Welcome to my graph project.\n=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\nHere's the Graph:");
		graph.createGraph();
		graph.display();
		
		boolean loop = true;
		while (loop)
		{
			System.out.println("\n=- What would you like to do? -=\n 1 | Get distance between two verticies\n 2 | Get a vericies neighbors\n 3 | Quit");
			
			String userin = scn.next();
		
			if (userin.equals("1") || userin.equals("2") || userin.equals("3"))
			{
				switch (userin)
				{
				case "1":
					System.out.println("Which node is your starting point?");
					int start = Integer.parseInt(scn.next());
					System.out.println("Which node is the end point?");
					int end = Integer.parseInt(scn.next());
					System.out.println("The distance between those nodes is: " + graph.getDistance(start, end));
					break;
					
				case "2":
					System.out.println("Get the neighbors of which node?");
					userin = scn.next();
					System.out.println(graph.getNeighbors(Integer.parseInt(userin)));
					break;
					
				case "3":
					loop = false;
					break;
				}
			} else {
				System.out.println("Please input a valid number");
			}
		}
		
		scn.close();
		
	}
}
