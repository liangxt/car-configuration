package adapter;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The interface for UpdateAuto.
 * @author Xiaotong Liang (xiaotonl@andrew.cmu.edu)
 */
public interface UpdateAuto {
    /**
     * Update OptionSet Name.
     * @param Modelname
     *            model name
     * @param OptionSetname
     *            the name of OptionSet
     * @param newName
     *            new OptionSet name
     */
    public void updateOptionSetName(String Modelname, String OptionSetname, String newName);

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
    public void updateOptionPrice(String Modelname, String Optionname, String Option, float newprice);
}
