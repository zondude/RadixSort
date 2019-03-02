/*
 * Winter TCSS 342
 * Assignment 2
 */

package tests;

import static org.junit.Assert.*;

import exceptions.EmptyCollectionException;
import org.junit.Before;
import org.junit.Test;
import structures.LinkedOutputRestrictedDeque;

/**
 * @author Jonathan Kim
 * @version 29 January 2019
 */
public class OutputRestrictedDequeADTTest {
  
    /**
     * an instance variable myList to use for the tests.
     */
    private LinkedOutputRestrictedDeque<Integer> myList;

    /**
     * 
     */
    @Before
    public void setUp() {
        myList = new LinkedOutputRestrictedDeque<Integer>();
        
    }

    /**
     * Test method for {@link structures.
     * OutputRestrictedDequeADT#enqueueAtFront(java.lang.Object)}.
     */
    @Test
    public void testEnqueueAtFront() {
        myList.enqueue(1);
        myList.enqueue(2);
        myList.enqueueAtFront(3);
        assertEquals((Integer) 3, myList.first());
    }
    
    /**
     * Test method for {@link structures.
     * OutputRestrictedDequeADT#enqueueAtFront(java.lang.Object)}.
     */
    @Test
    public void testEnqueueAtFrontEmpty() {
        myList.enqueueAtFront(1);
        assertEquals((Integer) 1, myList.first());
    }

    /**
     * Test method for {@link structures.QueueADT#enqueue(java.lang.Object)}.
     */
    @Test
    public void testEnqueue() {
        myList.enqueue(1);
        assertEquals((Integer) 1, myList.first());
    }

    /**
     * Test method for {@link structures.QueueADT#dequeue()}.
     */
    @Test
    public void testDequeue() {
        myList.enqueue(1);
        myList.enqueue(2);
        myList.enqueue(3);
        assertEquals((Integer) 1, myList.dequeue());
    }
    
    /**
     * Test method for {@link structures.QueueADT#dequeue()}.
     */
    @Test (expected = EmptyCollectionException.class)
    public void testDequeueEmpty() {
        assertEquals("The queue is empty.", myList.dequeue());
    }

    /**
     * Test method for {@link structures.QueueADT#first()}.
     */
    @Test
    public void testFirst() {
        myList.enqueue(1);
        myList.enqueue(2);
        assertEquals((Integer) 1, myList.first());
    }
    
    /**
     * Test method for {@link structures.QueueADT#first()}.
     */
    @Test (expected = EmptyCollectionException.class)
    public void testFirstSizeZero() { 
        assertEquals("The queue is empty.", myList.first());
    }

    /**
     * Test method for {@link structures.QueueADT#size()}.
     */
    @Test
    public void testSize() {
        assertEquals(0, myList.size());
    }

    /**
     * Test method for {@link structures.QueueADT#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertEquals(true, myList.isEmpty());
    }
    
    /**
     * Test method for {@link structures.QueueADT#isEmpty()}.
     */
    @Test
    public void testIsEmptyFalse() {
        myList.enqueue(5);
        assertEquals(false, myList.isEmpty());
    }
    
    /**
     * Test method for {@link structures.QueueADT#toString()}.
     */
    @Test
    public void testtoString() {
        myList.enqueue(5);
        myList.enqueue(6);
        assertEquals("front -> 5, 6", myList.toString());
    }
    
    /**
     * Test method for {@link structures.QueueADT#toString()}.
     */
    @Test
    public void testtoStringEmpty() {
        assertEquals("", myList.toString());
    }

}
