package Brandshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Brandshop {

    public static void main(String[] args) {
        Client c = new Client();
        Signin s = new Signin();
        c = s.ProcessHome();
        if (c.isIsAdmin()) 
        {
            AdminFlow admin = new AdminFlow(c);

        } else {
            UserFlow user = new UserFlow(c);

        }

    }

}


//SELECT * FROM `clients`
//INNER JOIN client_products on clients.client_id = client_products.client_id
//INNER JOIN products ON products.product_id = client_products.product_id
//where clients.client_id = 1;