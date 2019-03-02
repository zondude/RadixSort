/*
 * Winter TCSS 342
 * Assignment 2
 */

package applications;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import structures.LinkedOutputRestrictedDeque;
import structures.OutputRestrictedDequeADT;

/**
 * This class uses a radix sort to sort numbers in order.
 * @author Jonathan Kim
 * @version 29 January 2019
 */
public final class RadixSortDemo {


    /**
     * A static variable TEN to be used in the class.
     */
    private static final int TEN = 10;
    
    /**
     * Private constructor to inhibit.
     */
    private RadixSortDemo() {
        
    }
    /**
     * This method performs the radix sort.
     * @param theQueue the queue that parsed the text file.
     * @return returns a LinkedOutputRestrictedDeque<Integer>.
     */
    private static LinkedOutputRestrictedDeque<Integer>
        radixSort(final LinkedOutputRestrictedDeque<Integer> theQueue) {
        int maxDigit = 0;
        int mod = TEN;
        int div = 1;
        int current = 0;
        final int masterSize = theQueue.size();
        final LinkedOutputRestrictedDeque<Integer> master =
                        new LinkedOutputRestrictedDeque<>();
        final LinkedOutputRestrictedDeque<Integer> masterDupe = 
                        new LinkedOutputRestrictedDeque<>();
        for (int i = 0; i < masterSize; i++) {
            master.enqueue(theQueue.first());
            masterDupe.enqueue(theQueue.first());
            theQueue.dequeue();
        }

        @SuppressWarnings("unchecked")
        final OutputRestrictedDequeADT<Integer>[] bucket =
                        new LinkedOutputRestrictedDeque[TEN];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new LinkedOutputRestrictedDeque<>();
        }
        for (int i = 0; i < masterSize; i++) {
            final int digit = (int) Math.log10(masterDupe.first()) + 1;
            masterDupe.dequeue();
            if (digit > maxDigit) {
                maxDigit = digit;
            }
        }

        while (current < maxDigit) {
            for (int i = 0; i < masterSize; i++) {
                bucket[master.first() % mod / div].enqueue(master.first());
                master.dequeue();
            }
            for (int i = 0; i < bucket.length; i++) {
                while (!bucket[i].isEmpty()) {
                    master.enqueue(bucket[i].dequeue());
                }
            }

            current++;
            mod *= TEN;
            div *= TEN;
        }

        System.out.println(master.toString());
        return master;

    }
    
    /**
     * 
     * @param theFile the text file.
     * @return returns a LinkedOutputRestrictedDeque<Integer>.
     * @throws FileNotFoundException throws a file not found.
     */
    private static LinkedOutputRestrictedDeque<Integer>
        parseFile(final File theFile) throws FileNotFoundException {
        final Scanner fileScanner = new Scanner(theFile);
        final LinkedOutputRestrictedDeque<Integer> list = new LinkedOutputRestrictedDeque<>();
        while (fileScanner.hasNextLine()) {
            if (!fileScanner.hasNextInt()) {
                System.out.println("Invalid File: ");
            } else {
                list.enqueue(Integer.parseInt(fileScanner.nextLine()));
            }

        }
        fileScanner.close();
        return list;
    }

    /**
     * This method write to a file on sorted numbers.
     * @param theQueue the queue that is sorted by the radix sort.
     */
    private static void fileWrite(final LinkedOutputRestrictedDeque<Integer> theQueue) {
        final int masterSize = theQueue.size();
        try {
            final FileWriter fileWrite = new FileWriter("output.txt");
            for (int i = 0; i < masterSize; i++) {
                fileWrite.write(theQueue.dequeue() + "\n");
            }
            fileWrite.close();
        } catch (final IOException e) {
            System.out.println("Error");
        }
        
    }
    
    /**
     * Main to run the program.
     * @param theArgs the args.
     */
    public static void main(final String[] theArgs) {
        final Scanner userInput = new Scanner(System.in);
        boolean flag = true;
        String textFile = "";
        while (flag) {
            try {
                System.out.print("Enter the file name: ");
                textFile = userInput.nextLine();
                if (textFile.endsWith(".txt")) {
                    final File file = new File(textFile);
                    fileWrite(radixSort(parseFile(file)));
                    flag = false;
                    userInput.close();
                }
            } catch (final FileNotFoundException e) {
                System.out.println("Invalid file");
            }
        }

    }
}
