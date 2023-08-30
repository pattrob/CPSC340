// BubbleSort.java

import java.io.FileNotFoundException;
import java.util.Random;

public class BubbleSort {
    public static void bubbleSort(int[] array) {
        boolean changed = true;
        int subtract = 2;
        while (changed) {
            changed = false;
            for (int i = 0; i <= array.length - subtract; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    changed = true;
                }

            }
            subtract++;
        }
    }
    public static void main(String args[]) throws FileNotFoundException {
        // put in random numbers

        String num = args[0];
        int size = Integer.parseInt(num);


        int[] nums = new int[size];
        Random rng = new Random();

        for (int i = 0; i < size; i++) {
            nums[i] = rng.nextInt(100);

        }

        long startTime = System.nanoTime();

        bubbleSort(nums);
        long endTime = System.nanoTime();
        long elapsedMS = (endTime - startTime) / 1000000;
        System.out.println("Elapsed time = " + elapsedMS + " milliseconds.");


    }
}
