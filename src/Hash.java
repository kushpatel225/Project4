/**
 * Hash table class
 *
 * @author Kush, Rushil
 * @version 1.0
 */

public class Hash {
    // Implement a record hash table here
    private HashEntry[] table;
    private int size;
    private int count;
    private static final Record TOMBSTONE = new Record("TOMBSTONE", -1);

    /**
     * The constructor for Hash
     * 
     * @param initialSize
     *            The initial size of the hash table
     */
    public Hash(int initialSize) {
        table = new HashEntry[initialSize];
        size = initialSize;
        count = 0;
    }


    /**
     * Inserts a record into the hash table
     * 
     * @param key
     *            The name of the record
     * @param graph
     *            The graph instance
     * @param isArtist
     *            Depicts if the record is artist or song
     * @return The record inserted
     */
    public Record insert(String key, Graph graph, boolean isArtist) {
        if ((double)(count + 1) / size > 0.5) {
            resize(graph, isArtist);
        }
        int hash = h(key, size);
        int tombIndex = -1;
        for (int i = 0; i < size; i++) {
            int index = (hash + i * i) % size;
            HashEntry entry = table[index];
            if (entry == null) {
                Record newRecord = graph.createNode(key);
                int insertIndex = (tombIndex != -1) ? tombIndex : index;
                table[insertIndex] = new HashEntry(key, newRecord);
                count++;
                if (isArtist) {
                    System.out.println("|" + key
                        + "| is added to the artist database.");
                }
                else {
                    System.out.println("|" + key
                        + "| is added to the song database.");
                }
                return newRecord;
            }
            else if (entry.isTombStone() && tombIndex == -1) {
                tombIndex = index;
            }
            else if (entry.getKey().equals(key)) {
                return entry.getRecord(); // If record already there
            }
        }
        return null;
    }


    /**
     * Gets the number of entries in the hash
     * 
     * @return The count
     */
    public int getCount() {
        return count;
    }


    /**
     * Removes a record from the hash table and converts the entry into a
     * tombstone
     * 
     * @param key
     *            The name of the record to remove
     * @param isArtist
     *            Depicts if the record is artist or song
     * @return The record removed
     */
    public Record remove(String key, boolean isArtist) {
        int hash = h(key, size);
        int i = 0;
        int index;
        Record removed = null;
        while (i < size) {
            index = (hash + i * i) % size;
            HashEntry entry = table[index];

            if (entry == null) {
                break;
            }

            if (!entry.isTombStone() && entry.getKey().equals(key)) {
                removed = entry.getRecord();
                table[index].setTombstone(TOMBSTONE.getName(), TOMBSTONE);
                count--;
                if (isArtist) {
                    System.out.println("|" + key
                        + "| is removed from the artist database.");
                }
                else {
                    System.out.println("|" + key
                        + "| is removed from the song database.");

                }
                break;
            }
            i++;
        }
        return removed;
    }


    /**
     * Prints out the entries in the hash table
     * 
     * @return The number of entries
     */
    public int print() {
        for (int i = 0; i < size; i++) {
            HashEntry entry = table[i];
            if (entry != null) {
                if (!entry.isTombStone()) {
                    System.out.println(i + ": |" + entry.getKey() + "|");
                }
                else {
                    System.out.println(i + ": " + entry.getKey());
                }
            }
        }
        return count;
    }


    /**
     * Increases the hash table size if more than half of the hash table is
     * occupied
     * 
     * @param graph
     *            The instance of the graph
     * @param isArtist
     *            Depicts whether the hash is artist or song
     */
    private void resize(Graph graph, boolean isArtist) {
        HashEntry[] oldTable = table;
        size *= 2;
        table = new HashEntry[size];
        count = 0;

        for (HashEntry entry : oldTable) {
            if (entry != null && !entry.isTombStone()) {
                String key = entry.getKey();
                Record record = entry.getRecord();
                int hash = h(key, size);
                for (int i = 0; i < size; i++) {
                    int index = (hash + i * i) % size;
                    if (table[index] == null) {
                        table[index] = new HashEntry(key, record);
                        count++;
                        break;
                    }
                }
            }
        }
        if (isArtist) {
            System.out.println("Artist hash table size doubled.");
        }
        else {
            System.out.println("Song hash table size doubled.");
        }
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
