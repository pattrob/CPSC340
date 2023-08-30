// HeapSort.java
import java.util.Random;
class IntHeap {
    private int[] array;
    private int count;
    // start with nothing
    public IntHeap(int size) {
        array = new int[size];
        count = 0;
    }
    // find the left child of a node
    public int left(int node) {
        return 2 * node  + 1;
    }
    // find the right child of a node
    public int right(int node) {
        return 2 * node  + 2;
    }
    // find the parent of a node
    public int parent(int node) {
        return (node - 1) / 2;
    }
    // insert an item into the heap
    public void insert(int item) {
        // put item in next available slot
        array[count] = item;
        // keep indices into node we inserted and its parent
        int node = count;
        int p = parent(node);
        // increment total number of items
        count++;
        // upheap until it's root, or parent is bigger
        while ((node != 0) && (array[node] < array[p])) {
            // swap values
            int temp = array[node];
            array[node] = array[p];
            array[p] = temp;
            // update indices
            node = p;
            p = parent(node);
        }
    }
    // remove the top item
    public int dequeue() {
        // set aside root
        int retValue = array[0];
        // move last value to the root
        array[0] = array[count - 1];
        count--;
        // find the children
        int node = 0;
        int l = left(node);
        int r = right(node);
        // while one child is bigger than us
        while ((l < count) && (array[l] < array[node]) ||
                (r < count) && (array[r] < array[node])) {
            // find biggest child
            int max;
            if (l < count && r < count) {
                if (array[l] < array[r]) {
                    max = l;
                } else {
                    max = r;
                }
            } else {
                max = l;
            }
            // swap with the biggest child
            int temp = array[node];
            array[node] = array[max];
            array[max] = temp;
            // update indices
            node = max;
            l = left(node);
            r = right(node);
        }

        // return orig. root
        return retValue;
    }
    // return the largest item in the heap
    public int max() {
        return array[0];
    }
    public int getCount() {
        return count;
    }
    // print the contents of the array
    public void print() {
        System.out.print("Heap: ");
        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }
}
// main class does the actual sorting
public class HeapSort {
    public static void heapSort(int[] array) {
        IntHeap heap = new IntHeap(array.length); //Create a new heap
        for(int i = 0; i < array.length; i++){ //Loop through the array of values
            heap.insert(array[i]); //Add them to the heap
        }
        int i = 0; //Set i to 0
        while(heap.getCount() > 0){ //While the heap still has values in it
            int item = heap.dequeue(); //Deque the values from the heap
            array[i] = item; //Add the values into the array
            i++; //Increment i
        }


    }

    public static void main(String args[]) {
        // get size from first argument
        int size = Integer.parseInt(args[0]);
        long startTime = System.nanoTime();
        // put in random numbers
        int[] nums = new int[size];
        Random rng = new Random();
        for (int i = 0; i < size; i++) {
            nums[i] = rng.nextInt(100);
        }

        // sort them
        heapSort(nums);

        long endTime = System.nanoTime();
        long elapsedMS = (endTime - startTime) / 1000000;
        System.out.println("Sort completed in " + elapsedMS + " milliseconds.");
    }
}