
package Brandshop;

 import java.sql.DriverManager;
 import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class DatabaseConnection {
    
    private final String user = "root";
    private final String password = "";
    private final String uri = "jdbc:mysql://localhost/Brandshop";
    public static Connection connect_database;

    public  DatabaseConnection() {
         try {
              connect_database = DriverManager.getConnection(uri, user, password);
              System.out.println("You are connected to Brand shop Database\nWellcome to Brand Shop Market ");
             
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
    }
    
  
}
