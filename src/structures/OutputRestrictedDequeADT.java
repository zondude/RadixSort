package structures;

/**
 * @author Jonathan Kim
 * @version 28 January 2019
 * 
 * @param <E>
 */
public interface OutputRestrictedDequeADT<E> extends QueueADT<E> {

    /**
     * 
     * @param theElement
     */
    void enqueueAtFront(E theElement);
}
