package model;

import java.io.Serializable;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for OptionSet.
 */
public class OptionSet implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Array for Option.
     */
    private Option[] opt;
    /**
     * OptionSet Name.
     */
    private String name;

    /**
     * OptionSet Constructor.
     * @param n
     *            Name
     * @param size
     *            Array for Option size
     */
    protected OptionSet(String n, int size) {
        name = n;
        opt = new Option[size];
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
        if (index > opt.length - 1) {
            return null;
        }
        return opt[index];
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
     *            Length of the Option array
     */
    protected void setOption(int len) {
        opt = new Option[len];
        for (int i = 0; i < len; i++) {
            Option newopt = new Option();
            opt[i] = newopt;
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
        if (index > opt.length - 1) {
            return;
        }
        opt[index].setName(s);
    }

    /**
     * Set Option price.
     * @param p
     *            price
     * @param index
     *            index of Option
     */
    protected void setOptionPrice(float p, int index) {
        if (index > opt.length - 1) {
            return;
        }
        opt[index].setPrice(p);
    }

    /**
     * Find Option using name.
     * @param s
     *            name
     * @return Option
     */
    protected Option findOption(String s) {
        for (int i = 0; i < opt.length; i++) {
            if (opt[i] != null && opt[i].getName().equals(s)) {
                return opt[i];
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
            Option[] tmp = new Option[opt.length + 1];
            System.arraycopy(opt, 0, tmp, 0, opt.length);
            tmp[opt.length] = new Option();
            tmp[opt.length].setName(name);
            tmp[opt.length].setPrice(p);
            opt = tmp;
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
        if (index > opt.length - 1) {
            return;
        }
        Option[] newopt = new Option[opt.length - 1];
        System.arraycopy(opt, 0, newopt, 0, index);
        System.arraycopy(opt, index + 1, newopt, index, opt.length - index - 1);
        opt = newopt;
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
        for (int i = 0; i < opt.length; i++) {
            opt[i].print();
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
