/**
 * 
 */

/**
 * @author Kush, Rushil
 * @version 1.0
 */
public class HashEntry {
    private String key;
    private Record record;

    /**
     * The constructor for hash entry
     * 
     * @param key
     *            The name of the entry
     * @param record
     *            The record in the entry
     */
    public HashEntry(String key, Record record) {
        this.key = key;
        this.record = record;
    }


    /**
     * Gets the key of the entry
     * 
     * @return The key
     */
    public String getKey() {
        return key;
    }


    /**
     * Gets the record of the entry
     * 
     * @return The record
     */
    public Record getRecord() {
        return record;
    }


    /**
     * Sets an entry to a tombstone
     * 
     * @param key1
     *            Name to call tombstone
     * @param record1
     *            Record to set the tombstone
     */
    public void setTombstone(String key1, Record record1) {
        key = key1;
        record = record1;
    }


    /**
     * Checks if an entry is a tombstone
     * 
     * @return If the entry is a tombstone
     */
    public boolean isTombStone() {
        return record != null && "TOMBSTONE".equals(record.getName()) && record
            .getIndex() == -1;
    }
}
