import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//starter code for MazeSolver
//CST-201
//PROVIDED
public class Driver {
	
	public static int maxRow;
	public static int maxCol;
	public static MyStack<MazeCell> pathStack = new MyStack<MazeCell>();
	public static MyStack<MazeCell> stack = new MyStack<MazeCell>();

	/**
	 * 
	 * @param start
	 * @param end   
	 * 
	 * find a path through the maze if found, output the path from
	 * start to end if not path exists, output a message stating so
	 */
	// implement this method
	public static void depthFirst(MazeCell[][] cells, MazeCell start, MazeCell end) {		
		
		//Getting the current position
		int i = start.getRow();
		int j = start.getCol();
		//Boolean for if a route was found
		boolean found = true;
		
		//Pushing the start position to our stack
		stack.push(start);
		
		//Setting the current position
		MazeCell current = stack.top();

		//If tower that runs until a path is found or it discovers no path available
		if (cells[i][j] != cells[end.getRow()][end.getCol()]) {
			//If every direction has been checked and the stack size is 1 then there is no exit
			if (stack.top().getDirection() == 4 && stack.size() == 1)
			{
				System.out.println("No path found.");
				found = false;
			} else {
				//=-=-=-=-=-=-=-=
				//Checking north
				//=-=-=-=-=-=-=-=
				if (current.getDirection() == 0)
				{
					//Checking to make sure the next move is valid
					if (isValid(current.getRow() - 1, current.getCol()) && cells[current.getRow() - 1][current.getCol()].unVisited())
					{
						current.visit();
						var temp = current;
						current = cells[current.getRow() - 1][current.getCol()];
						//Checking if the algorithm ran into a wall (and if so it backs up and rotates)
						if (isWall(current.getRow(), current.getCol()))
						{
							current = temp;
							current.advanceDirection();
						}
						pathStack.push(current);
						depthFirst(cells, current, end);
					} else {
						current.advanceDirection();
						stack.pop();
						depthFirst(cells, current, end);
					}
				//=-=-=-=-=-=-=
				//Checking east
				//=-=-=-=-=-=-=
				} else if (current.getDirection() == 1){
					//Checking to make sure the next move is valid
					if (isValid(current.getRow(), current.getCol() + 1) && cells[current.getRow()][current.getCol() + 1].unVisited())
					{
						current.visit();
						var temp = current;
						current = cells[current.getRow()][current.getCol() + 1];
						//Checking if the algorithm ran into a wall (and if so it backs up and rotates)
						if (isWall(current.getRow(), current.getCol()))
						{
							current = temp;
							current.advanceDirection();
						}
						pathStack.push(current);
						depthFirst(cells, current, end);
					} else {
						current.advanceDirection();
						stack.pop();
						depthFirst(cells, current, end);
					}
				//=-=-=-=-=-=-=-=
				//Checking south
				//=-=-=-=-=-=-=-=
				} else if (current.getDirection() == 2){
					//Checking to make sure the next move is valid
					if (isValid(current.getRow() + 1, current.getCol()) && cells[current.getRow() + 1][current.getCol()].unVisited())
					{
						current.visit();
						var temp = current;
						current = cells[current.getRow() + 1][current.getCol()];
						//Checking if the algorithm ran into a wall (and if so it backs up and rotates)
						if (isWall(current.getRow(), current.getCol()))
						{
							current = temp;
							current.advanceDirection();
						}
						pathStack.push(current);
						depthFirst(cells, current, end);
					} else {
						current.advanceDirection();
						stack.pop();
						depthFirst(cells, current, end);
					}
				//=-=-=-=-=-=-=
				//Checking west
				//=-=-=-=-=-=-=
				} else if (current.getDirection() == 3){
					//Checking to make sure the next move is valid
					if (isValid(current.getRow(), current.getCol() - 1) && cells[current.getRow()][current.getCol() - 1].unVisited())
					{
						current.visit();
						var temp = current;
						current = cells[current.getRow()][current.getCol() - 1];
						//Checking if the algorithm ran into a wall (and if so it backs up and rotates)
						if (isWall(current.getRow(), current.getCol()))
						{
							current = temp;
							current.advanceDirection();
						}
						pathStack.push(current);
						depthFirst(cells, current, end);
					} else {
						current.advanceDirection();
						stack.pop();
						depthFirst(cells, current, end);
					}
				//=-=-=-=-=-=-=-=-=-=-=-=
				//Checked all directions
				//=-=-=-=-=-=-=-=-=-=-=-=
				} else if (current.getDirection() == 4)
				{
					stack.pop();
					current = stack.pop();
					stack.pop();
				}
			}
		}
		
		//Displaying the path the algorithm took to find the exit
		if (found)
		{
			//Creating an array so that we can display the points backwards (This way they are in order)
			String[] path = new String[pathStack.size()];
			System.out.println("Path found!");
			
			//Populating the array 
			int count = 0;
			while (!pathStack.empty())
			{
				path[count] = pathStack.top().toString();
				count++;
				pathStack.pop();
			}
			
			//Removing any duplicates
			int newSize = path.length;
			for (int x = 0; x < path.length; x++)
			{
				for (int y = 0; y < path.length; y++)
				{
					if (x != y)
					{
						if (path[x] != null)
						{
							if (path[x].equals(path[y]))
							{
								path[y] = null;
								newSize--;
							}		
						}
					}
				}
			}
			
			//Setting the new array without the duplicates
			String[] finalPath = new String[newSize];
			int counter = 0;
			for (int x = 0; x < path.length; x++)
			{
				if(path[x] != null)
				{
					finalPath[counter] = path[x];
					counter++;
				}
			}
			
			//Displaying the array
			for(int x = finalPath.length - 1; x > 0; x--)
			{
				System.out.println(finalPath[x]);
			}
			System.out.println(end.toString());
		}
		System.exit(0);
	}
	
