package labs.lab3;

import java.io.*;
import java.util.*;

/* Directed graphs represented as a map  
 * that associates each vertex with its set of neighbours
 * Compare for example http://en.wikipedia.org/wiki/Adjacency_list
 */

public class Graph<A> implements Cloneable, Serializable {
	public final Map<A, Set<A>> edges;

	public Graph() {
		edges = new TreeMap<>();
	}

	public void addEdge(A src, A tgt) {
		edges.putIfAbsent(src, new TreeSet<A>());
		edges.putIfAbsent(tgt, new TreeSet<A>());
		edges.get(src).add(tgt);
	}

	public Set<A> getVertices() {
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
		Graph<?> other = (Graph<?>) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		return true;
	}

	public static Graph<Integer> cycle(int n) {
		Graph<Integer> g = new Graph<>();
		for (int i = 0; i < n; i++)
			g.addEdge(i, (i + 1) % n);
		return g;
	}

	public void serialize(){

		ObjectOutputStream out;

		try {

			out = new ObjectOutputStream(
					new FileOutputStream("graph.bat"));
			out.writeObject(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Graph<A> deserialize(){
		ObjectInputStream in;

		Graph<A> a = new Graph<>();

		try {
			in = new ObjectInputStream(
					new FileInputStream("graph.bat"));

			a = (Graph) in.readObject();

		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();

		}

		return a;



	}

	public static void main(String[] args) {

		System.out.println("cycle(4):" + cycle(4));
		Graph<Integer> g = new Graph<>();


	}

	// shallow clone
	// TODO: replace with a deep clone and run GraphCloningTest

	@SuppressWarnings("unchecked")
	public Graph<Integer> clone() {

		return new Graph<Integer>().cycle(edges.size());

	}
}
