import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String args[]) throws FileNotFoundException {

        //Create the HashTable
        HashTable<String, Integer> table = new HashTable<>(50);

        //Read in the file with FileInputStream and Scanner
        FileInputStream file = new FileInputStream("sentiments.txt");
        Scanner scan = new Scanner(file);

        //Read through sentiments.txt, and add the pairings to the table
        while(scan.hasNext()){
            //Scanner
            String line = scan.nextLine();
            //This splits the line at the comma, that way I can differentiate between sentiment and value
            String[] l = line.split("[,]", 0);
            //The sentiment is the first part of the line
            String s = l[0];
            //The value is the second, must be parsed from a String to an int
            int i = Integer.parseInt(l[1]);
            //Insert the value
            table.insert(s, i);
        }

        //Prompt the user to input something
        System.out.println("Enter text: ");
        Scanner in = new Scanner(System.in);
        //input String is used to take the input line by line
        String input = "";
        //message String stores the entire input in one string
        String message = "";
        //While the user has not said "END", this is false
        boolean end = false;

        while(!end){
            //Take in the input line by line
            input = in.nextLine();
            //Store the entire input in one string
            message += input;
            //This ensures spacing is correct between lines
            message += " ";
            //If the user says END, the input is over
            if(input.equals("END")){
                end = true;
            }
        }

        //This removes all punctuation and switches everything to lowercase
        String[] words = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        //int to track total sentiment
        int sentiment = 0;
        //This is for total words, the -1 removes "END" from user input
        int number = words.length - 1; //This one is to output total words without decimal place
        double numWords = words.length - 1; //This one is to calculate average sentiment
        //count var is used for averaging later
        double count = 0;

        //loop through where the input is stored and check if the word is in the table
        for(int i = 0; i < numWords; i++){
            //single word
            if(table.lookup(words[i]) != null){
                //look up the word and add the sentiment value
                sentiment += table.lookup(words[i]);
                //increase for averaging
                count ++;
            }
            //two words
            else if(table.lookup(words[i] + " " + words[i+1]) != null){
                //look up the word and add the sentiment value
                sentiment += table.lookup(words[i] + " "  + words[i+1]);
                //increase for averaging
                count ++;
            }
        }

        //get the average
        double avg = sentiment/numWords;

        //output everything
        System.out.println("Words: " + number);
        System.out.println("Sentiment: " + sentiment);
        System.out.printf("Overall: %.2f" , avg);

    }
}
