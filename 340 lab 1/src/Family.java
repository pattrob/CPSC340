import java.util.Random;

public class Family {
    //Private variables
    private static int numgirls;
    private static int numboys;

    //Random number generator
    Random rand = new Random(1);


    //Setter method for boys and girls
    private void setNumgirls(){
        this.numgirls = 0;
    }
    private void setNumboys(){
        this.numboys = 0;
    }

    //Getter method for boys and girls
    public int getNumgirls(){
        return numgirls;
    }

    public int getNumboys(){
        return numboys;
    }

    //Family constructor
    public Family(){
        this.numboys = 0;
        this.numgirls = 0;
    }

    //Double to store random number generator
    double decider = 0;

    //Step method
    public boolean step(){
        boolean done = false;
        //If there is a girl, break
        if (numgirls == 1){
            done = true;
            return done;
        }
        //Otherwise, have a child based on random number generator
        if (numgirls == 0) {
            decider = rand.nextDouble();
            if (decider >= 0.5) {
                numboys += 1;
            } else {
                numgirls += 1;
                done = true;
            }
        }
        return done;
    }

    //Main method
    public static void main(String[] args) {
        //Initialize array of families
        Family fams[] = new Family[1000000];

        //Loop through the array and initialize each family object
        for (int i = 0; i < fams.length; i++) {
            fams[i] = new Family();
        }

        //Call step method on each family
        for (int i = 0; i < fams.length; i++) {
            while (fams[i].getNumgirls() == 0) {
                fams[i].step();
            }
        }

        //Calculate how many total boys and girls there are
        int total_girls = 0;
        int total_boys = 0;
        int j = 0;
        while (j <= 999999) {
            total_girls += fams[j].getNumgirls();
            total_boys += fams[j].getNumboys();
            j++;
        }

        //Calculate the percentage of girls
        double total = total_boys + total_girls;
        double girl_percent = (total_girls * 100) / total;

        //Output
        System.out.println("The total number of boys is " + total_boys);
        System.out.println("The total number of girls is " + total_girls);
        System.out.println("The percentage of girls is  " + girl_percent + "%");
    }
}





