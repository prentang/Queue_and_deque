import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    //instance variables
    private Item[] q; //array items of q
    private int n; //size of q

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2]; //q with cap of 2
        
        this.n = 0; //init instance var
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
        if (item == null){ 
            throw new NullPointerException("item is null");
        }

        if (n==q.length){  //if full
            resize(q.length * 2); //resize to 2x capacity
        }
        
        q[n] = item; //insert item -> item in q 

        n++; //increment
    }

    // Returns a random item from this queue.
    public Item sample() {
        if (isEmpty()){
            throw new NoSuchElementException("Random queue is empty");
        }
        
        int r = StdRandom.uniform(0, n); //r random integer from 0->n, non inclusive. 
        
        return q[r];
    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Random queue is empty");
        }
       
        int r = StdRandom.uniform(0,n); //random 0->n
        
        Item item = q[r]; //save q[r] in item, ^ random int from 0->n
        
        q[r] = q[n-1]; //set q[r] to q[n-1]
        q[n-1] = null; //set q[n-1] to null
        

        if (n == q.length/4){ //if at quarter (1/4) cap. 
            resize(q.length/2); //resize to 1/2 cap.
        }

        n--; //decrement
        
        return item;
    }
    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // Returns a string representation of this queue.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : this) {
            sb.append(item);
            sb.append(", ");
        }
        return n > 0 ? "[" + sb.substring(0, sb.length() - 2) + "]" : "[]";
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] items; //array
        private int current; //index -> current

        // Constructs an iterator.
        public RandomQueueIterator() {
            items = (Item[]) new Object[n]; //items with cap of n

            for (int i = 0; i<n; i++){ 
                items[i] = q[i]; //n items to q items
            }
            StdRandom.shuffle(items); //shuffle items

            this.current = 0; //init current 
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return current < n; //see if <n to see if more items to iterate
        }

        // Returns the next item.
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator is empty");
            }
            
            return items[current++]; // items at index current AND advance by one (increment)
            
        }
    }

    // Resizes the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
