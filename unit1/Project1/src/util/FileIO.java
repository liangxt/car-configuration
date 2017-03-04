package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.Automotive;

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
     */
    public Automotive buildAutoObject(String filename) {
        Automotive model = new Automotive();
        try {
            FileReader file = new FileReader(filename);
            @SuppressWarnings("resource")
            BufferedReader buff = new BufferedReader(file);
            String line1 = buff.readLine();
            if (line1 == null) {
                return null;
            }
            model.setName(line1);
            String line2 = buff.readLine();
            if (line2 == null) {
                return null;
            }
            if (!isNum(line2)) {
                System.out.println("Invalid Base Price!");
                return null;
            }
            float baseprice = Float.parseFloat(line2);
            model.setBasePrice(baseprice);
            String line3 = buff.readLine();
            if (line3 == null) {
                return null;
            }
            if (!isNum(line3)) {
                System.out.println("Invalid OptionSet Size!");
                return null;
            }
            int optionsetsize = Integer.parseInt(line3);
            model.setOptionSet(optionsetsize);
            String line4 = buff.readLine();
            if (line4 == null) {
                return null;
            }
            String[] optionsize = line4.split("\\,");
            for (int i = 0; i < optionsetsize; i++) {
                if (!isNum(optionsize[i])) {
                    System.out.println("Invalid Option Size!");
                    return null;
                }
                model.setOption(Integer.parseInt(optionsize[i].trim()), i);
            }
            String line5 = buff.readLine();
            if (line5 == null) {
                return null;
            }
            String[] optionsetname = line5.split("\\,");
            for (int i = 0; i < optionsetsize; i++) {
                model.setOptionSetName(optionsetname[i].trim(), i);
            }
            for (int i = 0; i < optionsetsize; i++) {
                String line = buff.readLine();
                if (line == null) {
                    return null;
                }
                String[] optionname = line.split("\\,");
                for (int j = 0; j < optionname.length; j++) {
                    model.setOptionName(optionname[j].trim(), i, j);
                }
            }
            for (int i = 0; i < optionsetsize; i++) {
                String line = buff.readLine();
                if (line == null) {
                    return null;
                }
                String[] optionprice = line.split("\\,");
                for (int j = 0; j < optionprice.length; j++) {
                    if (!isNum(optionprice[j].trim())) {
                        System.out.println("Invalid Option Price!");
                        return null;
                    }
                    model.setOptionPrice(Float.parseFloat(optionprice[j].trim()), i, j);
                }
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error--" + e.toString());
        }
        return model;
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
}
