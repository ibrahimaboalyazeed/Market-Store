package Brandshop;
import java.util.Scanner;

public  class Signin {

    Scanner input = new Scanner(System.in);

    public Client ProcessHome()
        {
            Client c = null;
            System.out.println("for sign up press 1 :\nfor Log in press 2 :");
            int x = input.nextInt();
            if (x == 1) {
                c=signIn();
            }
            if (x == 2) {
                c = logIn();
            }
            System.out.println("Welcome " + c.getName() + " to our store");
            System.out.println("************************************");
            return c;
        }

    public Client signIn() 
        {
                Client c = new Client();
            do {
                System.out.println("Enter Your name : ");
                c.setName(input.next());
                System.out.println("Enter your email : ");
                c.setEmail(input.next());
                System.out.println("Enter Your password : ");
                c.setPassword(input.next());
            } while (DatabaseManagement.DB.checkEmail(c.getEmail()));
            DatabaseManagement.DB.insertNewClient(c.getName(), c.getEmail(), c.getPassword());
            return c;

        }

    public Client logIn() 
        {

            while (true) {
                System.out.println("Enter Your email : ");
                String email = input.next();
                System.out.println("Enter Your password : ");
                String password = input.next();
                Client client = DatabaseManagement.DB.checkUser(email, password);
                if (client != null) 
                {
                    return client;
                }
            }
        }

}
