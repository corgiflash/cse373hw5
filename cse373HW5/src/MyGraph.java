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
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		// Check for erroneous data first
		for (Edge curEdge : e) {
			// Checking negative weights
			if (curEdge.getWeight() < 0) {
				// Throw negative error
				
			// Check for valid destinations and sources
			} else if (!v.contains(curEdge.getDestination()) || !v.contains(curEdge.getSource())) {
				// Throw missing vertex error
				
			// Check if any edges have conflicting weights
			} else {
				for (Edge anyEdge : e) {
					if (anyEdge.getDestination().equals(curEdge.getDestination())
							&& anyEdge.getSource().equals(curEdge.getSource())
							&& anyEdge.getWeight() != curEdge.getWeight()) {
						// Throw conflicting edge error
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
			vertices.add(new Vertex(curVertex.getLabel()));
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
	@Override
	public Collection<Vertex> vertices() {
		Collection<Vertex> verticesCopy = new ArrayList<Vertex>();
		for (Vertex curVertex : vertices) {
			verticesCopy.add(new Vertex(curVertex.getLabel()));
		}
		return verticesCopy;
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
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
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {

		// YOUR CODE HERE
		return null;
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
	@Override
	public int edgeCost(Vertex a, Vertex b) {

		// YOUR CODE HERE
		return 0;
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

		// YOUR CODE HERE (you might comment this out this method while doing
		// Part 1)
		return null;
	}

}
