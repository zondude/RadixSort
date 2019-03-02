package structures;


/**
 * Method to enqueue to the front of the queue.
 * @author Jonathan Kim
 * @version 28 January 2019
 * @param <E>
 *
 */
public class LinkedOutputRestrictedDeque<E> extends LinkedQueue<E> 
    implements OutputRestrictedDequeADT<E> {

    @Override
    public void enqueueAtFront(final E theElement) {
        if (mySize == 0) {
            myFront = new Node<E>(theElement);
            myRear = myFront;
        } else {
            final Node<E> node = new Node<E>(theElement, myFront);
            myFront = node;
            
        }
        mySize++;
    }

}
