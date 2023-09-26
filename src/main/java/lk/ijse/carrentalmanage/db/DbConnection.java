package lk.ijse.carrentalmanage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static DbConnection dbConnection;
    public static Connection con;

    public static String URL="jdbc:mysql://localhost:3306/carhire";
    public  static Properties props=new Properties();


    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    private DbConnection() throws SQLException {
        con= DriverManager.getConnection(URL,props);
    }

    public static DbConnection getInstance() throws SQLException {
        return(null==dbConnection)?dbConnection=new DbConnection(): dbConnection;
    }

    public static Connection getConnection() {
        return con;
    }



}
