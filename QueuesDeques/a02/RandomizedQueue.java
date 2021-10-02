package a02;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
* A randomized queue is similar to a stack or queue, except that the item
* removed is chosen uniformly at random from items in the data structure. 
* 
* @author Jordan Bramhall
* @author Kevin Mora
* @author Sungjoo Han
*/
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue;	  // Generic array representing the queue.
    private int queueFinal;   /* Index of the end of the queue,
                                 also the number of elements in the queue. */
    
    @SuppressWarnings("unchecked")
	public RandomizedQueue(){
        queue = (Item[]) new Object[1]; 
        queueFinal = 0;
    }
    
    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return queueFinal == 0;
    }

    /**
     * Returns the number of items in the queue.
     */
    public int size() {
        return queueFinal;
    }

    /**
     * Changes the queue size to the specified size.
     * @param newSize is the new queue size.
     */
    private void resize(int newSize) {
    	// copyOfRange(short[] original, int from, int to)
        Item[] newArray = Arrays.copyOfRange(queue, 0, newSize);
        queue = newArray; // Resizes the queue
    }
    
    /**
     * Adds an element to the queue. 
     * Doubling the size of the array when it is full.
     * @param elem is the new queue entry.
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (queueFinal == queue.length) {
        	resize(queue.length * 2);		// Doubles the size if the queue's length is equivalent to the size of the array.
        }
        queue[queueFinal++] = item; 		// Increases the index of the queue by a margin of one, and assigns the value to the generic instance.
    }
    
    /**
     * Every operation takes guaranteed constant (amortized) time.
     * Less wasted space: resizing array implementation.
     * Halving the size when it is 1/2 full leads to thrashing.
     * 
     * @returns a uniformly random entry from the queue.
     */
    public Item dequeue() {    
        if (isEmpty()) { 
        	throw new NoSuchElementException("There are no elements"); 
        }
        if (queueFinal <= queue.length / 4) {
        	resize(queue.length / 2);		// Halves the size of the array when it is 1/4 full.
        }
        int index = StdRandom.uniform(0,queueFinal);	// Selects a random index.
        Item returnValue = queue[index];   				/* Saves the element behind the randomly selected index 
                                               				which will be returned later. */
        queue[index] = queue[--queueFinal]; 			/* Fills the hole (randomly selected index is being deleted) 
                                               				with the last element in the queue. */
        queue[queueFinal] = null;         				// Avoids thrashing.
        
        return returnValue;
    }

    /**
     * Returns the value of a random element in 
     * the queue; doesn't modify the queue.
     * @return random entry of the queue.
     */
    public Item sample() {
    	if (isEmpty()) {
            throw new NoSuchElementException();
        }
    	int index = StdRandom.uniform(0,queueFinal);	// Selects a random index [0,N).
        return queue[index];
    }

    /**
     * Returns an independent iterator over items in random order.
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }
    /*
     * Every iteration will return entries in a different order.
     */
    private class RandomizedQueueIterator<T> implements Iterator<T> {
        @Override
        public boolean hasNext() {
        	return size() > 0;
        }
        
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (isEmpty()) {
            	throw new NoSuchElementException();
            }
            return (T) dequeue();
        }
        
        @Override
        public void remove() {
	    	throw new UnsupportedOperationException();
	    }
    }
}