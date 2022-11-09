
package Brandshop;

import java.sql.DriverManager;
 import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManagement extends DatabaseConnection{
    
    static Statement s;
  
    public void insertNewClient(String name,String email ,String password) 
    {
        try {
           
            s=connect_database.createStatement();
            String query ="INSERT INTO `clients` (`client_name`, `email`, `password`) VALUES ('" + name + "','" +email+ "','" + password +"');";
            s.execute(query);
            System.out.println("welcome to our store");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public boolean checkEmail(String email)
    { 
            boolean b =false;
            ResultSet result;
            String str="";
       try {
           
            s=connect_database.createStatement();
            result = s.executeQuery("SELECT * FROM `clients` WHERE email='"+ email +"'");
            result.next();
             str = result.getString(3);
            if(str.equals(email))
            {
                b=true;
                System.out.println("This email is already exist ");
            }
            
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    
       return b;
    }
    
    
    
     public boolean checkUser(String email,String password)
    { 
            boolean b =false;
            ResultSet result;
            String e="";
            String pass="";
       try {
           
            s=connect_database.createStatement();
            
            result = s.executeQuery("SELECT * FROM `clients` WHERE email='"+ email + "' and password='"+ password + "';");
            result.next();
            e = result.getString(3);
            pass=result.getString(4);
            if(e.equals(email) && pass.equals(password))
            {
                b=true;
                System.out.println("welcome to our store");
            }
            else
            { 
                System.out.println("please Re-enter your email and password");
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
        }
    
       return b;
    }
}
