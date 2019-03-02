/*
 * Winter TCSS 342
 * Assignment 2
 */

package applications;

/**
 * @author Jonathan Kim & Alan Fowler acfowler@uw.edu
 * @version 1.1
 */
public final class QueueDemoMain {

    /**
     * Private constructor to inhibit instantiation.
     */
    private QueueDemoMain() {
    }

    /**
     * Simple graphical Stack demo.
     * 
     * @param theArgs array of Strings
     */
    public static void main(final String[] theArgs) {
        new QueueDemoGUI().display();
    }
}
