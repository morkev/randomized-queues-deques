package a02;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Client program Subset.
 * 
 * Takes a command-line integer k; reads in a sequence of strings from standard input 
 * using StdIn.readString(); and prints out exactly k of them, uniformly at random. 
 * 
 * Each item from the sequence can be printed out at most once.
 * 
 * @author Sungjoo Han
 * @author Jordan Bramhall
 * @author Kevin Mora
 */
public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        StringBuilder sb = new StringBuilder();
        String letter = "";
        
        StdOut.print("Enter your first value. Type 'END' when finished: ");

        while (!StdIn.isEmpty()) {
            StdOut.print("Enter next letter: ");
            letter = StdIn.readString();
            if (letter.equalsIgnoreCase("end")) {       // Program ends if user types {end}.
                System.out.println("DONE" + "\n");
                break;
            }
            rq.enqueue(letter);                         // Enqueue the user's input.
            sb.append(letter.toUpperCase() + " ");      // Add the value to StringBuilder and make it upperCase.
        }        
        
        StdOut.print("% echo ");
        System.out.print(sb.toString());                // Display user's input as a String.
        int k = rq.size();                              // Request and store dimensions of queue.
        StdOut.println("| java Subset " + k);	
        
        rq.forEach((r) -> {                             // Lambda operation; prints every element in the queue in stochastic order.
            StdOut.println(r.toUpperCase() + " ");
        });
    }
}
