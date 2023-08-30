public class MainLab2 {
    public static void main(String args[]) {
        AList names = new AList(30);
        names.insert("Alice");
        names.insert("Bob");
        names.insert("Claire");
        names.insert("Dominic");
        names.insert("Estelle");
        names.insert("Frank");
        names.insert("Gwen");
        names.insert("Hugo");
        names.insert("Irene");
        names.insert("Claire");
        names.insert("Jack");
        // test the set method
        names.set(0, "Billy");
        System.out.println(names.getValue());
        try {
            names.set(100, "Slartibartfast");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception thrown correctly");
        }
        // test the last index method
        int index = names.lastIndexOf("Claire");
        System.out.println("Last index of Claire is " + index);
        // call the trimToSize method -- should ensure that works in method
        names.trimToSize();
        // print the final list
        System.out.println("List contents:");
        names.displayArray();
    }
}
