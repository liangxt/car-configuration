package adapter;

import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for BuildAuto.
 */
public class BuildAuto extends proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto {
    /**
     * Fix exception.
     * @param model
     *            Automobile object
     */
    @Override
    public void fix(Automobile model) {

    }

}
