import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    //Main method where we run the program/ parse command line args in String[] args
    public static void main(String[] args) {
        int count = 0;//Counter to parse through arguments
        if (args.length < 1) { // If no argument is given, throw exception
            System.err.println("No argument given");
            throw new IllegalArgumentException();
        }
        String fileName = null; //Initialize String to store filename so it can be updated while parsing args
        if (args[count].endsWith(".ppm")) { //Automatically update the filename if it is ppm file
            fileName = args[count];
        }
        while (count < args.length) {//Loop through until all args have been checked
            if (args[count].equals("-h")) {//Help argument to show other possible commands
                System.out.println("This is the help message. Proper command syntax:");
                System.out.println("cmdline -v: Displays version information");
                System.out.println("cmdline -h: Displays this help message");
                System.out.println("cmdline -f [file]: Sets file to file provided");
                count++;
            } else if (args[count].equals("-v")) {//This command displays version information
                System.out.println("Cmdline parse sample version 1.0.0");
                count++;
            } else if (args[count].equals("-f")) {//Take the filename as input
                if (args[count] == args[args.length - 1]) {//If user runs -f without filename, give them error and exit
                    System.err.println("No filename was given after -f argument. Exiting.");
                    System.exit(-1);
                }
                fileName = args[count + 1];//Filename is the input after -f, so it comes 1 after count
                System.out.println("Input file is " + fileName);//Output so user knows filename was inputted properly
                count = count + 2;//Increment counter to next item (skipping filename).
                break;
            } else if (args[count].endsWith(".ppm")) {//If any file is input with .ppm, it must be stored as filename
                fileName = args[count];//This case is used in case the user doesn't use -f
                System.out.println("Input file is " + fileName);//Output so user knows filename was inputted properly
                count = count + 1;//Increment counter to next item (skipping filename).
            } else { //If no recognizable paramater was given, give error and exit
                System.err.println("Unrecognized parameter " + args[count] + "\nExiting.");
                System.exit(-1);
                break;

            }
        }

        //If there is no filename, tell the user to input one
        if (fileName == null) {
            System.out.println("Please enter a file to convert.");
        } else { //Otherwise try to read file and convert to greyscale
            try { //Surrounded in try catch block otherwise there is an unhandled exception
                FileInputStream file = new FileInputStream(fileName); //Take in the filename
                Scanner input = new Scanner(file); //Store it in scanner
                String P3 = input.next(); //P3 must be the first line of the file. If it is not, throw error
                if (!Objects.equals(P3, "P3")) {
                    System.err.println("Error: Incorrect file format");
                    throw new java.lang.Exception();
                }
                int width = input.nextInt(); //Width size for image is next int in ppm format
                int height = input.nextInt(); //Followed by height
                int color_value = input.nextInt(); //Color value is next in ppm format
                Pixel[][] arr = new Pixel[width][height]; //Create 2D array to store pixels based on width and height
                for (int i = 0; i < width; i++) { //Nested for loop - this allows use to iterate through the array in a grid like manner
                    for (int j = 0; j < height; j++) {//It will go down each column first, then move to the next row
                        int red = input.nextInt(); //Pixel values in ppm format are ints for RBG in groupings of 3
                        int blue = input.nextInt(); //B
                        int green = input.nextInt(); //G
                        Pixel p = new Pixel(red, blue, green); //Store the RBG values in a pixel object
                        int avg = p.avg(red, blue, green); //Now find the average of the RBG values for greyscale
                        Pixel newpix = new Pixel(avg, avg, avg); //Create a new pixel object with the updated values
                        arr[i][j] = newpix; //Input the newly created pixel object into the array at the current index
                    }
                }

                file.close(); //Close the file - done reading it in



                PrintWriter output = new PrintWriter("greyscale.ppm"); //Now we output the greyscale image to greyscale.ppm
                output.println("P3"); //P3 must be the first line of the file in ppm format
                output.println(width + " " + height); //Width and height values are next in ppm format
                output.println(color_value); //Followed by the max color value
                for (int i = 0; i < width; i++) { //Same nested for loop concept - must move pixel by pixel across the grid
                    for (int j = 0; j < height; j++) {
                        int red = arr[i][j].getRed(); //Get the R value for the pixel at the current index(this will be the avg so it is greyscale)
                        output.println(red); //Output it to the file
                        int blue = arr[i][j].getBlue(); //Get the B value for the pixel at the current index(this will be the avg so it is greyscale)
                        output.println(blue); //Output it to the file
                        int green = arr[i][j].getGreen(); //Get the G value for the pixel at the current index(this will be the avg so it is greyscale)
                        output.println(green); //Output it to the file

                    }
                }
                output.close(); //Close the file


            } catch (FileNotFoundException e) { //Exception if the file cannot be located
                System.out.println("The file you entered cannot be found. Please make sure the file is located in the" +
                        "\nproper directory and try again. Exiting.");
                System.exit(-1);
            } catch (IOException e) { //Default exception whenever any sort of inputstream is used
                e.printStackTrace();
            } catch (Exception e) { //This is for the lang exception which is thrown when the file is not ppm format
                System.out.println("The file you entered is not the correct format. Exiting.");
                System.exit(-1);
            }


        }
    }

}
