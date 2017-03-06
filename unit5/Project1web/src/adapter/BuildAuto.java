package adapter;

import model.Automobile;
import scale.EditThread;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for BuildAuto.
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, EditThread {
    /**
     * Fix exception.
     * @param model
     *            Automobile object
     */
    @Override
    public void fix(Automobile model) {

    }

    public BuildAuto(String filename,String modelname) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setupAuto() {
        // TODO Auto-generated method stub

    }

}
