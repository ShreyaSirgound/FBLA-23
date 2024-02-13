import java.util.ArrayList;
import java.util.List;

public class Admin {
    /** 
     * The administrator's name
     */
    private String name;

    /**
     * The administrator's email
     */
    private String email;

    /**
     * The administrator's password
     */
    
    private String password;

    /**
     * Creates a new admin object
    */
    public Admin(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    //creates a list of all the administrators
    protected static List<Admin> adminList = new ArrayList<Admin>();

    //add an administrator to the admins list
    public static void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    /**
     * Unregisters an existing administrator account. Does nothing if the account does not exist.
     *
     * @param admin The administrator account to remove
     */
    public static void removeAdmin(Admin admin) {
        adminList.remove(admin);
    }
    
    /**
     * Gets a list of all active admin accounts.
     *
     * @return A list of administrators
     */
    public static List<Admin> getAdmins() {
        return adminList;
    }
}
