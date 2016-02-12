import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays; //delete later

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] items;
    private int N;
    private int head;
    public int tail;
    
    public RandomizedQueue() { 
        N = 0;
        head = N; //
        tail = N; //
        items = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        if (N == items.length) {
            resize(2 * items.length);
        }
        else if (tail == items.length) {
            System.out.println("Hello from inside the tail == items.length condition");
            resize(items.length);
        }
        
        if (item == null) {
            throwNullPointerException();
        }
        items[tail++] = item;
        System.out.println("items after enqueue: " + Arrays.toString(items));
      
        N++;
    }
    
    public Item dequeue() {
        if (N == 0) {
            throwNoElementException();
        }
        Item item = items[head];
        items[head++] = null; // delete head and update head pointer
        System.out.println("items after dequeue: " + Arrays.toString(items));
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
        int sampledIndex = StdRandom.uniform(head,tail);
        return items[sampledIndex];
    }
    
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item> {
        private int i = head;
        
        public boolean hasNext() {
           return i < tail;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throwNoElementException();
            }
            
            Item next = items[i++];
            return next;
        }
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        System.out.println("head is: " + head);
        int j = 0;
        for(int i = head; i < tail; i++) {
            copy[j] = items[i];
            j++;
        }
        
        System.out.println("items: " + Arrays.toString(items));
        System.out.println("copy: " + Arrays.toString(copy));
        
        items = copy;
        System.out.println("items after copy: " + Arrays.toString(items));
        head = 0;
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
        
        /* 
        integers.enqueue(1);
        integers.enqueue(2);
        integers.enqueue(3);
        integers.enqueue(4);
        integers.dequeue();
        System.out.println("tail: " + integers.tail);
        integers.enqueue(5);
        System.out.println("tail after enqueue: " + integers.tail);
        System.out.println("array length: " + integers.length());
        
        System.out.println("Testing sample method: " + integers.sample());
        
        Iterator<Integer> integersIterator = integers.iterator();
        
        while (integersIterator.hasNext()) {
            System.out.print(integersIterator.next() + " ");
        }
        */
        
        /* Trace array resizing test */
        integers.enqueue(1);
        integers.enqueue(2);
        integers.enqueue(3);
        integers.enqueue(4);
        integers.enqueue(5);
        integers.dequeue();
        integers.dequeue();
        integers.dequeue();
        integers.dequeue();
        
    }
    
}
                                      
                                      
                                    
                                   