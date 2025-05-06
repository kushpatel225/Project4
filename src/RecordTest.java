import student.TestCase;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class RecordTest extends TestCase {
    private Record record;

    /**
     * Creates the objects to use throughout the class
     */
    public void setUp() {
        record = new Record("a", 1);
    }


    /**
     * Tests the getIndex method
     */
    public void testGetIndex() {
        assertEquals(1, record.getIndex());
    }


    /**
     * Tests the getName method
     */
    public void testGetName() {
        assertEquals("a", record.getName());
    }


    /**
     * Tests the setIndex method
     */
    public void testSetIndex() {
        record.setIndex(2);
        assertEquals(2, record.getIndex());
    }


    /**
     * Tests the setName method
     */
    public void testSetName() {
        record.setName("b");
        assertEquals("b", record.getName());
    }
}
