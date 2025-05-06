import student.TestCase;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class HashEntryTest extends TestCase {
    private HashEntry entry;
    private HashEntry tomb;
    private HashEntry badTomb1;
    private HashEntry badTomb2;
    private HashEntry badTomb3;
    private Record record;
    private Record tombstone;

    /**
     * Creates the objects to be used throughout the class
     */
    public void setUp() {
        record = new Record("a", 2);
        entry = new HashEntry("a", record);
        tombstone = new Record("TOMBSTONE", -1);
        tomb = new HashEntry("b", tombstone);
        badTomb1 = new HashEntry("a", new Record("a", -1));
        badTomb2 = new HashEntry("a", new Record("TOMBSTONE", 1));
        badTomb3 = new HashEntry("b", null);
    }


    /**
     * Tests the get key method
     */
    public void testGetKey() {
        assertEquals("a", entry.getKey());
    }


    /**
     * Tests the get record method
     */
    public void testGetRecord() {
        assertEquals(record, entry.getRecord());
    }


    /**
     * Tests the isTombstone method
     */
    public void testIsTombstone() {
        assertTrue(tomb.isTombStone());
        assertFalse(entry.isTombStone());
        assertFalse(badTomb1.isTombStone());
        assertFalse(badTomb2.isTombStone());
        assertFalse(badTomb3.isTombStone());
    }


    /**
     * Tests the set tombstone method
     */
    public void testSetTombStone() {
        entry.setTombstone("TOMBSTONE", tombstone);
        assertTrue(entry.isTombStone());
    }
}
