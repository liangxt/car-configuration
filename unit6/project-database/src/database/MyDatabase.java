package database;

/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The Interface for MyDatabase.
 * @author Xiaotong Liang (xiaotonl@andrew.cmu.edu)
 */
public interface MyDatabase {
    String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    String USER = "root";
    String PASSWORD = "1234";
    String DRIVER = "oracle.jdbc.driver.OracleDriver";
}
