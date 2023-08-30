import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream(args[0]); //Get the file - will change to command line later
        Scanner input = new Scanner(file); //Scanner the file
        int size = input.nextInt(); //The size is the first thing in the file
        Graph<Object> graph = new Graph<>(size); //Create the graph
        input.nextLine(); //Move to the next line so we don't try to read in the size
        int n = 0; //Iterator var
        while (input.hasNextLine()) { //While the file is still being read
            String line = input.nextLine(); //Store the line in a String
            String[] s = line.split("\\s+"); //Split it by spaces into an array
            graph.setValue(n, s[0]); //Set the value for each class - index 0 of the String array stores the class
            n++; //Iterate upwards
        }
        input.close(); //Close the first scanner
        file.close(); //Close the first fileInputStream
        FileInputStream f2 = new FileInputStream(args[0]); //Reopen the file
        Scanner edge = new Scanner(f2); //New Scanner
        edge.nextLine(); //Move to next line so we aren't reading the size
        while (edge.hasNextLine()) { //While the file is still being read
            String line = edge.nextLine(); //Store the line in a String
            String[] s = line.split("\\s+"); //Split it by spaces into an array
            for (int i = 2; i < s.length; i++) { //Now we need to insert the edges, or the dependencies of classes to their prereqs
                graph.insertEdge(s[i], s[0]); //Insert the edge
            }
        }
        topologicalSort(graph, size); //Call the topologicalSort method

    }



    public static void topologicalSort(Graph g, int size) { //Topological sort method
        AList ordering = new AList(); //Ordering arraylist
        Object[] aset = new String[size]; //Active set method
        AList indexing = new AList(); //Indexing arraylist - used to find the index of the course with no edges
        int z = 0; //Incrementing var
        //This nested for loop finds the first node with no edges going out of it
        for (int i = 0; i < size; i++) { //Loop through the matrix
            int x = 0; //This var is very important - increment up when a node is not connected to another with an edge
            for (int j = 0; j < size; j++) { //Nested for loop to loop through matrix
                if (!g.isEdge(j, i)) { //If there is not an edge connecting nodes
                    x++; //Increment the counter
                    if (x == size) { //If the counter is at size - that means a node has no edges coming in to it
                        aset[z] = g.getValue(i); //Add that node to the active set
                        indexing.append(i); //Get the index of the node with no edges
                        z++; //Increment
                    }
                }
            }
        }
        int i = 0; //Incrementing var
        while(aset[i] != null){ //While there are values in the active set
            ordering.append(aset[i]); //Add the first node to the ordering
            for(int j = 0; j < size; j++){ //Loop through the graph
                Object obj = indexing.getValue(); //Object var to turn the value into an int for indexing
                String str = obj.toString(); //Turn object to String
                int index = Integer.parseInt(str); //Finally parse into an int
                if(g.isEdge(index, j)){ //If the first node is an edge with j
                    g.removeEdge(index, j); //Remove the edge
                    aset[i] = g.getValue(j); //Add the value at index j into the active set
                    indexing.append(j); //Add the index of j into the indexing arraylist
                    ordering.append(aset[i]); //Add the new node in the active set to ordering
                    int k = 0; //Incrementing var
                    while(k < size){ //While var is less than the size of the graph
                        if(g.isEdge(j, k)){ //If it is an edge
                            g.removeEdge(j, k); //Remove the edge
                            aset[j] = g.getValue(k); //Add the value at index k to the active set
                            indexing.append(k); //Add the index k to the indexing arraylist
                            ordering.append(aset[j]); //Add the new node in the active set to ordering
                            indexing.next(); //Move to the next index stored in indexing array list - removing this changes output in slight ways
                        }
                        k++; //Increment upwards in the while loop
                    }
                }
            }
            i++; //Increment upwards in the outside while loop
        }
        //Now I check if any values exist that are not in the ordering - meaning it is not a DAG
        //This is faulty because my topologicalSort skips over one or two classes for some reason - could not figure out why
        int h = 0; //Incrementing var
        for(int v = 0; v < g.getSize(); v++){ //Loop through graph
            for(int m = 0; m < g.getSize(); m++){ //Nested for loop to loop through graph
                if(ordering.getValue() != g.getValue(v)){ //If the value in the ordering is not equal to the graph value
                    h++; //Increment upwards
                    if(h == size){ //If the increment var is equal to size
                        System.out.println("This is not a DAG! These courses could not be completed: " + g.getValue(v)); //Output that it is not a DAG
                        ordering.next(); //Move to the next value in the ordering
                    }
                }
            }
        }

        //Print out the ordering
        int x = 0; //Incrementing var
        ordering.moveToStart(); //Move to the start of the ordering
        while (x < ordering.length()) { //While there are still values to loop through
            System.out.println(ordering.getValue()); //Print out the ordering
            ordering.next(); //Move to the next value in the ordering
            x++; //Increment upwards
        }
    }
}
