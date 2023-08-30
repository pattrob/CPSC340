import java.util.Scanner;

public class main {
    public static void main(String[] args){
        boolean valid = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a String: ");
        String line = input.nextLine();
        LStack list = new LStack();
        for(int i = 0; i < line.length(); i++){
            char z = line.charAt(i);
            if(z == '(' || z == '['){
                list.push(z);
            }
            else if(list.isEmpty()){
                valid = false;
            }
            else if(z == ')'){
                Object x = list.pop();
                if(x.equals('[')){
                    valid = false;
                }

            }
            else if(z == ']'){
                Object x = list.pop();
                if(x.equals('(')){
                    valid = false;
                }
            }
            else {
                valid = true;
            }

        }

        if(valid){
            System.out.println("The String is well formed.");
        }
        if(!valid){
            System.out.println("The String is not well formed.");
        }


    }
}
