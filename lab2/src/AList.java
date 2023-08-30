import java.util.NoSuchElementException;

// Array-based list implementation
class AList implements List {
    private Object listArray[];             // Array holding list elements
    private static final int DEFAULT_SIZE = 10; // Default size
    private int maxSize;                    // Maximum size of list
    private int listSize;                   // Current # of list items
    private int curr;                       // Position of current element

    // Constructors
    // Create a new list object with maximum size "size"
    AList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = new Object[size];         // Create listArray
    }

    // Create a list with the default capacity
    AList() {
        this(DEFAULT_SIZE);
    }          // Just call the other constructor

    public void clear()                     // Reinitialize the list
    {
        listSize = curr = 0;
    }              // Simply reinitialize values

    // Insert "it" at current position
    public boolean insert(Object it) {
        if (listSize >= maxSize) return false;
        for (int i = listSize; i > curr; i--)  // Shift elements up
            listArray[i] = listArray[i - 1];   //   to make room
        listArray[curr] = it;
        listSize++;                        // Increment list size
        return true;
    }

    // Append "it" to list
    public boolean append(Object it) {
        if (listSize >= maxSize) return false;
        listArray[listSize++] = it;
        return true;
    }

    // Remove and return the current element
    public Object remove() throws NoSuchElementException {
        if ((curr < 0) || (curr >= listSize))  // No current element
            throw new NoSuchElementException("remove() in AList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        Object it = listArray[curr];       // Copy the element
        for (int i = curr; i < listSize - 1; i++) // Shift them down
            listArray[i] = listArray[i + 1];
        listSize--;                        // Decrement size
        return it;
    }

    public void moveToStart() {
        curr = 0;
    }       // Set to front

    public void moveToEnd() {
        curr = listSize;
    }  // Set at end

    public void prev() {
        if (curr != 0) curr--;
    } // Move left

    public void next() {
        if (curr < listSize) curr++;
    } // Move right

    public int length() {
        return listSize;
    }      // Return list size

    public int currPos() {
        return curr;
    }         // Return current position

    // Set current list position to "pos"
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) return false;
        curr = pos;
        return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() {
        return curr == listSize;
    }

    // Return the current element
    public Object getValue() throws NoSuchElementException {
        if ((curr < 0) || (curr >= listSize)) // No current element
            throw new NoSuchElementException("getvalue() in AList has current of " + curr + " and size of "
                    + listSize + " that is not a a valid element");
        return listArray[curr];
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return listSize == 0;
    }

    //Set element of the array to new input
    void set (int index, Object item) throws IndexOutOfBoundsException {
        //If index is too big throw IndexOutOfBoundsException
        if (index > listSize) {
            throw new IndexOutOfBoundsException(index + " is out of bounds for the size of the Array which is " + listSize);
        }
        //Set the pointer to the index and then set the array element to the new input
        curr = index;
        listArray[curr] = item;
    }

    //Search the array and output the last time the item appears
    int lastIndexOf(Object item){
        //Placeholder int for return statement
        int num = 0;
        //Traverse the array top down and update the placeholder depending on whether the item appears
        for(int i = listSize - 1; i > 0; i--) {
            if (listArray[i].equals(item)){
                num = i;
                break;
            } else {
                num = -1;
            }
        }
        //Return the updated placeholder
        return num;

    }

    //Update the size of the array based on how many valid elements it has
    void trimToSize(){
        //Placeholder array
        Object copy[] = new Object[this.length()];
        //Copy elements of listArray into placeholder array - this will only copy over valid elements, not null ones
        for(int i = 0; i < copy.length; i++){
            Object item = listArray[i];
            copy[i] = item;
        }
        //Update the size of listArray based on how many valid elements were transferred
        Object[] listArray = new Object[copy.length];
        //Output updated size and capacity - size will not change but capacity should decrease
        System.out.println("Size = " + this.length());
        System.out.println("Capacity = " + listArray.length);

    }

    //Print every element of the array
    void displayArray(){
        moveToStart();
        //Set the pointer to beginning and output array elements while iterating - source: Dr. P Chandrasekar's note
        //in Piazza
        for (int i = 0; i < listSize; i++) {
            //Get the value
            Object it = listArray[i];
            //Output the value
            System.out.println(it);
        }


    }


}

