package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for OptionSet.
 * @author Xiaotong Liang (xiaotonl@andrew.cmu.edu)
 */
public class OptionSet implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * ArrayList for Option.
     */
    private ArrayList<Option> opt = new ArrayList<Option>();
    /**
     * OptionSet Name.
     */
    private String name;
    /**
     * Customer's choice.
     */
    private Option choice=new Option();

    /**
     * Get customer's choice.
     * @return choice
     */
    protected Option getOptionChoice() {
        return choice;
    }

    /**
     * Set customer's choice.
     * @param name
     *            name of choice
     */
    protected void setOptionChoice(String name) {
        if (name == null) {
            return;
        }
        choice.setName(name);
    }

    /**
     * OptionSet Constructor.
     * @param n
     *            Name
     */
    protected OptionSet(String n) {
        name = n;
    }

    /**
     * OptionSet Constructor without parameters.
     */
    protected OptionSet() {
    }

    /**
     * Get OptionSet name.
     * @return name
     */
    protected String getName() {
        return name;
    }

    /**
     * Get Option by index.
     * @param index
     *            Option index
     * @return Option
     */
    protected Option getOption(int index) {
        if (index > opt.size() - 1) {
            return null;
        }
        return opt.get(index);
    }

    /**
     * Set OptionSet Name.
     * @param name
     *            name
     */
    protected void setOptionSetName(String name) {
        this.name = name;
    }

    /**
     * Set Option.
     * @param len
     *            Length of the Option array list
     */
    protected void setOption(int len) {
        for (int i = 0; i < len; i++) {
            Option newopt = new Option();
            opt.add(newopt);
        }
    }

    /**
     * Set Option name.
     * @param s
     *            name
     * @param index
     *            index of Option
     */
    protected void setOptionName(String s, int index) {
        if (index > opt.size() - 1) {
            return;
        }
        opt.get(index).setName(s);
    }

    /**
     * Set Option price.
     * @param p
     *            price
     * @param index
     *            index of Option
     */
    protected void setOptionPrice(float p, int index) {
        if (index > opt.size() - 1) {
            return;
        }
        opt.get(index).setPrice(p);
    }

    /**
     * Find Option using name.
     * @param s
     *            name
     * @return Option
     */
    protected Option findOption(String s) {
        for (int i = 0; i < opt.size(); i++) {
            if (opt.get(i) != null && opt.get(i).getName().equals(s)) {
                return opt.get(i);
            }
        }
        return null;
    }

    /**
     * Update Option.
     * @param s
     *            name
     * @param p
     *            price
     */
    protected void updateOption(String s, float p) {
        if (findOption(s) == null) {
            Option tmp = new Option();
            tmp.setName(s);
            tmp.setPrice(p);
            opt.add(tmp);
        }
    }

    /**
     * Update Option price.
     * @param p
     *            price
     * @param name
     *            name of Option
     */
    protected void updateOptionPrice(float p, String name) {
        Option tmp = findOption(name);
        if (tmp != null) {
            tmp.setPrice(p);
        }
    }

    /**
     * Delete Option.
     * @param index
     *            index of Option
     */
    protected void deleteOption(int index) {
        if (index > opt.size() - 1) {
            return;
        }
        opt.remove(index);
    }

    /**
     * Print OptionSet.
     */
    protected void print() {
        StringBuffer buff = new StringBuffer();
        if (opt == null) {
            System.out.println("Error!");
            return;
        }
        buff.append("OptionSet Name: " + name);
        System.out.println(buff);
        for (int i = 0; i < opt.size(); i++) {
            opt.get(i).print();
        }
    }

    /**
     * The inner class for Option.
     * @author Xiaotong Liang (xiaotonl@andrew.cmu.edu)
     */
    protected class Option implements Serializable {
        /**
         * serialVersionUID.
         */
        private static final long serialVersionUID = 1L;
        /**
         * Option name.
         */
        private String name;
        /**
         * Option price.
         */
        private float price;

        /**
         * Set Option name.
         * @param name
         *            name
         */
        protected void setName(String name) {
            this.name = name;
        }

        /**
         * Set Option price.
         * @param price
         *            price
         */
        protected void setPrice(float price) {
            this.price = price;
        }

        /**
         * Get Option name.
         * @return name
         */
        protected String getName() {
            return name;
        }

        /**
         * Get Option price.
         * @return price
         */
        protected float getPrice() {
            return price;
        }

        /**
         * Print Option.
         */
        protected void print() {
            StringBuffer buff = new StringBuffer();
            if (name == null) {
                System.out.println("Error!");
                return;
            }
            buff.append(name);
            buff.append("    Price: $" + price);
            System.out.println(buff);
        }
    }
}
