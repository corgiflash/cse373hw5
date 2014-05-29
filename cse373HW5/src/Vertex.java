/**
 * Representation of a graph vertex
 */
public class Vertex {
	// label attached to this vertex
	private String label;
	
	// current minimum cost to get to this vertex
	private int cost;
	
	// Whether this node is known or not
	private boolean known;
	
	// Node to travel through to find the shortest path
	private Vertex path;
	
	// Distance in the shortest path to get to this point
	private int distance;

	/**
	 * Construct a new vertex
	 * 
	 * @param label
	 *            the label attached to this vertex
	 */
	
	public Vertex(String label) {
		this(label, null, Integer.MAX_VALUE);
	}
	

	/**
	 * Construct a new vertex
	 * 
	 * @param label
	 *            the label attached to this vertex
	 * @param path
	 *            node to travel through to find the shortest path
	 * @param distance
	 *            distance in the shortest path to get to this point
	 */
	public Vertex(String label, Vertex path, int distance) {
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		
		// Set cost to infinity
		this.cost = Integer.MAX_VALUE;
		
		// Node is unknown at creation
		this.known = false;
		
		// Distance in the shortest path to get to this point
		this.distance = distance;
		
		// Node to travel through to find the shortest path
		this.path = path;
	}

	/**
	 * Get a vertex label
	 * 
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Get a vertex cost
	 * 
	 * @return the cost to get to this vertex
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Get known state
	 * 
	 * @return whether the vertex is known or not
	 */
	public boolean getKnown() {
		return known;
	}
	
	/**
	 * Get a vertex path
	 * 
	 * @return the cheapest path node for the vertex
	 */
	public Vertex getPath() {
		return path;
	}
	
	/**
	 * Get a vertex distance
	 * 
	 * @return the distance in the shortest path to get to the vertex
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * Set a vertex cost
	 */
	public void setCost(int newCost) {
		cost = newCost;
	}
	
	/**
	 * Set known state
	 */
	public void setKnown(boolean newKnown) {
		known = newKnown;
	}
	
	/**
	 * Set vertex path
	 */
	public void setPath(Vertex newPath) {
		path = newPath;
	}	
	
	/**
	 * Set vertex distance
	 */
	public void setDistance(int newDistance) {
		distance = newDistance;
	}
	
	/**
	 * A string representation of this object
	 * 
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	// auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	// auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			return other.label == null;
		} else {
			return label.equals(other.label);
		}
	}

}
