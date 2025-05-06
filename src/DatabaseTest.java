import java.io.IOException;
import student.TestCase;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class DatabaseTest extends TestCase {
    private Database db;

    /**
     * The setUp method for this test class
     */
    public void setUp() {
        db = new Database(10);
    }


    /**
     * Tests the process commands method
     * 
     * @throws IOException
     *             Throws exception if file does not exist or can't be read
     */
    public void testProcessCommands() throws IOException {
        String path = "solutionTestData/P4_sampleInput.txt";
        db.processCommands(path);
        String path2 = "solutionTestData/badCommand.txt";
        db.processCommands(path2);
    }


    /**
     * Tests the insert command from Database
     */
    public void testInsert() {
        db.insert("a", "b");
    }


    /**
     * Tests the remove command
     */
    public void testRemove() {
        db.remove("a", false);
        db.remove("a", true);

    }


    /**
     * Tests the trim method
     */
    public void testPartsAfter() {
        String line = "a b";
        assertEquals("b", db.partsAfter(line, "a"));
    }
}
