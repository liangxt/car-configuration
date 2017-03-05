package adapter;

import model.Automobile;
import scale.EditThread;
import server.AutoServer;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for BuildAuto.
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, EditThread, AutoServer {
    /**
     * Fix exception.
     * @param model
     *            Automobile object
     */
    @Override
    public void fix(Automobile model) {

    }

    @Override
    public void setupAuto() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addMap(Automobile mobile, String modelname) {
        // TODO Auto-generated method stub

    }

    @Override
    public void BuildAuto(String filename) {
        // TODO Auto-generated method stub

    }

}
