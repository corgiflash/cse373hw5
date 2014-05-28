/**
 * Representation of a graph vertex
 */
public class Vertex {
	// label attached to this vertex
	private String label;
	private int cost;
	private boolean known;
	private Vertex path;
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
	

	public Vertex(String label, Vertex path, int distance) {
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.cost = Integer.MAX_VALUE;
		this.known = false;
		this.path = path;
		this. distance = distance;
	}

	/**
	 * Get a vertex label
	 * 
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	public int getCost() {
		return cost;
	}
	
	public boolean getKnown() {
		return known;
	}
	
	public Vertex getPath() {
		return path;
	}
	
	public int getDistance() {
		return distance;
	}
	
	
	public void setCost(int newCost) {
		cost = newCost;
	}
	
	public void setKnown(boolean newKnown) {
		known = newKnown;
	}
	
	public void setPath(Vertex newPath) {
		path = newPath;
	}	

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
