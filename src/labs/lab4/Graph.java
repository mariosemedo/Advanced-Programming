package labs.lab4;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* Directed graphs with integer nodes 
 * Represented as a map that associates each vertex with its set of neighbours
 * Compare for example http://en.wikipedia.org/wiki/Adjacency_list
 */

public class Graph {
	public final Map<Integer, Set<Integer>> edges;

	public Graph() {
		edges = new TreeMap<>();
	}

	public void addEdge(Integer src, Integer tgt) {
		edges.putIfAbsent(src, new TreeSet<Integer>());
		edges.putIfAbsent(tgt, new TreeSet<Integer>());
		edges.get(src).add(tgt);
	}

	public Set<Integer> getVertices() {
		return edges.keySet();
	}

	@Override
	public String toString() {
		return "" + edges;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		return true;
	}

	public static Graph cycle(int n) {
		Graph g = new Graph();
		for (int i = 0; i < n; i++)
			g.addEdge(i, (i + 1) % n);
		return g;
	}
	
	public static void main(String[] args) {
		System.out.println("cycle(4):" + cycle(4));
	}
}