/*
 * Winter TCSS 342
 * Assignment 2
 */

package applications;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import structures.LinkedOutputRestrictedDeque;


/**
 * A simple GUI to demonstrate enqueue, dequeue, and enqueue at front operations.
 * 
 * @author Jonathan Kim & Alan Fowler acfowler@uw.edu
 * @version 1.1
 */
public class QueueDemoGUI extends JPanel {
    
    /** A generated version ID for serialization. */
    private static final long serialVersionUID = 4303607662476596133L;

    /** The default width for text fields and text areas. */
    private static final int TEXT_WIDTH = 20;

    /** The default height for text areas. */
    private static final int TEXT_HEIGHT = 6;

    /** The width used in various margins and insets. */
    private static final int PADDING = 20;
    
    /** A message to display when the queue is empty. */
    private static final String EMPTY_MESSAGE = "The Queue is empty.";
    
    /** The escape sequence used to embed a quote symbol in a String. */
    private static final String QUOTE = "\"";
    
    /** A message to display when the queue is empty. */
    private static final String EMPTY_QUEUE = "Cannot enqueue empty data.\n";

    /**
     * Displays current queue contents.
     */
    private final JTextArea myQueueArea;

    /**
     * Input text field.
     */
    private final JTextField myInputText;

    /**
     * Displays all actions performed on the queue.
     */
    private final JTextArea myActionArea;

    /**
     * A button used to enqueue an element on the queue.
     */
    private final JButton myEnqueueButton;

    /**
     * The queue used in the demo.
     */
    
    // local classes
    private final LinkedOutputRestrictedDeque<String> myQueue =
                    new LinkedOutputRestrictedDeque<>();

    /**
     * Sets up the GUI.
     */
    public QueueDemoGUI() {
        super();

        myInputText = new JTextField(TEXT_WIDTH);
        myQueueArea = new JTextArea(TEXT_HEIGHT, TEXT_WIDTH);
        myActionArea = new JTextArea(TEXT_HEIGHT, TEXT_WIDTH);
        myEnqueueButton = new JButton("Enqueue");

        setupComponets();
    }

    /**
     * Helper method to perform the work of setting up the GUI components.
     */
    private void setupComponets() {
        myEnqueueButton.addActionListener(new EnqueueListener());
        myEnqueueButton.setMnemonic(KeyEvent.VK_U);
        
        final JButton enqueueFrontButton = new JButton("Enqueue at front");
        enqueueFrontButton.addActionListener(new EnqueueFrontListener());

        final JButton dequeueButton = new JButton("Dequeue");
        dequeueButton.addActionListener(new DequeueListener());
        dequeueButton.setMnemonic(KeyEvent.VK_O);

        final JLabel enqueueLabel = new JLabel("Add to Queue: ");

        myInputText.setEditable(true);

        myQueueArea.setMargin(new Insets(PADDING, PADDING, PADDING, PADDING));
        myQueueArea.setEditable(false);
        myQueueArea.setFocusable(false);
        myQueueArea.setText(EMPTY_MESSAGE);

        myActionArea.setMargin(new Insets(PADDING, PADDING, PADDING, PADDING));
        myActionArea.setEditable(false);
        myActionArea.setFocusable(false);

        final JPanel inputPanel = new JPanel();
        inputPanel.add(enqueueLabel);
        inputPanel.add(myInputText);
        inputPanel.add(myEnqueueButton);
        inputPanel.add(enqueueFrontButton);
        inputPanel.add(dequeueButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(myQueueArea), BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(myActionArea), BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
    }

    /**
     * Creates and displays the application frame.
     */
    public void display() {
        final JFrame frame = new JFrame("Queue Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        myInputText.grabFocus();
        getRootPane().setDefaultButton(myEnqueueButton);
    }
    
    /**
     * Returns a String representation of the contents of myQueue. 
     * The String will list the one per line with the most recently added element at the top.
     * 
     * @return A String with a vertical display of the elements in myQueue
     */
    private String queueToString() {
        return myQueue.toString();
    }
    
    
    // inner classes

    /**
     * An action listener for the enqueue button.
     */
    private class EnqueueListener implements ActionListener {
        /**
         * Adds the contents of the user entry text field to the front of this
         * queue.
         * 
         * @param theEvent incoming event
         */
        public void actionPerformed(final ActionEvent theEvent) {
            final String toEnqueue = myInputText.getText();
            if (toEnqueue.length() == 0) {
                myActionArea.append(EMPTY_QUEUE);
            } else {
                myQueue.enqueue(toEnqueue);
                myActionArea.append(QUOTE 
                                    + toEnqueue + QUOTE + " was enqueued onto the queue.\n");
                
                myQueueArea.setText(queueToString());
                
                myQueueArea.setCaretPosition(0); // forces scroll up
                myInputText.setText(null); // clears input field
            }
            myInputText.grabFocus();
        }
    }
    
    /**
     * An action listener for the enqueue to the front button.
     */
    private class EnqueueFrontListener implements ActionListener {
        /**
         * Adds the contents of the user entry text field to the front of this
         * queue.
         * 
         * @param theEvent incoming event
         */
        public void actionPerformed(final ActionEvent theEvent) {
            final String toEnqueueFront = myInputText.getText();
            if (toEnqueueFront.length() == 0) {
                myActionArea.append(EMPTY_QUEUE);
            } else {
                myQueue.enqueueAtFront(toEnqueueFront);
                myActionArea.append(QUOTE + toEnqueueFront 
                                    + QUOTE + " was enqueued onto the front of the queue.\n");
                
                myQueueArea.setText(queueToString());
                
                myQueueArea.setCaretPosition(0); // forces scroll up
                myInputText.setText(null); // clears input field
            }
            myInputText.grabFocus();
        }
    }

    /**
     * An action listener for the dequeue button.
     */
    private class DequeueListener implements ActionListener {
        /**
         * removes the element at the top of this queue.
         * 
         * @param theEvent incoming event
         */
        public void actionPerformed(final ActionEvent theEvent) {
            if (myQueue.isEmpty()) {
                myActionArea.append("Cannot dequeue from an empty queue.\n");
            } else {
                final String dequeue = myQueue.dequeue();
                myActionArea.append(QUOTE 
                                    + dequeue + QUOTE + " was dequeued off the queue.\n");
                if (myQueue.isEmpty()) {
                    myQueueArea.setText(EMPTY_MESSAGE);
                } else {
                    myQueueArea.setText(queueToString());
                }
                myQueueArea.setCaretPosition(0);
                myInputText.setText(null);
            }
            myInputText.grabFocus();
            
        }
    }
    
}
