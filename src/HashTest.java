import student.TestCase;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class HashTest extends TestCase {
    private Hash hash;
    private Graph graph;
    private Record record1;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        hash = new Hash(10);
        graph = new Graph();
        record1 = hash.insert("a", graph, false);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        hash.insert("b", graph, false);
        assertEquals(2, hash.getCount());
        hash.insert("b", graph, false);
        assertEquals(2, hash.getCount());
        hash.insert("c", graph, false);
        hash.insert("d", graph, false);
        hash.insert("e", graph, false);
        hash.insert("f", graph, false);
        hash.insert("g", graph, false);
        hash.insert("h", graph, false);
        assertEquals(8, hash.getCount());
        hash.insert("i", graph, true);
        assertEquals(9, hash.getCount());
    }


    /**
     * Tests the count method
     */
    public void testgetCount() {
        assertEquals(1, hash.getCount());
    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        assertEquals(record1, hash.remove("a", false));
        assertNull(hash.remove("b", false));
        Record record2 = hash.insert("b", graph, true);
        assertEquals(record2, hash.remove("b", true));
    }


    /**
     * Tests the print method
     */
    public void testPrint() {
        assertEquals(1, hash.print());
        hash.insert("b", graph, false);
        hash.insert("c", graph, false);
        hash.remove("b", false);
        assertEquals(2, hash.print());
    }


    /**
     * Tests the resize method
     */
    public void testResize() {
        hash.insert("b", graph, true);
        hash.insert("c", graph, true);
        hash.insert("d", graph, true);
        hash.insert("e", graph, true);
        hash.insert("f", graph, true);
        hash.insert("g", graph, true);
        hash.insert("h", graph, true);
        assertEquals(8, hash.getCount());
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }
}
