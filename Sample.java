import stdlib.StdOut;

public class Sample {
    // Entry point.
    public static void main(String[] args) {
        //lo, hi, k, mode --> command line arguements
        int lo = Integer.parseInt(args[0]);
        int hi = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        
        String mode = args[3];
    
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        for (int i = lo; i <= hi; i++) { //random q containing low --> hi
            q.enqueue(i);
        }
        
        if(!mode.equals("+") && !mode.equals("-")) { //if mode diff from + or -
            throw new IllegalArgumentException("Illegal mode");
        }
        else if (mode.equals("+")) { //if mode +, sample/write k int FROM q to stdoutput
            for (int x = 0; x <k; x++) {
                StdOut.println(q.sample());
            }
        }
        else if (mode.equals("-")) { //if mode -, dequeue/write k int FROM q to stdoutput
            for (int x = 0; x < k; x++) {
                StdOut.println(q.dequeue());
            }
        }
    }
}
