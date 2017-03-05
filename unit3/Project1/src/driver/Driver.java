package driver;

import adapter.BuildAuto;
import scale.EditOptions;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Driver.
 */
public class Driver {
    /**
     * Driver for project 1.
     * @param args
     *            arguments
     */
    public static void main(String[] args) {
        /**
         * I put synchronized methods in Automobile class and implement them 
         * in EditOption Class by using synchronized Automobile object.
         * 
         * When removing object locking usage, data will get corrupted and the
         * result is shown inside the test_output.txt.
         */
        // Change the same Automobile
        System.out.println("Change the same Automobile");
        BuildAuto auto = new BuildAuto();
        auto.BuildAuto("src/FordZTW1.txt", "model1");
        EditOptions e1 = new EditOptions(auto,"model1",1);
        EditOptions e2 = new EditOptions(auto,"model1",2);
        e1.start();
        e2.start();
        boolean e1IsAlive = true;
        boolean e2IsAlive = true;
        while (e1IsAlive || e2IsAlive) {
            if (e1IsAlive && !e1.isAlive()) {
                e1IsAlive = false;
            }

            if (e2IsAlive && !e2.isAlive()) {
                e2IsAlive = false;
            }
        }
        // Change different Automobile
        System.out.println("Change different Automobile");
        auto.BuildAuto("src/FordZTW2.txt", "model2");
        EditOptions t1 = new EditOptions(auto,"model1",1);
        EditOptions t2 = new EditOptions(auto,"model2",1);
        t1.start();
        t2.start();
        boolean t1IsAlive = true;
        boolean t2IsAlive = true;
        while (t1IsAlive || t2IsAlive) {
            if (t1IsAlive && !t1.isAlive()) {
                t1IsAlive = false;
            }

            if (t2IsAlive && !t2.isAlive()) {
                t2IsAlive = false;
            }
        }
    }
}
