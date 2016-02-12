
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        Item item;
        Node before;
        Node next;  
    }
    
    private Node first;
    private Node last; 
    private int length;
    
    public Deque() {
        first = null;
        last = null;
        length = 0;
    }
    
    public boolean isEmpty() {
        return length == 0;
    }
    
    public int size(){
        return length;
    }
    
    public void addFirst(Item item) {
        if (item == null){
            throwNullPointerException();
        }
        Node lastFirst = first;
        first = new Node();
        first.item = item;
        first.before = null;
        first.next = lastFirst;
        if (first.next != null) {
            first.next.before = first;
        }
        length++;
        if (length == 1) {
            last = first;
        }
    }
    
    public void addLast(Item item) {
        if (item == null){
            throwNullPointerException();
        }
        if (length == 0) {
            addFirst(item);
        }
        else {
            Node lastLast = last;
            last = new Node();
            last.item = item;
            lastLast.next = last;
            last.before = lastLast;
            last.before.next = last;
            last.next = null;
            length++;
        }
         
    }
    
    public Item removeFirst() {
        if (length == 0) {
            throwNoElementException(); 
        }
        
        Item item = first.item;
        first = first.next;
        first.before = null;
        length--;
        return item;
       
    }
    
    public Item removeLast() {
        if (length == 0) {
            throwNoElementException();
        }
       
        Item item = last.item;
        last = last.before;
        if (last != null) {
            last.next = null;
        }
        length--;
        return item; 
      
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throwNoElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
    }  
    
    private void throwNullPointerException() {
        throw new java.lang.NullPointerException();
    }
    
    private static void throwNoElementException() {
        throw new java.util.NoSuchElementException();
    }
    
    public static void main(String[] args) {
        Deque<Integer> integers = new Deque<Integer>();
        
        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        
        for (int i = 0; i < 3; i++) {
            System.out.println(integers.removeLast());
        }
        
        integers.addLast(4);
        integers.addLast(5);
        integers.addFirst(3);
        
        for (int i = 0; i < 3; i++) {
            System.out.println(integers.removeLast());
        }
        
        System.out.println("length is " + integers.length);
        
        integers.addFirst(21);
        integers.addLast(22);
        integers.addFirst(20);
        integers.addFirst(100);
        integers.addLast(1000);
        integers.removeFirst();
        integers.removeLast();
        
        System.out.println("Testing iterator: ");
        
        Iterator<Integer> integersIterator = integers.iterator();
        
        while (integersIterator.hasNext()) {
            System.out.println(integersIterator.next());
        }
            
    }
    
}