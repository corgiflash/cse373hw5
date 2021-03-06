import java.util.*;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	private Collection<Edge> edges;
	private Collection<Vertex> vertices;
	private Map<Vertex, ArrayList<Vertex>> adjacentVertices;

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 * @throws NegativeWeightException
	 * 			  if an edge has a negative weight
	 * @throws IncorrectVertexException
	 * 			  if an input collection of vertices has a problem
	 * @throws IncorrectEdgeException
	 * 			  if an input collection of edges has a problem
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		// Check for erroneous data first
		for (Edge curEdge : e) {
			// Checking negative weights
			if (curEdge.getWeight() < 0) {
				throw new NegativeWeightException();
				
			// Check for valid destinations and sources
			} else if (!v.contains(curEdge.getDestination()) || !v.contains(curEdge.getSource())) {
				throw new IncorrectVertexException();
				
			// Check if any edges have conflicting weights
			} else {
				for (Edge anyEdge : e) {
					if (anyEdge.getDestination().equals(curEdge.getDestination())
							&& anyEdge.getSource().equals(curEdge.getSource())
							&& anyEdge.getWeight() != curEdge.getWeight()) {
						throw new IncorrectEdgeException();
					}
				}
			}
		}
		
		// No defined errors should exist beyond here, safe to allocate
		// memory for data fields
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
		adjacentVertices = new HashMap<Vertex, ArrayList<Vertex>>();
		
		// Copy over all vertices
		for (Vertex curVertex : v) {
			vertices.add(new Vertex(curVertex.getLabel(), curVertex.getPath(), curVertex.getDistance()));
		}
		
		// Copy edges and link to existing vertices
		for (Edge curEdge : e) {
			
			// Find source and destination vertices
			Vertex edgeDestination = null;
			Vertex edgeSource = null;
			for (Vertex aVertex : vertices) {
				if (aVertex.equals(curEdge.getSource())) {
					edgeSource = aVertex;
				} else if (aVertex.equals(curEdge.getDestination())) {
					edgeDestination = aVertex;
				}
			}
			
			// Add edge with correct source, destination, and weight
			edges.add(new Edge(edgeSource, edgeDestination, curEdge.getWeight()));
		}
		
		// Initialize the adjacent vertices list with each vertex and
		// an empty list of vertices potentially adjacent to it
		for (Vertex curVertex : vertices) {
			adjacentVertices.put(curVertex, new ArrayList<Vertex>());
		}
		
		// Add each source's destination to its adjacent list
		for (Edge curEdge : edges) {
			adjacentVertices.get(curEdge.getSource()).add(curEdge.getDestination());
		}

	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	
	public Collection<Vertex> vertices() {
		// Create and return a copy of the vertices to preserve the local copy
		Collection<Vertex> verticesCopy = new ArrayList<Vertex>();
		for (Vertex curVertex : vertices) {
			verticesCopy.add(new Vertex(curVertex.getLabel(), curVertex.getPath(), curVertex.getDistance()));
		}
		return verticesCopy;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	
	public Collection<Edge> edges() {
		// Create and return a copy of the edges to preserve local copy
		Collection<Edge> edgesCopy = new ArrayList<Edge>();
		for (Edge curEdge : edges) {
			edgesCopy.add(new Edge(curEdge.getSource(), curEdge.getDestination(), curEdge.getWeight()));
		}
		return edgesCopy;
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	
	public Collection<Vertex> adjacentVertices(Vertex v) {
		// Throw an error if the vertex does not exist
		if (!adjacentVertices.containsKey(v)) {
			throw new IllegalArgumentException();
		}
		
		// Get the values adjacent to vertex v and return them
		return adjacentVertices.get(v);
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	
	public int edgeCost(Vertex a, Vertex b) {
		// Make sure vertices exist
		if (!vertices.contains(a) || !vertices.contains(b)) {
			throw new IllegalArgumentException();
		}
		// Initialize the cost
		int cost = -1;
		
		// Make sure that b is adjacent to a
		if (!adjacentVertices.get(a).contains(b)) {
			// Return default value of cost, -1
			return cost;
		
		// Analyze list of edges to find the correct edge.
		} else {
			for (Edge curEdge : edges) {
				// Set cost to the edge weight if source matches a, destination matches b
				if (curEdge.getSource().equals(a) && curEdge.getDestination().equals(b)) {
					cost = curEdge.getWeight();
				}
			}
			// Return the cost of going from a to b
			return cost;
		}
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {
		// Check if vertices exist
		if (!vertices.contains(a) || !vertices.contains(b)) {
			throw new IllegalArgumentException();
		}
		
		// Find a and b in the local vertex list
		for(Vertex curVertex : vertices) {
			if(curVertex.equals(a)){
				a = curVertex;
			} else if(curVertex.equals(b)) {
				b = curVertex;
			}	
		}
		
		// Check simplest case when begin point and end point are the same
		if(a.equals(b)){
			List<Vertex> shortList = new ArrayList<Vertex>();
			shortList.add(a);
			return new Path(shortList, 0);
		}
		
		// Find the shortest path using dijkstra's algorithm
		dijkstra(a);
		
		// Create a list to hold the shortest path
		List<Vertex> shortList = new ArrayList<Vertex>();
		
		// Traverse through the shortest path in the vertex objects until
		// the beginning vertex is reached.
		Vertex temp = b;
		while(!temp.equals(a)) {
			// Return a null path if there is a dead end
			if(temp.getPath() == null) {
				resetVertices();
				return null;
			}
			// Add the vertex to the shortest path
			shortList.add(temp);
			
			// Go to the next vertex
			temp = temp.getPath();
		}
		
		// B's distance field will contain the total path cost
		int pathLen = b.getDistance();
		
		// Reset all vertex fields for the next runthrough
		resetVertices();
		
		// Create a new path and return it
		return new Path(shortList, pathLen);
	}
	
	
	/**
	 * Runs dijkstra's algorithm on a list of vertices to find
	 * the shortest path
	 * 
	 * @param start
	 *            The start vertex in a graph of vertices to analyze
	 */
	private void dijkstra (Vertex start) {
		// List of unknown vertices
		Collection<Vertex> vList = new ArrayList<Vertex>();
		
		// Find the start vertex in the list of vertices
		for (Vertex curVertex: vertices) {
			curVertex.setPath(null);
			if(curVertex.getLabel().equals(start.getLabel())) {
				// Set distance to zero
				curVertex.setDistance(0);
			}
			// Add every vertex to the unknown list
			vList.add(curVertex);
		}
		boolean deadEnd = false;
		// Analyze until all nodes are known
		while(!vList.isEmpty() && !deadEnd) {
			// Choose the least costly option at the time
			Vertex v = smallestDist(vList);
			
			if (v == null) {
				deadEnd = true;
			} else {
				// Set node to known and remove it form the list
				v.setKnown(true);
				vList.remove(v);
	
				// Check all of the adjacent vertices and calculate
				// the current cost
				for(Vertex adjVertex : adjacentVertices.get(v)) {
					
					// Only check unknown vertices
					if(!adjVertex.getKnown()) {
						
						// Cost of going from the current vertex to the unknown adjacent vertex
						int tempCost = edgeCost(v,adjVertex);
						
						// Update the minimum cost and lest expensive path of the node if it is lower
						// than what it was previously
						if((v.getDistance() + tempCost) < adjVertex.getDistance()) {
							adjVertex.setDistance(v.getDistance() + tempCost);
							adjVertex.setPath(v);
						}
					}
				}
			}
		}
	
	}
	
	/**
	 * Returns the vertex from the given vertex list that has the shortest distance
	 * 
	 * @param vList
	 *            A list of vertices to check for a minimum distance
	 * @return v
	 * 			  a vertex that is the minimum distance away from the current vertex
	 */
	private Vertex smallestDist (Collection<Vertex> vList) {
    	int min = Integer.MAX_VALUE;
		Vertex v = null;
    	for(Vertex curVertex: vList) {
			if (curVertex.getDistance() < min) {
				min = curVertex.getDistance();
				v = curVertex;
			}
		}
    	return v;
	}
	
	/**
	 * Reset all vertex fields for the next runthrough
	 */
	private void resetVertices() {
		for (Vertex curVertex : vertices) {
			curVertex.setCost(Integer.MAX_VALUE);
			curVertex.setDistance(Integer.MAX_VALUE);
			curVertex.setKnown(false);
			curVertex.setPath(null);
		}
	}
	
	/**
	 * Thrown when an input collection of edges has a problem
	 */
	private class IncorrectEdgeException extends RuntimeException {
		public IncorrectEdgeException() {
    	}
    }
	
	/**
	 * Thrown when an input collection of vertices has a problem
	 */
	private class IncorrectVertexException extends RuntimeException {
		public IncorrectVertexException() {
    	}
    }
	
	/**
	 * Thrown when an edge has a negative weight during graph construction
	 */
	private class NegativeWeightException extends RuntimeException {
		public NegativeWeightException() {
    	}
    }
}
