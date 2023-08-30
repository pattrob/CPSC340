// HashTable.java
public class HashTable<Key, Value> {
    // these are parallel arrays, so indices in one correspond to the other
    private Value[] values;
    private Key[] keys;
    private int maxSize;
    private int entries;
    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        this.entries = 0;

        // make the arrays (with generic array workaround)
        values = (Value[]) new Object[maxSize];
        keys = (Key[]) new Object[maxSize];
    }
    // insert a key/value pair
    public void insert(Key key, Value value) {

        //If the table is half full, expand
        int half = maxSize/2;
        if(entries >= half){
            expand();
        }

        // get the starting index from hash function
        int index = Math.abs(key.hashCode()) % maxSize;

        // linear probe until spot is open
        while (values[index] != null) {
            index = (index + 1) % maxSize;
        }

        // now insert this pair here
        values[index] = value;
        keys[index] = key;

        //Increase the number of entries
        entries++;
    }
    // lookup a value by its key
    public Value lookup(Key key) {
        // get the starting index from hash function
        int index = Math.abs(key.hashCode()) % maxSize;
        // loop while there is data here
        while (keys[index] != null) {
            // if the key here matches target, return corresponding value
            if (keys[index].equals(key)) {
                return values[index];
            }
            // move to next index to search (with wrap around)
            index = (index + 1) % maxSize;
        }
        // if we fell off the end, the key is not here
        return null;
    }

    private void expand(){
        //increase the max size by two
        maxSize *= 2;
        //Create new, larger arrays
        Value[] v2 = (Value[]) new Object[maxSize];
        Key[] k2 = (Key[]) new Object[maxSize];

        //loop through the old array, create new index, and put it n the new array
        for(int i = 0; i < keys.length; i++){
            //some keys will be null so this is a failsafe
            if(keys[i] != null){
                //create the new index, maxSize has been doubled so the modulus works
                int index = Math.abs(keys[i].hashCode()) % maxSize;
                //Insert the value into the new arrays
                v2[index] = values[i];
                k2[index] = keys[i];
            }
        }
        //point the array references to the new, larger arrays
        values = v2;
        keys = k2;

    }
}