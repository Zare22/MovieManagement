package hr.algebra.model;

/**
 *
 * @author Leo
 */
public class User {
    
    private int id;
    private final String username;
    private final String password;
    private final boolean isAdmin;

    public User(int id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }
}
