package scale;

import adapter.BuildAuto;
import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Driver.
 */
public class EditOptions extends Thread implements EditThread {
    /**
     * Automobile object.
     */
    private Automobile mobile;
    /**
     * Thread index.
     */
    private int index;
    /**
     * Model's name.
     */
    private String modelname;
    /**
     * BuildAuto object.
     */
    private BuildAuto auto;

    /**
     * EditOptions constructor.
     * @param auto
     *            BuildAuto object
     * @param modelname
     *            Name of model
     * @param index
     *            Index of thread
     */
    public EditOptions(BuildAuto auto, String modelname, int index) {
        this.auto = auto;
        this.modelname = modelname;
        this.index = index;
    }

    /**
     * Set up the Automobile object.
     */
    public synchronized void setupAuto() {
        mobile = auto.getMap().get(modelname);
    }

    /**
     * Run thread.
     */
    public void run() {
        setupAuto();
        if (index == 1) {
            runFirstThread();
        } else {
            runSecondThread();
        }
    }

    /**
     * Set wait time.
     */
    public void setWait() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }

    /**
     * Run the first thread.
     */
    public void runFirstThread() {
        try {
            if (index == 2) {
                wait();
            }
            synchronized (mobile) {
                mobile.updateOptionSetName("Color", "Color choice");
                System.out.println(getName() + " set OptionSet Name: " + "Color choice");
                mobile.setOptionChoice("Color choice", "Infra-Red Clearcoat");
                System.out.print(getName() + " set Color choice: " + mobile.getOptionChoice("Color choice"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Color choice"));
                setWait();
                mobile.setOptionChoice("Side Impact Air Bags", "present");
                System.out.print(
                        getName() + " set Side Impact Air Bags: " + mobile.getOptionChoice("Side Impact Air Bags"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Side Impact Air Bags"));
                setWait();
                mobile.setOptionChoice("Power Moonroof", "present");
                System.out.print(getName() + " set Power Moonroof: " + mobile.getOptionChoice("Power Moonroof"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Power Moonroof"));
                setWait();
                mobile.updateOptionPrice(600, "Color choice", "Infra-Red Clearcoat");
                System.out.println(
                        getName() + " set Color choice Price: $" + mobile.getOptionChoicePrice("Color choice"));
                setWait();
                mobile.updateOptionPrice(200, "Side Impact Air Bags", "present");
                System.out.println(getName() + " set Side Impact Air Bags Price: $"
                        + mobile.getOptionChoicePrice("Side Impact Air Bags"));
                setWait();
                System.out.println(getName() + " delete Side Impact Air Bags Option choice: "
                        + mobile.getOptionChoice("Side Impact Air Bags"));
                mobile.deleteOptionChoice("Side Impact Air Bags");
                setWait();
                mobile.notify();
            }

        } catch (InterruptedException e) {
            System.out.println("Error!");
        }

    }

    /**
     * Run the second thread.
     */
    public void runSecondThread() {
        try {
            if (index == 1) {
                wait();
            }
            synchronized (mobile) {
                // Different thread change the same property
                mobile.updateOptionSetName("Color choice", "Color");
                System.out.println(getName() + " set OptionSet Name: " + "Color");
                mobile.setOptionChoice("Color", "Fort Knox Gold Clearcoat Mettallic");
                System.out.print(getName() + " set Color: " + mobile.getOptionChoice("Color"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Color"));
                setWait();
                // Different thread change different property
                mobile.setOptionChoice("Transmission", "Manual");
                System.out.print(getName() + " set Transmission: " + mobile.getOptionChoice("Transmission"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Transmission"));
                setWait();
                mobile.setOptionChoice("Brakes/Traction Control", "ABS");
                System.out.print(getName() + " set Brakes/Traction Control: "
                        + mobile.getOptionChoice("Brakes/Traction Control"));
                System.out.println(" Price: $" + mobile.getOptionChoicePrice("Brakes/Traction Control"));
                setWait();
                mobile.updateOptionPrice(600, "Color", "Fort Knox Gold Clearcoat Mettallic");
                System.out.println(getName() + " set Color Price: $" + mobile.getOptionChoicePrice("Color"));
                setWait();
                System.out.println(getName() + " delete Color choice: " + mobile.getOptionChoice("Color"));
                mobile.deleteOptionChoice("Color");
                setWait();
                mobile.notify();
            }

        } catch (InterruptedException e) {
            System.out.println("Error!");
        }
    }
}
