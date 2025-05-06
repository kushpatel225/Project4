import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class Database {
    // This will create the instances of graph and hash table
    // We will be able to choose which methods are called for each data
    // structure in here
    // Insert a record into graph first then hash table
    // For remove from hash table, define a tombstone record object and have all
    // removed pointers point to this one object
    // Tombstone: name = tombstone, index = -1
    // When rehashing, don't add the tombstones
    // To remove from graph, don't shift the other objects, just make the array
    // index that points to the linked list to null
    // Have a free linked list, that contains the index of all null values in
    // the adjacency list
    // Then insert into this index and then remove that index from the free list
    //

    private Hash artistTable;
    private Hash songTable;
    private Graph graph;

    /**
     * The constructor for the database class
     * 
     * @param initialSize
     *            initial size for each hash table
     */
    public Database(int initialSize) {
        artistTable = new Hash(initialSize);
        songTable = new Hash(initialSize);
        graph = new Graph();

    }


    /**
     * Reads the commands and passes the inputs into their specific methods
     * 
     * @param commandFile
     *            The file to process
     * @throws IOException
     *             Throws error when file is not found or can't be read
     */
    public void processCommands(String commandFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(commandFile));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("insert")) {
                String[] parts = line.substring(7).split("<SEP>");
                insert(parts[0], parts[1]);
            }
            else if (line.startsWith("remove artist")) {
                remove(partsAfter(line, "remove artist"), true);
            }
            else if (line.startsWith("remove song")) {
                remove(partsAfter(line, "remove song"), false);
            }
            else if (line.startsWith("print artist")) {
                int count = artistTable.print();
                System.out.println("total artists: " + count);
            }
            else if (line.startsWith("print song")) {
                int count = songTable.print();
                System.out.println("total songs: " + count);
            }
            else if (line.startsWith("print graph")) {
                graph.printGraph();
            }
            else {
                System.out.println("Invalid command");
            }
        }
        reader.close();
    }


    /**
     * Adds into the hash tables first then into the graph
     * 
     * @param artistName
     *            Name of the artist record
     * @param songName
     *            Name of the song record
     */
    public void insert(String artistName, String songName) {
        Record artist = artistTable.insert(artistName, graph, true);
        Record song = songTable.insert(songName, graph, false);
        graph.addEdge(artist, song);
    }


    /**
     * Removes from hash table first then from graph
     * 
     * @param name
     *            The name of the record that needs to be removed
     * @param isArtist
     *            Depicts whether the record is in Artist or Song
     */
    public void remove(String name, boolean isArtist) {
        Record record = isArtist
            ? artistTable.remove(name, isArtist)
            : songTable.remove(name, isArtist);
        if (record != null) {
            graph.removeNode(record.getIndex());
        }
        else {
            if (isArtist) {
                System.out.println("|" + name
                    + "| does not exist in the Artist database.");
            }
            else {
                System.out.println("|" + name
                    + "| does not exist in the Song database.");
            }
        }
    }


    /**
     * Trims the string to remove the command and holds the data after the
     * command
     * 
     * @param line
     *            The line needed to be trimmed
     * @param prefix
     *            The command that needs to be trimmed
     * @return The trimmed string
     */
    public String partsAfter(String line, String prefix) {
        return line.substring(prefix.length()).trim();
    }
}
