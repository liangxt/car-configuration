package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.OptionSet.Option;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Automotive.
 */
public class Automobile implements Serializable {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Make.
     */
    private String make;
    /**
     * Automotive model.
     */
    private String model;
    /**
     * ArrayList for OptionSet.
     */
    private ArrayList<OptionSet> opset = new ArrayList<OptionSet>();
    /**
     * ArrayList for Choice.
     */
    private ArrayList<Option> choice = new ArrayList<Option>();
    /**
     * Base price.
     */
    private float baseprice;

    /**
     * Automotive constructor without parameters.
     */
    public Automobile() {

    }

    /**
     * Get OptionSet Name.
     */
    public ArrayList<String> getOptionSetName() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < opset.size(); i++) {
            if (opset.get(i) != null) {
                list.add(opset.get(i).getName());
            }
        }
        return list;
    }

    /**
     * Get Option Name.
     */
    public ArrayList<String> getOptionName(String setname) {
        OptionSet s = findOptionSet(setname);
        return s.getOption();
    }

    /**
     * Set Model.
     * @param model
     *            model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get the Model of automotive.
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set Make.
     * @param make
     *            make
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Get the make of automotive.
     * @return make
     */
    public String getMake() {
        return make;
    }

    /**
     * Get the base price of automotive.
     * @return base price
     */
    public float getBasePrice() {
        return baseprice;
    }

    /**
     * Get customer's choice.
     * @return choice
     */
    public synchronized String getOptionChoice(String setName) {
        OptionSet tmp = findOptionSet(setName);
        if (tmp != null) {
            return tmp.getOptionChoice().getName();
        }
        return null;
    }

    /**
     * Get customer's choice price.
     * @return choice
     */
    public synchronized float getOptionChoicePrice(String setName) {
        OptionSet tmp = findOptionSet(setName);
        String tmps = getOptionChoice(setName);
        if (tmp != null && tmp.findOption(tmps) != null) {
            return tmp.findOption(tmps).getPrice();
        }
        return -1;
    }

    /**
     * Get OptionSet.
     * @param index
     *            Index of OptionSet array list
     * @return OptionSet
     */
    public OptionSet getOptionSet(int index) {
        if (index > opset.size() - 1) {
            return null;
        }
        return opset.get(index);
    }

    /**
     * Get OptionSet.
     * @param index
     *            Index of OptionSet array list
     * @return OptionSet
     */
    public float getPriceByIndexAndKey(int index, String key) {

        OptionSet colorOptionSet = getOptionSet(index);
        if (colorOptionSet != null && colorOptionSet.getOptionList() != null) {
            for (Option option : colorOptionSet.getOptionList()) {
                if (key.equals(option.getName())) {
                    return option.getPrice();
                }
            }
        }

        return -1;

    }

    /**
     * Set customer's choice price.
     * @param setName
     *            the name of OptionSet
     * @param optionname
     *            the name of option
     */
    public synchronized void setOptionChoice(String setName, String optionname) {
        if (setName == null || optionname == null) {
            return;
        }
        OptionSet tmp = findOptionSet(setName);
        if (tmp != null) {
            tmp.setOptionChoice(optionname);
            choice.add(tmp.getOptionChoice());
        }
    }

    /**
     * Get option's total price.
     * @return total price
     */
    public float getTotalPrice() {
        float price = baseprice;
        for (int i = 0; i < opset.size(); i++) {
            if (opset.get(i) != null) {
                price = price + getOptionChoicePrice(opset.get(i).getName());
            }
        }
        return price;
    }

    /**
     * Find OptionSet.
     * @param s
     *            Name of OptionSet
     * @return OptionSet
     */
    public OptionSet findOptionSet(String s) {
        if (s == null) {
            return null;
        }
        for (int i = 0; i < opset.size(); i++) {
            if (opset.get(i) != null && (opset.get(i).getName().equals(s))) {
                return opset.get(i);
            }
        }
        return null;
    }

    /**
     * Find Option.
     * @param s
     *            Name of Option
     * @param index
     *            Index of OptionSet array list
     * @return Option
     */
    public Option findOption(String s, int index) {
        if (s == null) {
            return null;
        }
        if (index > opset.size() - 1) {
            return null;
        }
        OptionSet tmp = opset.get(index);
        return tmp.findOption(s);
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
     *            Length of the OptionSet array list
     */
    public void setOptionSet(int len) {
        for (int i = 0; i < len; i++) {
            OptionSet ops = new OptionSet();
            opset.add(ops);
        }
    }

    /**
     * Set Option.
     * @param len
     *            Length of the Option array list
     * @param index
     *            Index of the OptionSet
     */
    public void setOption(int len, int index) {
        if (index > opset.size() - 1) {
            return;
        }
        opset.get(index).setOption(len);
    }

    /**
     * Set name of the OptionSet.
     * @param s
     *            name
     * @param index
     *            Index of the OptionSet
     */
    public void setOptionSetName(String s, int index) {
        if (index > opset.size() - 1) {
            return;
        }
        opset.get(index).setOptionSetName(s);
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
        if (index1 > opset.size() - 1) {
            return;
        }
        opset.get(index1).setOptionName(s, index2);
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
        if (index1 > opset.size() - 1) {
            return;
        }
        opset.get(index1).setOptionPrice(p, index2);
    }

    /**
     * Delete Option.
     * @param setname
     *            Name of the OptionSet
     * @param option
     *            Name of the Option
     */
    public void deleteOption(String setname, String option) {
        OptionSet tmp = findOptionSet(setname);
        tmp.deleteOption(option);
    }

    /**
     * Delete Option choice.
     * @param setname
     *            Name of the OptionSet
     */
    public synchronized void deleteOptionChoice(String setname) {
        setOptionChoice(setname, "");
    }

    /**
     * Delete Option.
     * @param setname
     *            Name of the OptionSet
     */
    public void deleteOptionSet(String setname) {
        OptionSet tmp = findOptionSet(setname);
        if (tmp == null) {
            return;
        }
        opset.remove(setname);
    }

    /**
     * Update OptionSet.
     * @param s
     *            Name of the OptionSet
     */
    public void updateOptionSet(String s) {
        if (findOptionSet(s) == null) {
            OptionSet tmp = new OptionSet();
            tmp.setOptionSetName(s);
            opset.add(tmp);
        }
    }

    /**
     * Update name of the OptionSet.
     * @param s
     *            name of OptionSet
     * @param n
     *            new name of the OptionSet
     */
    public synchronized void updateOptionSetName(String s, String n) {
        OptionSet tmp = findOptionSet(s);
        if (tmp != null) {
            tmp.setOptionSetName(n);
        } else {
            OptionSet newops = new OptionSet();
            newops.setOptionSetName(n);
        }
    }

    /**
     * Set name of the Option.
     * @param p
     *            Price
     * @param name1
     *            Name of the OptionSet
     * @param name2
     *            Name of the Option
     */
    public synchronized void updateOptionPrice(float p, String name1, String name2) {
        OptionSet tmp = findOptionSet(name1);
        if (tmp != null) {
            tmp.updateOptionPrice(p, name2);
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
        buff.append("Make: " + make + "\n");
        buff.append("Model: " + model + "\n");
        buff.append("Base Price: $" + baseprice + "\n");
        System.out.println(buff);
        for (int i = 0; i < opset.size(); i++) {
            opset.get(i).print();
            System.out.println();
        }
    }

}
