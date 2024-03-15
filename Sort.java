import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedDeque<String> d = new LinkedDeque<String>(); //create queue d

        for (String w : StdIn.readAllStrings()) {
            if (!d.isEmpty() && less(w, d.peekFirst())){ // if < first in d
                d.addFirst(w); //add w to front
            }
            
            else if (!d.isEmpty() && less(d.peekLast(), w)){ // if > last in d
                
                d.addLast(w); //add w to back
            }

            else { //else
   
        LinkedStack<String> s = new LinkedStack<>(); 
            
        while (!d.isEmpty() && less(d.peekFirst(), w)){ // remove front of deque if < w
                 s.push(d.removeFirst()); //push onto stack
                }
                d.addFirst(w); //w added to front of deque d

            for (String f : s) {
                d.addFirst(f); //add back to front 
                }
            }
        }

            for (String f : d) {
                StdOut.println(f); //write words from d to stdout
            }
        }




    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
