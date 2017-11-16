package labs.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Created by mariooliveira on 25/10/2017.
 */
public class Parallel {

	/* "map" a function concurrently over all elements of a collection */
	/* Solution uses join */

        public static <A, B> Map<A, B> parallelMapFun(final Function<A, B> fun, Iterable<A> arguments) {

            final Map<A, B> result = new ConcurrentHashMap<>();

            List<Thread> threads = new ArrayList<>();
            for (A argument : arguments) {
                Thread t = new Thread(() -> result.put(argument, fun.apply(argument)));
                threads.add(t);
                t.start();
            }

            for (Thread t : threads) {
                try {
                    t.join();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
}
