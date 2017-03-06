package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for CreateDatabase.
 */
public class CreateDatabase implements MyDatabase {
    /**
     * Constructor.
     */
    public CreateDatabase() {
        CreateDB();
    }

    /**
     * Initialize database.
     */
    public void CreateDB() {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stat = conn.createStatement();
            FileReader file = new FileReader("src\\command.txt");
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            String command = "";
            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    break;
                }
                command = command + line;
                if (command.length() > 0 && command.charAt(command.length() - 1) == ';') {
                    stat.executeUpdate(command);
                    command = "";
                }
            }
            buff.close();
            stat.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
