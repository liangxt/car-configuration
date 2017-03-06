package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.Automobile;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for UpdateDatabase.
 */
public class UpdateDatabase implements MyDatabase {
    /**
     * Constructor.
     */
    public UpdateDatabase(LinkedHashMap<String, Automobile> list) {
        addAuto(list.get("test1"));
        addAuto(list.get("test2"));
    }

    /**
     * Add table.
     */
    public void addAuto(Automobile auto) {
        String make = auto.getMake();
        String model = auto.getModel();
        float price = auto.getBasePrice();
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String command1 = "INSERT INTO automobile (make,model,price) " + "VALUES(" + "\'" + make + "\'," + "\'"
                    + model + "\'," + "\'" + price + "\')";
            Statement stat = conn.createStatement();
            stat.executeUpdate(command1, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stat.getGeneratedKeys();
            int AutoID = 0;
            if (rs.next()) {
                AutoID = rs.getInt(1);
            } else {
                throw new SQLException();
            }
            ArrayList<String> ops = auto.getOptionSetName();
            for (int i = 0; i < ops.size(); i++) {
                if (ops.get(i) != null) {
                    String name = ops.get(i);
                    String command2 = "INSERT INTO optionset (name,AUTOID) " + "VALUES(" + "\'" + name + "\'," + AutoID
                            + ")";
                    stat.executeUpdate(command2, Statement.RETURN_GENERATED_KEYS);
                    ResultSet rss = stat.getGeneratedKeys();
                    int opsID = 0;
                    if (rss.next()) {
                        opsID = rss.getInt(1);
                    } else {
                        throw new SQLException();
                    }
                    ArrayList<String> op = auto.getOptionName(name);
                    PreparedStatement preparedStatement = null;
                    for (int j = 0; j < op.size(); j++) {
                        if (op.get(j) != null) {
                            String opname = op.get(j);
                            float opprice = auto.getPrice(name, opname);
                            String insertTableSQL = "INSERT INTO optionchoice" + "(name, opprice, OPSID) VALUES"
                                    + "(?,?,?)";
                            preparedStatement = conn.prepareStatement(insertTableSQL);
                            preparedStatement.setString(1, opname);
                            preparedStatement.setString(2, Float.toString(opprice));
                            preparedStatement.setInt(3, opsID);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        }
                    }
                }
            }
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update Automobile make.
     */
    public void updateAutoMake(int ID, String makename) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "UPDATE automobile SET make=" + "\'" + makename + "\'" + "WHERE AUTOID=" + ID;
            stat.executeUpdate(command);
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update Automobile model.
     */
    public void updateAutoModel(int ID, String modelname) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "UPDATE automobile SET model=" + "\'" + modelname + "\'" + "WHERE AUTOID=" + ID;
            stat.executeUpdate(command);
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update optionset name.
     */
    public void updateOptionSetName(int ID, String ops) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "UPDATE optionset SET name=" + "\'" + ops + "\'" + "WHERE OPSID=" + ID;
            stat.executeUpdate(command);
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update option name.
     */
    public void updateOptionName(int ID, String op) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "UPDATE optionchoice SET name=" + "\'" + op + "\'" + " WHERE OPID=" + ID;
            stat.executeUpdate(command);
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update option price.
     */
    public void updateOptionPrice(int ID, float price) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "UPDATE optionchoice SET opprice=" + "\'" + price + "\'" + " WHERE OPID=" + ID;
            stat.executeUpdate(command);
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
