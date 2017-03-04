package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import exception.AutoException;
import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for FileIO.
 * @author Xiaotong Liang (xiaotonl@andrew.cmu.edu)
 */
public class FileIO {
    /**
     * Read file and build Automotive object.
     * @param filename
     *            File's name
     * @return Automotive object
     * @throws IOException
     */
    public Automobile buildAutoObject(String filename) throws IOException {
        Automobile auto = new Automobile();
        Logger logger = Logger.getAnonymousLogger();
        try {
            if (filename == null || filename.length() == 0) {
                throw new AutoException(1);
            }
            FileReader file = new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            try {
                String line1 = buff.readLine();
                if (line1 == null || line1.length() == 0) {
                    buff.close();
                    throw new AutoException(2);
                }
                auto.setName(line1);
            } catch (AutoException e) {
                logger.log(Level.SEVERE, e.getErrormsg(), e);
            }
            try {
                String line2 = buff.readLine();
                if (line2.length() == 0 || !isNum(line2)) {
                    throw new AutoException(3);
                }
                float baseprice = Float.parseFloat(line2);
                auto.setBasePrice(baseprice);
            } catch (AutoException e) {
                logger.log(Level.SEVERE, e.getErrormsg(), e);
                e.fix(auto);
            }
            String line3 = buff.readLine();
            if (line3 == null || line3.length() == 0) {
                buff.close();
                return null;
            }
            if (!isNum(line3)) {
                System.out.println("Invalid OptionSet Size!");
                buff.close();
                return null;
            }
            int optionsetsize = Integer.parseInt(line3);
            auto.setOptionSet(optionsetsize);
            String line4 = buff.readLine();
            if (line4 == null || line4.length() == 0) {
                buff.close();
                return null;
            }
            String[] optionsize = line4.split("\\,");
            for (int i = 0; i < optionsetsize; i++) {
                if (!isNum(optionsize[i])) {
                    System.out.println("Invalid Option Size!");
                    buff.close();
                    return null;
                }
                auto.setOption(Integer.parseInt(optionsize[i].trim()), i);
            }
            try {
                String line5 = buff.readLine();
                if (line5 == null || line5.length() == 0) {
                    buff.close();
                    throw new AutoException(4);
                }
                String[] optionsetname = line5.split("\\,");
                for (int i = 0; i < optionsetsize; i++) {
                    auto.setOptionSetName(optionsetname[i].trim(), i);
                }
            } catch (AutoException e) {
                logger.log(Level.SEVERE, e.getErrormsg(), e);
            }

            try {
                for (int i = 0; i < optionsetsize; i++) {
                    String line = buff.readLine();
                    if (line == null || line.length() == 0) {
                        buff.close();
                        throw new AutoException(5);
                    }
                    String[] optionname = line.split("\\,");
                    for (int j = 0; j < optionname.length; j++) {
                        auto.setOptionName(optionname[j].trim(), i, j);
                    }
                }
            } catch (AutoException e) {
                logger.log(Level.SEVERE, e.getErrormsg(), e);
            }

            for (int i = 0; i < optionsetsize; i++) {
                String line = buff.readLine();
                if (line == null || line.length() == 0) {
                    buff.close();
                    return null;
                }
                String[] optionprice = line.split("\\,");
                for (int j = 0; j < optionprice.length; j++) {
                    if (!isNum(optionprice[j].trim())) {
                        System.out.println("Invalid Option Price!");
                        buff.close();
                        return null;
                    }
                    auto.setOptionPrice(Float.parseFloat(optionprice[j].trim()), i, j);
                }
            }
            buff.close();
        } catch (AutoException e) {
            logger.log(Level.SEVERE, e.getErrormsg(), e);
        }
        return auto;
    }

    /**
     * Check a certain string is number.
     * @param s
     *            String s
     * @return Boolean result
     */
    public boolean isNum(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            int tmp = s.charAt(i);
            if (tmp == 45 || tmp == 46) {
                continue;
            }
            if (tmp > 57 || tmp < 48) {
                return false;
            }
        }
        return true;
    }

    /**
     * Write and read serialized file.
     * @param obj
     *            Automobile object
     * @param filename
     *            File's name
     */
    public void writtenToDisk(Automobile obj, String filename) {
        try {
            @SuppressWarnings("resource")
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(obj);
            @SuppressWarnings("resource")
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Automobile Automodel = (Automobile) ois.readObject();
            Automodel.print();
        } catch (Exception e1) {
            System.out.println("Problem\n");
        }

    }
}
