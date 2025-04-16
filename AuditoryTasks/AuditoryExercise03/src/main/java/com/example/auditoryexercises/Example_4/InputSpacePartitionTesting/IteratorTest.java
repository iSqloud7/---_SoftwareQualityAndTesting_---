package com.example.auditoryexercises.Example_4.InputSpacePartitionTesting;

/*
 * This class contains 13 JUnit tests for the Iterator interface. The tests are derived from an
 * IDM (input domain modeling) based on the JavaDoc API for Iterator.
 * The three methods tested are: hasNext(), next(), remove()
 * The following characteristics have been identified and are used to generate tests for the methods:
 * C1: iterator has more values
 * C2: iterator returns a non-null object reference
 * C3: remove() is supported
 * C4: remove() precondition is satisfied
 * C5: collection in consistent state while iterator in use
 * Each characteristic has a boolean partition
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorTest {

    // creating a collection that is iterable
    private List<String> list;

    // an iterator whose state is needed
    private Iterator<String> iterator;

    // method for initializing the corresponding fixtures
    @BeforeEach
    public void setup() {
        this.list = new ArrayList<>();
        list.add("Software Quality And Testing!");
        list.add("Advanced Programming!");
        this.iterator = list.iterator();
    }

    /*
     3 Tests for Iterator method hasNext()
     The 2 characteristics associated with hasNext() are: C1, C5
    */

    // TEST-1 of hasNext(): testHasNext_BaseCase():  C1-T, C5-T
    @Test
    public void testHasNext_BaseCase() {
        assertTrue(iterator.hasNext());
    }

    // TEST-2 of hasNext(): testHasNext_C1(): C1-F, C5-T
    @Test
    public void testHasNext_C1() {
        iterator.next();
        iterator.next(); // consume the Software Quality And Testing! and the Advanced Programming!
        assertFalse(iterator.hasNext()); // now empty
    }

    // TEST-3 of hasNext(): testHasNext_C5(): C1-T, C5-F
    // This test fails!
    // The reason is that standard Java implementations of the Iterator
    // Interface are not consistent in their use of ConcurrentModificationException.
    @Test
    public void testHasNext_C5() {
        list.add("Operative Systems!");
        assertThrows(ConcurrentModificationException.class, () -> iterator.hasNext());
    }

    /*
     4 Tests for Iterator method next()
     The 3 characteristics associated with next() are: C1, C2, C5
    */

    // TEST-1 of next(): testNext_BaseCase(): C1-T, C2-T, C5-T
    @Test
    public void testNext_BaseCase() {
        assertEquals("Software Quality And Testing!", iterator.next());
    }

    // TEST-2 of next(): testNext_C1(): C1-F, C2-F, C5-T
    @Test
    public void testNext_C1() {
        iterator.next();
        iterator.next(); // consume the Software Quality And Testing! and the Advanced Programming!
        assertThrows(NoSuchElementException.class, () -> iterator.next()); // now empty
    }

    // TEST-3 of next(): testNext_C2(): C1-T, C2-F, C5-T
    @Test
    public void testNext_C2() {
        list = new ArrayList<String>();
        list.add(null);
        iterator = list.iterator();
        assertNull(iterator.next());
    }

    // TEST-4 of next(): testNext_C5(): C1-T, C2-F, C5-F
    @Test
    public void testNext_C5() {
        list.add("Operative Systems!");
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }

    /*
     6 Tests for Iterator method remove()
     The 5 characteristics associated with remove() are: C1, C2, C3, C4, C5
    */

    // TEST-1 of remove(): testRemove_BaseCase(): C1-T, C2-T, C3-T, C4-T, C5-T
    @Test
    public void testRemove_BaseCase() {
        iterator.next();
        iterator.remove();
        assertFalse(list.contains("Software Quality And Testing!"));
    }

    // TEST-2 of remove(): testRemove_C1(): C1-F, C2-F, C3-T, C4-T, C5-T
    @Test
    public void testRemove_C1() {
        iterator.next();
        iterator.next(); // consume the Software Quality And Testing! and the Advanced Programming!
        iterator.remove(); // remove Advanced Programming! from list.
        assertFalse(list.contains("Advanced Programming!"));
    }

    // TEST-3 of remove(): testRemove_C2(): C1-T, C2-F, C3-T, C4-T, C5-T
    @Test
    public void testRemove_C2() {
        list.add(null);
        list.add("Operative Systems!");
        iterator = list.iterator();
        iterator.next();
        iterator.next(); // consume the Software Quality And Testing! and the Advanced Programming!
        iterator.next(); // consume null; iterator not empty
        iterator.remove(); // remove null from list
        assertFalse(list.contains(null));
    }

    // TEST-4 of remove(): testRemove_C3(): C1-T, C2-T, C3-F, C4-T, C5-T
    @Test
    public void testRemove_C3() {
        list = Collections.unmodifiableList(list);
        iterator = list.iterator();
        iterator.next(); // consume first element to make C4 true
        assertThrows(UnsupportedOperationException.class, () -> iterator.remove());
    }

    // TEST-5 of remove(): testRemove_C4(): C1-T, C2-T, C3-T, C4-F, C5-T
    @Test
    public void testRemove_C4() {
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }


    // TEST-6 of next(): testRemove_C5(): C1-T, C2-T, C3-T, C4-T, C5-F
    @Test
    public void testRemove_C5() {
        iterator.next();
        list.add("Operative Systems!");
        assertThrows(ConcurrentModificationException.class, () -> iterator.remove());
    }
}
