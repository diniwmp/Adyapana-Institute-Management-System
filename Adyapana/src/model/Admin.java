
package model;

public class Admin {

    private String id;
    private String admin_name;
    private String password;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the admin_name
     */
    public String getAdmin_name() {
        return admin_name;
    }

    /**
     * @param admin_name the admin_name to set
     */
    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
