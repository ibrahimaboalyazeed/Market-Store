
package Brandshop;

import java.util.Scanner;


public class AdminFlow {
    Scanner input = new Scanner(System.in);


    public AdminFlow(Client c) 
    {
        do{
        System.out.println("Select 1 to view Products :\nSelect 2 to Add a product :\nSelect 3 to update a Product :\nSelect 4 to Delete a product :\nSelect 5 to Logout:");
        String x = input.next();
        if (x.equals("1"))
        DatabaseManagement.DB.showProducts();
        if(x.equals("2"))
        DatabaseManagement.DB.addProduct();
        if(x.equals("3"))
        DatabaseManagement.DB.updateProduct();
        if(x.equals("4"))
        DatabaseManagement.DB.deleteProduct();
        if(x.equals("5"))
        {
            DatabaseManagement.DB.closeConnection();
            break;
        }
        else
        {
            System.out.println("Please Enter a Valid Number");
            System.out.println("***************************");
        }
        }while(true);
    }
    
}
