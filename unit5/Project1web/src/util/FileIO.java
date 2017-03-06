package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
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
 */
public class FileIO {
    public Automobile readProperties(String filename, String fileType) throws IOException {
        if (!fileType.toLowerCase().equals("properties")) {
            System.out.println("Format error!");
            throw new IOException();
        }
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(filename);
        props.load(in);
        return readProperties(props);
    }

    public Automobile readProperties(Properties props) throws IOException {

        Automobile auto = new Automobile();

        String CarMake = props.getProperty("CarMake");
        if (CarMake != null) {
            auto.setMake(CarMake);
            String CarModel = props.getProperty("CarModel");
            auto.setModel(CarModel);
            auto.setOptionSet(5);
            String Option1 = props.getProperty("Option1");
            auto.setOptionSetName(Option1, 0);
            auto.setOption(10, 0);
            String OptionValue1a = props.getProperty("OptionValue1a");
            auto.setOptionName(OptionValue1a, 0, 0);
            String OptionValue1b = props.getProperty("OptionValue1b");
            auto.setOptionName(OptionValue1b, 0, 1);
            String OptionValue1c = props.getProperty("OptionValue1c");
            auto.setOptionName(OptionValue1c, 0, 2);
            String OptionValue1d = props.getProperty("OptionValue1d");
            auto.setOptionName(OptionValue1d, 0, 3);
            String OptionValue1e = props.getProperty("OptionValue1e");
            auto.setOptionName(OptionValue1e, 0, 4);
            String OptionValue1f = props.getProperty("OptionValue1f");
            auto.setOptionName(OptionValue1f, 0, 5);
            String OptionValue1g = props.getProperty("OptionValue1g");
            auto.setOptionName(OptionValue1g, 0, 6);
            String OptionValue1h = props.getProperty("OptionValue1h");
            auto.setOptionName(OptionValue1h, 0, 7);
            String OptionValue1i = props.getProperty("OptionValue1i");
            auto.setOptionName(OptionValue1i, 0, 8);
            String OptionValue1j = props.getProperty("OptionValue1j");
            auto.setOptionName(OptionValue1j, 0, 9);
            String OptionPrice1a = props.getProperty("OptionPrice1a");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1a), 0, 0);
            String OptionPrice1b = props.getProperty("OptionPrice1b");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1b), 0, 1);
            String OptionPrice1c = props.getProperty("OptionPrice1c");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1c), 0, 2);
            String OptionPrice1d = props.getProperty("OptionPrice1d");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1d), 0, 3);
            String OptionPrice1e = props.getProperty("OptionPrice1e");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1e), 0, 4);
            String OptionPrice1f = props.getProperty("OptionPrice1f");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1f), 0, 5);
            String OptionPrice1g = props.getProperty("OptionPrice1g");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1g), 0, 6);
            String OptionPrice1h = props.getProperty("OptionPrice1h");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1h), 0, 7);
            String OptionPrice1i = props.getProperty("OptionPrice1i");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1i), 0, 8);
            String OptionPrice1j = props.getProperty("OptionPrice1j");
            auto.setOptionPrice(Integer.parseInt(OptionPrice1j), 0, 9);
            String Option2 = props.getProperty("Option2");
            auto.setOptionSetName(Option2, 1);
            auto.setOption(2, 1);
            String OptionValue2a = props.getProperty("OptionValue2a");
            auto.setOptionName(OptionValue2a, 1, 0);
            String OptionValue2b = props.getProperty("OptionValue2b");
            auto.setOptionName(OptionValue2b, 1, 1);
            String OptionPrice2a = props.getProperty("OptionPrice2a");
            auto.setOptionPrice(Integer.parseInt(OptionPrice2a), 1, 0);
            String OptionPrice2b = props.getProperty("OptionPrice2b");
            auto.setOptionPrice(Integer.parseInt(OptionPrice2b), 1, 1);
            String Option3 = props.getProperty("Option3");
            auto.setOptionSetName(Option3, 2);
            auto.setOption(3, 2);
            String OptionValue3a = props.getProperty("OptionValue3a");
            auto.setOptionName(OptionValue3a, 2, 0);
            String OptionValue3b = props.getProperty("OptionValue3b");
            auto.setOptionName(OptionValue3b, 2, 1);
            String OptionValue3c = props.getProperty("OptionValue3c");
            auto.setOptionName(OptionValue3c, 2, 2);
            String OptionPrice3a = props.getProperty("OptionPrice3a");
            auto.setOptionPrice(Integer.parseInt(OptionPrice3a), 2, 0);
            String OptionPrice3b = props.getProperty("OptionPrice3b");
            auto.setOptionPrice(Integer.parseInt(OptionPrice3b), 2, 1);
            String OptionPrice3c = props.getProperty("OptionPrice3c");
            auto.setOptionPrice(Integer.parseInt(OptionPrice3c), 2, 2);
            String Option4 = props.getProperty("Option4");
            auto.setOptionSetName(Option4, 3);
            auto.setOption(2, 3);
            String OptionValue4a = props.getProperty("OptionValue4a");
            auto.setOptionName(OptionValue4a, 3, 0);
            String OptionValue4b = props.getProperty("OptionValue4b");
            auto.setOptionName(OptionValue4b, 3, 1);
            String OptionPrice4a = props.getProperty("OptionPrice4a");
            auto.setOptionPrice(Integer.parseInt(OptionPrice4a), 3, 0);
            String OptionPrice4b = props.getProperty("OptionPrice4b");
            auto.setOptionPrice(Integer.parseInt(OptionPrice4b), 3, 1);
            String Option5 = props.getProperty("Option5");
            auto.setOptionSetName(Option5, 4);
            auto.setOption(2, 4);
            String OptionValue5a = props.getProperty("OptionValue5a");
            auto.setOptionName(OptionValue5a, 4, 0);
            String OptionValue5b = props.getProperty("OptionValue5b");
            auto.setOptionName(OptionValue5b, 4, 1);
            String OptionPrice5a = props.getProperty("OptionPrice5a");
            auto.setOptionPrice(Integer.parseInt(OptionPrice5a), 4, 0);
            String OptionPrice5b = props.getProperty("OptionPrice5b");
            auto.setOptionPrice(Integer.parseInt(OptionPrice5b), 4, 1);
            
            String basePrice = props.getProperty("BasePrice");
            auto.setBasePrice(Integer.parseInt(basePrice));
        }
        return auto;
    }

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
                auto.setModel(line1);
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
