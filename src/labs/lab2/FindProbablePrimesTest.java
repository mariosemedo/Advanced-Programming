package labs.lab2;

import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mariooliveira on 25/10/2017.
 */
public class FindProbablePrimesTest {
    public static final int BIT_LENGTH = 1300;
    public static final int NUMBER_OF_PPRIMES = 8;
    public static Map<BigInteger, BigInteger> testResult = new HashMap<>();

    public FindProbablePrimesTest() {
    }

    @BeforeClass
    public static void init() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_PPRIMES; i++) {
            BigInteger x = new BigInteger(BIT_LENGTH, random);
            testResult.put(x, x.nextProbablePrime());
        }
        System.out.println("Next probable primes:");
        for (BigInteger value : testResult.values())
            System.out.println(value);
    }

    public Map<BigInteger, BigInteger> run(Function<Iterable<BigInteger>, Map<BigInteger, BigInteger>> f,
                                           String fName) {
        long startTime = System.currentTimeMillis();
        Map<BigInteger, BigInteger> result = f.apply(testResult.keySet());
        long endTime = System.currentTimeMillis();
        System.out.println(fName + " computation: " + (endTime - startTime) + "ms");
        return result;
    }

    @Test
    public void runParallel() {
        assertEquals(testResult, run(FindProbablePrimes::parallel, "Parallel"));
    }

    @Test
    public void runSequential() {
        assertEquals(testResult, run(FindProbablePrimes::sequential, "Sequential"));
    }

}
