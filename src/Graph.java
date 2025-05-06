import java.util.LinkedList;

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class Graph {
    // Implement an array of linked lists
    // Don't store nodes or records, store pointers to the linked list
    // Use a doubly linked list for easy previous accesses

    private LinkedList<Integer>[] adjacencyList;
    private Record[] records;
    private int capacity;
    private int nextIndex;
    private LinkedList<Integer> freeList;

    /**
     * The Graph constructor
     */
    @SuppressWarnings("unchecked")
    public Graph() {
        capacity = 10;
        adjacencyList = new LinkedList[capacity];
        records = new Record[capacity];
        nextIndex = 0;
        freeList = new LinkedList<>();
    }


    /**
     * Creates a node to be added to the graph
     * 
     * @param name
     *            The name of the record that needs to be inserted
     * @return An instance of the record created
     */
    public Record createNode(String name) {
        int index;

        if (!freeList.isEmpty()) {
            index = freeList.removeFirst();
        }
        else {
            if (nextIndex == capacity) {
                resize();
            }
            index = nextIndex++;
        }

        adjacencyList[index] = new LinkedList<>();
        Record record = new Record(name, index);
        records[index] = record;
        return record;
    }


    /**
     * Gets the adjacency list
     * 
     * @return the adjacency list
     */
    public LinkedList<Integer>[] getList() {
        return adjacencyList;
    }


    /**
     * Resizes the lists if there is no more space
     */
    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] newList = new LinkedList[capacity];
        Record[] newRecords = new Record[capacity];

        System.arraycopy(adjacencyList, 0, newList, 0, adjacencyList.length);
        System.arraycopy(records, 0, newRecords, 0, records.length);
        adjacencyList = newList;
        records = newRecords;
    }


    /**
     * Creates an edge between two records
     * 
     * @param a
     *            First record
     * @param b
     *            Second record
     */
    public void addEdge(Record a, Record b) {
        int i = a.getIndex();
        int j = b.getIndex();
        if (adjacencyList[i].contains(j) && adjacencyList[j].contains(i)) {
            System.out.println("|" + a.getName() + "<SEP>" + b.getName()
                + "| duplicates a record already in the database.");
        }
        if (!adjacencyList[i].contains(j)) {
            adjacencyList[i].add(j);
        }

        if (!adjacencyList[j].contains(i)) {
            adjacencyList[j].add(i);
        }
    }


    /**
     * Removes a node from the list based on the index in the list
     * 
     * @param index
     *            The place where to remove from
     */
    public void removeNode(int index) {
        if (index < 0 || index >= nextIndex) {
            return;
        }

        for (int i = 0; i < nextIndex; i++) {
            if (adjacencyList[i] != null) {
                adjacencyList[i].remove((Integer)index);
            }
        }

        adjacencyList[index] = null;
        records[index] = null;
        freeList.add(index);
    }


    /**
     * Creates a console output of the graph with max components and length of
     * longest chain
     */
    public void printGraph() {
        int[] parent = new int[nextIndex];
        int[] size = new int[nextIndex];

        // Initializes the arrays
        for (int i = 0; i < nextIndex; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < nextIndex; i++) {
            if (records[i] == null || adjacencyList[i] == null) {
                continue;
            }
            for (int neighbor : adjacencyList[i]) {
                if (records[neighbor] != null) {
                    union(i, neighbor, parent, size);
                }
            }
        }
        boolean[] visited = new boolean[nextIndex];
        int numComps = 0;
        int maxComps = 0;

        for (int i = 0; i < nextIndex; i++) {
            if (records[i] != null) {
                int root = find(i, parent);
                if (!visited[root]) {
                    visited[root] = true;
                    numComps++;
                    maxComps = Math.max(maxComps, size[root]);
                }
            }
        }
        System.out.println("There are " + numComps + " connected components.");
        System.out.println("The largest connected component has " + maxComps
            + " elements.");
    }


    /**
     * Merges two subtrees if they are different
     * 
     * @param x
     *            subtree one
     * @param y
     *            subtree two
     * @param parent
     *            The array to combine in
     * @param size
     *            Size of each tree to ensure combination into the larger tree
     */
    private void union(int x, int y, int[] parent, int[] size) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX == rootY) {
            return;
        }

        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootY];
        }
        else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }


    /**
     * Finds the root and makes the ancestors point to the root
     * 
     * @param x
     *            Current tree
     * @param parent
     *            Ancestor list
     * @return The root of the tree
     */
    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}
