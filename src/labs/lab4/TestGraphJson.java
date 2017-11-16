package labs.lab4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by mariooliveira on 01/11/2017.
 */
public class TestGraphJson {

    public final static labs.lab4.Graph ORIGINAL = labs.lab4.Graph.cycle(10);
    public final static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public final static String GRAPH_JSON_FILE = "src/labs/lab4/graph.json";

    @Test
    public void testRoundTrip() throws IOException, ClassNotFoundException {

        Path path = Paths.get(GRAPH_JSON_FILE);
        BufferedWriter wr = Files.newBufferedWriter(path);
        GSON.toJson(ORIGINAL,wr);
        wr.close();

        BufferedReader reader = Files.newBufferedReader(path);
        Type t = new TypeToken<Graph>(){}.getType();
        Graph g = GSON.fromJson(reader, t);
        reader.close();
        assertEquals(ORIGINAL, g);

       /* Gson gson = new Gson();
        String json = gson.toJson(ORIGINAL);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "graph.txt"));
        out.writeObject(json);*/
    }
}
