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
        Automotive FordZTW1 = util1.buildAutoObject("src/FordZTW1.txt");
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
    //        if (FordZTW1 == null) {
//            System.out.println("Error!");
//        } else {
//            FordZTW1.print();
//            System.out.println("**************Written object to disk*************");
//            try {
//                @SuppressWarnings("resource")
//                ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("src/util/FordZTW1.ser"));
//                oos1.writeObject(FordZTW1);
//                @SuppressWarnings("resource")
//                ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("src/util/FordZTW1.ser"));
//                Automotive Automodel1 = (Automotive) ois1.readObject();
//                Automodel1.print();
//            } catch (Exception e1) {
//                System.out.println("Problem\n");
//            }
//        }
//
//        System.out.println("Test 2:");
//        FileIO util2 = new FileIO();
//        Automotive FordZTW2 = util2.buildAutoObject("src/FordZTW2.txt");
//        if (FordZTW2 == null) {
//            System.out.println("Error!");
//        } else {
//            FordZTW2.print();
//            System.out.println("**************Written object to disk*************");
//            try {
//                @SuppressWarnings("resource")
//                ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("src/util/FordZTW2.ser"));
//                oos2.writeObject(FordZTW2);
//                @SuppressWarnings("resource")
//                ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream("src/util/FordZTW2.ser"));
//                Automotive Automodel2 = (Automotive) ois2.readObject();
//                Automodel2.print();
//            } catch (Exception e1) {
//                System.out.println("Problem\n");
//            }
//        }
//
//        System.out.println("Test 3:");
//        FileIO util3 = new FileIO();
//        Automotive FordZTW3 = util3.buildAutoObject("src/FordZTW3.txt");
//        if (FordZTW3 == null) {
//            System.out.println("Error!");
//        } else {
//            FordZTW3.print();
//            System.out.println("**************Written object to disk*************");
//            try {
//                @SuppressWarnings("resource")
//                ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("src/util/FordZTW3.ser"));
//                oos3.writeObject(FordZTW2);
//                @SuppressWarnings("resource")
//                ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream("src/util/FordZTW3.ser"));
//                Automotive Automodel3 = (Automotive) ois3.readObject();
//                Automodel3.print();
//            } catch (Exception e1) {
//                System.out.println("Problem\n");
//            }
//        }
//        
//        
//        
}
