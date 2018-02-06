package exception;

import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for Helper.
 */
public class Helper {

    /**
     * Fix Base Price Exception.
     * @param auto
     *            Automobile object
     */
    public void fixPrice(Automobile auto) {
        float a = 18445;
        auto.setBasePrice(a);
        System.out.println("got here --> fixPrice");
        System.out.println("Fix Base Price Problem!");
    }
}
