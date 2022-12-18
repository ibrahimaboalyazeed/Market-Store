package Brandshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DatabaseManagement {

    private final String user = "root";
    private final String password = "";
    private final String uri = "jdbc:mysql://localhost/Brandshop";
    private Connection connect_database;
    private Statement s;
    public static DatabaseManagement DB = new DatabaseManagement();

    public DatabaseManagement() 
        {
            try {
                connect_database = DriverManager.getConnection(uri, user, password);
                //System.out.println("You are connected to Brand shop Database\nWellcome to Brand Shop Market ");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    public void insertNewClient(String name, String email, String password) 
        {
            try {

                s = connect_database.createStatement();
                String query = "INSERT INTO `clients` (`name`, `email`, `password`) VALUES ('" + name + "','" + email + "','" + password + "');";
                s.execute(query);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    public boolean checkEmail(String email) 
        {
            boolean b = false;
            ResultSet result;
            try 
            {
                s = connect_database.createStatement();
                result = s.executeQuery("SELECT * FROM `clients` WHERE email='" + email + "'");
                result.next();
                String str = result.getString(3);
                if (str.equals(email)) {
                b = true;
                System.out.println("This email is already exist ");
                }

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }

            return b;
        }

    public Client checkUser(String email, String password)
        {
            ResultSet result;

            try {

                s = connect_database.createStatement();
                result = s.executeQuery("SELECT * FROM `clients` WHERE email='" + email + "' and password='" + password + "';");
                result.next();
                String db_pass = result.getString(4);
                String db_email = result.getString(3);
                String db_name = result.getString(2);
                boolean isAdmin = result.getBoolean(5);
                if (db_email.equals(email) && db_pass.equals(password)) 
                {   
                    return new Client(db_name, db_email, null, isAdmin);
                } else {
                    System.out.println("please Re-enter your email and password");
                }
                } 
            catch (SQLException ex) 
                {
                //System.out.println(ex.getMessage());
                }

            return null;
        }

    public String namequery(Client c) 
        {
            ResultSet result;
            String name = "";
            try {

                s = connect_database.createStatement();

                result = s.executeQuery("SELECT * FROM `clients` WHERE email='" + c.getEmail() + "' and password='" + c.getPassword() + "';");
                result.next();
                name = result.getString(2);

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }
            return name;
        }

    public void showProducts() 
        {
            ResultSet result;
            try {

                s = connect_database.createStatement();

                result = s.executeQuery("SELECT * FROM `products`");
                System.out.println("id     product     price     description");
                while (result.next()) {
                    System.out.print(result.getInt(1) + "      ");
                    System.out.print(result.getString(2) + "        ");
                    System.out.print(result.getString(3) + "         ");
                    System.out.print(result.getString(4));
                    System.out.println();
                }
                System.out.println("************************************");

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }
        }

   

  public void addProduct() 
        {
            ResultSet result;
            Scanner input = new Scanner(System.in);
            try {
    

                System.out.println("Enter the product name :");
                String name = input.next();
                 System.out.println("Enter the product price :");
                String price = input.next();
                 System.out.println("Enter the product description :");
                String description = input.next();
                s = connect_database.createStatement();
                String query ="INSERT INTO `products`(`name`, `price`,`description`) VALUES ('"+ name+ "','"+ price +"','"+ description +"');";
                s.execute(query);
                System.out.println("Product Added");
                System.out.println("******************************");
            } catch (SQLException ex) {
                System.out.println("the product is already exists");
            }
        }
  public void deleteProduct() 
        {
            ResultSet result;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the product name :");
            String name = input.next();
            try {     
                s = connect_database.createStatement();
                String query = "DELETE FROM `products` WHERE name='" + name + "';";
                s.execute(query);
                System.out.println("Product Deleted");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
  public void showClientProducts(Client c) 
        {
            ResultSet result;
            try {

                s = connect_database.createStatement();
                result = s.executeQuery("SELECT\n" +
                                        "    clients.id,clients.name,products.name AS 'order',products.price,products.description\n" +
                                        "FROM\n" +
                                        "    `clients`\n" +
                                        "INNER JOIN client_products ON clients.id = client_products.client_id\n" +
                                        "INNER JOIN products ON products.id = client_products.product_id\n" +
                                        "WHERE clients.email ='" +c.getEmail()+ "';");
               if(!result.next())
               {System.out.println("You Dont have any Products");
               }
               else{
                    System.out.println("id     name         product     price     description");
                do{
                    System.out.print(result.getInt(1) + "      ");
                    System.out.print(result.getString(2) + "      ");
                    System.out.print(result.getString(3) + "      ");
                    System.out.print(result.getString(4)+"      ");
                    System.out.print(result.getString(5));
                    System.out.println();
                }while (result.next());
               }
                System.out.println("********************************************");
                

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
 
   public void addClientProduct(Client c) 
        {
            
            ResultSet result;
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("Enter the product name :");
                String name = input.next();
                s = connect_database.createStatement();
                result=s.executeQuery("SELECT * FROM clients WHERE email= '"+c.getEmail()+"';");
                result.next();
                int c_id = result.getInt(1);
                result=s.executeQuery("SELECT * FROM products WHERE name= '"+name+"';");
                if(!result.next())
                {
                  System.out.println("The Product dosent exists");
                }
                int p_id = result.getInt(1);
                String query ="INSERT INTO `client_products`(`client_id`, `product_id`) VALUES ('"+c_id+ "','"+ p_id +"');";
                s.execute(query);
                System.out.println("Product Added");
                System.out.println("************************************");
            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }
        }
    public void deleteClientProduct(Client c) 
        {
            ResultSet result;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the product name :");
            String name = input.next();
            try {
                    s = connect_database.createStatement();
                    result=s.executeQuery("SELECT * FROM clients WHERE email= '"+c.getEmail()+"';");
                    result.next();
                    int c_id = result.getInt(1);
                    result=s.executeQuery("SELECT * FROM products WHERE name= '"+name+"';");
                    if(!result.next())
                    {
                      System.out.println("The Product dosent exists");
                    }
                    int p_id = result.getInt(1);
                    s = connect_database.createStatement();
                    String query = "DELETE FROM `client_products` WHERE client_id='" + c_id + "' AND product_id='"+p_id+"';";
                    s.execute(query);
                    System.out.println("Product Deleted");
                    System.out.println("***********************************");

                } catch (SQLException ex) {
                    //System.out.println("The Product is already exists");
                }
        }

   public void updateProduct() 
       {
            ResultSet result;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the product name :");
            String name = input.next();
            System.out.println("Enter the product new name :");
            String p_name = input.next();
             System.out.println("Enter the product new price :");
            String p_price = input.next();
             System.out.println("Enter the product new descriprion :");
            String p_description = input.next();
            try {     
                s = connect_database.createStatement();
                result=s.executeQuery("SELECT * FROM products WHERE name= '"+name+"';");
                 if(!result.next())
                    {
                      System.out.println("The Product dosent exists");
                    }
                int p_id = result.getInt(1);
                String query = "UPDATE `products` SET `id`='"+p_id+"',`name`='"+p_name+"',`price`='"+p_price+"',`description`='"+p_description+"' WHERE name='"+name+"';";
                s.execute(query);
                System.out.println("Product Updated");
                System.out.println("****************************");

            } catch (SQLException ex) {
                //System.out.println(ex.getMessage());
            }

       }

    public void closeConnection()
        {
            
             try
             {
               connect_database.close();
               s.close();
             }
             catch(SQLException ex)
             {
                 System.out.println(ex.getMessage());
             }

            
        }
}