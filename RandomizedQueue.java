import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays; 

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] items;
    private int N;
   // private int head;
    private int tail;
    //private int dequeuedIndex;
    //private int sampledIndex;
    
    public RandomizedQueue() { 
        N = 0;
        //head = N; 
        tail = N; 
        items = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        /*if (N == items.length) {
            resize(2 * items.length);
        }
        else */if (tail == items.length) {
            //System.out.println("Hello from inside the tail == items.length condition");
            resize(2*items.length);//(tail + 1);//items.length);
        }
        
        if (item == null) {
            throwNullPointerException();
        }
        items[tail++] = item;
        //items[N++] = item;
        //System.out.println("items after enqueue: " + Arrays.toString(items));
      
        N++;
    }
    
    public Item dequeue() {
        if (N == 0) {
            throwNoElementException();
        }
        
        int dequeuedIndex; 
        
        do {
            dequeuedIndex = StdRandom.uniform(tail);
        }while (items[dequeuedIndex] == null);
        
        Item item = items[dequeuedIndex];
        items[dequeuedIndex] = null;
        //System.out.println("items after dequeue: " + Arrays.toString(items));
        
        N--;
        if (N > 0 && N == items.length/4) {
            resize(items.length/2);
        }
        
        return item;
    }
    
    public Item sample() {
        if (N == 0) {
            throwNoElementException();
        }
        int sampledIndex;//= StdRandom.uniform(head,tail);
        do {
           sampledIndex = StdRandom.uniform(tail);
        }while (items[sampledIndex] == null);
        
        return items[sampledIndex];
    }
    
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {
        private Item[] copy;
        private int numItems;
        private int nextIndex;
        
        private ArrayIterator() {
            copy = (Item[]) new Object[N];
            int j = 0;
            for (int i = 0; i < items.length; i++) {
                if(items[i] != null) { 
                    copy[j++] = items[i];
                    //j++;
                }
            }
            shuffleArray(copy);
            numItems = N;
        }
        
        private int i = 0;
        
        public boolean hasNext() {
           return numItems != 0;//i < N;//tail;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throwNoElementException();
            }
            
            //Item next = items[i++];
            do {
                nextIndex = StdRandom.uniform(copy.length);
            }while (copy[nextIndex] == null);
            
            Item next = copy[nextIndex];
            copy[nextIndex] = null;
            
            numItems--;
            return next;
        }
    }
    
    private void shuffleArray(Item[] arr) {
       
        for (int i = 0; i < arr.length; i++) {
            
            int switchIndex = StdRandom.uniform(i+1);
            Item temp = arr[switchIndex];
            arr[switchIndex] = arr[i];
            arr[i] = temp;
           
        }
        
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        //System.out.println("head is: " + head);
        int j = 0;
        for(int i = 0; i < tail; i++) {
            if (items[i] != null) { 
                copy[j++] = items[i];
                //j++;
            }
        }
        
        //System.out.println("items: " + Arrays.toString(items));
        //System.out.println("copy: " + Arrays.toString(copy));
        
        items = copy;
        //System.out.println("items after copy: " + Arrays.toString(items));
        
        tail = N;
    }
    
    private void throwNullPointerException() {
        throw new java.lang.NullPointerException();
    }
    
    private static void throwNoElementException() {
        throw new java.util.NoSuchElementException();
    }
    
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> integers = new RandomizedQueue<Integer>();
         
        integers.enqueue(25);
        integers.dequeue();
        System.out.println("size: " + integers.size());
        
        integers.enqueue(1);
        integers.enqueue(2);
        integers.enqueue(3);
        integers.enqueue(4);
        //integers.dequeue();
        integers.enqueue(10);
        //System.out.println("tail: " + integers.tail);
        integers.enqueue(5);
        //System.out.println("tail after enqueue: " + integers.tail);
        //System.out.println("array length: " + integers.length());
        
        //System.out.println("Testing sample method: " + integers.sample());
        //integers.dequeue();
        //integers.dequeue();
        //integers.dequeue();
        
        Iterator<Integer> integersIterator = integers.iterator();
        Iterator<Integer> integersIterator2 = integers.iterator();
        System.out.println("Iterator test: ");
        while (integersIterator.hasNext()) {
            System.out.print(integersIterator.next() + " ");
        }
        System.out.println();
        System.out.println("Iterator 2 test: ");
        while(integersIterator2.hasNext()) {
            System.out.print(integersIterator2.next() + " ");
        }
        StdOut.println();
        
        
        
        //Trace array resizing test 
        integers.enqueue(1);
        integers.enqueue(2);
        integers.enqueue(3);
        integers.enqueue(4);
        integers.enqueue(5);
        System.out.println(integers.dequeue());
        System.out.println(integers.dequeue());
        System.out.println(integers.dequeue());
        System.out.println(integers.dequeue());
        System.out.println(integers.dequeue());
        System.out.println("size: " + integers.size());
        
        
    }
    
   }

                                      
                                      
                                    
                                   