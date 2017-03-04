package driver;

import java.io.IOException;

import adapter.BuildAuto;
import model.Automobile;
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
        try {
            FileIO file = new FileIO();
            Automobile Auto = file.buildAutoObject("src/FordZTW1.txt");
            Auto.setModel("Mustang");
            Auto.setMake("Toyota");
            Auto.setOptionChoice("Color", "Infra-Red Clearcoat");
            Auto.setOptionChoice("Transmission", "Manual");
            Auto.setOptionChoice("Brakes/Traction Control", "ABS");
            Auto.setOptionChoice("Side Impact Air Bags", "present");
            Auto.setOptionChoice("Power Moonroof", "present");
            System.out.println("***************Customer's Choice*****************");
            System.out.println("Model: " + Auto.getModel());
            System.out.println("Make: " + Auto.getMake());
            System.out.println("Base Price: $" + Auto.getBasePrice());
            System.out.print("Color: " + Auto.getOptionChoice("Color"));
            System.out.println("  Price: $" + Auto.getOptionChoicePrice("Color"));
            System.out.print("Transmission: " + Auto.getOptionChoice("Transmission"));
            System.out.println("  Price: $" + Auto.getOptionChoicePrice("Transmission"));
            System.out.print("Brakes/Traction Control: " + Auto.getOptionChoice("Brakes/Traction Control"));
            System.out.println("  Price: $" + Auto.getOptionChoicePrice("Brakes/Traction Control"));
            System.out.print("Side Impact Air Bags: " + Auto.getOptionChoice("Side Impact Air Bags"));
            System.out.println("  Price: $" + Auto.getOptionChoicePrice("Side Impact Air Bags"));
            System.out.print("Power Moonroof: " + Auto.getOptionChoice("Power Moonroof"));
            System.out.println("  Price: $" + Auto.getOptionChoicePrice("Power Moonroof"));
            float price = Auto.getTotalPrice();
            System.out.println("Total price: $" + price);
            System.out.println();
            System.out.println("***************Iterator*****************");
            BuildAuto auto = new BuildAuto();
            auto.BuildAuto("src/FordZTW1.txt", "model1");
            auto.BuildAuto("src/FordZTW2.txt", "model2");
            auto.BuildAuto("src/FordZTW3.txt", "model3");
            auto.iteratorPrint();
        } catch (IOException e) {
            System.out.println("Error!");
        }

    }
}
