import java.util.ArrayList;

class SearchThread extends Thread{
    private int start;
    private int end;
    private ArrayList<String> list;
    private String s;
    private boolean exists;

    public SearchThread(int start, int end, ArrayList<String> list, String s){
        this.start = start;
        this.end = end;
        this.list = list;
        this.s = s;
    }

    @Override
    public void run(){
        for(int i = start; i < end; i++){
            if(list.get(i).equals(s)){
                System.out.println(Thread.currentThread().getName() + " found the name.");
                this.exists = true;
            } else {
                this.exists = false;
            }
        }
    }

    public boolean getBool(){
        return exists;
    }

}

// a class for storing a set of strings
class Set {
    // items are stored in an ArrayList
    private ArrayList<String> items;

    public Set() {
        items = new ArrayList<String>();
    }

    // add an item in
    public void add(String element) {
        items.add(element);
    }

    // check if an item exists
    // TODO parallelize this process
    boolean exists(String element) {

        SearchThread t1 = new SearchThread(1, 50, items, element);
        SearchThread t2 = new SearchThread(51, 100, items, element);
        t1.setName("Thread 1");
        t2.setName("Thread 2");

        int half = items.size() / 2;
        int secondHalf = half + 1;

        for(int i = 0; i < half; i++){
            if(items.get(i).equals(element)){
                t1.start();
                try {
                    t1.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return true;
            }
        }

        for(int i = secondHalf; i < items.size(); i++){
            if(items.get(i).equals(element)){
                t2.start();
                try {
                    t2.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }
}


public class Lab15 {
    public static void main(String args[]) {
        // create a test set
        Set names = new Set();

        // add 100 random names
        names.add("Santiago");
        names.add("Darcel");
        names.add("Illa");
        names.add("Myrta");
        names.add("Greg");
        names.add("Annabell");
        names.add("Lonnie");
        names.add("Ramona");
        names.add("Pearl");
        names.add("Jaqueline");
        names.add("Winnifred");
        names.add("Roland");
        names.add("Alysa");
        names.add("Hilda");
        names.add("Jeanett");
        names.add("Kimberly");
        names.add("Annalee");
        names.add("Deane");
        names.add("Brittani");
        names.add("Natacha");
        names.add("Herta");
        names.add("Giovanna");
        names.add("Tressa");
        names.add("Morton");
        names.add("Ela");
        names.add("Chante");
        names.add("Melani");
        names.add("Omega");
        names.add("Roman");
        names.add("Rashida");
        names.add("Myles");
        names.add("Devorah");
        names.add("Luther");
        names.add("Annette");
        names.add("Tessa");
        names.add("Darryl");
        names.add("Thad");
        names.add("Freda");
        names.add("Laurence");
        names.add("Asa");
        names.add("Burma");
        names.add("Lila");
        names.add("Tierra");
        names.add("Idell");
        names.add("Ninfa");
        names.add("Denae");
        names.add("Randy");
        names.add("Milan");
        names.add("Karey");
        names.add("Carter");
        names.add("Arlette");
        names.add("Estela");
        names.add("Dacia");
        names.add("Cory");
        names.add("Leatrice");
        names.add("Maura");
        names.add("Tiana");
        names.add("Billy");
        names.add("Brittany");
        names.add("Kendall");
        names.add("Merri");
        names.add("Liane");
        names.add("Simone");
        names.add("Hilaria");
        names.add("Neely");
        names.add("Jeromy");
        names.add("Kiyoko");
        names.add("Alta");
        names.add("Lucien");
        names.add("Patria");
        names.add("Alphonso");
        names.add("Jenae");
        names.add("Sanda");
        names.add("Suk");
        names.add("Berry");
        names.add("Terry");
        names.add("Wei");
        names.add("Milagros");
        names.add("Adrianne");
        names.add("Dusti");
        names.add("Ivy");
        names.add("Cyndi");
        names.add("Quiana");
        names.add("Ellyn");
        names.add("Garnet");
        names.add("Hipolito");
        names.add("Eugena");
        names.add("Laveta");
        names.add("Eunice");
        names.add("Arnulfo");
        names.add("Luz");
        names.add("Ranee");
        names.add("Adolfo");
        names.add("Leola");
        names.add("Miguel");
        names.add("Jacque");
        names.add("Aja");
        names.add("Kelsie");
        names.add("Pamula");
        names.add("Marcus");

        // test
        System.out.printf("%s exists: %b.\n", "Ivy", names.exists("Ivy"));
        System.out.printf("%s exists: %b.\n", "Mark", names.exists("Mark"));
        System.out.printf("%s exists: %b.\n", "Pearl", names.exists("Pearl"));
        System.out.printf("%s exists: %b.\n", "Georgette", names.exists("Georgette"));
        System.out.printf("%s exists: %b.\n", "Simone", names.exists("Simone"));
        System.out.printf("%s exists: %b.\n", "Bobby", names.exists("Bobby"));
        System.out.printf("%s exists: %b.\n", "Roland", names.exists("Roland"));
        System.out.printf("%s exists: %b.\n", "Agatha", names.exists("Agatha"));
        System.out.printf("%s exists: %b.\n", "Luz", names.exists("Luz"));
        System.out.printf("%s exists: %b.\n", "Herman", names.exists("Herman"));
    }
}
