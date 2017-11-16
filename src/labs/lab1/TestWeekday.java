package labs.lab1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by mariooliveira on 25/10/2017.
 */
public class TestWeekday {

        @Test
        public void testNext() {
                Assert.assertEquals(Weekday.SATURDAY.next(),Weekday.SUNDAY);
                Assert.assertEquals(Weekday.SUNDAY.next(),Weekday.MONDAY);
        }


        @Test
        public void testPrevious() {
                Assert.assertEquals(Weekday.SATURDAY.previous(),Weekday.FRIDAY);
                Assert.assertEquals(Weekday.SUNDAY.previous(),Weekday.SATURDAY);
        }


        @Test
        public void testParse() {
                Assert.assertEquals(Weekday.parse("TuesDay"),Weekday.TUESDAY);
        }
}
