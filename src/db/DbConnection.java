package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection=null;
    private Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection= DriverManager.getConnection(
                "jdbc:mysql://localhost/WholesaleDB",
                "root",
                null
        );
    }

    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        return (dbConnection==null) ? (dbConnection=new DbConnection()) : (dbConnection) ;
    }

    public Connection getConnection(){
        return connection;
    }
}
