# Randomized Queues Deques
Write a generic data type for a deque and a randomized queue. Implement elementary data structures using arrays and linked lists, as well as generics and iterators.

<img width="450" alt="Screen Shot 2021-10-03 at 14 16 21" src="https://user-images.githubusercontent.com/83437383/135770092-b0987de6-d197-4c39-b6da-2944e0e3d46a.png">

## Dequeue 
A double-ended queue or deque is a generalization of a stack and a queue that supports inserting and removing items from either the front or the back of the data structure. Create a generic data type Deque that implements the following API:

```java
public class Deque<Item> implements Iterable<Item> {
   public Deque()                           // construct an empty deque
   public boolean isEmpty()                 // is the deque empty?
   public int size()                        // return the number of items on the deque
   public void addFirst(Item item)          // insert the item at the front
   public void addLast(Item item)           // insert the item at the end
   public Item removeFirst()                // delete and return the item at the front
   public Item removeLast()                 // delete and return the item at the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
}
```

### Corner Cases 
Throw a <b><i>java.lang.NullPointerException</i></b> if the client attempts to add a null item; throw a <b><i>java.util.NoSuchElementException</i></b> if the client attempts to remove an item from an empty deque; throw a <b><i>java.lang.UnsupportedOperationException</i></b> if the client calls the remove() method in the iterator; throw a <b><i>java.util.NoSuchElementException</i></b> if the client calls the next() method in the iterator and there are no more items to return.

### Performance Requirements 
Your deque implementation must support each deque operation (including construction) in constant worst-case time and use space proportional to the number of items currently in the deque. Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.

## Randomized Queue
A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure. Create a generic data type RandomizedQueue that implements the following API:

```java
public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // delete and return a random item
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
}
```

### Corner Cases
The order of two or more iterators to the same randomized queue must be mutually independent; each iterator must maintain its own random order. Throw a <b><i>java.lang.NullPointerException</i></b> if the client attempts to add a null item; throw a <b><i>java.util.NoSuchElementException</i></b> if the client attempts to sample or dequeue an item from an empty randomized queue; throw a <b><i>java.lang.UnsupportedOperationException</i></b> if the client calls the remove() method in the iterator; throw a <b><i>java.util.NoSuchElementException</i></b> if the client calls the next() method in the iterator and there are no more items to return.

### Performance Requirements
Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in constant amortized time and use space proportional to the number of items currently in the queue. That is, any sequence of M randomized queue operations (starting from an empty queue) must take at most <i>cM</i> steps in the worst case, for some constant <i>c</i>. Additionally, your iterator implementation must support next() and hasNext() in constant worst-case time and construction in linear time; you may use a linear amount of extra memory per iterator.

## Client
Write a client program Subset.java that takes a command-line integer k; reads in a sequence of strings from standard input using StdIn.readString(); and prints out exactly k of them, uniformly at random. Each item from the sequence can be printed out at most once.

```java
% echo A B C D E F G H I | java Subset 3       % echo AA BB BB BB BB BB CC CC | java Subset 8
C                                              BB
G                                              AA
A                                              BB
                                               CC
% echo A B C D E F G H I | java Subset 3       BB
E                                              BB
F                                              CC
G                                              BB
```

Your program must implement the following API:
```java
public class Subset {
   public static void main(String[] args)
}
```

You may assume that <i>0 ≤ k ≤ N</i>, where <i>N</i> is the number of string on standard input.

The running time of Subset must be linear in the size of the input. You may use only a constant amount of memory plus either one <i>Deque</i> or <i>RandomizedQueue</i> object of maximum size at most <i>N</i>.
