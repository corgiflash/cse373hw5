import java.util.*;
import java.io.*;

/**
 * Testing version of FindPaths that reads in multiple test files.
 * 
 * The first test file is normal and has no dead ends or errors
 * The second test file has a dead end node, D.
 * The third test file should return errors when a graph is made
 * 
 */

public class TestGraph {
	public static void main(String[] args) {
		
		// A normal file
		MyGraph g0 = readGraph("TESTVERTEX","TESTEDGE");
		
		// File with a dead end node
		MyGraph g1 = readGraph("TESTVERTEX2","TESTEDGE2");
		
		// Check for errors
		try {
			MyGraph g2 = readGraph("TESTVERTEX3","TESTEDGE3");
		} catch (RuntimeException myException) {
			System.out.println("Got errors reading test 3, Good!");
		}
		
		// Create list of graphs
		ArrayList<MyGraph> myGraphs = new ArrayList<MyGraph>();
		myGraphs.add(g0);
		myGraphs.add(g1);
		
		// Run FindPaths for each graph
		for (MyGraph g : myGraphs) {
			@SuppressWarnings("resource")
			Scanner console = new Scanner(System.in);
			Collection<Vertex> v = g.vertices();
	                Collection<Edge> e = g.edges();
			System.out.println("Vertices are "+v);
			System.out.println("Edges are "+e);
			
			// Allow exiting and moving on to the next test file
			String input = "";
			while(!input.equals("exit")) {
				System.out.print("Start vertex? (exit for next test/quit) ");
				input = console.nextLine();
				if (!input.equals("exit")) {
					Vertex a = new Vertex(input);
					if(!v.contains(a)) {
						System.out.println("no such vertex");
						System.exit(0);
					}
					
					System.out.print("Destination vertex? ");
					Vertex b = new Vertex(console.nextLine());
					if(!v.contains(b)) {
						System.out.println("no such vertex");
						System.exit(1);
					}
					
					// Get the shortest path
					Path shortestPath = g.shortestPath(a, b);
					
					// If it is impossible, print an error
					if (shortestPath == null) {
						System.out.println("does not exist");
						
					// Print the path and cost
					} else {
						// Initial path information
						System.out.println("Shortest path from "+a.getLabel() + " to "+b.getLabel()+":");
						
						// Only one thing to print if start and end are equal
						if (a.getLabel().equals(b.getLabel())) {
							System.out.print(a.getLabel());
							
						// Print through path list backwards to get correct order in output
						} else {
							System.out.print(a.getLabel());
							for (int i = shortestPath.vertices.size() - 1; i >= 0; i--) {
								Vertex myVertex = shortestPath.vertices.get(i);
								
								System.out.print(" " + myVertex.getLabel());
							}
						}
						// Print the cost of the path
						System.out.println();
						System.out.println(shortestPath.cost);
					}
				}
			}
		}
	}

	public static MyGraph readGraph(String f1, String f2) {
		Scanner s = null;
		try {
			s = new Scanner(new File(f1));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f1);
			System.exit(2);
		}

		Collection<Vertex> v = new ArrayList<Vertex>();
		while(s.hasNext())
			v.add(new Vertex(s.next()));

		try {
			s = new Scanner(new File(f2));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: "+f2);
			System.exit(2);
		}

		Collection<Edge> e = new ArrayList<Edge>();
		while(s.hasNext()) {
			try {
				Vertex a = new Vertex(s.next());
				Vertex b = new Vertex(s.next());
				int w = s.nextInt();
				e.add(new Edge(a,b,w));
			} catch (NoSuchElementException e2) {
				System.err.println("EDGE FILE FORMAT INCORRECT");
				System.exit(3);
			}
		}

		return new MyGraph(v,e);
	}
}
