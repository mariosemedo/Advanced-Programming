package labs.lab3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class TestGraphCloning {

	Graph<Integer> graph, clone;

	@Before
	public void setUp() {
		graph = Graph.cycle(4);
		clone = graph.clone();
	}

	@Test
	public void testClone() {
		assertFalse(graph == clone);
		assertEquals(graph, clone);
		assertEquals(graph.getClass(), clone.getClass()); 
	}

	@Test
	public void testDeepClone1() {
		clone.addEdge(0, 2);
		assertNotEquals(graph, clone);
		assertEquals(graph, Graph.cycle(4));
	}

	@Test
	public void testDeepClone2() {
		clone.edges.remove(0);
		assertNotEquals(graph, clone);
		assertEquals(graph, Graph.cycle(4));
	}

}
