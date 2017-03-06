package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import adapter.BuildAuto;
import database.CreateDatabase;
import database.DeleteDatabase;
import database.UpdateDatabase;
import model.Automobile;
/**
 * 18-641 Java Smart Phone Development.
 * Project 1
 */
/**
 * The class for Driver.
 */
public class Driver {
    /**
     * Driver for project 1.
     * @param args
     *            arguments
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false","root","1234");
        BuildAuto build=new BuildAuto();
        build.BuildAuto("src\\test1.txt","test1");
        build.BuildAuto("src\\test2.txt","test2");
        LinkedHashMap<String,Automobile> list=build.getMap();
        CreateDatabase cdb=new CreateDatabase();
        UpdateDatabase udb=new UpdateDatabase(list);  
        Statement stat = conn.createStatement();
        System.out.println("************Show automobile table************");
        showTable("test.automobile",stat);
        System.out.println("************Show optionset table************");
        showTable("test.optionset",stat);
        System.out.println("************Show optionchoice table************");
        showTable("test.optionchoice",stat);
        System.out.println("************Update make name in automobile table************");
        udb.updateAutoMake(1,"Nissan");
        showTable("test.automobile",stat);
        System.out.println("************Update model name in automobile table************");
        udb.updateAutoModel(1,"Sport");
        showTable("test.automobile",stat);
        System.out.println("************Update optionset name in optionset table************");
        udb.updateOptionSetName(1,"Color Choice");
        showTable("test.optionset",stat);
        System.out.println("************Update option name in optionchoice table************");
        udb.updateOptionName(23,"Light Green");
        showTable("test.optionchoice",stat);
        System.out.println("************Update option price in optionchoice table************");
        udb.updateOptionPrice(2,800);
        showTable("test.optionchoice",stat);
        System.out.println("************Delete Automobile in automobile table************");
        DeleteDatabase ddb=new DeleteDatabase();
        ddb.deleteAuto(2);
        showTable("test.automobile",stat);
        showTable("test.optionset",stat);
        showTable("test.optionchoice",stat);
        System.out.println("************Delete optionset in optionset table************");
        ddb.deleteOptionSet(1);
        showTable("test.automobile",stat);
        showTable("test.optionset",stat);
        showTable("test.optionchoice",stat);
        System.out.println("************Delete option in optionset table************");
        ddb.deleteOption(11);
        showTable("test.automobile",stat);
        showTable("test.optionset",stat);
        showTable("test.optionchoice",stat);
        System.out.println("************Delete All************");
        ddb.deleteAll();
        showTable("test.automobile",stat);
        showTable("test.optionset",stat);
        showTable("test.optionchoice",stat);        
    }
    /**
     * Show Table.
     * @throws SQLException 
     */
    public static void showTable(String tableName, Statement stat) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        ResultSet rs = stat.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                if (i > 1)
                    System.out.print(", ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        rs.close();
    }

}
