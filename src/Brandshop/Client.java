
package Brandshop;


public class Client {
   
    private String name;
    private String email ;
    private String password;
    private boolean isAdmin;

    public Client() {
       
    }
    
    
    public Client(String name, String email, String password, boolean isAdmin) {
       
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }
    
    
}
