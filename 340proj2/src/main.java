import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class main {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(args[0]); //Take input from command line
        Scanner input = new Scanner(file); //Scanner for the input
        String line = input.nextLine(); //Store each new line from the file in a String
        AStack nums = new AStack(); //Instantiate the stack
        Object num1; //Variable to pop number off the stack
        Object num2; //Variable to pop number off the stack
        String p1; //Variable to turn Object returned from popping to a String
        String p2; //Variable to turn Object returned from popping to a String


        String[] arr = line.split("\\s+"); //Split the line to get rid of massive spaces
        for (int i = 0; i < arr.length; i++){ //Loop through the array that was created by splitting (The line element by element)
            String number = arr[i]; //This gets the next thing in the line while iterating
            if (!number.equals("+") && !number.equals("*") && !number.equals("^")) { //If it is a number
                nums.push(number); //Push it
            }
            if(number.equals("+") || number.equals("*") || number.equals("^")){ //If it is an operator
                num1 = nums.pop(); //Pop a number
                num2 = nums.pop(); //Pop a number
                String op = number; //Set a string equal to the operator
                switch (op) { //See with operator it is, and do an operation
                    case "+": //Addition
                        p1 = num1.toString(); //Turn the object that was popped to a String
                        p2 = num2.toString(); //Turn the second object that was popped to a String
                        Object add = addition.addition(p1, p2); //Call addition method
                        nums.push(add); //Push the return value
                        break; //Addition is finished
                    case "*": //Multiplication
                        p1 = num1.toString(); //Turn the object that was popped to a String
                        p2 = num2.toString(); //Turn the second object that was popped to a String
                        Object mult = multiply.multiply(p1, p2); //Call the multiplication method
                        nums.push(mult); //Push the return value
                        break; //Multiplication is finished
                    case "^": //Exponentiation
                        p1 = num1.toString(); //Turn the object that was popped to a String
                        p2 = num2.toString(); //Turn the object that was popped to a String
                        Object expon = expo.expo(p2, p1); //Call the exponentiation method
                        nums.push(expon); //Push the return value
                        break; //Exponentiation is finished

                }
            }


        }



        System.out.print(" ="); //Includes the '=' in output
        System.out.println(); //Prints a new line for formatting


        Object get; //Object var to store item popped off the stack
        String putout; //String var to turn popped item to String
         for(int i = 0; i < nums.length(); i++){ //Loop through the Stack
            get = nums.pop(); //Pop each line
            putout = get.toString(); //Turn the line from Object to String

            System.out.println(putout); //Output it
         }
    }

    //Exponentiation function
    public static class expo{
        public static String expo(String base, String exponent){
            String r = ""; //String to store output from multiply function
            LList b = stringToLL.stringToLL(base); //Turn the base to Linked List
            LList e = stringToLL.stringToLL(exponent); //Turn the exponent to Linked List
            Object o = e.getValue(); //Get the exponent value
            String s = o.toString(); //Turn it to a String
            int n = Integer.parseInt(s); //Now to an integer
            if(n == 0){ //If it is 0
                String out = "1"; //Answer will always be 1
                r = out; //Return it
            }
            else if(n % 2 == 0) { //If the exponent is odd
                int ex = n / 2; //Divide the exponent by 2
                String exp = Integer.toString(ex); //Turn it into a String
                r =  expo.expo(multiply.multiply(base, base), exp); //Multiply it with a recursive method call and get output
            } else if (n % 2 != 0){ //If exponent is even
                int ex = (n-1) / 2; //Divide the exponent -1 by 2
                String exp = Integer.toString(ex); //Turn it into a String
                r = multiply.multiply(base, expo.expo(multiply.multiply(base,base), exp)); //Multiply it with a recursive method call and get output

            }
            return r; //Return output

        }

    }

    //Multiplication function - You asked us to delete this method when
    // whe discussed our algorithm with you. It did not work properly.
    public static class multiply {

        public static String multiply(String a, String b) {

        }
    }

    public static class addition{ //Addition method
        static String addition(String a, String b) {
            Object num1; //Object var to store output of getValue() method call
            Object num2; //Object var to store output of getValue() method call
            String s1; //String var for toString()
            String s2; //String var for toString()
            int n1; //Int var for parsing
            int n2; //Int var for parsing
            LList L1 = stringToLL.stringToLL(a); //Turn the first String to a LinkedList
            LList L2 = stringToLL.stringToLL(b); //Turn the second String to a LinkedList
            LList output = new LList();  //Third LL to store output
            int carry = 0; //Int var to store carry over value when adding
            int sum = 0; //Sum var to store the total sum
            int number = 0; //Third int var that is used while adding
            String out = ""; //String var which is used to output the sum
            if(L1.length() == L2.length()){ //If both numbers are the same size, add
                while (!L1.isAtEnd()){ //While the LL that represents the number is not at the last digit
                    num1 = L1.getValue(); //Get the first digit of the first number
                    s1 = num1.toString();//Get the first digit of the first number
                    n1 = Integer.parseInt(s1); //Parse to int - originally returned as an Object
                    num2 = L2.getValue(); //Get the first digit of the second number
                    s2 = num2.toString(); //Turn it into a String
                    n2 = Integer.parseInt(s2); //Parse to int - originally returned as an Object
                    number = n1 + n2 + carry; //Add them, along with the carry value (like traditional addition)
                    carry = number / 10; //Divide number (the sum) by 10 to get carry value
                    sum = number % 10; //Get the sum of the first two digits by using mod 10
                    output.insert(sum); //Output it to the LL designated for output

                    L1.next(); //Move to the next digit in the LL
                    L2.next(); //Move to the next digit in the LL

                }
                out = LLtoString.LLtoString(output); //Turn the LL back into a String for output

            } else if(L1.length() > L2.length()) { //If the first is bigger than the second
                int diff = L1.length() - L2.length(); //Find the difference in the size of the numbers
                int i = 0; //Var used to increment upwards for padding
                while (i < diff) { //Loop for padding to make the numbers the same size
                    L2.append("0"); //Append zeros for padding
                    i++; //Increment upwards
                }
                while (!L1.isAtEnd()) { //While the LL that represents the number is not at the last digit
                    num1 = L1.getValue(); //Get the first digit of the first number
                    s1 = num1.toString(); //Turn it into a String
                    n1 = Integer.parseInt(s1); //Parse to int - originally returned as an Object
                    num2 = L2.getValue(); //Get the first digit of the second number
                    s2 = num2.toString(); //Turn it into a String
                    n2 = Integer.parseInt(s2); //Parse to int - originally returned as an Object
                    number = n1 + n2 + carry; //Add them, along with the carry value (like traditional addition)
                    carry = number / 10; //Divide number (the sum) by 10 ti get carry value
                    sum = number % 10; //Get the sum of the first two digits by using mod 10
                    output.insert(sum); //Output it to the LL designated for output

                    L1.next(); //Move to the next digit in the LL
                    L2.next(); //Move to the next digit in the LL
                }
                out = LLtoString.LLtoString(output); //Turn the LL back into a String for output

            } else if(L2.length() > L1.length()){ //If the second is bigger than the first
                int diff = L2.length() - L1.length(); //Find the difference in the size of the numbers
                int i = 0; //Var used to increment upwards for padding
                while (i < diff) { //Loop for padding to make the numbers the same size
                    L1.append("0"); //Append zeros for padding
                    i++; //Increment upwards
                }
                while (!L1.isAtEnd()) { //While the LL that represents the number is not at the last digit
                    num1 = L1.getValue(); //Get the first digit of the first number
                    s1 = num1.toString(); //Turn it into a String
                    n1 = Integer.parseInt(s1); //Parse to int - originally returned as an Object
                    num2 = L2.getValue(); //Get the first digit of the second number
                    s2 = num2.toString(); //Turn it into a String
                    n2 = Integer.parseInt(s2); //Parse to int - originally returned as an Object
                    number = n1 + n2 + carry; //Add them, along with the carry value (like traditional addition)
                    carry = number / 10; //Divide number (the sum) by 10 ti get carry value
                    sum = number % 10; //Get the sum of the first two digits by using mod 10
                    output.insert(sum); //Output it to the LL designated for output

                    L1.next(); //Move to the next digit in the LL
                    L2.next(); //Move to the next digit in the LL
                }
                out = LLtoString.LLtoString(output); //Turn the LL back into a String for output

            }
            return out; //Return the output as a String





        }
    }

    public static class stringToLL{ //Helper method to turn a String to a LL
        public static LList stringToLL(String a){ //String as input param
            LList list = new LList(); //Create an LL to store the String
            for(int i = 0; i < a.length(); i++){ //Loop through the String
                char c = a.charAt(i); //Get the character at each index of the string
                list.insert(c); //Insert it into the LL

            }
            return list; //Output the LL
        }

    }

    public static class LLtoString { //Helper method to turn a LL to a String
        public static String LLtoString(LList list) { //LL as input param
            list.moveToStart(); //Start at the beginning of the list
            String string = ""; //String var to store the String
            for (int i = 0; i < list.length(); i++) { //Loop through the LL
                Object x = list.getValue(); //Get the value at each index - returned as an Object
                string += x; //Add it to the String
                list.next(); //Move to the next index of the LL
            }
            return string; //Return the String
        }
    }

}








