package driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automotive;
import model.OptionSet;
import util.FileIO;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Driver.
 */
public class Driver {
    /**
     * Test driver for project 1.
     * @param args
     *            arguments
     */
    public static void main(String[] args) {
        System.out.println("Test 1:");
        FileIO util1 = new FileIO();
        Automotive FordZTW1 = util1.buildAutoObject("src\\FordZTW1.txt");
        FordZTW1.print();
        System.out.println("The test of creating OptionSet and Option was done when the Automoyive object was created.");
        System.out.println("**************Delete OptionSet*************");
        FordZTW1.deleteOptionSet(0);
        FordZTW1.print();
        System.out.println("**************Delete Option*************");
        FordZTW1.deleteOption(1,0);
        FordZTW1.print();
        System.out.println("**************Update OptionSet*************");
        FordZTW1.setOptionSetName("Other", 0);
        FordZTW1.print();
        System.out.println("**************Update Option Name*************");
        FordZTW1.setOptionName("Other", 0, 0);
        FordZTW1.print();
        System.out.println("**************Update Option Price*************");
        FordZTW1.setOptionPrice(100, 0, 0);
        FordZTW1.print();        
   }
}
