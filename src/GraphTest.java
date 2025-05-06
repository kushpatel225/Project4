import student.TestCase;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class GraphTest extends TestCase {
    private Graph graph;
    private Record record1;
    private Record record2;

    /**
     * Creates objects to be used throughout this class
     */
    public void setUp() {
        graph = new Graph();
        record1 = graph.createNode("a");
        record2 = graph.createNode("b");
    }


    /**
     * Tests the add edge method
     */
    public void testAddEdge() {
        graph.addEdge(record1, record2);
        assertEquals(1, graph.getList()[0].size());
        graph.addEdge(record2, record1);
        assertEquals(1, graph.getList()[0].size());
    }


    /**
     * Tests the create node method
     */
    public void testCreateNode() {
        graph.createNode("c");
        assertEquals(0, graph.getList()[2].size());
        graph.removeNode(2);
        graph.createNode("d");
        assertEquals(0, graph.getList()[2].size());
    }


    /**
     * Tests the print graph method
     */
    public void testPrintGraph() {
        graph.printGraph();
        assertEquals(0, graph.getList()[1].size());
        Record rec3 = graph.createNode("c");
        Record rec4 = graph.createNode("d");
        Record rec5 = graph.createNode("e");
        Record rec6 = graph.createNode("f");
        graph.addEdge(record1, record2);
        graph.addEdge(record2, rec3);
        graph.addEdge(record2, rec4);
        graph.addEdge(rec5, rec6);
        graph.addEdge(rec4, rec6);
        graph.printGraph();
        assertEquals(1, graph.getList()[0].size());
    }


    /**
     * Tests the remove node method
     */
    public void testRemoveNode() {
        graph.addEdge(record2, record1);
        graph.removeNode(-1);
        assertEquals(1, graph.getList()[0].size());
        graph.removeNode(10);
        assertEquals(1, graph.getList()[0].size());
        graph.removeNode(0);
        assertEquals(0, graph.getList()[1].size());
        graph.removeNode(2);
        assertEquals(null, graph.getList()[2]);
        graph.removeNode(2);
        assertEquals(null, graph.getList()[2]);
    }


    /**
     * Tests the resize method
     */
    public void testResize() {
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        graph.createNode("c");
        assertEquals(20, graph.getList().length);
    }

}
