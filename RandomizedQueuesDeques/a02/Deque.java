package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements a double-ended queue or deque (pronounced "deck") is a 
 * generalization of a stack and a queue that supports inserting and removing 
 * items from either the front or the back of the data structure.
 * 
 * @author Kevin Mora
 * @author Sungjoo Han
 * @author Jordan Bramhall
 */
public class Deque<Item> implements Iterable<Item> {
    private Node front;      	// Front node in queue
    private Node rear;       	// Rear node in queue
    private int size;    	    // Number of items in the queue

    /**
     * Creates a node that includes references to the item on the left and right of the node.
     */
    private class Node {
        private Item data;		// Data    
        private Node right;  	// Reference to the right of the item
        private Node left;   	// Reference to the left of item
    }

    /**
     * Create an empty deque.
     */
    public Deque() {
       this.front = null;		// Setting the front item in queue as null
       this.rear = null;		// Setting the rear item in queue as null
       this.size = 0;			// Setting size as 0
    }

    /**
     * Confirms if the deque is empty.
     * 
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the size of the deque.
     * 
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Adds an item to the beginning of the deque.
     * 
     * @param item
     */
    public void addFirst(Item item) {
        if (item != null) {
            Node newFront = new Node();			// Create a new node.
            newFront.data = item;				// Assign item as newFront's data.

            if(isEmpty()) {
                front = newFront;				// Assign newFront as the front of the deque.
                rear = newFront;				// Assign newFront as the rear of the deque.
            }
            else {
                newFront.right = front;			// Right reference of newFront = current front.
                front.left = newFront;			// Left reference of current front = newFront.
                front = newFront;				// Set newFront as the front of the deque.
            }
            size++;								// Increase the size.
        }
        else {
            throw new NullPointerException("Cannot insert null item.");
        }
    }

    /**
     * Adds an item to the end of the deque. 
     * 
     * @param item
     */
    public void addLast(Item item) {
        if (item != null) {
            Node newRear = new Node();			// Create a node.
            newRear.data = item;				// Assign item as newRear's data.
            
            if(isEmpty()) {
                front = newRear;				// Assign newRear as the front of the deque.
                rear = newRear;					// Assign newRear as the rear of the deque.
            }
            else {
                rear.right = newRear;			// Current rear's right reference = newRear.
                newRear.left = rear;			// newRear's left reference = current rear.
                rear = newRear;					// Set newRear as the rear of the deque. 
            }
            size++;								// Increase size.
        }
        else {
            throw new NullPointerException("Cannot insert null item.");
        }
    }

    /**
     * Removes the node at the front of the deque.
     * 
     * @return
     */
    public Item removeFirst() {
        if (!isEmpty()) {
            Item deletedFront = front.data;		// Assign the current front as the item that will be deleted.
            front = front.right;				// Assign the node to the right of the current front as the new front of the deqeue. 
            size--;								// Decrease the size of the deqeue.
            if (size <= 0) {
                front = null;					// If there's nothing in the deque, set the front as null.
                rear = null;					// If there's nothing in the deque, set the rear as null.
            }
            return deletedFront;				// Return the front that we deleted.
        } else {
            throw new NoSuchElementException("No item to remove from deque.");
        }
    }
    
    /**
     * Remove the node at the rear of the deque.
     * 
     * @return
     */
    public Item removeLast() {
        if (!isEmpty()) {
            Item deletedRear = rear.data;		// Assign the current rear as the item that will be deleted.
            rear = rear.left;					// Assign the node to the left of the current rear as the new rear of the deqeue.
            size--;								// Decrease the size of the deqeue.
            if (size <= 0) {
                rear = null;					// If there is nothing in the deqeue, set the rear as null;
                front = null;					// If there is nothing in the deqeue, set the front as null;
            }
            return deletedRear;					// Return the rear that we deleted.
        } else {
            throw new NoSuchElementException("No item to remove from deque.");
        }
    }
    
    /**
     * Returns an iterator over items in order from front to end of the deque.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    /**
     * Iteration returns items in order from front to end of the deque.
     */
    private class DequeIterator implements Iterator<Item>{
        private Node currentNode = front;   	// Ongoing node is assigned to the front value in deque.
        
        /**
         * Returns true if the iteration has more elements, false if it doesn't.
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;        	// If the next location is not empty, proceed until false.
        }
        
        /**
         * Returns the next element in the iteration.
         */
        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException(); //fix location
            }
            Item item = currentNode.data;		//Assigns the currentNode to item.
            currentNode = currentNode.right;    //Assigning the node to the right of the currentNode as the new currentNode.	
            return item;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}