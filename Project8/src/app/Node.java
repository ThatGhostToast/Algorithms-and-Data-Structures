package app;

public class Node {
	protected int location;
	protected int dest;
	protected int dist;
	
	@Override
	public String toString()
	{
		return "=-Node: Destination = " + dest + ", Distance = " + dist + "-=";
	}
}
