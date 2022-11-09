
package Brandshop;

import java.util.Scanner;
import java.sql.Connection;

public class Signin extends DatabaseManagement{
    
    Scanner input = new Scanner(System.in);
    
    public Signin() {
        
        System.out.println("for sign up press 1 :\nfor Log in press 2 :");
        int x = input.nextInt();
        if (x==1)
        signIn();
        if(x==2)
        logIn();
    }
    
    

    public void signIn() {
        String name,email,password;
       do{ 
        System.out.println("Enter Your name : ");
        name = input.next();
        System.out.println("Enter yoyr email : ");
        email = input.next();
        System.out.println("Enter Your password : ");
        password = input.next();
       }while(checkEmail(email));
        insertNewClient(name,email,password);
           
    }

    public void logIn() {
        String email,password;
       do{ 
      
        System.out.println("Enter yoyr email : ");
        email = input.next();
        System.out.println("Enter Your password : ");
        password = input.next();
       }
        while(!checkUser(email,password));
       
      
    }
    
    
 
    
}