	//Method to make sure the algorithm stays inside the maze
	public static boolean isValid(int r, int c)
	{
		if(r >= 0 && r < maxRow && c >= 0 && c < maxCol)
		{
			return true;
		} 
		return false;
	}
	
	//Checks if the algorithm ran into a wall
	public static boolean isWall(int r, int c)
	{
		if(r == -1 || c == -1)
		{
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {

		// create the Maze from the file
		Scanner fin = new Scanner(new File("Maze.in"));
		// read in the rows and cols
		int rows = fin.nextInt();
		int cols = fin.nextInt();
		
		maxRow = rows;
		maxCol = cols;

		// create the maze
		int[][] grid = new int[rows][cols];

		// read in the data from the file to populate
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = fin.nextInt();
			}
		}

		// look at it
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 3)
					System.out.print("S ");
				else if (grid[i][j] == 4)
					System.out.print("E ");
				else
					System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}

		// make a 2-d array of cells
		MazeCell[][] cells = new MazeCell[rows][cols];

		// populate with MazeCell obj - default obj for walls

		MazeCell start = new MazeCell(), end = new MazeCell();

		// iterate over the grid, make cells and set coordinates
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// make a new cell
				cells[i][j] = new MazeCell();
				// if it isn't a wall, set the coordinates
				if (grid[i][j] != 0) {
					cells[i][j].setCoordinates(i, j);
					// look for the start and end cells
					if (grid[i][j] == 3)
						start = cells[i][j];
					if (grid[i][j] == 4)
						end = cells[i][j];

				}

			}
		}

		// testing
		System.out.println("start:" + start + " end:" + end);

		pathStack.push(start);
		// solve it!
		depthFirst(cells, start, end);

	}
}

/*
 *
 * Provided starter code MazeCell class DO NOT CHANGE THIS CLASS
 *
 * models an open cell in a maze each cell knows its coordinates (row, col),
 * direction keeps track of the next unchecked neighbor\ cell is considered
 * 'visited' once processing moves to a neighboring cell the visited variable is
 * necessary so that a cell is not eligible for visits from the cell just
 * visited
 *
 */

class MazeCell {
	private int row, col;
	// direction to check next
	// 0: north, 1: east, 2: south, 3: west, 4: complete
	private int direction;
	private boolean visited;

	// set row and col with r and c
	public MazeCell(int r, int c) {
		row = r;
		col = c;
		direction = 0;
		visited = false;
	}

	// no-arg constructor
	public MazeCell() {
		row = col = -1;
		direction = 0;
		visited = false;
	}

	// copy constructor
	MazeCell(MazeCell rhs) {
		this.row = rhs.row;
		this.col = rhs.col;
		this.direction = rhs.direction;
		this.visited = rhs.visited;
	}

	public int getDirection() {
		return direction;
	}

	// update direction. if direction is 4, mark cell as visited
	public void advanceDirection() {
		direction++;
		if (direction == 4)
			visited = true;
	}

	// change row and col to r and c
	public void setCoordinates(int r, int c) {
		row = r;
		col = c;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazeCell other = (MazeCell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	// set visited status to true
	public void visit() {
		visited = true;
	}

	// reset visited status
	public void reset() {
		visited = false;
	}

	// return true if this cell is unvisited
	public boolean unVisited() {
		return !visited;
	}

	// may be useful for testing, return string representation of cell
	public String toString() {
		return "(" + row + "," + col + ")";
	}
} // end of MazeCell class

//Stack class
class MyStack<T> {
	public class Node {
		T data;
		Node next;

		public Node(T data) {
			this.data = data;
			next = null;
		}

		public Node(Node node) {
			data = node.data;
			if (node.next == null) {
				next = null;
			} else {
				next = new Node(node.next);
			}
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public void setValue(T value) {
			data = value;
		}

		public T getValue() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public boolean hasNext() {
			return next != null;
		}
	}

	Node head;
	Node tail;
	int nodeCount;

	public MyStack() {
		nodeCount = 0;
		head = null;
		tail = null;
	}

	// copy constructor for listO(n)
	public MyStack(MyStack<T> input) {
		nodeCount = input.nodeCount;
		head = new Node(input.head);
		tail = new Node(input.tail);
	}

	public boolean empty() {
		return head == null;
	}

	public void push(T element) {

		nodeCount++;
		Node node = new Node(element);
		// set node to head
		node.setNext(head);
		head = node;
	}

	public T pop() {
		if (head == null)
			return null;
		nodeCount--;
		T output = head.getValue();
		head = head.getNext();
		return output;
	}

	public T top() {
		return head.getValue();
	}

	public int size() {
		return nodeCount;
	}

	@Override
	public String toString() {
		return "MyStack [head=" + head.getValue() + ", tail=" + tail + ", nodeCount=" + nodeCount + "]";
	}

}
