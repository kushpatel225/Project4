/**
 * 
 */

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class Record {
    private String name;
    private int index; // The index of the record in the adjacency list

    /**
     * The record constructor
     * 
     * @param name
     *            Name of record
     * @param index
     *            Index of record in adjacency list
     */
    public Record(String name, int index) {
        this.name = name;
        this.index = index;
    }


    /**
     * Gets the name of the record
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the index of the record
     * 
     * @return The index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Sets the name
     * 
     * @param name
     *            The new name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Sets the index
     * 
     * @param index
     *            The new index
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
