import java.util.LinkedList;

public class main {
    public static void main(String[] args){
        long startTime = System.nanoTime();
        LinkedList<Integer> list = new LinkedList<>();
        int length = 500000;
        for(int i = 0; i < length; i++){
            list.add(0, i);
        }
        long endTime = System.nanoTime();
        long elapsedMS = (endTime - startTime) / 1000000;
        System.out.println("Elapsed time = " + elapsedMS + " milliseconds.");
    }
}
