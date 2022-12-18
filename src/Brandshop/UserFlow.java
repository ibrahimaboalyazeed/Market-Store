
package Brandshop;

import java.util.Scanner;


public class UserFlow {
        Scanner input = new Scanner(System.in);


    public UserFlow(Client c) 
    {
        do{
        System.out.println("Select 1 to view Our Products :\nSelest 2 to view Your Cart :\nSelect 3 to Add a product :\nSelect 4 to Delete a product :\nSelect 5 to Logout:");
        String x = input.next();
        if (x.equals("1"))
        DatabaseManagement.DB.showProducts();
        else if (x.equals("2"))
        DatabaseManagement.DB.showClientProducts(c);
        else if (x.equals("3"))
        DatabaseManagement.DB.addClientProduct(c);
        else if (x.equals("4"))
        DatabaseManagement.DB.deleteClientProduct(c);
        else if (x.equals("5"))
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
