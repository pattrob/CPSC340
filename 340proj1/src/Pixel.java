public class Pixel { //Pixel class so we can create pixel objects which store RBG values
    private int red; //Private instance variables
    private int blue;
    private int green;




    public Pixel(int red, int blue, int green){ //Pixel constructor which takes RBG ints as params
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

    public int avg(int R, int B, int G){ //Avg method which takes RBG values and avergaes them to create greyscale
        double avg = (R+B+G) / 3;
        return (int)avg;
    }

    public int getRed(){ //Red getter method
        return red;
    }

    public int getBlue(){ //Blue getter method
        return blue;
    }

    public int getGreen(){ //Green getter method
        return green;
    }






}
