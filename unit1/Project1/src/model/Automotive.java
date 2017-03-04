package model;

import java.io.Serializable;

import model.OptionSet.Option;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Automotive.
 */
public class Automotive implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Automotive name.
     */
    private String name;
    /**
     * Array for OptionSet.
     */
    private OptionSet[] opset;
    /**
     * Base price.
     */
    private float baseprice;

    /**
     * Automotive constructor.
     * @param name
     *            Automotive name
     * @param baseprice
     *            Base price
     * @param OptionSetsize
     *            Size of OptionSet array
     */
    public Automotive(String name, float baseprice, int OptionSetsize) {
        this.baseprice = baseprice;
        Model(OptionSetsize, name);
    }
    public OptionSet[] getOptionSet(){
        return opset;
    }

    /**
     * Automotive constructor without parameters.
     */
    public Automotive() {

    }

    /**
     * Model.
     * @param size
     *            Size of OptionSet array
     * @param n
     *            name
     */
    public void Model(int size, String n) {
        opset = new OptionSet[size];
        name = n;
    }

    /**
     * Get the name of automotive.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the base price of automotive.
     * @return base price
     */
    public float getBasePrice() {
        return baseprice;
    }

    /**
     * Get OptionSet.
     * @param index
     *            Index of OptionSet array
     * @return OptionSet
     */
    public OptionSet getOptionSet(int index) {
        if (index > opset.length - 1) {
            return null;
        }
        return opset[index];
    }

    /**
     * Find OptionSet.
     * @param s
     *            Name of OptionSet
     * @return OptionSet
     */
    public OptionSet findOptionSet(String s) {
        for (int i = 0; i < opset.length; i++) {
            if (opset[i] != null && (opset[i].getName().equals(s))) {
                return opset[i];
            }
        }
        return null;
    }

    /**
     * Find Option.
     * @param s
     *            Name of Option
     * @param index
     *            Index of OptionSet array
     * @return Option
     */
    public Option findOption(String s, int index) {
        if (index > opset.length - 1) {
            return null;
        }
        OptionSet tmp = opset[index];
        return tmp.findOption(s);
    }

    /**
     * Set name of the automotive.
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set base price of the automotive.
     * @param baseprice
     *            Base price
     */
    public void setBasePrice(float baseprice) {
        this.baseprice = baseprice;
    }

    /**
     * Set OptionSet.
     * @param len
     *            Length of the OptionSet array
     */
    public void setOptionSet(int len) {
        opset = new OptionSet[len];
        for (int i = 0; i < len; i++) {
            OptionSet ops = new OptionSet();
            opset[i] = ops;
        }
    }

    /**
     * Set Option.
     * @param len
     *            Length of the Option array
     * @param index
     *            Index of the OptionSet
     */
    public void setOption(int len, int index) {
        if (index > opset.length - 1) {
            return;
        }
        opset[index].setOption(len);
    }

    /**
     * Set name of the OptionSet.
     * @param s
     *            name
     * @param index
     *            Index of the OptionSet
     */
    public void setOptionSetName(String s, int index) {
        if (index > opset.length - 1) {
            return;
        }
        opset[index].setOptionSetName(s);
    }

    /**
     * Set name of the Option.
     * @param s
     *            name
     * @param index1
     *            Index of the OptionSet
     * @param index2
     *            Index of the Option
     */
    public void setOptionName(String s, int index1, int index2) {
        if (index1 > opset.length - 1) {
            return;
        }
        opset[index1].setOptionName(s, index2);
    }

    /**
     * Set name of the Option.
     * @param p
     *            Price
     * @param index1
     *            Index of the OptionSet
     * @param index2
     *            Index of the Option
     */
    public void setOptionPrice(float p, int index1, int index2) {
        if (index1 > opset.length - 1) {
            return;
        }
        opset[index1].setOptionPrice(p, index2);
    }

    /**
     * Delete Option.
     * @param index1
     *            Index of the OptionSet
     * @param index2
     *            Index of the Option
     */
    public void deleteOption(int index1, int index2) {
        if (index1 > opset.length - 1) {
            return;
        }
        opset[index1].deleteOption(index2);
    }

    /**
     * Delete Option.
     * @param index
     *            Index of the OptionSet
     */
    public void deleteOptionSet(int index) {
        if (index > opset.length - 1) {
            return;
        }
        OptionSet[] newops = new OptionSet[opset.length - 1];
        System.arraycopy(opset, 0, newops, 0, index);
        System.arraycopy(opset, index + 1, newops, index, opset.length - index - 1);
        opset = newops;
    }

    /**
     * Update OptionSet.
     * @param s
     *            Name of the OptionSet
     */
    public void updateOptionSet(String s) {
        if (findOptionSet(s) == null) {
            OptionSet[] tmp = new OptionSet[opset.length + 1];
            System.arraycopy(opset, 0, tmp, 0, opset.length);
            tmp[opset.length] = new OptionSet();
            tmp[opset.length].setOptionSetName(s);
            opset = tmp;
        }
    }

    /**
     * Update Option.
     * @param s
     *            Name of the Option
     * @param index
     *            Index of the OptionSet
     * @param p
     *            Price of the Option
     */
    public void updateOption(String s, int index, float p) {
        if (findOption(s, index) == null) {
            OptionSet tmp = opset[index];
            tmp.updateOption(s, p);
        }
    }

    /**
     * Print automotive.
     */
    public void print() {
        StringBuffer buff = new StringBuffer();
        if (opset == null) {
            System.out.println("Error!");
            return;
        }
        buff.append("Name: " + name + "\n");
        buff.append("Base Price: $" + baseprice + "\n");
        System.out.println(buff);
        for (int i = 0; i < opset.length; i++) {
            opset[i].print();
            System.out.println();
        }
    }

}
