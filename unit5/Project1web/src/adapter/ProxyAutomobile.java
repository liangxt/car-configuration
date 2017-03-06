package adapter;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for proxyAutomobile.
 */
public abstract class ProxyAutomobile {
    /**
     * Automobile object.
     */
    private static Automobile a1;
    /**
     * LinkedHashMap for Automobile.
     */
    private LinkedHashMap<String, Automobile> map = new LinkedHashMap<String, Automobile>();

    /**
     * Update OptionSet Name.
     * @param Modelname
     *            model name
     * @param OptionSetname
     *            the name of OptionSet
     * @param newName
     *            new OptionSet name
     */
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName) {
        a1 = map.get(Modelname);
        if (a1 != null) {
            a1.updateOptionSetName(OptionSetname, newName);
        }
    }

    /**
     * Update Option Price.
     * @param Modelname
     *            model name
     * @param Optionname
     *            the name of Option
     * @param Option
     *            Option Choice
     * @param newprice
     *            new price
     */
    public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice) {
        a1 = map.get(Modelname);
        a1.updateOptionPrice(newprice, Optionname, Option);
    }

    /**
     * Build Automobile and handle exception.
     * @param filename
     *            File's name
     * @param modelname
     *            Model's name
     */
    public void BuildAuto(String filename, String modelname) {
        Logger logger = Logger.getAnonymousLogger();
        try {
            FileIO file = new FileIO();
            a1 = file.buildAutoObject(filename);
            if (a1 == null) {
                throw new AutoException();
            }
            map.put(modelname, a1);
        } catch (AutoException e) {
            logger.log(Level.SEVERE, e.getErrormsg(), e);
        } catch (IOException e) {
            System.out.println("Error!");
        }

    }

    /**
     * Print Automobile.
     * @param Modelname
     *            model name
     */
    public void printAuto(String Modelname) {
        a1 = map.get(Modelname);
        a1.print();
    }
    /**
     * Print elements in map.
     */
    public void iteratorPrint() {
        Iterator<Automobile> itr = map.values().iterator();
        while (itr.hasNext()) {
            Automobile tmp = itr.next();
            tmp.print();
            System.out.println("**********************");
        }
    }
    public LinkedHashMap<String, Automobile> getMap(){
        return map;
    }
}
