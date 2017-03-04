package driver;

import adapter.BuildAuto;
import adapter.CreateAuto;

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
        System.out.println("Step 1 test:");
        CreateAuto Auto1 = new BuildAuto();
        Auto1.BuildAuto("src/FordZTW1.txt");
        Auto1.printAuto("");
        System.out.println("Cannot update OptionSetName or Option Price for the Auto instance");
        // Auto1.updateOptionSetName("FordZTW", "Color", "Power Moonroof");
        BuildAuto Auto2 = new BuildAuto();
        Auto2.BuildAuto("src\\FordZTW1.txt");
        Auto2.updateOptionPrice("", "Brakes/Traction Control", "ABS", 1000);
        System.out.println("************************Update Option Price*************************");
        Auto2.printAuto("");
        Auto2.updateOptionSetName("", "Color", "Color choice");
        System.out.println("********************Update OptionSet Name************************");
        Auto2.printAuto("");
        System.out.println("****************************************************");
        System.out.println("************************Fix AutoException***********************");
        System.out.println("Step 2 test 1:");
        CreateAuto Auto3 = new BuildAuto();
        Auto3.BuildAuto("src/FordZTW2.txt");
        Auto3.printAuto("");
        System.out.println("************************Other AutoException***********************");
        System.out.println("Step 2 test 2:");
        CreateAuto Auto4 = new BuildAuto();
        Auto4.BuildAuto("src/FordZTW3.txt");
        Auto4.printAuto("");
    }
}
