package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for DeleteDatabase.
 */
public class DeleteDatabase implements MyDatabase {
    /**
     * Constructor.
     */
    public DeleteDatabase() {
    }

    /**
     * Delete all elements.
     */
    public void deleteAll() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "DELETE FROM automobile";
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete automobile.
     */
    public void deleteAuto(int ID) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "DELETE FROM automobile WHERE AUTOID=" + ID;
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete optionset.
     */
    public void deleteOptionSet(int ID) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "DELETE FROM optionset WHERE OPSID=" + ID;
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete option.
     */
    public void deleteOption(int ID) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            String command = "DELETE FROM optionchoice WHERE OPID=" + ID;
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
