public class Credentials {
    String password;
    byte[] salt;

    public Credentials(String password, byte[] salt) {  
        this.password = password;
        this.salt = salt;
    }

    public String getPassword(){
        return password;
    }
    
    public byte[] getSalt() {
        return salt;
    }
}
