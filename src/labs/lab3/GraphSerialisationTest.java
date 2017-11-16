package labs.lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Created by mariooliveira on 25/10/2017.
 */
public class GraphSerialisationTest {

    /*Graph<Integer> graph;

    @Before
    public void setGraph(){
        graph = Graph.cycle(4);
        graph.serialize();
    }

    @Test
    public void testSerialize(){

        assertEquals(graph.clone(), graph.deserialize());
    }*/

    public final static Graph<Integer> ORIGINAL = Graph.cycle(10);

    @Test
    public void testRoundTrip() throws IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "graph.dat"));
        out.writeObject(ORIGINAL);
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("graph.dat"));
        @SuppressWarnings("unchecked")
        Graph<Integer> g = (Graph<Integer>) in.readObject();
        assertEquals(ORIGINAL,g);
        out.close();
        in.close();
    }

}
